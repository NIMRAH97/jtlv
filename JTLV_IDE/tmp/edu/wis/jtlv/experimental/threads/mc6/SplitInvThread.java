package edu.wis.jtlv.experimental.threads.mc6;

import edu.wis.jtlv.env.JTLVThread;
import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDVarSet;
import edu.wis.jtlv.env.Env;
import edu.wis.jtlv.env.module.SMVModule;
import java.util.concurrent.Semaphore;

public class SplitInvThread extends JTLVThread {
	public static boolean delta_theta_pref_imp = true;

	protected boolean verbose;
	// the id of this module
	protected int id;
	// total number of threads
	protected int N;
	// protected main module
	protected SMVModule main;
	// this module of smv file
	protected SMVModule module;
	// vars shared between processes
	protected BDDVarSet sharedVars;
	// all vars known to this module
	protected BDDVarSet vars;
	// local vars of this module
	protected BDDVarSet localVars;
	// prime version of local vars
	protected BDDVarSet primeLocalVars;
	// both prime and unprime local vars
	protected BDDVarSet primeUnprimeLocalVars;
	// the original transition relation of this module
	protected BDD trans;
	// the transition relation of this module disjuncted
	// with transitions obtained from other threads
	protected BDD extTrans;
	// the idle transition of this module
	protected BDD idleTrans;
	// initial condition for this module, only over local variables!!!
	protected BDD initCond;
	protected BDD oldTheta;
	protected BDD newTheta;
	protected BDD deltaTheta;
	// output channels for broadcasting to other threads
	public Channel[] outputChannels;
	// a flag indicating whether the thread should continue running
	protected boolean alive;
	// local True and False BDDs
	protected BDD True;
	protected BDD False;
	// the other threads
	protected SplitInvThread[] otherThreads;
	// vars for deciding termination:
	// the current marker index of this module
	protected int myMarkerIndex;
	// markers obtained from other modules
	protected int[] markersFromOthers;
	// indicates if other modules replied to the latest
	// marker that this module sent
	protected boolean[] repliedToMyLatestMarker;

	// semaphores that are used only for a sanity check:
	// that all states found are correct.
	Semaphore semThread;
	Semaphore semMain;

	public SplitInvThread(int _id, int _N, SMVModule _main, boolean _verbose) {
		False = adjustBDDToManager(Env.FALSE());
		True = adjustBDDToManager(Env.TRUE());
		main = _main;
		id = _id;
		N = _N;
		verbose = _verbose;
		markersFromOthers = new int[N];
		repliedToMyLatestMarker = new boolean[N];
		outputChannels = new Channel[N];
		for (int i = 0; i < N; i++) {
			if (i != id) {
				outputChannels[i] = new Channel(this);
			}
		}
		for (int i = 0; i < N; i++) {
			markersFromOthers[i] = -1;
			repliedToMyLatestMarker[i] = false;
		}
		myMarkerIndex = 0;
	}

	// computes the transition relation from newTheta
	// to newTheta, using trans, existenitial quantifying
	// the local vars (i.e. only over the shared vars)
	protected BDD computeGlobalTrans() {
		return (trans.and(newTheta).and(Env.prime(newTheta)))
				.exist(primeUnprimeLocalVars);
	}

	// sets the array of other threads
	public void setOtherThreads(SplitInvThread[] _otherThreads) {
		otherThreads = _otherThreads;
	}

	// gets newTheta adjusted to base
	public BDD getNewTheta() {
		return adjustBDDToBase(newTheta);
	}

	// kills this thread
	protected synchronized void killThread() {
		alive = false;
	}

	// kills all the threads, including this
	protected void killAll() {
		// System.out.println("module " + id + " kills all");
		for (int i = 0; i < N; i++) { // including self
			try {
				otherThreads[i].killThread();
			} catch (Exception e) {
				System.out.println("module " + id + " could not kill " + i);
			}
		}
	}

	// computing one step successor
	protected BDD newSucc(BDD from, BDD trans, BDDVarSet unprimeVars) {
		BDD prime_to = from.and(trans).exist(unprimeVars);
		return Env.unprime(prime_to);
	}

