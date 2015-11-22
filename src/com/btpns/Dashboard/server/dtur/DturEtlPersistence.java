package com.btpns.Dashboard.server.dtur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.btpns.Dashboard.server.PersistenceService;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;

public class DturEtlPersistence extends PersistenceService {
	
	@SuppressWarnings("unchecked")
	public List<DturEtlSummaryModel> getDturEtlSummaryModel(Date dateFilter) {
		
		List<DturEtlSummaryModel> models = new ArrayList<DturEtlSummaryModel>();
		
		EntityManager em = begin();

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("from DturEtlSummaryModel e where e.tgl = :dateFilter");

		Query query = em.createQuery(sbQuery.toString(), DturEtlSummaryModel.class);

		// filter date
		query.setParameter("dateFilter",dateFilter);

		setSize(query.getResultList().size());

		try {
			models = query.getResultList();
			commit();
		}catch (Exception e) {
			rollback();
			e.printStackTrace();
		}finally {
			close();
		}

		return models;
	}
	
	@SuppressWarnings("unchecked")
	public List<DturEtlDetailModel> getDturEtlDetailModel(int offset, int limit,
			List<? extends SortInfo> sortInfoList,
			List<FilterConfig> filterConfigList, Date dateFilter) {
		
		List<DturEtlDetailModel> models = new ArrayList<DturEtlDetailModel>();
		
		EntityManager em = begin();

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("from DturEtlDetailModel e where e.tgl = :dateFilter");

		StringBuilder sbOrder = new StringBuilder();

		Query query = null;
		if (sortInfoList.size() == 0) {
			sbQuery.append(" order by e.idJob ASC");
		} else {

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

		query = em.createQuery(sbQuery.toString(), DturEtlDetailModel.class);

		// filter date
		query.setParameter("dateFilter",dateFilter);

		setSize(query.getResultList().size());
		query.setFirstResult(offset);
		query.setMaxResults(limit);

		try {
			models = query.getResultList();
			commit();
		}catch (Exception e) {
			rollback();
			e.printStackTrace();
		}finally {
			close();
		}

		return models;
	}
}
