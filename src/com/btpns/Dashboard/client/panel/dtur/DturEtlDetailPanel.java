package com.btpns.Dashboard.client.panel.dtur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.dtur.DturEtlDetail;
import com.btpns.Dashboard.client.service.dtur.DturService;
import com.btpns.Dashboard.client.service.dtur.DturServiceAsync;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfig;
import com.sencha.gxt.data.shared.loader.FilterPagingLoadConfigBean;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

public class DturEtlDetailPanel extends DturEtlPanel {

	public DturEtlDetailPanel(String title, ImageResource icon, Date dateFilter) {
		super(title, icon, dateFilter);
	}

	interface DturProgressProperties extends PropertyAccess<DturEtlDetail> {
		ModelKeyProvider<DturEtlDetail> idJob();

		ValueProvider<DturEtlDetail, Integer> no();

		ValueProvider<DturEtlDetail, String> jobName();

		ValueProvider<DturEtlDetail, String> status();

		ValueProvider<DturEtlDetail, Date> startDate();

		ValueProvider<DturEtlDetail, Date> endDate();

		ValueProvider<DturEtlDetail, String> duration();
	}

	@Override
	public Widget asWidget() {
		final DturServiceAsync service = GWT.create(DturService.class);

		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<DturEtlDetail>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<DturEtlDetail>>() {

			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<DturEtlDetail>> callback) {
				service.getDturEtlDetail(loadConfig, dateFilter, callback);
			}
		};

		DturProgressProperties props = GWT.create(DturProgressProperties.class);

		ListStore<DturEtlDetail> store = new ListStore<DturEtlDetail>(
				new ModelKeyProvider<DturEtlDetail>() {
					@Override
					public String getKey(DturEtlDetail item) {
						return "" + item.getIdJob();
					}
				});

		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<DturEtlDetail>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<DturEtlDetail>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);
		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, DturEtlDetail, PagingLoadResult<DturEtlDetail>>(
				store));

		final PagingToolBar toolBar = new PagingToolBar(
				DashboardConstant.PAGE_SIZE);
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolBar.bind(loader);

		ColumnConfig<DturEtlDetail, Integer> noColumn = new ColumnConfig<DturEtlDetail, Integer>(
				props.no(), DashboardConstant.SIZE_NO, "No");
		noColumn.setSortable(false);
		ColumnConfig<DturEtlDetail, String> jobNameColumn = new ColumnConfig<DturEtlDetail, String>(
				props.jobName(), 150, "Job Name");
		ColumnConfig<DturEtlDetail, String> statusColumn = new ColumnConfig<DturEtlDetail, String>(
				props.status(), 50, "Status");
		ColumnConfig<DturEtlDetail, Date> startDateColumn = new ColumnConfig<DturEtlDetail, Date>(
				props.startDate(), 100, "Start Date");
		startDateColumn.setCell(new AbstractCell<Date>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Date value, SafeHtmlBuilder sb) {
				if (value != null) {
					sb.appendHtmlConstant(DateTimeFormat.getFormat(
							DashboardConstant.DATETIME_FORMAT).format(value));
				} else {
					sb.appendHtmlConstant(DashboardConstant.EMPTY_STRING);
				}
			}
		});
		ColumnConfig<DturEtlDetail, Date> endDateColumn = new ColumnConfig<DturEtlDetail, Date>(
				props.endDate(), 100, "End Date");
		endDateColumn.setCell(new AbstractCell<Date>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Date value, SafeHtmlBuilder sb) {
				if (value != null) {
					sb.appendHtmlConstant(DateTimeFormat.getFormat(
							DashboardConstant.DATETIME_FORMAT).format(value));
				} else {
					sb.appendHtmlConstant(DashboardConstant.EMPTY_STRING);
				}
			}
		});
		ColumnConfig<DturEtlDetail, String> durationColumn = new ColumnConfig<DturEtlDetail, String>(
				props.duration(), 150, "Duration");
		durationColumn.setSortable(false);

		List<ColumnConfig<DturEtlDetail, ?>> l = new ArrayList<ColumnConfig<DturEtlDetail, ?>>();
		l.add(noColumn);
		l.add(jobNameColumn);
		l.add(statusColumn);
		l.add(startDateColumn);
		l.add(endDateColumn);
		l.add(durationColumn);

		ColumnModel<DturEtlDetail> cm = new ColumnModel<DturEtlDetail>(l);

		Grid<DturEtlDetail> grid = new Grid<DturEtlDetail>(store, cm) {
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
		grid.getView().refresh(true);
		
		StringFilter<DturEtlDetail> jobNameFilter = new StringFilter<DturEtlDetail>(
				props.jobName());
		StringFilter<DturEtlDetail> statusFilter = new StringFilter<DturEtlDetail>(
				props.status());

		GridFilters<DturEtlDetail> filters = new GridFilters<DturEtlDetail>(
				loader);

		filters.initPlugin(grid);
		filters.addFilter(jobNameFilter);
		filters.addFilter(statusFilter);

		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.add(grid,
				new VerticalLayoutData(1, DashboardConstant.getHeightGrid(
						Window.getClientHeight(), true)));
		con.add(toolBar, new VerticalLayoutData(1, -1));

		return con;
	}
}