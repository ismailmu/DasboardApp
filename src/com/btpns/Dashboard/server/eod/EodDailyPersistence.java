package com.btpns.Dashboard.server.eod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.btpns.Dashboard.client.model.eod.EodDailySummary;
import com.btpns.Dashboard.server.PersistenceService;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;

public class EodDailyPersistence extends PersistenceService {
	
	@SuppressWarnings("unchecked")
	public List<EodDailyDetailModel> getEodDailyDetailModel(int offset, int limit,
			List<? extends SortInfo> sortInfoList,
			List<FilterConfig> filterConfigList, Date dateFilter) {
		
		List<EodDailyDetailModel> eodModels = new ArrayList<EodDailyDetailModel>();
		
		EntityManager em = begin();

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("from EodDailyDetailModel e where e.tgl = :dateFilter");

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

		query = em.createQuery(sbQuery.toString(), EodDailyDetailModel.class);

		// filter date
		query.setParameter("dateFilter",dateFilter);

		if (filterConfigList.size() > 0) {
			for (FilterConfig filterConfig : filterConfigList) {
				if (filterConfig.getField().equals("wisma")
						|| filterConfig.getField().equals("wismaName")) {
					query.setParameter(filterConfig.getField() + "Filter", "%"
							+ filterConfig.getValue() + "%");
				} else {
					if (filterConfig.getField().equals("countFile")) {
						query.setParameter(filterConfig.getField() + "Filter",
								Integer.parseInt(filterConfig.getValue()));
					} else {
						BigDecimal dec = new BigDecimal(filterConfig.getValue());
						query.setParameter(filterConfig.getField() + "Filter",
								dec);
					}
				}
			}
		}

		setSize(query.getResultList().size());
		query.setFirstResult(offset);
		query.setMaxResults(limit);

		try {
			eodModels = query.getResultList();
			commit();
		}catch (Exception e) {
			rollback();
			e.printStackTrace();
		}finally {
			close();
		}

		return eodModels;
	}
	
	public List<EodDailySummary> getEodDailySummaryModel(Date dateFilter) {
		EntityManager em = begin();
		Query query = em.createQuery("select count(*),sum(e.sumTrans),sum(e.countFile)" +
				" from EodDailyDetailModel e where e.tgl = :dateFilter");
		// filter date
		query.setParameter("dateFilter", dateFilter);

		List<EodDailySummary> eods = new ArrayList<EodDailySummary>();
		try {
			Object[] objects = (Object[]) query.getSingleResult();
			
			BigDecimal sumTrans = new BigDecimal(0);
			if (objects[1]!=null) {
				sumTrans = new BigDecimal(objects[1].toString());
			}
			
			Integer countFile = (objects[2]==null)?0:Integer.parseInt(objects[2].toString());
			
			eods.add(new EodDailySummary(1,Integer.parseInt(objects[0].toString()), sumTrans, countFile));
			
			commit();
		}catch (Exception e) {
			rollback();
			e.printStackTrace();
		}finally {
			close();
		}
		
		return eods;
	}
}