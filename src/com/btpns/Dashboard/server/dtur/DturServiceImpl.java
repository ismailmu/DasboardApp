package com.btpns.Dashboard.server.dtur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.Period;

import com.btpns.Dashboard.client.model.dtur.DturEtlDetail;
import com.btpns.Dashboard.client.model.dtur.DturEtlSummary;
import com.btpns.Dashboard.client.service.dtur.DturService;
import com.btpns.Dashboard.shared.DateTimeUtil;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class DturServiceImpl extends RemoteServiceServlet implements DturService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public PagingLoadResult<DturEtlDetail> getDturEtlDetail(
			FilterPagingLoadConfig config, Date dateFilter) {
		
		List<DturEtlDetail> dturs = new ArrayList<DturEtlDetail>();
		DturEtlPersistence persistence = new DturEtlPersistence();
		
		List<DturEtlDetailModel> models = persistence.getDturEtlDetailModel(config.getOffset(), config.getLimit()
				, config.getSortInfo(), config.getFilters(),dateFilter);
		
		Integer no=0;
		for(DturEtlDetailModel model:models) {
			no++;
			
			String duration="";
			if (model.getStartDate() != null && model.getEndDate() != null) {
				Period p = new Period(model.getStartDate().getTime(),model.getEndDate().getTime());
				Integer hour = p.getHours();
				Integer minute = p.getMinutes();
				Integer second = p.getSeconds();
				
				duration=DateTimeUtil.getInstance().DurationInString(hour,minute,second);
			}
			
			dturs.add(new DturEtlDetail(config.getOffset()+no,model.getIdJob(),model.getTgl(),model.getJobName()
					,model.getStatus(),model.getStartDate(),model.getEndDate(),duration));
		}
		return new PagingLoadResultBean<DturEtlDetail>(dturs,persistence.getSize(), config.getOffset());
	}

	@Override
	public PagingLoadResult<DturEtlSummary> getDturEtlSummary(
			PagingLoadConfig config, Date dateFilter) {
		
		List<DturEtlSummary> dturs = new ArrayList<DturEtlSummary>();
		DturEtlPersistence persistence = new DturEtlPersistence();
		
		List<DturEtlSummaryModel> models = persistence.getDturEtlSummaryModel(dateFilter);
		
		Integer no=0;
		for(DturEtlSummaryModel model:models) {
			no++;
			
			String maxDuration="";
			Period p = null;
			Integer hour = 0;
			Integer minute = 0;
			Integer second = 0;
			
			if (model.getMaxStartDate() != null && model.getMaxEndDate() != null) {
				p = new Period(model.getMaxStartDate().getTime(),model.getMaxEndDate().getTime());
				hour = p.getHours();
				minute = p.getMinutes();
				second = p.getSeconds();
				
				maxDuration=DateTimeUtil.getInstance().DurationInString(hour,minute,second);
			}
			
			String etlDuration="";
			if (model.getStartEtl() != null && model.getEndEtl() != null) {
				p = new Period(model.getStartEtl().getTime(),model.getEndEtl().getTime());
				hour = p.getHours();
				minute = p.getMinutes();
				second = p.getSeconds();
				
				etlDuration=DateTimeUtil.getInstance().DurationInString(hour,minute,second);
			}
			
			
			dturs.add(new DturEtlSummary(model.getId(),model.getJobName(),model.getTgl(),model.getCountProcess()
					,maxDuration,model.getStartEtl(),model.getEndEtl(),etlDuration));
		}
		return new PagingLoadResultBean<DturEtlSummary>(dturs,persistence.getSize(), config.getOffset());
	}
}