package com.btpns.Dashboard.client.service.menu;

import java.util.List;

import com.btpns.Dashboard.client.model.menu.MenuToolbar;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MenuServiceAsync {
	void getMenuToolbar(String query, AsyncCallback<List<MenuToolbar>> callback);
}
