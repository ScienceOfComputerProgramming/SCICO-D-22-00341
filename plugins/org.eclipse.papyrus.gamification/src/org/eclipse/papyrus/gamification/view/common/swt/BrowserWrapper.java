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

import org.eclipse.papyrus.gamification.data.Logger;
import org.eclipse.papyrus.gamification.view.common.OsCheck;
import org.eclipse.papyrus.gamification.view.common.OsCheck.OSType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.browser.ProgressListener;
import org.eclipse.swt.widgets.Composite;

/**
 * @author maxim
 *         A browser wrapper
 */
public class BrowserWrapper {

	protected Browser browser;

	/**
	 * Constructor.
	 *
	 */
	public BrowserWrapper(Composite parent) {
		boolean isWindows = OsCheck.getOperatingSystemType().equals(OSType.Windows);
		browser = new Browser(parent, isWindows ? SWT.EDGE : SWT.NONE);

		Logger.getInstance().logDebug(getClass(), "Browser", "Created browser for " + OsCheck.getOperatingSystemType());
	}

	public void setBounds(int x, int y, int width, int height) {
		browser.setBounds(x, y, width, height);
	}

	public void setFocus() {
		browser.setFocus();
	}

	public void addProgressListener(ProgressListener progressListener) {
		browser.addProgressListener(progressListener);
	}

	public Object evaluate(String script) {
		return browser.evaluate(script);
	}

	public Object execute(String script) {
		return browser.execute(script);
	}

	public void setText(String content) {
		browser.setText(content);
	}

	public String getText() {
		return browser.getText();
	}

	/**
	 * @return the current browser
	 */
	public Browser getBrowser() {
		return browser;
	}

	// might be useful for clean closing, but it currently unused.
	public void close() {
		browser.dispose();
		browser = null;
	}
}
