package com.btpns.Dashboard.server.snapshot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.btpns.Dashboard.client.model.snapshot.SnapshotDailySummary;
import com.btpns.Dashboard.server.PersistenceService;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;

public class SnapshotDailyPersistence extends PersistenceService {
	@SuppressWarnings("unchecked")
	public List<SnapshotDailyDetailModel> getSnapshotDailyDetailModel(int offset,
			int limit, List<? extends SortInfo> sortInfoList,
			List<FilterConfig> filterConfigList, Date dateFilter) {
		EntityManager em = begin();

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("from SnapshotDailyDetailModel e where e.tgl = :dateFilter");

		StringBuilder sbOrder = new StringBuilder();

		StringBuilder sbFilter = new StringBuilder();

		Query query = null;
		if (sortInfoList.size() == 0 && filterConfigList.size() == 0) {
			sbQuery.append(" order by e.wisma ASC");
		} else {
			for (FilterConfig filterConfig : filterConfigList) {
				if (filterConfig.getField().equals("wisma")
						|| filterConfig.getField().equals("wismaName") || filterConfig.getField().equals("kcs")
						|| filterConfig.getField().equals("kcsName")) {
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

		query = em.createQuery(sbQuery.toString(), SnapshotDailyDetailModel.class);

		// filter date
		query.setParameter("dateFilter", dateFilter);
		
		if (filterConfigList.size() > 0) {
			for (FilterConfig filterConfig : filterConfigList) {
				if (filterConfig.getField().equals("wisma")
						|| filterConfig.getField().equals("wismaName") || filterConfig.getField().equals("kcs")
						|| filterConfig.getField().equals("kcsName")) {
					query.setParameter(filterConfig.getField() + "Filter", "%"
							+ filterConfig.getValue() + "%");
				} else if (filterConfig.getField().equals("sumDeposit") || filterConfig.getField().equals("sumWithdraw")
						|| filterConfig.getField().equals("sumDisburse") || filterConfig.getField().equals("sumRepayment")
						|| filterConfig.getField().equals("sumEarlyPayment") || filterConfig.getField().equals("sumCoverIn")
						|| filterConfig.getField().equals("sumCoverOut")) {
					query.setParameter(filterConfig.getField() + "Filter",new BigDecimal(filterConfig.getValue()));
				}else {
					query.setParameter(filterConfig.getField() + "Filter",Integer.parseInt(filterConfig.getValue()));
				}
			}
		}

		setSize(query.getResultList().size());
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<SnapshotDailyDetailModel> snapshotDailyModels = new ArrayList<SnapshotDailyDetailModel>();
		snapshotDailyModels = query.getResultList();

		try {
			snapshotDailyModels = query.getResultList();
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		return snapshotDailyModels;
	}
	
	public List<SnapshotDailySummary> getSnapshotDailySummaryModel(Date dateFilter) {
		EntityManager em = begin();
		Query query = em.createQuery("select count(*),sum(e.countNewSaving),sum(e.countDeposit),sum(e.sumDeposit)" +
				",sum(e.countWithdraw),sum(e.sumWithdraw),sum(e.countNewLoan),sum(e.countDisburse),sum(e.sumDisburse)" +
				",sum(e.countRepayment),sum(e.sumRepayment),sum(e.countEarlyPayment),sum(e.sumEarlyPayment)" +
				",sum(e.countCc),sum(e.countCoverIn),sum(e.sumCoverIn),sum(e.countCoverOut),sum(e.sumCoverOut)" +
				" from SnapshotDailyDetailModel e where e.tgl = :dateFilter");
		// filter date
		query.setParameter("dateFilter", dateFilter);
		
		List<SnapshotDailySummary> snapshots = new ArrayList<SnapshotDailySummary>();
		
		try {
			Object[] objects = (Object[]) query.getSingleResult();
			
			Integer totalCountNewSaving = (objects[1]==null)?0: Integer.parseInt(objects[1].toString());
			Integer totalCountDeposit = (objects[2]==null)?0: Integer.parseInt(objects[2].toString());
			BigDecimal totalSumDeposit = (objects[3]==null)?new BigDecimal(0): new BigDecimal(objects[3].toString());
			Integer totalCountWithdraw = (objects[4]==null)?0: Integer.parseInt(objects[4].toString());
			BigDecimal totalSumWithdraw = (objects[5]==null)?new BigDecimal(0): new BigDecimal(objects[5].toString());
			Integer totalCountNewLoan = (objects[6]==null)?0: Integer.parseInt(objects[6].toString());
			Integer totalCountDisburse = (objects[7]==null)?0: Integer.parseInt(objects[7].toString());
			BigDecimal totalSumDisburse = (objects[8]==null)?new BigDecimal(0): new BigDecimal(objects[8].toString());
			Integer totalCountRepayment = (objects[9]==null)?0: Integer.parseInt(objects[9].toString());
			BigDecimal totalSumRepayment = (objects[10]==null)?new BigDecimal(0): new BigDecimal(objects[10].toString());
			Integer totalCountEarlyPayment = (objects[11]==null)?0: Integer.parseInt(objects[11].toString());
			BigDecimal totalSumEarlyPayment = (objects[12]==null)?new BigDecimal(0): new BigDecimal(objects[12].toString());
			Integer totalCountCc = (objects[13]==null)?0: Integer.parseInt(objects[13].toString());
			Integer totalCountCoverIn = (objects[14]==null)?0: Integer.parseInt(objects[14].toString());
			BigDecimal totalSumCoverIn = (objects[15]==null)?new BigDecimal(0): new BigDecimal(objects[15].toString());
			Integer totalCountCoverOut = (objects[16]==null)?0: Integer.parseInt(objects[16].toString());
			BigDecimal totalSumCoverOut = (objects[17]==null)?new BigDecimal(0): new BigDecimal(objects[17].toString());
			
			
			
			snapshots.add(new SnapshotDailySummary(1,Integer.parseInt(objects[0].toString()),totalCountNewSaving
					,totalCountDeposit,totalSumDeposit,totalCountWithdraw,totalSumWithdraw,totalCountNewLoan
					,totalCountDisburse,totalSumDisburse,totalCountRepayment,totalSumRepayment,totalCountEarlyPayment
					,totalSumEarlyPayment,totalCountCc,totalCountCoverIn,totalSumCoverIn,totalCountCoverOut,totalSumCoverOut));
			
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		
		return snapshots;
	}
}