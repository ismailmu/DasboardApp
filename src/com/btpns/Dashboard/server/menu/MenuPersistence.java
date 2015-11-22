package com.btpns.Dashboard.server.menu;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.btpns.Dashboard.server.PersistenceService;

public class MenuPersistence extends PersistenceService {

	@SuppressWarnings("unchecked")
	public List<MenuToolbarModel> getMenuToolbarModel(String queryText) {
		EntityManager em = begin();
		Query query = em.createQuery(queryText, MenuToolbarModel.class);
		
		List<MenuToolbarModel> menuToolbars = new ArrayList<MenuToolbarModel>();

		try {
			menuToolbars = query.getResultList();
			setSize(menuToolbars.size());
			
			commit();
		}catch(Exception ex) {
			rollback();
			ex.printStackTrace();
		}finally {
			close();
		}
		
		return menuToolbars;
	}
}
