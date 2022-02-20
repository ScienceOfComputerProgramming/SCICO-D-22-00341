/*****************************************************************************
 * Copyright (c) 2022 CEA LIST.
 *
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *  Ansgar Radermacher - Initial API and implementation
 *
 *****************************************************************************/

package org.eclipse.papyrus.gamification.perspective;

import org.eclipse.papyrus.gamification.view.ViewManager;
import org.eclipse.papyrus.uml.perspective.PapyrusPerspective;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * PapyGame perspective
 */
public class PapyGamePerspective extends PapyrusPerspective implements IPerspectiveFactory {

	/**
	 * define layout
	 */
	@Override
	public void defineLayout(IPageLayout layout) {
		super.defineLayout(layout);
		layout.addView(ViewManager.GAMIFICATION_VIEW, IPageLayout.BOTTOM, 0, IPageLayout.ID_PROP_SHEET);
	}
}
