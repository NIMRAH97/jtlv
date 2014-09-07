package edu.wis.jtlv.bench.mtmc;

import net.sf.javabdd.BDD;




public abstract class AbstractAlgorithm {
	protected Configuration conf;

	public AbstractAlgorithm(Configuration conf) {
		this.conf = conf;
	}

	public abstract BDD performAlg();
}
