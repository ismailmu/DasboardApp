package com.btpns.Dashboard.client.panel.eod;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.eod.EodDuration;
import com.btpns.Dashboard.client.panel.DashboardLayoutPanel;
import com.btpns.Dashboard.client.service.eod.EodService;
import com.btpns.Dashboard.client.service.eod.EodServiceAsync;
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
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.DateFilter;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

public class EodDurationPanel extends DashboardLayoutPanel {

	public EodDurationPanel(String title, ImageResource icon) {
		super(title,icon);
	}
	
	interface EodDurationProperties extends PropertyAccess<EodDuration> {
		ModelKeyProvider<EodDuration> id();

		ValueProvider<EodDuration, Integer> no();
		
		ValueProvider<EodDuration, Date> startTime();

		ValueProvider<EodDuration, Date> endTime();

		ValueProvider<EodDuration, String> durationString();

		ValueProvider<EodDuration, String> status();
		
		ValueProvider<EodDuration, String> failTask();
	}

	@Override
	public Widget asWidget() {
		final EodServiceAsync service = GWT.create(EodService.class);

		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<EodDuration>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<EodDuration>>() {

			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<EodDuration>> callback) {
				service.getEodDuration(loadConfig, callback);
			}
		};

		EodDurationProperties props = GWT.create(EodDurationProperties.class);

		ListStore<EodDuration> store = new ListStore<EodDuration>(
				new ModelKeyProvider<EodDuration>() {
					@Override
					public String getKey(EodDuration item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<EodDuration>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<EodDuration>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);
		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, EodDuration, PagingLoadResult<EodDuration>>(
				store));

		final PagingToolBar toolBar = new PagingToolBar(
				DashboardConstant.PAGE_SIZE);
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolBar.bind(loader);		
				
		ColumnConfig<EodDuration, Integer> noColumn = new ColumnConfig<EodDuration, Integer>(
				props.no(), DashboardConstant.SIZE_NO,"No");
		noColumn.setSortable(false);
		ColumnConfig<EodDuration, Date> startColumn = new ColumnConfig<EodDuration, Date>(
				props.startTime(), 50, "Start Time");
		startColumn.setCell(new AbstractCell<Date>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Date value, SafeHtmlBuilder sb) {
				if (value !=null)
					sb.appendHtmlConstant(DateTimeFormat.getFormat(DashboardConstant.DATETIME_FORMAT).format(value));
			}
		});
		ColumnConfig<EodDuration, Date> endColumn = new ColumnConfig<EodDuration, Date>(
				props.endTime(), 50, "End Time");
		endColumn.setCell(new AbstractCell<Date>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Date value, SafeHtmlBuilder sb) {
				if (value !=null)
					sb.appendHtmlConstant(DateTimeFormat.getFormat(DashboardConstant.DATETIME_FORMAT).format(value));
			}
		});
		ColumnConfig<EodDuration, String> durationColumn = new ColumnConfig<EodDuration, String>(
				props.durationString(), 100, "Duration");
		durationColumn.setSortable(false);
		ColumnConfig<EodDuration, String> statusColumn = new ColumnConfig<EodDuration, String>(
				props.status(), 50, "Status");
		ColumnConfig<EodDuration, String> failureColumn = new ColumnConfig<EodDuration, String>(
				props.failTask(), 250, "Failure Task");
		failureColumn.setSortable(false);
		
		List<ColumnConfig<EodDuration, ?>> l = new ArrayList<ColumnConfig<EodDuration, ?>>();
		l.add(noColumn);
		l.add(startColumn);
		l.add(endColumn);
		l.add(durationColumn);
		l.add(statusColumn);
		l.add(failureColumn);

		ColumnModel<EodDuration> cm = new ColumnModel<EodDuration>(l);

		Grid<EodDuration> grid = new Grid<EodDuration>(store, cm) {
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
		
		DateFilter<EodDuration> startFilter = new DateFilter<EodDuration>(
				props.startTime());
		DateFilter<EodDuration> endFilter = new DateFilter<EodDuration>(
				props.endTime());

		GridFilters<EodDuration> filters = new GridFilters<EodDuration>(loader);

		filters.initPlugin(grid);
		filters.addFilter(startFilter);
		filters.addFilter(endFilter);

		grid.getView().setForceFit(true);
		grid.setLoadMask(true);
		grid.setLoader(loader);

		//VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.add(grid, new VerticalLayoutData(1, DashboardConstant.getHeightGrid(Window.getClientHeight(), false)));
		con.add(toolBar, new VerticalLayoutData(1, -1));
		
		//setWidget(con);
		return super.asWidget();
	}
}
