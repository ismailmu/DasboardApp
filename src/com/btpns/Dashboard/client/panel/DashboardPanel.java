package com.btpns.Dashboard.client.panel;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.ContentPanel;

public abstract class DashboardPanel extends ContentPanel implements IsWidget {

	protected ImageResource icon;
	protected String title;
	
	public DashboardPanel(String title, ImageResource icon) {
		this.title = title;
		this.icon = icon;
	}

	@Override
	public Widget asWidget() {
		
		setHeadingText(title);
		getHeader().setIcon(icon);
		
		//contentPanel.setHeight(575);
		
		return this;
	}
}
