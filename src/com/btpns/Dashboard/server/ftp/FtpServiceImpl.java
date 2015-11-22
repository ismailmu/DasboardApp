package com.btpns.Dashboard.server.ftp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.ftp.FtpChartTrx;
import com.btpns.Dashboard.client.model.ftp.FtpFailureDetail;
import com.btpns.Dashboard.client.model.ftp.FtpFailureSummary;
import com.btpns.Dashboard.client.service.ftp.FtpService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoadResultBean;

public class FtpServiceImpl extends RemoteServiceServlet implements FtpService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public PagingLoadResult<FtpFailureDetail> getFtpFailureDetail(
			FilterPagingLoadConfig config, Date dateFilter) {
		List<FtpFailureDetail> ftps = new ArrayList<FtpFailureDetail>();
		FtpFailurePersistence persistence = new FtpFailurePersistence();
		
		List<FtpFailureDetailModel> ftpModels = persistence.getFtpFailureDetailModel(
				config.getOffset(), config.getLimit(), config.getSortInfo(), config.getFilters(),dateFilter);
		
		Integer no=0;
		for(FtpFailureDetailModel model:ftpModels) {
			no++;
			ftps.add(new FtpFailureDetail(config.getOffset()+no,model.getId(),model.getWisma()
					, model.getWismaName(),model.getFileReceived(),model.getRecordReceived(),model.getHasBeenProcessed()
					,model.getFileProcessed(),model.getTotalRecord(),model.getTotalRecordSuccess(),model.getTotalRecordFailure()));
		}
		return new PagingLoadResultBean<FtpFailureDetail>(ftps,persistence.getSize(), config.getOffset());
	}

	@Override
	public PagingLoadResult<FtpFailureSummary> getFtpFailureSummary(
			PagingLoadConfig config, Date dateFilter) {
		FtpFailurePersistence persistence = new FtpFailurePersistence();

		List<FtpFailureSummary> ftps = persistence.getFtpFailureDetailModel(dateFilter);
		
		return new PagingLoadResultBean<FtpFailureSummary>(ftps,ftps.size(),config.getOffset());
	}

	@Override
	public List<FtpChartTrx> getFtpChartTrxDetail(ListLoadConfig loadConfig,
			Date dateFilter) {
		
		FtpDetailTrxPersistence persistence = new FtpDetailTrxPersistence();
		
		List<FtpChartTrx> ftps = new ArrayList<FtpChartTrx>();
		
		List<FtpChartTrxModel> ftpModels = persistence.getFtpChartTrxModel(dateFilter);
		
		Integer no=0;
		for(FtpChartTrxModel model:ftpModels) {
			no++;
			ftps.add(new FtpChartTrx(no,model.getId(), model.getTimeReference(), model.getCountIncoming(), model.getCountOutgoing()));
		}
		
		return ftps;
	}

	@Override
	public PagingLoadResult<FtpChartTrx> getFtpChartTrxData(
			PagingLoadConfig config, Date dateFilter) {
		List<FtpChartTrx> ftps = new ArrayList<FtpChartTrx>();
		
		FtpDetailTrxPersistence persistence = new FtpDetailTrxPersistence();
		
		List<FtpChartTrxModel> ftpModels = persistence.getFtpChartTrxModel(dateFilter, config.getSortInfo());
		
		Integer no=0;
		for(FtpChartTrxModel model:ftpModels) {
			no++;
			ftps.add(new FtpChartTrx(no,model.getId(), model.getTimeReference(), model.getCountIncoming(), model.getCountOutgoing()));
		}
		return new PagingLoadResultBean<FtpChartTrx>(ftps,ftps.size(), config.getOffset());
	}

}
