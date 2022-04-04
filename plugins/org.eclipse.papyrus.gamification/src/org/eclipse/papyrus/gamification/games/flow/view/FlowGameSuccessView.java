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

package org.eclipse.papyrus.gamification.games.flow.view;

import org.eclipse.papyrus.gamification.data.entity.GameScore;
import org.eclipse.papyrus.gamification.games.framework.communication.OnResumeToDashboardItf;
import org.eclipse.papyrus.gamification.games.framework.entity.LevelContext;
import org.eclipse.papyrus.gamification.view.common.swt.BrowserWrapper;
import org.eclipse.papyrus.gamification.view.game.GameFinishedView;

/**
 * @author maximesavaryleblanc
 *
 */
public class FlowGameSuccessView extends GameFinishedView {



	public FlowGameSuccessView(LevelContext levelContext, OnResumeToDashboardItf onResumeToDashboardItf, GameScore gameScore) {
		super(levelContext, onResumeToDashboardItf, gameScore);
	}

	@Override
	public void registerJavaScriptFunctions(BrowserWrapper browser) {
		super.registerJavaScriptFunctions(browser);
	}

	@Override
	public String getHtmlPath() {
		return "/html/gamesuccess.html"; //$NON-NLS-1$
	}

	@Override
	public void onHtmlPageLoaded(BrowserWrapper browser) {
		super.onHtmlPageLoaded(browser);

		// TODO set content
	}

}
