package com.btpns.Dashboard.server.menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.btpns.Dashboard.server.IModelPersistence;

@Entity
@Table(name="menu")
public class MenuToolbarModel implements IModelPersistence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column(name="text",nullable=false)
	private String text;
	@Column(name="iconCls",nullable=false)
	private String iconCls;
	@Column(name="parent_menu",nullable=true)
	private Integer parentMenu;
	@Column(name="ordinal_position")
	private Integer ordinalPosition;
	@Column(name="action")
	private String action = "";	
	
	public MenuToolbarModel() {}

	public MenuToolbarModel(Integer id, String text,String iconCls, Integer parentMenu, Integer ordinalPosition,
			String action) {
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
