package org.eclipse.papyrus.gamification.view.dashboard;

import org.eclipse.papyrus.gamification.view.common.swt.BrowserWrapper;
import org.eclipse.papyrus.gamification.view.common.swt.JavascriptFunction;

public class JSLevelClicked extends JavascriptFunction {

	BrowserWrapper browser;
	LevelClickedInterface levelClickedItf;

	public JSLevelClicked(BrowserWrapper browser, LevelClickedInterface levelClickedItf) {
		super(browser, "levelClicked");
		this.browser = browser;
		this.levelClickedItf = levelClickedItf;
	}

	@Override
	public Object functionBody(Object[] args) {

		try {
			// new TestClass().run();
			String seriesLabel = String.class.cast(args[0]);
			String levelLabel = String.class.cast(args[1]);
			levelClickedItf.onLevelClicked(seriesLabel, levelLabel);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return "";
	}

	interface LevelClickedInterface {
		void onLevelClicked(String seriesLabel, String levelLabel);
	}

}