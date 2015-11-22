package com.btpns.Dashboard.client.panel.snapshot;

import java.util.Date;

import com.btpns.Dashboard.client.panel.DashboardTabPanel;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

public class SnapshotDailyPanel extends DashboardTabPanel {

	protected Date dateFilter;
	public SnapshotDailyPanel(String title, ImageResource icon,Date dateFilter) {
		super(title, icon);
		this.dateFilter = dateFilter;
	}

	@Override
	public Widget asWidget() {
		SnapshotDailySummaryPanel snapshotSummary = new SnapshotDailySummaryPanel(title,icon,dateFilter);
		SnapshotDailyDetailPanel snapshotDetail = new SnapshotDailyDetailPanel(title,icon,dateFilter);
		
		tabPanel.add(snapshotSummary.asWidget(), summaryTab);
		tabPanel.add(snapshotDetail.asWidget(), detailTab);
		
		return super.asWidget();
	}
}