package com.btpns.Dashboard.client.panel.eod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.eod.EodDailyDetail;
import com.btpns.Dashboard.client.service.eod.EodService;
import com.btpns.Dashboard.client.service.eod.EodServiceAsync;
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
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.NumberPropertyEditor;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.NumericFilter;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

public class EodDailyDetailPanel extends EodDailyPanel {
	
	public EodDailyDetailPanel(String title, ImageResource icon, Date dateFilter) {
		super(title, icon, dateFilter);
	}

	interface EodDailyProperties extends PropertyAccess<EodDailyDetail> {
		ModelKeyProvider<EodDailyDetail> id();

		ValueProvider<EodDailyDetail, Integer> no();
		
		ValueProvider<EodDailyDetail, String> wisma();

		ValueProvider<EodDailyDetail, String> wismaName();

		ValueProvider<EodDailyDetail, BigDecimal> sumTrans();

		ValueProvider<EodDailyDetail, Integer> countFile();
	}

	@Override
	public Widget asWidget() {
		final EodServiceAsync service = GWT.create(EodService.class);

		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<EodDailyDetail>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<EodDailyDetail>>() {

			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<EodDailyDetail>> callback) {
				service.getEodDailyDetail(loadConfig, dateFilter, callback);
			}
		};

		EodDailyProperties props = GWT.create(EodDailyProperties.class);

		ListStore<EodDailyDetail> store = new ListStore<EodDailyDetail>(
				new ModelKeyProvider<EodDailyDetail>() {
					@Override
					public String getKey(EodDailyDetail item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<EodDailyDetail>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<EodDailyDetail>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);
		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, EodDailyDetail, PagingLoadResult<EodDailyDetail>>(
				store));

		final PagingToolBar toolBar = new PagingToolBar(
				DashboardConstant.PAGE_SIZE);
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolBar.bind(loader);

		final NumberFormat numberFormat = NumberFormat.getFormat(DashboardConstant.INTEGER_FORMAT);
		
		ColumnConfig<EodDailyDetail, Integer> noColumn = new ColumnConfig<EodDailyDetail, Integer>(
				props.no(), DashboardConstant.SIZE_NO,"No");
		noColumn.setSortable(false);
		ColumnConfig<EodDailyDetail, String> wismaColumn = new ColumnConfig<EodDailyDetail, String>(
				props.wisma(), 50, "Wisma");
		ColumnConfig<EodDailyDetail, String> wismaNameColumn = new ColumnConfig<EodDailyDetail, String>(
				props.wismaName(), 150, "Wisma Name");
		ColumnConfig<EodDailyDetail, Integer> countColumn = new ColumnConfig<EodDailyDetail, Integer>(
				props.countFile(), 150, "Count File");
		countColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}

		});

		countColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<EodDailyDetail, BigDecimal> sumColumn = new ColumnConfig<EodDailyDetail, BigDecimal>(
				props.sumTrans(), 150, "Amount Transaction");
		sumColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		sumColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		List<ColumnConfig<EodDailyDetail, ?>> l = new ArrayList<ColumnConfig<EodDailyDetail, ?>>();
		l.add(noColumn);
		l.add(wismaColumn);
		l.add(wismaNameColumn);
		l.add(countColumn);
		l.add(sumColumn);

		ColumnModel<EodDailyDetail> cm = new ColumnModel<EodDailyDetail>(l);

		Grid<EodDailyDetail> grid = new Grid<EodDailyDetail>(store, cm) {
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

		StringFilter<EodDailyDetail> wismaFilter = new StringFilter<EodDailyDetail>(
				props.wisma());
		StringFilter<EodDailyDetail> wismaNameFilter = new StringFilter<EodDailyDetail>(
				props.wismaName());
		NumericFilter<EodDailyDetail, Integer> countFileFilter = new NumericFilter<EodDailyDetail, Integer>(
				props.countFile(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<EodDailyDetail, BigDecimal> sumTransFilter = new NumericFilter<EodDailyDetail, BigDecimal>(
				props.sumTrans(),
				new NumberPropertyEditor.BigDecimalPropertyEditor());

		GridFilters<EodDailyDetail> filters = new GridFilters<EodDailyDetail>(loader);

		filters.initPlugin(grid);
		filters.addFilter(wismaFilter);
		filters.addFilter(wismaNameFilter);
		filters.addFilter(countFileFilter);
		filters.addFilter(sumTransFilter);

		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.add(grid, new VerticalLayoutData(1,  DashboardConstant.getHeightGrid(Window.getClientHeight(),true)));
		con.add(toolBar, new VerticalLayoutData(1, -1));
		
		//con.setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		return con;
	}
	
}