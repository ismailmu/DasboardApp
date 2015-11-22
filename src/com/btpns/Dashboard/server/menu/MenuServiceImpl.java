package com.btpns.Dashboard.server.menu;

import java.util.ArrayList;
import java.util.List;

import com.btpns.Dashboard.client.model.menu.MenuToolbar;
import com.btpns.Dashboard.client.service.menu.MenuService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class MenuServiceImpl extends RemoteServiceServlet implements MenuService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<MenuToolbar> getMenuToolbar(String query) {
		
		List<MenuToolbar> menuToolbars = new ArrayList<MenuToolbar>();
		List<MenuToolbarModel> menuToolbarModels = new MenuPersistence().getMenuToolbarModel(query);
		
		for (MenuToolbarModel menu : menuToolbarModels) {
			menuToolbars.add(new MenuToolbar(menu.getId(),menu.getText(), menu.getIconCls()
				, menu.getParentMenu(), menu.getOrdinalPosition(), menu.getAction()));
		}
		return menuToolbars;
	}

}
