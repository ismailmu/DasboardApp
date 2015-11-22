package com.btpns.Dashboard.client.panel.ftp;

import java.util.Date;

import com.btpns.Dashboard.client.panel.DashboardTabPanel;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

public class FtpFailurePanel extends DashboardTabPanel {

	protected Date dateFilter;
	public FtpFailurePanel(String title, ImageResource icon, Date dateFilter) {
		super(title, icon);
		this.dateFilter = dateFilter;
	}

	@Override
	public Widget asWidget() {
		FtpFailureSummaryPanel fptSummary = new FtpFailureSummaryPanel(title,icon,dateFilter);
		FtpFailureDetailPanel ftpDetail = new FtpFailureDetailPanel(title,icon,dateFilter);
		
		tabPanel.add(fptSummary.asWidget(), summaryTab);
		tabPanel.add(ftpDetail.asWidget(), detailTab);
		
		return super.asWidget();
	}
}