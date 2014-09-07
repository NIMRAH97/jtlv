import edu.wis.jtlv.env.Env;
import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDFactory;

public class Patch1_2_0_copyToManyFactories {
	public static BDD[] multiCopyBDD(int[] managerIdxs, BDD toCopy) {
		// retrieving all factories.
		BDDFactory[] factories = new BDDFactory[managerIdxs.length];
		for (int i = 0; i < factories.length; i++)
			// getting a simple BDD in that manager, and extracting its factory.
			factories[i] = Env.copyBDDIntoManager(managerIdxs[i], Env.TRUE())
					.getFactory();

		return multiCopyBDDHelper(factories, toCopy);
	}

	private static BDD[] multiCopyBDDHelper(BDDFactory[] factories, BDD that) {
		// ///////////////////////////////////
		// the actual copy.
		BDD[] res = new BDD[factories.length];
		if (that.isUniverse()) {
			for (int i = 0; i < res.length; i++)
				res[i] = factories[i].one();
			return res;
		}
		if (that.isZero()) {
			for (int i = 0; i < res.length; i++)
				res[i] = factories[i].zero();
			return res;
		}

		BDD that_l = that.low();
		BDD that_h = that.high();
		if (that_l.equals(that_h)) {
			res = multiCopyBDDHelper(factories, that_l);
		} else {
			BDD[] l = multiCopyBDDHelper(factories, that_l);
			BDD[] h = multiCopyBDDHelper(factories, that_h);
			for (int i = 0; i < res.length; i++)
				res[i] = factories[i].ithVar(that.var()).ite(h[i], l[i]);
		}
		
		return res;
	}
}
