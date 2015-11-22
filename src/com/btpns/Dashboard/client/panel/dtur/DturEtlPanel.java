package com.btpns.Dashboard.client.panel.dtur;

import java.util.Date;

import com.btpns.Dashboard.client.panel.DashboardTabPanel;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

public class DturEtlPanel extends DashboardTabPanel {

	protected Date dateFilter;
	public DturEtlPanel(String title, ImageResource icon,Date dateFilter) {
		super(title, icon);
		this.dateFilter = dateFilter;
	}
	
	@Override
	public Widget asWidget() {

		DturEtlSummaryPanel dturSummary = new DturEtlSummaryPanel(title,icon,dateFilter);
		DturEtlDetailPanel dturDetail = new DturEtlDetailPanel(title,icon,dateFilter);
		
		tabPanel.add(dturSummary.asWidget(), summaryTab);
		tabPanel.add(dturDetail.asWidget(), detailTab);
		
		return super.asWidget();
	}
}