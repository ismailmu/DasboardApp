package com.btpns.Dashboard.server.wisma;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.btpns.Dashboard.client.model.wisma.Wisma;
import com.btpns.Dashboard.server.PersistenceService;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;

public class WismaPersistence extends PersistenceService {
	@SuppressWarnings("unchecked")
	public List<WismaModel> getWismaModel(int offset,
			int limit, List<? extends SortInfo> sortInfoList,
			List<FilterConfig> filterConfigList) {
		EntityManager em = begin();

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("from WismaModel e");

		StringBuilder sbOrder = new StringBuilder();

		StringBuilder sbFilter = new StringBuilder();

		Query query = null;
		if (sortInfoList.size() == 0 && filterConfigList.size() == 0) {
			sbQuery.append(" order by e.wisma ASC");
		} else {
			for(int i=0;i<filterConfigList.size();i++) {
				FilterConfig filterConfig = filterConfigList.get(i);
				
				if (i==0) {
					sbFilter.append(" WHERE e." + filterConfig.getField()
							+ " LIKE :" + filterConfig.getField() + "Filter");
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

		query = em.createQuery(sbQuery.toString(), WismaModel.class);
		
		for (FilterConfig filterConfig : filterConfigList) {
			query.setParameter(filterConfig.getField() + "Filter", "%"
					+ filterConfig.getValue() + "%");
		}

		setSize(query.getResultList().size());
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		
		List<WismaModel> wismaModel = new ArrayList<WismaModel>();
		wismaModel = query.getResultList();

		try {
			wismaModel = query.getResultList();
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		
		return wismaModel;
	}
	
	@SuppressWarnings("unchecked")
	public List<WismaEmployeeModel> getWismaEmployeeModel(int offset,
			int limit, List<? extends SortInfo> sortInfoList,
			List<FilterConfig> filterConfigList,Wisma wisma) {
		EntityManager em = begin();

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("from WismaEmployeeModel e where e.wisma = :wismaFilter");

		StringBuilder sbOrder = new StringBuilder();

		StringBuilder sbFilter = new StringBuilder();

		Query query = null;
		if (sortInfoList.size() == 0 && filterConfigList.size() == 0) {
			sbQuery.append(" order by e.ordinalPosition ASC");
		} else {
			for(FilterConfig filterConfig:filterConfigList) {
				sbFilter.append(" AND e." + filterConfig.getField()
						+ " LIKE :" + filterConfig.getField() + "Filter");
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

		query = em.createQuery(sbQuery.toString(), WismaEmployeeModel.class);
		
		//filter wisma
		query.setParameter("wismaFilter",wisma.getWisma());
		
		for (FilterConfig filterConfig : filterConfigList) {
			query.setParameter(filterConfig.getField() + "Filter", "%"
					+ filterConfig.getValue() + "%");
		}

		setSize(query.getResultList().size());
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		
		List<WismaEmployeeModel> wismaEmployeeModel = new ArrayList<WismaEmployeeModel>();
		wismaEmployeeModel = query.getResultList();

		try {
			wismaEmployeeModel = query.getResultList();
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		
		return wismaEmployeeModel;
	}
}
