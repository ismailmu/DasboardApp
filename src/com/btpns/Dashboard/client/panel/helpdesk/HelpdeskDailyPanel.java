package com.btpns.Dashboard.client.panel.helpdesk;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.helpdesk.HelpdeskDaily;
import com.btpns.Dashboard.client.panel.DashboardLayoutPanel;
import com.btpns.Dashboard.client.service.helpdesk.HelpdeskService;
import com.btpns.Dashboard.client.service.helpdesk.HelpdeskServiceAsync;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
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
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.NumericFilter;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

public class HelpdeskDailyPanel extends DashboardLayoutPanel {

	private Date dateFilter;
	public HelpdeskDailyPanel(String title, ImageResource icon,Date dateFilter) {
		super(title, icon);
		this.dateFilter=dateFilter;
	}
	
	interface HelpdeskDailyProperties extends PropertyAccess<HelpdeskDaily> {
		ModelKeyProvider<HelpdeskDaily> id();

		ValueProvider<HelpdeskDaily, Integer> no();
		
		ValueProvider<HelpdeskDaily, Integer> ticketId();
		
		ValueProvider<HelpdeskDaily, String> wisma();

		ValueProvider<HelpdeskDaily, String> wismaName();

		ValueProvider<HelpdeskDaily, String> status();

		ValueProvider<HelpdeskDaily, String> priority();
		
		ValueProvider<HelpdeskDaily, Integer> aging();
		
		ValueProvider<HelpdeskDaily, String> category1();

		ValueProvider<HelpdeskDaily, String> category2();

		ValueProvider<HelpdeskDaily, String> category3();

		ValueProvider<HelpdeskDaily, String> category4();
	}

	@Override
	public Widget asWidget() {
		final HelpdeskServiceAsync service = GWT.create(HelpdeskService.class);

		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<HelpdeskDaily>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<HelpdeskDaily>>() {

			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<HelpdeskDaily>> callback) {
				service.getHelpdeskDaily(loadConfig, dateFilter, callback);
			}
		};

		HelpdeskDailyProperties props = GWT.create(HelpdeskDailyProperties.class);

		ListStore<HelpdeskDaily> store = new ListStore<HelpdeskDaily>(
				new ModelKeyProvider<HelpdeskDaily>() {
					@Override
					public String getKey(HelpdeskDaily item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<HelpdeskDaily>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<HelpdeskDaily>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);
		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, HelpdeskDaily, PagingLoadResult<HelpdeskDaily>>(
				store));

		final PagingToolBar toolBar = new PagingToolBar(
				DashboardConstant.PAGE_SIZE);
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolBar.bind(loader);

		final NumberFormat numberFormat = NumberFormat.getFormat(DashboardConstant.INTEGER_FORMAT);
		
		ColumnConfig<HelpdeskDaily, Integer> noColumn = new ColumnConfig<HelpdeskDaily, Integer>(
				props.no(), DashboardConstant.SIZE_NO,"No");
		noColumn.setSortable(false);
		ColumnConfig<HelpdeskDaily, Integer> ticketColumn = new ColumnConfig<HelpdeskDaily, Integer>(
				props.ticketId(), 50, "Ticket ID");
		ColumnConfig<HelpdeskDaily, String> wismaColumn = new ColumnConfig<HelpdeskDaily, String>(
				props.wisma(), 50, "Wisma");
		ColumnConfig<HelpdeskDaily, String> wismaNameColumn = new ColumnConfig<HelpdeskDaily, String>(
				props.wismaName(), 150, "Wisma Name");
		ColumnConfig<HelpdeskDaily, String> statusColumn = new ColumnConfig<HelpdeskDaily, String>(
				props.status(), 100, "Status");
		ColumnConfig<HelpdeskDaily, String> priorityColumn = new ColumnConfig<HelpdeskDaily, String>(
				props.priority(), 100, "Priority");
		ColumnConfig<HelpdeskDaily, Integer> agingColumn = new ColumnConfig<HelpdeskDaily, Integer>(
				props.aging(), 50, "Aging");
		agingColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		agingColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		ColumnConfig<HelpdeskDaily, String> cat1Column = new ColumnConfig<HelpdeskDaily, String>(
				props.category1(), 70, "Category 1");
		ColumnConfig<HelpdeskDaily, String> cat2Column = new ColumnConfig<HelpdeskDaily, String>(
				props.category2(), 70, "Category 2");
		ColumnConfig<HelpdeskDaily, String> cat3Column = new ColumnConfig<HelpdeskDaily, String>(
				props.category3(), 70, "Category 3");
		ColumnConfig<HelpdeskDaily, String> cat4Column = new ColumnConfig<HelpdeskDaily, String>(
				props.category4(), 70, "Category 4");
		
		List<ColumnConfig<HelpdeskDaily, ?>> l = new ArrayList<ColumnConfig<HelpdeskDaily, ?>>();
		l.add(noColumn);
		l.add(ticketColumn);
		l.add(wismaColumn);
		l.add(wismaNameColumn);
		l.add(statusColumn);
		l.add(priorityColumn);
		l.add(agingColumn);
		l.add(cat1Column);
		l.add(cat2Column);
		l.add(cat3Column);
		l.add(cat4Column);
		
		ColumnModel<HelpdeskDaily> cm = new ColumnModel<HelpdeskDaily>(l);

		Grid<HelpdeskDaily> grid = new Grid<HelpdeskDaily>(store, cm) {
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

		StringFilter<HelpdeskDaily> wismaFilter = new StringFilter<HelpdeskDaily>(
				props.wisma());
		StringFilter<HelpdeskDaily> wismaNameFilter = new StringFilter<HelpdeskDaily>(
				props.wismaName());
		NumericFilter<HelpdeskDaily, Integer> agingFilter = new NumericFilter<HelpdeskDaily, Integer>(
				props.aging(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<HelpdeskDaily, Integer> ticketFilter = new NumericFilter<HelpdeskDaily, Integer>(
				props.ticketId(),
				new NumberPropertyEditor.IntegerPropertyEditor());

		GridFilters<HelpdeskDaily> filters = new GridFilters<HelpdeskDaily>(loader);

		filters.initPlugin(grid);
		filters.addFilter(ticketFilter);
		filters.addFilter(wismaFilter);
		filters.addFilter(wismaNameFilter);
		filters.addFilter(agingFilter);

		con.add(grid, new VerticalLayoutData(1,  DashboardConstant.getHeightGrid(Window.getClientHeight(), false)));
		con.add(toolBar, new VerticalLayoutData(1, -1));
		
		//setWidget(con);
		
		return super.asWidget();
	}
}