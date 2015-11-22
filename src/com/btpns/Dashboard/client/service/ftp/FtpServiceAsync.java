package com.btpns.Dashboard.client.service.ftp;

import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.ftp.FtpChartTrx;
import com.btpns.Dashboard.client.model.ftp.FtpFailureDetail;
import com.btpns.Dashboard.client.model.ftp.FtpFailureSummary;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface FtpServiceAsync {
	void getFtpFailureDetail(FilterPagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<FtpFailureDetail>> callback);
	
	void getFtpFailureSummary(PagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<FtpFailureSummary>> callback);
	
	void getFtpChartTrxDetail(ListLoadConfig loadConfig, Date dateFilter,
			AsyncCallback<List<FtpChartTrx>> callback);

	void getFtpChartTrxData(PagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<FtpChartTrx>> callback);
}
