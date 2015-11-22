package com.btpns.Dashboard.client.model.eod;

import com.btpns.Dashboard.client.model.IModelClient;

public class EodDurationFilter implements IModelClient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String label;
	
	public EodDurationFilter(){}

	public EodDurationFilter(String id, String label) {
		super();
		this.id = id;
		this.label = label;
	}
	
	public String getId() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
