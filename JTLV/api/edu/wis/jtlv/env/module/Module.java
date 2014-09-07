/**
 * 
 */
package edu.wis.jtlv.env.module;

import net.sf.javabdd.BDD;
import net.sf.javabdd.BDDException;
import edu.wis.jtlv.env.JTLVIllegalVariableException;

/**
 * @author yaniv
 * 
 */
public interface Module {

	/**
	 * @param new_vars
	 */
	//public abstract void add_var_list(String[] new_vars) throws ModuleException;

	/**
	 * @param new_var
	 * @throws JTLVIllegalVariableException 
	 */
	public abstract void addVar(String new_var) throws ModuleException, JTLVIllegalVariableException;

	//public abstract JTLVBDDCouple get_var_look_hard(String var) throws ModuleException;

	public abstract void add_initial(BDD to_add) throws ModuleException;

	public abstract void conjunctTrans(BDD to_add) throws ModuleException;

	public abstract void disjunctTrans(BDD to_add) throws ModuleException;

	// /////////////////////////
	// getters
	public abstract BDD initial();

	public abstract BDD trans();

	/**
	 * @param set
	 * @return
	 * @throws BDDException
	 */
	public abstract BDD pred(BDD to) throws BDDException;

	/**
	 * @param set
	 * @return
	 * @throws BDDException
	 */
	public abstract BDD succ(BDD from) throws BDDException;

	public abstract String toString();
	
	public abstract String getFullInstName();
	
	public abstract String getPath();

	public abstract String getName();
}