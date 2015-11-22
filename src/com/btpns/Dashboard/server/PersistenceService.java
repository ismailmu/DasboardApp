package com.btpns.Dashboard.server;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

public abstract class PersistenceService {
	public static final ThreadLocal<EntityManager> MANAGERS = new ThreadLocal<EntityManager>();

//	private ReentrantLock lock = new ReentrantLock();
	private Integer size = 0;
	//private Date dateFilter;

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

//	public Date getDateFilter() {
//		return dateFilter;
//	}
//
//	public void setDateFilter(Date dateFilter) {
//		this.dateFilter = dateFilter;
//	}

	public static EntityManager getEntityManager() {
		EntityManager mgr = MANAGERS.get();
		// if manager is null assume we are not running in a web context
		if (mgr == null) {
			mgr = EMF.get().createEntityManager();
			MANAGERS.set(mgr);
			return mgr;
		}
		return MANAGERS.get();
	}

	public static CriteriaBuilder getCriteriaBuilder() {
		return EMF.get().getCriteriaBuilder();
	}

	protected EntityManager begin() {
		try {
			//lock.lock();
			EntityManager em = getEntityManager();
			if (!em.getTransaction().isActive()) {
				em.getTransaction().begin();
			}
			return em;
		} finally {
			//lock.unlock();
		}
	}

	protected void commit() {
		try {
			//lock.lock();
			EntityManager em = getEntityManager();
			em.getTransaction().commit();
		} finally {
			//lock.unlock();
		}
	}

	protected void rollback() {
		try {
			//lock.lock();
			EntityManager em = getEntityManager();
			em.getTransaction().rollback();
		} finally {
			//lock.unlock();
		}
	}

	protected void close() {
		try {
			EntityManager em = MANAGERS.get();
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
				em.close();
			}
			//jikalau di 10.2.72.34 masih keluar JDBC failed
			//coba buka remark MANAGERS.set(null)
			//dibuka tgl 1-03-2013
			MANAGERS.set(null);
		} finally {
		}
	}

//	public void closeEmf() {
//		try {
//			close();
//			MANAGERS.set(null);
//			if (EMF.get() != null) EMF.get().close();
//		} finally {
//
//		}
//
//	}
}