package com.btpns.Dashboard.client.panel.eod;

import java.util.Date;

import com.btpns.Dashboard.client.panel.DashboardTabPanel;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

public class EodDailyPanel extends DashboardTabPanel {

	protected Date dateFilter;
	public EodDailyPanel(String title, ImageResource icon,Date dateFilter) {
		super(title, icon);
		this.dateFilter = dateFilter;
	}
	
	@Override
	public Widget asWidget() {
		EodDailySummaryPanel eodSummary = new EodDailySummaryPanel(title,icon,dateFilter);
		EodDailyDetailPanel eodDetail = new EodDailyDetailPanel(title,icon,dateFilter);
		
		tabPanel.add(eodSummary.asWidget(), summaryTab);
		tabPanel.add(eodDetail.asWidget(), detailTab);
		
		return super.asWidget();
	}
}