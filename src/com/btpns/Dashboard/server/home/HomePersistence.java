package com.btpns.Dashboard.server.home;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.btpns.Dashboard.server.PersistenceService;

public class HomePersistence extends PersistenceService {

	@SuppressWarnings("unchecked")
	public List<EodHomeSummaryModel> getEodSummaryModel(Date dateFilter) {
		
		EntityManager em = begin();
		
		Query query = em.createQuery("from EodHomeSummaryModel e where e.tgl = :dateFilter", EodHomeSummaryModel.class);
		
		// filter date
		query.setParameter("dateFilter", dateFilter);
		
		setSize(query.getResultList().size());
		List<EodHomeSummaryModel> home = new ArrayList<EodHomeSummaryModel>();
		home = query.getResultList();
		
		try {
			home = query.getResultList();
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		
		return home;
	}
	
	@SuppressWarnings("unchecked")
	public List<FtpHomeSummaryModel> getFtpSummaryModel(Date dateFilter) {
		
		EntityManager em = begin();
		
		Query query = em.createQuery("from FtpHomeSummaryModel e where e.tgl = :dateFilter", FtpHomeSummaryModel.class);
		
		// filter date
		query.setParameter("dateFilter", dateFilter);
		
		setSize(query.getResultList().size());
		List<FtpHomeSummaryModel> home = new ArrayList<FtpHomeSummaryModel>();
		try {
			home = query.getResultList();
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		
		return home;
	}
	
	@SuppressWarnings("unchecked")
	public List<HelpdeskHomeSummaryModel> getHelpdeskSummaryModel(Date dateFilter) {
		
		EntityManager em = begin();
		
		Query query = em.createQuery("from HelpdeskHomeSummaryModel e where e.tgl = :dateFilter", HelpdeskHomeSummaryModel.class);
		
		// filter date
		query.setParameter("dateFilter", dateFilter);
		
		setSize(query.getResultList().size());
		List<HelpdeskHomeSummaryModel> home = new ArrayList<HelpdeskHomeSummaryModel>();
		try {
			home = query.getResultList();
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		
		return home;
	}
	
	@SuppressWarnings("unchecked")
	public List<PortfolioHomeSummaryModel> getPortfolioSummaryModel(Date dateFilter) {
		
		EntityManager em = begin();
		
		Query query = em.createQuery("from PortfolioHomeSummaryModel e where e.tgl = :dateFilter", PortfolioHomeSummaryModel.class);
		
		// filter date
		query.setParameter("dateFilter", dateFilter);
		
		setSize(query.getResultList().size());
		List<PortfolioHomeSummaryModel> home = new ArrayList<PortfolioHomeSummaryModel>();
		try {
			home = query.getResultList();
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		
		return home;
	}
}