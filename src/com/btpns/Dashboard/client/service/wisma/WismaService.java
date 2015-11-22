package com.btpns.Dashboard.client.service.wisma;
import com.btpns.Dashboard.client.model.wisma.Wisma;
import com.btpns.Dashboard.client.model.wisma.WismaEmployee;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;

@RemoteServiceRelativePath("wismaService")
public interface WismaService extends RemoteService {
	PagingLoadResult<Wisma> getWisma(FilterPagingLoadConfig config);
	
	PagingLoadResult<WismaEmployee> getWismaEmployee(FilterPagingLoadConfig config,Wisma wisma);
}
