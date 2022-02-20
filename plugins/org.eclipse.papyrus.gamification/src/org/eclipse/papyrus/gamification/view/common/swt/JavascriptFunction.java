/*****************************************************************************
 * Copyright (c) 2021 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *   CEA LIST - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.gamification.view.common.swt;

/**
 * @author maxim
 *
 */
public abstract class JavascriptFunction {

	Vanilla fct;
	
	public JavascriptFunction(BrowserWrapper browser, String functionName) {
		fct = new JavascriptFunction.Vanilla(browser.getBrowser(), functionName);
	}

	public abstract Object functionBody(Object[] args);

	private class Vanilla extends org.eclipse.swt.browser.BrowserFunction {

		public Vanilla(org.eclipse.swt.browser.Browser browser, String name) {
			super(browser, name);
		}

		@Override
		public Object function(Object[] arguments) {
			return functionBody(arguments);
		}

	}
}
