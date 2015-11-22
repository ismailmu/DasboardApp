package com.btpns.Dashboard.client.service.eod;

import java.util.Date;

import com.btpns.Dashboard.client.model.eod.EodDailyDetail;
import com.btpns.Dashboard.client.model.eod.EodDailySummary;
import com.btpns.Dashboard.client.model.eod.EodDuration;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface EodServiceAsync {
	void getEodDailyDetail(FilterPagingLoadConfig config,Date dateFilter, AsyncCallback<PagingLoadResult<EodDailyDetail>> callback);
	void getEodDuration(FilterPagingLoadConfig config, AsyncCallback<PagingLoadResult<EodDuration>> callback);
	void getEodDailySummary(PagingLoadConfig config, Date dateFilter
			, AsyncCallback<PagingLoadResult<EodDailySummary>> callback);
}
