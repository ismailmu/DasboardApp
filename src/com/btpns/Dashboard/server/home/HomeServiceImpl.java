package com.btpns.Dashboard.server.home;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.Period;

import com.btpns.Dashboard.client.model.home.HomeSummary;
import com.btpns.Dashboard.client.service.home.HomeService;
import com.btpns.Dashboard.shared.DateTimeUtil;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class HomeServiceImpl extends RemoteServiceServlet implements HomeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HomePersistence persistence = new HomePersistence();
	@Override
	public PagingLoadResult<HomeSummary> getEodSummary(PagingLoadConfig config,
			Date dateFilter) {
		List<HomeSummary> home = new ArrayList<HomeSummary>();
		List<EodHomeSummaryModel> models = persistence.getEodSummaryModel(dateFilter);
		if (models.size()>0) {
			EodHomeSummaryModel model = models.get(0);
			home.add(new HomeSummary(1,"Total Wisma",String.format("%,d",model.getTotWisma())));
			
			String duration="";
			if(model.getStartTime() != null && model.getEndTime() != null) {
				Period p = new Period(model.getStartTime().getTime(),model.getEndTime().getTime());
				Integer hour = p.getHours();
				Integer minute = p.getMinutes();
				Integer second = p.getSeconds();
				
				duration=DateTimeUtil.getInstance().DurationInString(hour,minute,second);
			}
			
			home.add(new HomeSummary(2,"Duration",duration));
			home.add(new HomeSummary(3,"Total Transaction",NumberFormat.getNumberInstance().format(model.getSumTrans())));
			home.add(new HomeSummary(4,"Total File(s)",String.format("%,d",model.getTotFiles())));
		}
		
		return new PagingLoadResultBean<HomeSummary>(home,home.size(),config.getOffset());
	}

	@Override
	public PagingLoadResult<HomeSummary> getFtpSummary(PagingLoadConfig config,
			Date dateFilter) {
		List<HomeSummary> home = new ArrayList<HomeSummary>();
		List<FtpHomeSummaryModel> models = persistence.getFtpSummaryModel(dateFilter);
		if (models.size()>0) {
			FtpHomeSummaryModel model = models.get(0);
			home.add(new HomeSummary(1,"Total Incoming",String.format("%,d",model.getCountIncoming())));
			home.add(new HomeSummary(2,"Total Outgoing",String.format("%,d",model.getCountOutgoing())));
			home.add(new HomeSummary(3,"Total Wisma Incoming",String.format("%,d",model.getCountWismaIncoming())));
			home.add(new HomeSummary(4,"Total Wisma Outgoing",String.format("%,d",model.getCountWismaOutgoing())));
		}
		
		return new PagingLoadResultBean<HomeSummary>(home,home.size(),config.getOffset());
	}

	@Override
	public PagingLoadResult<HomeSummary> getHelpdeskSummary(
			PagingLoadConfig config, Date dateFilter) {
		
		List<HomeSummary> home = new ArrayList<HomeSummary>();
		List<HelpdeskHomeSummaryModel> models = persistence.getHelpdeskSummaryModel(dateFilter);
		if (models.size()>0) {
			for(int i=0;i<models.size();i++) {
				HelpdeskHomeSummaryModel model=models.get(i);
				if (i==0) {
					home.add(new HomeSummary(i+1,"Total Wisma",String.format("%,d",model.getCountWisma())));
				}else {
					home.add(new HomeSummary(i+1, "Total " + model.getStatusId(), String.format("%,d",model.getCountStatus())));
				}
			}
		}
		
		return new PagingLoadResultBean<HomeSummary>(home,home.size(),config.getOffset());
	}

	@Override
	public PagingLoadResult<HomeSummary> getPortfolioSummary(
			PagingLoadConfig config, Date dateFilter) {
		
		List<HomeSummary> home = new ArrayList<HomeSummary>();
		List<PortfolioHomeSummaryModel> models = persistence.getPortfolioSummaryModel(dateFilter);
		if (models.size()>0) {
			PortfolioHomeSummaryModel model = models.get(0);
			home.add(new HomeSummary(1,"Total CIF",String.format("%,d",model.getCountCif())));
			home.add(new HomeSummary(2,"Total Saving",String.format("%,d",model.getCountSaving())));
			home.add(new HomeSummary(3,"Total Loan",String.format("%,d",model.getCountLoan())));
			home.add(new HomeSummary(4,"Amount Saving",NumberFormat.getNumberInstance().format(model.getSumSaving())));
			home.add(new HomeSummary(5,"Amount Loan",NumberFormat.getNumberInstance().format(model.getSumLoan())));
		}
		
		return new PagingLoadResultBean<HomeSummary>(home,home.size(),config.getOffset());
	}
}