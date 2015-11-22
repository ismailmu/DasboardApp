package com.btpns.Dashboard.server.snapshot;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.btpns.Dashboard.client.model.snapshot.SnapshotWismaSummary;
import com.btpns.Dashboard.server.PersistenceService;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;


public class SnapshotWismaPersistence extends PersistenceService {
	@SuppressWarnings("unchecked")
	public List<SnapshotWismaDetailModel> getSnapshotWismaDetailModel(int offset,
			int limit, List<? extends SortInfo> sortInfoList,
			List<FilterConfig> filterConfigList, Date dateFilter) {
		EntityManager em = begin();

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("from SnapshotWismaDetailModel e where e.tgl = :dateFilter");

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

		query = em.createQuery(sbQuery.toString(), SnapshotWismaDetailModel.class);

		// filter date
		query.setParameter("dateFilter", dateFilter);
		
		if (filterConfigList.size() > 0) {
			for (FilterConfig filterConfig : filterConfigList) {
				if (filterConfig.getField().equals("wisma")
						|| filterConfig.getField().equals("wismaName") || filterConfig.getField().equals("kcs")
						|| filterConfig.getField().equals("kcsName")) {
					query.setParameter(filterConfig.getField() + "Filter", "%"
							+ filterConfig.getValue() + "%");
				} else {
					if (filterConfig.getField().equals("amountSaving") || filterConfig.getField().equals("amountOs")
							|| filterConfig.getField().equals("amountDisburse")) {
						query.setParameter(filterConfig.getField() + "Filter",new BigDecimal(filterConfig.getValue()));
					}else {
						query.setParameter(filterConfig.getField() + "Filter",Integer.parseInt(filterConfig.getValue()));
					}
					
				}
			}
		}

		setSize(query.getResultList().size());
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List<SnapshotWismaDetailModel> snapshotWismaModels = new ArrayList<SnapshotWismaDetailModel>();
		snapshotWismaModels = query.getResultList();

		try {
			snapshotWismaModels = query.getResultList();
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		
		return snapshotWismaModels;
	}
	
	public List<SnapshotWismaSummary> getSnapshotWismaDetailModel(Date dateFilter) {
		EntityManager em = begin();
		Query query = em.createQuery("select count(*),sum(e.totalCustomer),sum(e.totalGroup),sum(e.totalSentra)" +
				",sum(e.totalSaving),sum(e.amountSaving),sum(e.totalLoan),sum(e.amountOs),sum(e.amountDisburse)" +
				" from SnapshotWismaDetailModel e where e.tgl = :dateFilter");
		// filter date
		query.setParameter("dateFilter", dateFilter);
		
		List<SnapshotWismaSummary> snapshots = new ArrayList<SnapshotWismaSummary>();
		
		try {
			Object[] objects = (Object[]) query.getSingleResult();
			Integer totalCustomer = (objects[1]==null)?0: Integer.parseInt(objects[1].toString());
			Integer totalGroup = (objects[2]==null)?0: Integer.parseInt(objects[2].toString());
			Integer totalSentra = (objects[3]==null)?0: Integer.parseInt(objects[3].toString());
			Integer totalSaving = (objects[4]==null)?0: Integer.parseInt(objects[4].toString());
			
			BigDecimal totalAmountSaving = (objects[5]==null)?new BigDecimal(0):new BigDecimal(objects[5].toString());
			totalAmountSaving = totalAmountSaving.divide(DashboardConstant.BILLION_ROUND);
			totalAmountSaving.setScale(2, RoundingMode.CEILING);
			
			Integer totalLoan = (objects[6]==null)?0: Integer.parseInt(objects[6].toString());
			
			BigDecimal totalAmountOs = (objects[7]==null)?new BigDecimal(0):new BigDecimal(objects[7].toString());
			totalAmountOs = totalAmountOs.divide(DashboardConstant.BILLION_ROUND);
			totalAmountOs.setScale(2, RoundingMode.CEILING);
			
			BigDecimal totalAmountDisburse = (objects[8]==null)?new BigDecimal(0):new BigDecimal(objects[8].toString());
			totalAmountDisburse = totalAmountDisburse.divide(DashboardConstant.BILLION_ROUND);
			totalAmountDisburse.setScale(2, RoundingMode.CEILING);
			
			
			
			snapshots.add(new SnapshotWismaSummary(1,Integer.parseInt(objects[0].toString())
					,totalCustomer, totalGroup
					, totalSentra, totalSaving
					, totalAmountSaving, totalLoan
					, totalAmountOs,totalAmountDisburse));
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