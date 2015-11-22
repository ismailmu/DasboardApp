package com.btpns.Dashboard.client.service.dtur;

import java.util.Date;

import com.btpns.Dashboard.client.model.dtur.DturEtlDetail;
import com.btpns.Dashboard.client.model.dtur.DturEtlSummary;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@RemoteServiceRelativePath("dturService")
public interface DturService extends RemoteService {
	PagingLoadResult<DturEtlDetail> getDturEtlDetail(
			FilterPagingLoadConfig config, Date dateFilter);
	
	PagingLoadResult<DturEtlSummary> getDturEtlSummary(
			PagingLoadConfig config, Date dateFilter);
}
