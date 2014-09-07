package edu.wis.jtlv.lib.games;
import java.io.IOException;


public class IllegalGameException extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public IllegalGameException(String str) {
		super(str);
	}
}
