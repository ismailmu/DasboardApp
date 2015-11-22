package com.btpns.Dashboard.client.service.snapshot;

import java.util.Date;

import com.btpns.Dashboard.client.model.snapshot.SnapshotDailyDetail;
import com.btpns.Dashboard.client.model.snapshot.SnapshotDailySummary;
import com.btpns.Dashboard.client.model.snapshot.SnapshotWismaDetail;
import com.btpns.Dashboard.client.model.snapshot.SnapshotWismaSummary;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface SnapshotServiceAsync {

	void getSnapshotWismaDetail(FilterPagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<SnapshotWismaDetail>> callback);

	void getSnapshotWismaSummary(PagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<SnapshotWismaSummary>> callback);

	void getSnapshotDailyDetail(FilterPagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<SnapshotDailyDetail>> callback);

	void getSnapshotDailySummary(PagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<SnapshotDailySummary>> callback);
}
