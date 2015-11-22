package com.btpns.Dashboard.client.panel.home;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.home.HomeSummary;
import com.btpns.Dashboard.client.panel.DashboardLayoutPanel;
import com.btpns.Dashboard.client.service.home.HomeService;
import com.btpns.Dashboard.client.service.home.HomeServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.Portlet;
import com.sencha.gxt.widget.core.client.container.PortalLayoutContainer;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class HomePanel extends DashboardLayoutPanel {

	private Date dateFilter;
	private final HomeSummaryProperties props = GWT.create(HomeSummaryProperties.class);
	private final HomeServiceAsync service = GWT.create(HomeService.class);
	
	private List<ColumnConfig<HomeSummary, ?>> l;
	
	private ListStore<HomeSummary> store;
	
	private ColumnModel<HomeSummary> cm;
	
	public HomePanel(String title, ImageResource icon, Date dateFilter) {
		super(title, icon);
		this.dateFilter = dateFilter;
		
		ColumnConfig<HomeSummary, String> nameColumn = new ColumnConfig<HomeSummary, String>(props.name(), 150, "Name");
		nameColumn.setSortable(false);
		
		ColumnConfig<HomeSummary, String> valueColumn = new ColumnConfig<HomeSummary, String>(props.value(), 100, "Value");
		valueColumn.setSortable(false);
		
		l = new ArrayList<ColumnConfig<HomeSummary, ?>>();
		l.add(nameColumn);
		l.add(valueColumn);

		cm = new ColumnModel<HomeSummary>(l);	
	}

	@Override
	public Widget asWidget() {
		
		PortalLayoutContainer portal = new PortalLayoutContainer(2);
		portal.getElement().getStyle().setBackgroundColor("white");
		portal.setColumnWidth(0, .50);
		portal.setColumnWidth(1, .50);

		Portlet portlet = new Portlet();
		portlet.setHeadingText("EOD Prospera");
		configPanel(portlet);
		portlet.add(createEodHome());
		portal.add(portlet, 0);

		portlet = new Portlet();
		portlet.setHeadingText("FTP Statistics");
		configPanel(portlet);
		portlet.add(createFtpHome());
		portal.add(portlet, 1);

		portlet = new Portlet();
		portlet.setHeadingText("Helpdesk Tickets");
		configPanel(portlet);
		portlet.add(createHelpdeskHome());
		portal.add(portlet, 0);

		portlet = new Portlet();
		portlet.setHeadingText("Portfolio Wisma");
		configPanel(portlet);
		portlet.add(createPortfolioHome());
		portal.add(portlet, 1);
		
		con.add(portal);

		return super.asWidget();
	}
	
	private Widget createEodHome() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<HomeSummary>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<HomeSummary>>() {

			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<HomeSummary>> callback) {
				service.getEodSummary(loadConfig, dateFilter, callback);
			}
		};
		
		store = new ListStore<HomeSummary>(
				new ModelKeyProvider<HomeSummary>() {
					@Override
					public String getKey(HomeSummary item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<PagingLoadConfig, PagingLoadResult<HomeSummary>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<HomeSummary>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(false);
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, HomeSummary, PagingLoadResult<HomeSummary>>(
				store));
		
		Grid<HomeSummary> grid = new Grid<HomeSummary>(store, cm) {
			@Override
			protected void onAfterFirstAttach() {
				super.onAfterFirstAttach();
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						loader.load();
					}
				});
			}
		};

		grid.getView().setForceFit(true);
		grid.setLoadMask(true);
		grid.setLoader(loader);
		
		return grid;
	}
	
	private Widget createFtpHome() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<HomeSummary>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<HomeSummary>>() {

			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<HomeSummary>> callback) {
				service.getFtpSummary(loadConfig, dateFilter, callback);
			}
		};
		
		store = new ListStore<HomeSummary>(
				new ModelKeyProvider<HomeSummary>() {
					@Override
					public String getKey(HomeSummary item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<PagingLoadConfig, PagingLoadResult<HomeSummary>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<HomeSummary>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(false);
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, HomeSummary, PagingLoadResult<HomeSummary>>(
				store));
		
		Grid<HomeSummary> grid = new Grid<HomeSummary>(store, cm) {
			@Override
			protected void onAfterFirstAttach() {
				super.onAfterFirstAttach();
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						loader.load();
					}
				});
			}
		};

		grid.getView().setForceFit(true);
		grid.setLoadMask(true);
		grid.setLoader(loader);
		
		return grid;
	}
	
	private Widget createHelpdeskHome() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<HomeSummary>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<HomeSummary>>() {

			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<HomeSummary>> callback) {
				service.getHelpdeskSummary(loadConfig, dateFilter, callback);
			}
		};
		
		store = new ListStore<HomeSummary>(
				new ModelKeyProvider<HomeSummary>() {
					@Override
					public String getKey(HomeSummary item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<PagingLoadConfig, PagingLoadResult<HomeSummary>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<HomeSummary>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(false);
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, HomeSummary, PagingLoadResult<HomeSummary>>(
				store));
		
		Grid<HomeSummary> grid = new Grid<HomeSummary>(store, cm) {
			@Override
			protected void onAfterFirstAttach() {
				super.onAfterFirstAttach();
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						loader.load();
					}
				});
			}
		};

		grid.getView().setForceFit(true);
		grid.setLoadMask(true);
		grid.setLoader(loader);
		
		return grid;
	}
	
	private Widget createPortfolioHome() {
		RpcProxy<PagingLoadConfig, PagingLoadResult<HomeSummary>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<HomeSummary>>() {

			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<HomeSummary>> callback) {
				service.getPortfolioSummary(loadConfig, dateFilter, callback);
			}
		};
		
		store = new ListStore<HomeSummary>(
				new ModelKeyProvider<HomeSummary>() {
					@Override
					public String getKey(HomeSummary item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<PagingLoadConfig, PagingLoadResult<HomeSummary>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<HomeSummary>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(false);
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, HomeSummary, PagingLoadResult<HomeSummary>>(
				store));
		
		Grid<HomeSummary> grid = new Grid<HomeSummary>(store, cm) {
			@Override
			protected void onAfterFirstAttach() {
				super.onAfterFirstAttach();
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						loader.load();
					}
				});
			}
		};

		grid.getView().setForceFit(true);
		grid.setLoadMask(true);
		grid.setLoader(loader);
		
		return grid;
	}

	private void configPanel(final Portlet panel) {
		panel.setCollapsible(true);
		panel.setAnimCollapse(true);
		panel.setWidth(Window.getClientWidth() / 3 + 100);
		panel.setHeight(Window.getClientHeight() / 3 + 40);
	}
}
