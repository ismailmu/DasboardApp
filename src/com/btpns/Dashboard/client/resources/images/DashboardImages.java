package com.btpns.Dashboard.client.resources.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface DashboardImages extends ClientBundle {
	public DashboardImages INSTANCE = GWT.create(DashboardImages.class);
	
	@Source("search16.png")
	ImageResource search16();
	
	@Source("table16.png")
	ImageResource table16();
	
	@Source("house16.png")
	ImageResource house16();
	
	@Source("home16.png")
	ImageResource home16();
	
	@Source("process16.png")
	ImageResource process16();
	
	@Source("ftp16.png")
	ImageResource ftp16();
	
	@Source("chart16.png")
	ImageResource chart16();
	
	@Source("office16.png")
	ImageResource office16();
	
	@Source("snapshot16.png")
	ImageResource snapshot16();
	
	@Source("helpdesk16.png")
	ImageResource helpdesk16();
	
	@Source("form_edit16.png")
	ImageResource formEdit16();
	
	@Source("view16.png")
	ImageResource view16();
	
	@Source("close16.png")
	ImageResource close16();
	
	@Source("save16.png")
	ImageResource save16();
	
	@Source("db16.png")
	ImageResource db16();
	
	@Source("excel16.png")
	ImageResource excel16();
}
