package com.btpns.Dashboard.client.service.menu;

import java.util.List;

import com.btpns.Dashboard.client.model.menu.MenuToolbar;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("menuService")
public interface MenuService extends RemoteService {
	List<MenuToolbar> getMenuToolbar(String query);
}
