package com.btpns.Dashboard.client.service.home;

import java.util.Date;

import com.btpns.Dashboard.client.model.home.HomeSummary;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface HomeServiceAsync {

	void getEodSummary(PagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<HomeSummary>> callback);

	void getFtpSummary(PagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<HomeSummary>> callback);

	void getHelpdeskSummary(PagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<HomeSummary>> callback);

	void getPortfolioSummary(PagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<HomeSummary>> callback);

}
