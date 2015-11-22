package com.btpns.Dashboard.server.eod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.Period;

import com.btpns.Dashboard.client.model.eod.EodDailyDetail;
import com.btpns.Dashboard.client.model.eod.EodDailySummary;
import com.btpns.Dashboard.client.model.eod.EodDuration;
import com.btpns.Dashboard.client.service.eod.EodService;
import com.btpns.Dashboard.shared.DateTimeUtil;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class EodServiceImpl extends RemoteServiceServlet implements
		EodService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public PagingLoadResult<EodDailyDetail> getEodDailyDetail(FilterPagingLoadConfig config,Date dateFilter) {
		List<EodDailyDetail> eods = new ArrayList<EodDailyDetail>();
		EodDailyPersistence persistence = new EodDailyPersistence();
		
		List<EodDailyDetailModel> eodModels = persistence.getEodDailyDetailModel(config.getOffset(), config.getLimit()
				, config.getSortInfo(), config.getFilters(),dateFilter);
		
		Integer no=0;
		for(EodDailyDetailModel model:eodModels) {
			no++;
			eods.add(new EodDailyDetail(config.getOffset()+no,model.getId(),model.getTgl(),model.getWisma(), model.getWismaName(), model.getSumTrans()
					, model.getCountFile()));
		}
		return new PagingLoadResultBean<EodDailyDetail>(eods,persistence.getSize(), config.getOffset());
	}

	@Override
	public PagingLoadResult<EodDailySummary> getEodDailySummary(
			PagingLoadConfig config, Date dateFilter) {
		EodDailyPersistence persistence = new EodDailyPersistence();

		List<EodDailySummary> eods = persistence.getEodDailySummaryModel(dateFilter);
		
		return new PagingLoadResultBean<EodDailySummary>(eods,eods.size(),config.getOffset());
	}
	
	@Override
	public PagingLoadResult<EodDuration> getEodDuration(
			FilterPagingLoadConfig config) {
		
		List<EodDuration> eods = new ArrayList<EodDuration>();
		EodDurationPersistence persistence = new EodDurationPersistence();
		
		List<EodDurationModel> eodModels = persistence.getEodDurationModel(config.getOffset(), config.getLimit()
				, config.getSortInfo(), config.getFilters());
		
		Integer no=0;
		for(EodDurationModel model:eodModels) {
			no++;
			
			String duration="";
			
			if (model.getStartTime() != null && model.getEndTime() != null) {
				Period p = new Period(model.getStartTime().getTime(),model.getEndTime().getTime());
				Integer hour = p.getHours();
				Integer minute = p.getMinutes();
				Integer second = 0;
				
				duration=DateTimeUtil.getInstance().DurationInString(hour,minute,second);
			}
			
			eods.add(new EodDuration(config.getOffset()+no,model.getId(),model.getStartTime(),model.getEndTime()
					,duration,model.getStatus(),model.getFailTask()));
		}
		return new PagingLoadResultBean<EodDuration>(eods,persistence.getSize(), config.getOffset());
	}
}
