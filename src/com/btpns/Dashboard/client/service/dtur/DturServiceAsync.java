package com.btpns.Dashboard.client.service.dtur;

import java.util.Date;

import com.btpns.Dashboard.client.model.dtur.DturEtlDetail;
import com.btpns.Dashboard.client.model.dtur.DturEtlSummary;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface DturServiceAsync {

	void getDturEtlDetail(FilterPagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<DturEtlDetail>> callback);

	void getDturEtlSummary(PagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<DturEtlSummary>> callback);

}
