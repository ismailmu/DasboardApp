package com.btpns.Dashboard.client.panel;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;

public abstract class DashboardLayoutPanel extends DashboardPanel {

	protected final VerticalLayoutContainer con = new VerticalLayoutContainer();
	
	public DashboardLayoutPanel(String title, ImageResource icon) {
		super(title, icon);
	}

	@Override
	public Widget asWidget() {
		
		add(con);
		
		return super.asWidget();
	}

}