	// computing all successors
	protected BDD newAllSucc(BDD from, BDD trans, BDDVarSet unprimeVars) {
		BDD oldSucc = from;
		BDD newSucc = from;
		do {
			oldSucc = newSucc;
			newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars));
		} while (!oldSucc.equals(newSucc));
		return newSucc;
	}

	// computing the transition relation a conjunct by conjunct
	protected BDD assignTransToDedicatedManager(SMVModule m) {
		BDD res = True;
		for (BDD toConj : m.getAllConjunctTransElements()) {
			res = res.and(adjustBDDToManager(toConj));
		}
		return res;
	}

	// just for the sanity check
	public void setSemaphores(Semaphore _semMain, Semaphore _semThread) {
		semMain = _semMain;
		semThread = _semThread;
	}

	protected BDD newAllSuccNotIncl(BDD from, BDD trans, BDDVarSet unprimeVars) {
		return newSuccNotInclWithLoopCond(-1, from, trans, unprimeVars);
	}

	protected BDD newSuccNotInclWithLoopCond(int max_iter, BDD from, BDD trans,
			BDDVarSet unprimeVars) {
		// /////////////////////////
		// int counter = 0;
		// BDD oldSucc = from;
		// BDD newSucc = from;
		//
		// do {
		// oldSucc = newSucc;
		// newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars)).and(
		// from.not());
		// counter++;
		// } while ((!oldSucc.equals(newSucc)) & (counter != max_iter));
		// printTimedMes("Counter " + counter, 0);
		// return newSucc;
		// /////////////////////////
		int counter = 0;
		BDD deltaSucc = newSucc(from, trans, unprimeVars).and(from.not());
		BDD newSucc = deltaSucc;
		BDD oldSucc = deltaSucc;

		do {
			oldSucc = newSucc;
			deltaSucc = newSucc(deltaSucc, trans, unprimeVars).and(
					oldSucc.not()).and(from.not());
			newSucc = oldSucc.or(deltaSucc);
			counter++;
		} while ((!oldSucc.equals(newSucc)) & (counter != max_iter));
		printTimedMes("Counter " + counter, 0);
		return newSucc;
		// /////////////////////////
		// int counter = 0;
		// trans = trans.and(Env.prime(from).not());
		// BDD oldSucc, newSucc = newSucc(from, trans, unprimeVars);
		//		
		// do {
		// oldSucc = newSucc;
		// newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars));
		// counter++;
		// } while ((!oldSucc.equals(newSucc)) & (counter != max_iter));
		// printTimedMes("Counter " + counter, 0);
		// return newSucc;
		// /////////////////////////
		// BDD oldSucc = from;
		// BDD newSucc = from;
		// trans = trans.and(Env.prime(from).not());
		//		
		// boolean first_time = true;
		// do {
		// oldSucc = newSucc;
		// newSucc = newSucc(oldSucc, trans, unprimeVars);
		// if (first_time) { // only newSucc
		// first_time = false;
		// } else { // otherwise, include the previous
		// newSucc = oldSucc.or(newSucc);
		// }
		// } while (!oldSucc.equals(newSucc));
		// return newSucc;
		// /////////////////////////
	}

	protected BDD newSuccNotInclWithTimeCond(long max_time, BDD from,
			BDD trans, BDDVarSet unprimeVars) {
		long start = System.currentTimeMillis();
		long time_from_start = start;
		BDD oldSucc = from;
		BDD newSucc = from;
		do {
			oldSucc = newSucc;
			newSucc = oldSucc.or(newSucc(oldSucc, trans, unprimeVars)).and(
					from.not());
			time_from_start = System.currentTimeMillis() - start;
		} while ((!oldSucc.equals(newSucc)) & (time_from_start < max_time));
		return newSucc;
	}

	protected static void printTimedMes(String header, long rel) {
		System.out.println(header + ": " + (System.currentTimeMillis() - rel));
	}
}
