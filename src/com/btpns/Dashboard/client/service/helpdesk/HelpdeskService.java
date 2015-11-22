package com.btpns.Dashboard.client.service.helpdesk;

import java.util.Date;

import com.btpns.Dashboard.client.model.helpdesk.HelpdeskDaily;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@RemoteServiceRelativePath("helpdeskService")
public interface HelpdeskService extends RemoteService {
	PagingLoadResult<HelpdeskDaily> getHelpdeskDaily(FilterPagingLoadConfig config,Date dateFilter);
}
