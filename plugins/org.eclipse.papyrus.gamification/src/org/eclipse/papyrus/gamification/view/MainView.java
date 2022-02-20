package org.eclipse.papyrus.gamification.view;

import org.eclipse.papyrus.gamification.view.common.swt.BrowserWrapper;
import org.eclipse.papyrus.gamification.view.login.LoginView;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class MainView extends ViewPart {

	Label label;
	BrowserWrapper browser;
	private ViewManager viewManager;

	@Override
	public void createPartControl(Composite parent) {

		// System.out.println("Working with Java : " + System.getProperty("java.runtime.version"));
		// System.out.println("OS DETECTED IS : " + OsCheck.getOperatingSystemType());

		browser = new BrowserWrapper(parent);
		browser.setBounds(0, 0, 1000, 800);

		this.viewManager = new ViewManager(browser);

		// Init with login view
		viewManager.displayView(new LoginView());
	}

	@Override
	public void setFocus() {
		browser.setFocus();
	}
}
