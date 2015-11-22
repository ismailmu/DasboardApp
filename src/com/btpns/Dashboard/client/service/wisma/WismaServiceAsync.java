package com.btpns.Dashboard.client.service.wisma;
import com.btpns.Dashboard.client.model.wisma.Wisma;
import com.btpns.Dashboard.client.model.wisma.WismaEmployee;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;


public interface WismaServiceAsync {

	void getWisma(FilterPagingLoadConfig config,
			AsyncCallback<PagingLoadResult<Wisma>> callback);

	void getWismaEmployee(FilterPagingLoadConfig config, Wisma wisma,
			AsyncCallback<PagingLoadResult<WismaEmployee>> callback);

}
