package com.btpns.Dashboard.server;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class DashboardContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		EntityManagerFactory factory = EMF.get();
		EntityManager em = PersistenceService.MANAGERS.get();
		if (em != null && em.getTransaction().isActive()) {
			em.getTransaction().rollback();
			em.close();
		}
		PersistenceService.MANAGERS.set(null);
		factory.close();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		PersistenceService.getEntityManager();
	}

}