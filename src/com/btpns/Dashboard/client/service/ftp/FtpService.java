package com.btpns.Dashboard.client.service.ftp;

import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.ftp.FtpChartTrx;
import com.btpns.Dashboard.client.model.ftp.FtpFailureDetail;
import com.btpns.Dashboard.client.model.ftp.FtpFailureSummary;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@RemoteServiceRelativePath("ftpService")
public interface FtpService extends RemoteService {
	PagingLoadResult<FtpFailureDetail> getFtpFailureDetail(FilterPagingLoadConfig config,Date dateFilter);
	
	PagingLoadResult<FtpFailureSummary> getFtpFailureSummary(PagingLoadConfig config,Date dateFilter);
	
	PagingLoadResult<FtpChartTrx> getFtpChartTrxData(PagingLoadConfig config,Date dateFilter);
	
	List<FtpChartTrx> getFtpChartTrxDetail(ListLoadConfig loadConfig,
			Date dateFilter);
}