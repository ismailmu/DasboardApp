package com.btpns.Dashboard.client.window.wisma;

import java.util.ArrayList;
import java.util.List;

import com.btpns.Dashboard.client.model.wisma.Wisma;
import com.btpns.Dashboard.client.model.wisma.WismaEmployee;
import com.btpns.Dashboard.client.service.wisma.WismaService;
import com.btpns.Dashboard.client.service.wisma.WismaServiceAsync;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer.BoxLayoutPack;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

public class WismaEmployeeWindow extends Window {
	
	private Wisma wisma;
	interface WismaEmployeeProperties extends PropertyAccess<WismaEmployee> {
		ModelKeyProvider<WismaEmployee> id();

		ValueProvider<WismaEmployee, Integer> no();

		ValueProvider<WismaEmployee, String> employeeName();

		ValueProvider<WismaEmployee, String> email();

		ValueProvider<WismaEmployee, String> telephone();

		ValueProvider<WismaEmployee, String> hp();
		
		ValueProvider<WismaEmployee, String> position();
	}
	
	public WismaEmployeeWindow(final Wisma wisma) {
		this.setWisma(wisma);
		
		setHeadingText("Detail Employee Wisma " + wisma.getWisma());
		setBodyBorder(true);
		setBorders(true);
		setSize("600px", "400px");
		setBlinkModal(true);
		setDraggable(true);
		setMaximizable(true);
		setOnEsc(true);
		setButtonAlign(BoxLayoutPack.CENTER);
		
		final WismaServiceAsync service = GWT.create(WismaService.class);
		
		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<WismaEmployee>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<WismaEmployee>>() {

			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<WismaEmployee>> callback) {
				service.getWismaEmployee(loadConfig, wisma, callback);
			}
		};

		WismaEmployeeProperties props = GWT.create(WismaEmployeeProperties.class);

		final ListStore<WismaEmployee> store = new ListStore<WismaEmployee>(
				new ModelKeyProvider<WismaEmployee>() {
					@Override
					public String getKey(WismaEmployee item) {
						return "" + item.getId();
					}
				});
		
		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<WismaEmployee>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<WismaEmployee>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);
		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, WismaEmployee, PagingLoadResult<WismaEmployee>>(
				store));

		final PagingToolBar toolBar = new PagingToolBar(
				DashboardConstant.PAGE_SIZE);
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolBar.bind(loader);

		ColumnConfig<WismaEmployee, Integer> noColumn = new ColumnConfig<WismaEmployee, Integer>(
				props.no(), DashboardConstant.SIZE_NO, "No");
		noColumn.setSortable(false);
		ColumnConfig<WismaEmployee, String> employeeColumn = new ColumnConfig<WismaEmployee, String>(
				props.employeeName(), 150, "Employee Name");
		ColumnConfig<WismaEmployee, String> emailColumn = new ColumnConfig<WismaEmployee, String>(
				props.email(), 50, "Email");
		ColumnConfig<WismaEmployee, String> tlpColumn = new ColumnConfig<WismaEmployee, String>(
				props.telephone(), 50, "Telephone");
		ColumnConfig<WismaEmployee, String> hpColumn = new ColumnConfig<WismaEmployee, String>(
				props.hp(), 50, "Hp");
		ColumnConfig<WismaEmployee, String> positionColumn = new ColumnConfig<WismaEmployee, String>(
				props.position(), 100, "Position");
		
		List<ColumnConfig<WismaEmployee, ?>> l = new ArrayList<ColumnConfig<WismaEmployee, ?>>();
		l.add(noColumn);
		l.add(employeeColumn);
		l.add(emailColumn);
		l.add(tlpColumn);
		l.add(hpColumn);
		l.add(positionColumn);
		
		ColumnModel<WismaEmployee> cm = new ColumnModel<WismaEmployee>(l);

		Grid<WismaEmployee> grid = new Grid<WismaEmployee>(store, cm) {
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

		StringFilter<WismaEmployee> nameFilter = new StringFilter<WismaEmployee>(props.employeeName());
		StringFilter<WismaEmployee> emailFilter = new StringFilter<WismaEmployee>(props.email());
		StringFilter<WismaEmployee> positionFilter = new StringFilter<WismaEmployee>(
				props.position());

		GridFilters<WismaEmployee> filters = new GridFilters<WismaEmployee>(loader);

		filters.initPlugin(grid);
		filters.addFilter(nameFilter);
		filters.addFilter(emailFilter);
		filters.addFilter(positionFilter);

		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.setBorders(true);
		con.add(grid, new VerticalLayoutData(1, 1));
		con.add(toolBar, new VerticalLayoutData(1, -1));
		
		add(con);
	}

	public Wisma getWisma() {
		return wisma;
	}

	public void setWisma(Wisma wisma) {
		this.wisma = wisma;
	}
}