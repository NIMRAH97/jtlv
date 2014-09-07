/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package edu.wis.jtlv.env.ui.editors.fds;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;


/**
 * An implementation of <code>IRule</code> detecting a numerical value.
 */
public class FDSValueRule implements IRule {

	/** Internal setting for the un-initialized column constraint */
	protected static final int UNDEFINED= -1;
	/** The token to be returned when this rule is successful */
	protected IToken fToken;

	/**
	 * Creates a rule which will return the specified token when a numerical
	 * sequence is detected.
	 * 
	 * @param token
	 *            the token to be returned
	 */
	public FDSValueRule(IToken token) {
		Assert.isNotNull(token);
		fToken= token;
	}

	/*
	 * @see IRule#evaluate(ICharacterScanner)
	 */
	public IToken evaluate(ICharacterScanner scanner) {
		int c= scanner.read();
		if ((char)c == '0' | (char)c == '1') {
				do {
					c= scanner.read();
				} while ((char)c == '0' | (char)c == '1');
				scanner.unread();
				return fToken;
		}

		scanner.unread();
		return Token.UNDEFINED;
//		//scanner.unread();
//		int c= scanner.read();
//		//if (!Character.isJavaIdentifierPart((char)c)) {
//			//c= scanner.read();
//			if (c == '0' | c == '1') { // chars 0 and 1
//				scanner.unread();
//				return fToken;
//			}
//		//}
//
//		scanner.unread();
//		return Token.UNDEFINED;
	}
}
