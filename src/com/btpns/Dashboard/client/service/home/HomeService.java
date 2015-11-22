package com.btpns.Dashboard.client.service.home;

import java.util.Date;

import com.btpns.Dashboard.client.model.home.HomeSummary;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@RemoteServiceRelativePath("homeService")
public interface HomeService extends RemoteService {
	PagingLoadResult<HomeSummary> getEodSummary(PagingLoadConfig config,Date dateFilter);
	
	PagingLoadResult<HomeSummary> getFtpSummary(PagingLoadConfig config,Date dateFilter);
	
	PagingLoadResult<HomeSummary> getHelpdeskSummary(PagingLoadConfig config,Date dateFilter);
	
	PagingLoadResult<HomeSummary> getPortfolioSummary(PagingLoadConfig config,Date dateFilter);
}
