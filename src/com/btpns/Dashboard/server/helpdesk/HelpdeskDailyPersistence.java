package com.btpns.Dashboard.server.helpdesk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.btpns.Dashboard.server.PersistenceService;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;

public class HelpdeskDailyPersistence extends PersistenceService {
	
	
	@SuppressWarnings("unchecked")
	public List<HelpdeskDailyModel> getHelpdeskDailyModel(int offset,
			int limit, List<? extends SortInfo> sortInfoList,
			List<FilterConfig> filterConfigList, Date dateFilter) {
		EntityManager em = begin();

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("from HelpdeskDailyModel e where e.tgl = :dateFilter");

		StringBuilder sbOrder = new StringBuilder();

		StringBuilder sbFilter = new StringBuilder();

		Query query = null;
		if (sortInfoList.size() == 0 && filterConfigList.size() == 0) {
			sbQuery.append(" order by e.wisma ASC");
		} else {
			for (FilterConfig filterConfig : filterConfigList) {
				if (filterConfig.getField().equals("aging") || filterConfig.getField().equals("ticketId")) {
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
				}else {
					sbFilter.append(" AND e." + filterConfig.getField()
							+ " LIKE :" + filterConfig.getField() + "Filter");
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

		query = em.createQuery(sbQuery.toString(), HelpdeskDailyModel.class);

		// filter date
		query.setParameter("dateFilter", dateFilter);
		
		if (filterConfigList.size() > 0) {
			for (FilterConfig filterConfig : filterConfigList) {
				if (filterConfig.getField().equals("aging") || filterConfig.getField().equals("ticketId")) {
					query.setParameter(filterConfig.getField() + "Filter",
							Integer.parseInt(filterConfig.getValue()));
				}else {
					query.setParameter(filterConfig.getField() + "Filter", "%"
							+ filterConfig.getValue() + "%");
				}
			}
		}

		setSize(query.getResultList().size());
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		
		List<HelpdeskDailyModel> helpdeskDailyModel = new ArrayList<HelpdeskDailyModel>();

		try {
			helpdeskDailyModel = query.getResultList();
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		
		return helpdeskDailyModel;
	}
}
