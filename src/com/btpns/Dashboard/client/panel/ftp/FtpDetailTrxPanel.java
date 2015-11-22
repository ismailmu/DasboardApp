package com.btpns.Dashboard.client.panel.ftp;

import java.util.Date;

import com.btpns.Dashboard.client.panel.DashboardTabPanel;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

public class FtpDetailTrxPanel extends DashboardTabPanel {

	protected Date dateFilter;
	public FtpDetailTrxPanel(String title, ImageResource icon, Date dateFilter) {
		super(title, icon);
		this.dateFilter = dateFilter;
	}

	@Override
	public Widget asWidget() {
		summaryTab.setText("Chart");
		detailTab.setText("Data");
		tabPanel.add(new FtpChartTrx2Panel(title,icon,dateFilter).asWidget(), summaryTab);
		tabPanel.add(new FtpChartTrxDataPanel(title,icon,dateFilter).asWidget(), detailTab);
		
		return super.asWidget();
	}
}
