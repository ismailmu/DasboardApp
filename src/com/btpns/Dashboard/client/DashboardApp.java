package com.btpns.Dashboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.GWT.UncaughtExceptionHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class DashboardApp implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	private DashboardShell2 shell;

	public DashboardApp() {
		shell = new DashboardShell2();
	}

	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			public void onUncaughtException(Throwable e) {
				Info.display("Dashboard error",
						"Unexpected Error, contact administrator");
				e.printStackTrace();
			}
		});
		
		RootPanel.get().add(shell);
//		Viewport viewport = new Viewport();
//		viewport.add(shell);
//		Window.enableScrolling(false);
//		viewport.setPagePosition(0, 0);
//		viewport.setPixelSize(Window.getClientWidth(), Window.getClientHeight());
//		RootPanel.get().add(viewport);
	}
}
