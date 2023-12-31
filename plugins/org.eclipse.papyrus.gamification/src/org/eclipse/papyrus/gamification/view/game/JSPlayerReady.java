/*****************************************************************************
 * Copyright (c) 2020 CEA LIST and others.
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

package org.eclipse.papyrus.gamification.view.game;

import org.eclipse.papyrus.gamification.games.framework.communication.OnPlayerReadyItf;
import org.eclipse.papyrus.gamification.view.common.swt.BrowserWrapper;
import org.eclipse.papyrus.gamification.view.common.swt.JavascriptFunction;


public class JSPlayerReady extends JavascriptFunction {

	BrowserWrapper browser;
	OnPlayerReadyItf playerReadyItf;

	public JSPlayerReady(BrowserWrapper browser, OnPlayerReadyItf playerReadyItf) {
		super(browser, "playerReady");
		this.browser = browser;
		this.playerReadyItf = playerReadyItf;
	}

	@Override
	public Object functionBody(Object[] args) {
		try {
			playerReadyItf.onPlayerReady();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

}
