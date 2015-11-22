package com.btpns.Dashboard.client.resources;

import java.util.HashMap;

import com.btpns.Dashboard.client.resources.images.DashboardImages;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;

public class Resources {
	public static final DashboardImages IMAGES = GWT.create(DashboardImages.class);
	private static Resources resources=null;
	private static final HashMap<String, ImageResource> IMAGESCOLL = new HashMap<String, ImageResource>();
	private Resources() {
		IMAGESCOLL.put(IMAGES.chart16().getName(), IMAGES.chart16());
		IMAGESCOLL.put(IMAGES.home16().getName(), IMAGES.home16());
		IMAGESCOLL.put(IMAGES.process16().getName(), IMAGES.process16());
		IMAGESCOLL.put(IMAGES.close16().getName(), IMAGES.close16());
		IMAGESCOLL.put(IMAGES.db16().getName(), IMAGES.db16());
		IMAGESCOLL.put(IMAGES.excel16().getName(), IMAGES.excel16());
		IMAGESCOLL.put(IMAGES.ftp16().getName(), IMAGES.ftp16());
		IMAGESCOLL.put(IMAGES.helpdesk16().getName(), IMAGES.helpdesk16());
		IMAGESCOLL.put(IMAGES.house16().getName(), IMAGES.house16());
		IMAGESCOLL.put(IMAGES.office16().getName(), IMAGES.office16());
		IMAGESCOLL.put(IMAGES.save16().getName(), IMAGES.save16());
		IMAGESCOLL.put(IMAGES.search16().getName(), IMAGES.search16());
		IMAGESCOLL.put(IMAGES.snapshot16().getName(), IMAGES.snapshot16());
		IMAGESCOLL.put(IMAGES.table16().getName(), IMAGES.table16());
		IMAGESCOLL.put(IMAGES.view16().getName(), IMAGES.view16());
		
	}
	
	public static Resources getInstance() {
		if (resources == null) {
			resources = new Resources();
		}
		return resources;
	}
	public ImageResource getImage(String key) {
		return IMAGESCOLL.get(key);
	}
}