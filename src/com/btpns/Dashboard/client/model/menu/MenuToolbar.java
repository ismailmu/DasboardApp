package com.btpns.Dashboard.client.model.menu;

import com.btpns.Dashboard.client.model.IModelClient;

public class MenuToolbar implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String text;
	private String iconCls;
	private Integer parentMenu;
	private Integer ordinalPosition;
	private String action;
	
	public MenuToolbar(){}
	
	public MenuToolbar(Integer id,String text, String iconCls, Integer parentMenu,
			Integer ordinalPosition, String action) {
		super();
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.parentMenu = parentMenu;
		this.ordinalPosition = ordinalPosition;
		this.action = action;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Integer getParentMenu() {
		return parentMenu;
	}

	public void setParentMenu(Integer parentMenu) {
		this.parentMenu = parentMenu;
	}

	public Integer getOrdinalPosition() {
		return ordinalPosition;
	}

	public void setOrdinalPosition(Integer ordinalPosition) {
		this.ordinalPosition = ordinalPosition;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
}
