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

package org.eclipse.papyrus.gamification.view.dashboard;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.eclipse.papyrus.gamification.Preferences;
import org.eclipse.papyrus.gamification.data.Encryptor;
import org.eclipse.papyrus.gamification.data.Logger;
import org.eclipse.papyrus.gamification.data.di.RepositoryFactory;
import org.eclipse.papyrus.gamification.data.entity.Level;
import org.eclipse.papyrus.gamification.data.entity.LevelPerformed;
import org.eclipse.papyrus.gamification.data.entity.PlayerProfile;
import org.eclipse.papyrus.gamification.data.entity.SeriesPerformed;
import org.eclipse.papyrus.gamification.games.framework.LevelExecutor;
import org.eclipse.papyrus.gamification.games.framework.entity.LevelContext;
import org.eclipse.papyrus.gamification.view.common.DisplayableView;
import org.eclipse.papyrus.gamification.view.common.swt.Browser;
import org.eclipse.papyrus.gamification.view.dashboard.JSLevelClicked.LevelClickedInterface;
import org.eclipse.swt.widgets.Display;

import com.google.gson.Gson;

/**
 * @author maximesavaryleblanc
 *
 */
public class DashboardView extends DisplayableView implements DashboardContract.View, LevelClickedInterface {

	private DashboardPresenter dashboardPresenter;
	private String login;
	private PlayerProfile currentPlayerProfile;

	public DashboardView(String login) {
		this.login = login;
	}

	@Override
	public void registerJavaScriptFunctions(Browser browser) {
		super.registerJavaScriptFunctions(browser);
		new JSLevelClicked(browser, this);
	}

	@Override
	public void displayPlayerProfile(PlayerProfile playerProfile) {
		this.currentPlayerProfile = playerProfile;

		System.out.println("DISPLAYPROFILE : " + playerProfile.toString());
		String playerProfileJSON = (new Gson()).toJson(playerProfile);
		System.out.println("displayPlayerProfile(" + playerProfileJSON + ");");
		callJSScript("displayPlayerProfile(" + playerProfileJSON + ");");

	}

	@Override
	public String getHtmlPath() {
		return "/html/newDashboard.html";
	}

	@Override
	public void start() {
		super.start();
		this.dashboardPresenter = new DashboardPresenter();
		this.dashboardPresenter.registerView(this);
	}

	@Override
	public void onLevelClicked(String seriesLabel, String levelLabel) {
		// TODO : improve selection ?
		System.out.println("onLevelClicked(" + seriesLabel + "," + levelLabel + ")");
		Level levelToRun = null;

		// TODO DELETE
		// levelLabel = "GREEN BELT";
		for (SeriesPerformed seriesPerformed : currentPlayerProfile.getSeriesPerformed()) {
			System.out.println("Series Performed name : " + seriesPerformed.getName());
			if (seriesPerformed.getName().equals(seriesLabel)) {
				for (LevelPerformed level : seriesPerformed.getLevelsPerformed()) {
					System.out.println("level name : " + level.getLabel());
					if (level.getLabel().equals(levelLabel)) {
						LevelContext levelContext = new LevelContext(level, currentPlayerProfile, seriesPerformed);
						// LevelContext levelContext = null;
						Display.getDefault().asyncExec(new Runnable() {

							@Override
							public void run() {
								try {
									LevelExecutor.getInstance().start(levelContext);
								} catch (Exception e) {
									e.printStackTrace();
									Logger.getInstance().logError(this.getClass(), e, "onLevelClicked");
								}
							}
						});
						// ViewManager.getInstance().displayView(new LevelView(levelContext));
						return;
					}
				}
			}
		}
	}


	@Override
	public void showVideo() {
		initiateVideo(Preferences.INTRO_VIDEO_URL);
	}

	@Override
	public void skipVideo() {
		callJSScript("showMainContainer()");
	}



	@Override
	public void clearJavascriptFunctions(Browser browser) {
	}

	@Override
	public void onHtmlPageLoaded(Browser browser) {
		System.out.println("page loaded on dashboard");

		this.dashboardPresenter.getPlayerProfile(login);
		// this.dashboardPresenter.getVideoIntroductionState(login);
		skipVideo();
	}

	@Override
	public void onOpenLink(String url) {
		if ("questionnaire".equals(url)) {
			Encryptor encryptor = RepositoryFactory.getEncryptor();
			try {
				openLinkInExternalBrowser(Preferences.PAPY_QUESTIONNAIRE_URL + "?key=" +
						URLEncoder.encode(
								encryptor.encrypt(currentPlayerProfile.getPlayerId()),
								StandardCharsets.UTF_8.toString()));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not encode the URL key parameter");
				e.printStackTrace();
			}
		}
		if ("prize".equals(url)) {
			Encryptor encryptor = RepositoryFactory.getEncryptor();
			try {
				openLinkInExternalBrowser(Preferences.PAPY_PRIZE_URL + "?key=" +
						URLEncoder.encode(
								encryptor.encrypt(currentPlayerProfile.getPlayerId()),
								StandardCharsets.UTF_8.toString()));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not encode the URL key parameter");
				e.printStackTrace();
			}
		} else {
			openLinkInExternalBrowser(url);
		}
	}
}
