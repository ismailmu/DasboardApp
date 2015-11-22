package com.btpns.Dashboard.client.panel;

import com.btpns.Dashboard.client.resources.Resources;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class ExportContextMenu extends Menu implements SelectionHandler<Item> {
	
	private String pathExcel = "";
	public ExportContextMenu(String pathExcel) {
		this.pathExcel = pathExcel;
		
		MenuItem item = new MenuItem("Export to Excel");
		item.setIcon(Resources.IMAGES.excel16());
		item.setId("exportExcel");
		item.addSelectionHandler(this);
		add(item);
	}

	@Override
	public void onSelection(SelectionEvent<Item> event) {
		//Info.display("Export Excel", GWT.getHostPageBaseURL() + "excel/" + pathExcel);
		Window.open(GWT.getHostPageBaseURL() + DashboardConstant.FOLDER_EXCEL + pathExcel, "_blank", null);
	}
}