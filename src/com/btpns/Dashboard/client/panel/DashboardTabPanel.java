package com.btpns.Dashboard.client.panel;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;

public abstract class DashboardTabPanel extends DashboardPanel	 {

	protected final TabPanel tabPanel = new TabPanel();
	protected final TabItemConfig summaryTab = new TabItemConfig("Summary");
	protected final TabItemConfig detailTab = new TabItemConfig("Detail");
	
	public DashboardTabPanel(String title, ImageResource icon) {
		super(title, icon);
	}

	@Override
	public Widget asWidget() {
		summaryTab.setClosable(false);
		detailTab.setClosable(false);
		
		add(tabPanel);
		//contentPanel.setHeight(575);
		return super.asWidget();
	}


}
