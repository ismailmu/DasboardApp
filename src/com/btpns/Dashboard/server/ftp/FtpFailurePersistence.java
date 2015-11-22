package com.btpns.Dashboard.server.ftp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.btpns.Dashboard.client.model.ftp.FtpFailureSummary;
import com.btpns.Dashboard.server.PersistenceService;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;

public class FtpFailurePersistence extends PersistenceService {
	@SuppressWarnings("unchecked")
	public List<FtpFailureDetailModel> getFtpFailureDetailModel(int offset,
			int limit, List<? extends SortInfo> sortInfoList,
			List<FilterConfig> filterConfigList, Date dateFilter) {
		EntityManager em = begin();

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("from FtpFailureDetailModel e where e.tgl = :dateFilter");

		StringBuilder sbOrder = new StringBuilder();

		StringBuilder sbFilter = new StringBuilder();

		Query query = null;
		if (sortInfoList.size() == 0 && filterConfigList.size() == 0) {
			sbQuery.append(" order by e.wisma ASC");
		} else {
			for (FilterConfig filterConfig : filterConfigList) {
				if (filterConfig.getField().equals("wisma")
						|| filterConfig.getField().equals("wismaName")) {
					sbFilter.append(" AND e." + filterConfig.getField()
							+ " LIKE :" + filterConfig.getField() + "Filter");
				} else {
					if (filterConfig.getComparison().equals("gt")) {
						sbFilter.append(" AND e." + filterConfig.getField()
								+ " > :" + filterConfig.getField() + "Filter");
					} else if (filterConfig.getComparison().equals("lt")) {
						sbFilter.append(" AND e." + filterConfig.getField()
								+ " < :" + filterConfig.getField() + "Filter");
					} else { // eq
						sbFilter.append(" AND e." + filterConfig.getField()
								+ " = :" + filterConfig.getField() + "Filter");
					}
				}
			}
			sbQuery.append(sbFilter);

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

		query = em.createQuery(sbQuery.toString(), FtpFailureDetailModel.class);

		// filter date
		query.setParameter("dateFilter", dateFilter);
		
		if (filterConfigList.size() > 0) {
			for (FilterConfig filterConfig : filterConfigList) {
				if (filterConfig.getField().equals("wisma")
						|| filterConfig.getField().equals("wismaName")) {
					query.setParameter(filterConfig.getField() + "Filter", "%"
							+ filterConfig.getValue() + "%");
				} else {
					query.setParameter(filterConfig.getField() + "Filter",Integer.parseInt(filterConfig.getValue()));
				}
			}
		}

		setSize(query.getResultList().size());
		query.setFirstResult(offset);
		query.setMaxResults(limit);

		List<FtpFailureDetailModel> ftpDetailModels = new ArrayList<FtpFailureDetailModel>();
		
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

	public List<FtpFailureSummary> getFtpFailureDetailModel(Date dateFilter) {
		EntityManager em = begin();
		Query query = em.createQuery("select count(*),sum(e.fileReceived),sum(e.recordReceived),sum(e.hasBeenProcessed)" +
				",sum(e.fileProcessed),sum(e.totalRecord),sum(e.totalRecordSuccess),sum(e.totalRecordFailure)" +
				" from FtpFailureDetailModel e where e.tgl = :dateFilter");
		// filter date
		query.setParameter("dateFilter", dateFilter);

		List<FtpFailureSummary> ftps = new ArrayList<FtpFailureSummary>();
		
		try {
			Object[] objects = (Object[]) query.getSingleResult();
			Integer fileReceived = (objects[1]==null)?0:Integer.parseInt(objects[1].toString());
			Integer recordReceived = (objects[2]==null)?0:Integer.parseInt(objects[2].toString());
			Integer hasBeenProcessed = (objects[3]==null)?0:Integer.parseInt(objects[3].toString());
			Integer fileProcessed = (objects[4]==null)?0:Integer.parseInt(objects[4].toString());
			Integer totalRecord = (objects[5]==null)?0:Integer.parseInt(objects[5].toString());
			Integer totalRecordSuccess = (objects[6]==null)?0:Integer.parseInt(objects[6].toString());
			Integer totalRecordFailure = (objects[7]==null)?0:Integer.parseInt(objects[7].toString());
			
			ftps.add(new FtpFailureSummary(1,Integer.parseInt(objects[0].toString()), fileReceived
					, recordReceived, hasBeenProcessed
					, fileProcessed, totalRecord
					, totalRecordSuccess, totalRecordFailure));
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		return ftps;
	}
}
