package com.btpns.Dashboard.server.ftp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.btpns.Dashboard.server.PersistenceService;
import com.sencha.gxt.data.shared.SortInfo;

public class FtpDetailTrxPersistence extends PersistenceService {
	
	@SuppressWarnings("unchecked")
	public List<FtpChartTrxModel> getFtpChartTrxModel(Date dateFilter) {
		EntityManager em = begin();

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("from FtpChartTrxModel e where e.tgl = :dateFilter order by e.timeReference");
		
		Query query = em.createQuery(sbQuery.toString(), FtpChartTrxModel.class);

		// filter date
		query.setParameter("dateFilter", dateFilter);

		List<FtpChartTrxModel> models = new ArrayList<FtpChartTrxModel>();
		try {
			models = query.getResultList();
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		return models;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<FtpChartTrxModel> getFtpChartTrxModel(Date dateFilter,List<? extends SortInfo> sortInfoList) {
		EntityManager em = begin();

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("from FtpChartTrxModel e where e.tgl = :dateFilter");

		StringBuilder sbOrder = new StringBuilder();


		Query query = null;
		if (sortInfoList.size() == 0) {
			sbQuery.append(" Order By e.timeReference ASC");
		}else {
			// sorting
			if (sortInfoList.size() > 0) {
				sbOrder.append(" Order By");
			}
			for (SortInfo sortInfo : sortInfoList) {
				sbOrder.append(" e." + sortInfo.getSortField() + " "
						+ sortInfo.getSortDir().name() + ",");
			}
			if (sbOrder.length() > 0) {
				sbOrder.deleteCharAt(sbOrder.length() - 1);
				sbQuery.append(sbOrder);
			}
		}

		query = em.createQuery(sbQuery.toString(), FtpChartTrxModel.class);

		// filter date
		query.setParameter("dateFilter", dateFilter);

		setSize(query.getResultList().size());

		List<FtpChartTrxModel> ftpDetailModels = new ArrayList<FtpChartTrxModel>();
		ftpDetailModels = query.getResultList();

		try {
			ftpDetailModels = query.getResultList();
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		
		return ftpDetailModels;
	}
}
