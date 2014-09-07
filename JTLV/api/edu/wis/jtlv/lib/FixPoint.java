/**
 * 
 */
package edu.wis.jtlv.lib;



/**
 * @author yaniv
 *
 */
public class FixPoint<T> {

	private T previous;

	public FixPoint() {
		previous = null;
	}

	public boolean advance(T last) {
		boolean res = (previous == null)? true : (!last.equals(previous));
		previous = last;
		return res;
	}
}
