package com.btpns.Dashboard.client.service.helpdesk;

import java.util.Date;

import com.btpns.Dashboard.client.model.helpdesk.HelpdeskDaily;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

public interface HelpdeskServiceAsync {

	void getHelpdeskDaily(FilterPagingLoadConfig config, Date dateFilter,
			AsyncCallback<PagingLoadResult<HelpdeskDaily>> callback);

}
