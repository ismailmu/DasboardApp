package com.btpns.Dashboard.client.service.eod;

import java.util.Date;

import com.btpns.Dashboard.client.model.eod.EodDailyDetail;
import com.btpns.Dashboard.client.model.eod.EodDailySummary;
import com.btpns.Dashboard.client.model.eod.EodDuration;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@RemoteServiceRelativePath("eodService")
public interface EodService extends RemoteService {
	PagingLoadResult<EodDailyDetail> getEodDailyDetail(FilterPagingLoadConfig config,Date dateFilter);
	
	PagingLoadResult<EodDailySummary> getEodDailySummary(
			PagingLoadConfig config, Date dateFilter);
	
	PagingLoadResult<EodDuration> getEodDuration(FilterPagingLoadConfig config);
}
