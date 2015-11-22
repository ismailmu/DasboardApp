package com.btpns.Dashboard.server.eod;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.btpns.Dashboard.server.PersistenceService;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.btpns.Dashboard.shared.DateTimeUtil;
import com.sencha.gxt.data.shared.SortInfo;
import com.sencha.gxt.data.shared.loader.FilterConfig;

public class EodDurationPersistence extends PersistenceService {
	@SuppressWarnings("unchecked")
	public List<EodDurationModel> getEodDurationModel(int offset, int limit,
			List<? extends SortInfo> sortInfoList,List<FilterConfig> filterConfigList) {
		EntityManager em = begin();

		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append("from EodDurationModel e");

		StringBuilder sbOrder = new StringBuilder();

		StringBuilder sbFilter = new StringBuilder();

		Query query = null;
		if (sortInfoList.size() == 0 && filterConfigList.size() == 0) {
			sbQuery.append(" Order By e.startTime DESC");
		} else {

			// sorting
			if (sortInfoList.size() > 0) {
				sbOrder.append(" Order By");
			}
			for(int i=0;i<filterConfigList.size();i++) {
				FilterConfig filterConfig = filterConfigList.get(i);
				
				if (i==0) {
					sbFilter.append(" Where e.");
				}else {
					sbFilter.append(" And e.");
				}
				
				if (filterConfig.getComparison().equals("before")) {
					sbFilter.append(filterConfig.getField() + " < " + ":"+filterConfig.getField()+"Filter");
				}else if (filterConfig.getComparison().equals("after")) {
					sbFilter.append(filterConfig.getField() + " > " + ":"+filterConfig.getField()+"Filter");
				}else {
					sbFilter.append(filterConfig.getField() + " = " + ":"+filterConfig.getField()+"Filter");
				}
			}
			if (sbFilter.length()>0) {
				sbQuery.append(sbFilter);
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

		query = em.createQuery(sbQuery.toString(), EodDurationModel.class);
		for(FilterConfig filterConfig:filterConfigList) {
			query.setParameter(filterConfig.getField()+"Filter", DateTimeUtil.getInstance().getDate(
					filterConfig.getValue(),DashboardConstant.DATETIME_FORMAT));
		}
		
		setSize(query.getResultList().size());
		
		query.setFirstResult(offset);
		query.setMaxResults(limit);

		List<EodDurationModel> eodModels = new ArrayList<EodDurationModel>();
		
		try {
			eodModels = query.getResultList();
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}
		
		return eodModels;
	}
}
