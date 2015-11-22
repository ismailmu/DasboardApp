package com.btpns.Dashboard.server.snapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.snapshot.SnapshotDailyDetail;
import com.btpns.Dashboard.client.model.snapshot.SnapshotDailySummary;
import com.btpns.Dashboard.client.model.snapshot.SnapshotWismaDetail;
import com.btpns.Dashboard.client.model.snapshot.SnapshotWismaSummary;
import com.btpns.Dashboard.client.service.snapshot.SnapshotService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class SnapshotServiceImpl extends RemoteServiceServlet implements
		SnapshotService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public PagingLoadResult<SnapshotWismaDetail> getSnapshotWismaDetail(
			FilterPagingLoadConfig config, Date dateFilter) {

		List<SnapshotWismaDetail> snapshots = new ArrayList<SnapshotWismaDetail>();
		SnapshotWismaPersistence persistence = new SnapshotWismaPersistence();
		
		List<SnapshotWismaDetailModel> snapshotModels = persistence.getSnapshotWismaDetailModel(
				config.getOffset(), config.getLimit(), config.getSortInfo(), config.getFilters(),dateFilter);
		
		Integer no=0;
		for(SnapshotWismaDetailModel model:snapshotModels) {
			no++;
			snapshots.add(new SnapshotWismaDetail(config.getOffset()+no,model.getId()
					,model.getKcs(),model.getKcsName(),model.getWisma(), model.getWismaName()
					,model.getTotalCustomer(),model.getTotalGroup(),model.getTotalSentra()
					,model.getTotalSaving(),model.getAmountSaving(),model.getTotalLoan(),model.getAmountOs()
					,model.getAmountDisburse()));
		}
		return new PagingLoadResultBean<SnapshotWismaDetail>(snapshots,persistence.getSize(), config.getOffset());
	}

	@Override
	public PagingLoadResult<SnapshotWismaSummary> getSnapshotWismaSummary(
			PagingLoadConfig config, Date dateFilter) {
		
		SnapshotWismaPersistence persistence = new SnapshotWismaPersistence();
		
		List<SnapshotWismaSummary> snapshots = persistence.getSnapshotWismaDetailModel(dateFilter);
				
		return new PagingLoadResultBean<SnapshotWismaSummary>(snapshots,snapshots.size(),config.getOffset());
	}

	@Override
	public PagingLoadResult<SnapshotDailyDetail> getSnapshotDailyDetail(
			FilterPagingLoadConfig config, Date dateFilter) {
		
		List<SnapshotDailyDetail> snapshots = new ArrayList<SnapshotDailyDetail>();
		SnapshotDailyPersistence persistence = new SnapshotDailyPersistence();
		
		List<SnapshotDailyDetailModel> snapshotModels = persistence.getSnapshotDailyDetailModel(
				config.getOffset(), config.getLimit(), config.getSortInfo(), config.getFilters(),dateFilter);
		
		Integer no=0;
		for(SnapshotDailyDetailModel model:snapshotModels) {
			no++;
			snapshots.add(new SnapshotDailyDetail(config.getOffset()+no,model.getId()
					,model.getKcs(),model.getKcsName(),model.getWisma(), model.getWismaName(),model.getCountNewSaving()
					,model.getCountDeposit(),model.getSumDeposit(),model.getCountWithdraw(),model.getSumWithdraw()
					,model.getCountNewLoan(),model.getCountDisburse(),model.getSumDisburse(),model.getCountRepayment()
					,model.getSumRepayment(),model.getCountEarlyPayment(),model.getSumEarlyPayment(),model.getCountCc()
					,model.getCountCoverIn(),model.getSumCoverIn(),model.getCountCoverOut(),model.getSumCoverOut()));
		}
		return new PagingLoadResultBean<SnapshotDailyDetail>(snapshots,persistence.getSize(), config.getOffset());
	}

	@Override
	public PagingLoadResult<SnapshotDailySummary> getSnapshotDailySummary(
			PagingLoadConfig config, Date dateFilter) {
		SnapshotDailyPersistence persistence = new SnapshotDailyPersistence();
		
		List<SnapshotDailySummary> snapshots = persistence.getSnapshotDailySummaryModel(dateFilter);
				
		return new PagingLoadResultBean<SnapshotDailySummary>(snapshots,snapshots.size(),config.getOffset());
	}
}

/*
 * Integer totalWisma=0;
		Integer totalCustomer=0;
		Integer totalGroup=0;
		Integer totalSentra=0;
		Integer totalSaving=0;
		BigDecimal totalAmountSaving = new BigDecimal(0);
		Integer totalLoan=0;
		BigDecimal totalAmountOs=new BigDecimal(0);
		BigDecimal totalAmountDisburse=new BigDecimal(0);
		
		for(SnapshotDailyDetailModel model:snapshotModels) {
			totalWisma++;
			totalCustomer+=model.getTotalCustomer();
			totalGroup+=model.getTotalGroup();
			totalSentra+=model.getTotalSentra();
			totalSaving+=model.getTotalSaving();
			totalAmountSaving = totalAmountSaving.add(model.getAmountSaving());
			totalLoan+=model.getTotalLoan();
			totalAmountOs = totalAmountOs.add(model.getAmountOs());
			totalAmountDisburse = totalAmountDisburse.add(model.getAmountDisburse());
		}
		totalAmountSaving = totalAmountSaving.divide(DashboardConstant.BILLION_ROUND);
		totalAmountSaving.setScale(2, RoundingMode.CEILING);
		totalAmountOs = totalAmountOs.divide(DashboardConstant.BILLION_ROUND, RoundingMode.CEILING);
		totalAmountOs.setScale(2);
		totalAmountDisburse = totalAmountDisburse.divide(DashboardConstant.BILLION_ROUND, RoundingMode.CEILING);
		totalAmountDisburse.setScale(2);
		
		snapshots.add(new SnapshotWismaSummary(1,totalWisma, totalCustomer, totalGroup
				, totalSentra, totalSaving, totalAmountSaving, totalLoan, totalAmountOs,totalAmountDisburse));
				*/
