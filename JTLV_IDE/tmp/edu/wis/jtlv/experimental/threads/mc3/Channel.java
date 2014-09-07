package edu.wis.jtlv.experimental.threads.mc3;

import java.util.concurrent.ConcurrentLinkedQueue;

import edu.wis.jtlv.env.Env;
import net.sf.javabdd.BDD;
import edu.wis.jtlv.env.JTLVThread;

public class Channel {
	private int manIdx;
	private ConcurrentLinkedQueue<Object> queue;
	
	public Channel(JTLVThread thread) {
		this.manIdx = Env.assignDedicatedBDDManagerIdx(thread);
		queue = new ConcurrentLinkedQueue<Object>();
	}
	
	public BDD read() {
		return (BDD)queue.poll();
	}
	
	public void writeBDD(BDD to_write) {
		queue.add(Env.copyBDDIntoManager(manIdx, to_write));
	}

}
