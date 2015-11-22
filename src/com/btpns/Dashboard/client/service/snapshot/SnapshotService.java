package com.btpns.Dashboard.client.service.snapshot;

import java.util.Date;

import com.btpns.Dashboard.client.model.snapshot.SnapshotDailyDetail;
import com.btpns.Dashboard.client.model.snapshot.SnapshotDailySummary;
import com.btpns.Dashboard.client.model.snapshot.SnapshotWismaDetail;
import com.btpns.Dashboard.client.model.snapshot.SnapshotWismaSummary;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@RemoteServiceRelativePath("snapshotService")
public interface SnapshotService extends RemoteService {
	PagingLoadResult<SnapshotWismaDetail> getSnapshotWismaDetail(FilterPagingLoadConfig config,Date dateFilter);
	
	PagingLoadResult<SnapshotWismaSummary> getSnapshotWismaSummary(PagingLoadConfig config,Date dateFilter);
	
	PagingLoadResult<SnapshotDailyDetail> getSnapshotDailyDetail(FilterPagingLoadConfig config,Date dateFilter);
	
	PagingLoadResult<SnapshotDailySummary> getSnapshotDailySummary(PagingLoadConfig config,Date dateFilter);
}