package com.btpns.Dashboard.server.helpdesk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.helpdesk.HelpdeskDaily;
import com.btpns.Dashboard.client.service.helpdesk.HelpdeskService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class HelpdeskServiceImpl extends RemoteServiceServlet implements HelpdeskService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public PagingLoadResult<HelpdeskDaily> getHelpdeskDaily(
			FilterPagingLoadConfig config, Date dateFilter) {
		List<HelpdeskDaily> helpdesks = new ArrayList<HelpdeskDaily>();
		HelpdeskDailyPersistence persistence = new HelpdeskDailyPersistence();
		
		List<HelpdeskDailyModel> helpdeskModels = persistence.getHelpdeskDailyModel(
				config.getOffset(), config.getLimit(), config.getSortInfo(), config.getFilters(),dateFilter);
		
		Integer no=0;
		for(HelpdeskDailyModel model:helpdeskModels) {
			no++;
			helpdesks.add(new HelpdeskDaily(config.getOffset()+no,model.getId(),model.getTicketId(),model.getWisma()
					, model.getWismaName(),model.getStatus(),model.getPriority(),model.getAging()
					,model.getCategory1(),model.getCategory2(),model.getCategory3(),model.getCategory4()));
		}
		return new PagingLoadResultBean<HelpdeskDaily>(helpdesks,persistence.getSize(), config.getOffset());
	}
}
