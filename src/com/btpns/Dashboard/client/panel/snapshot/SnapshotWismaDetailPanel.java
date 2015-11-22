package com.btpns.Dashboard.client.panel.snapshot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.snapshot.SnapshotWismaDetail;
import com.btpns.Dashboard.client.panel.ExportContextMenu;
import com.btpns.Dashboard.client.service.snapshot.SnapshotService;
import com.btpns.Dashboard.client.service.snapshot.SnapshotServiceAsync;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.i18n.shared.DateTimeFormat;
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

public class SnapshotWismaDetailPanel extends SnapshotWismaPanel {
	
	public SnapshotWismaDetailPanel(String title, ImageResource icon,
			Date dateFilter) {
		super(title, icon, dateFilter);
	}

	interface SnapshotWismaDetailProperties extends PropertyAccess<SnapshotWismaDetail> {
		ModelKeyProvider<SnapshotWismaDetail> id();

		ValueProvider<SnapshotWismaDetail, Integer> no();
		
		ValueProvider<SnapshotWismaDetail, String> kcs();

		ValueProvider<SnapshotWismaDetail, String> kcsName();

		ValueProvider<SnapshotWismaDetail, String> wisma();

		ValueProvider<SnapshotWismaDetail, String> wismaName();

		ValueProvider<SnapshotWismaDetail, Integer> totalCustomer();

		ValueProvider<SnapshotWismaDetail, Integer> totalGroup();
		
		ValueProvider<SnapshotWismaDetail, Integer> totalSentra();
		
		ValueProvider<SnapshotWismaDetail, Integer> totalSaving();
		
		ValueProvider<SnapshotWismaDetail, BigDecimal> amountSaving();
		
		ValueProvider<SnapshotWismaDetail, Integer> totalLoan();
		
		ValueProvider<SnapshotWismaDetail, BigDecimal> amountOs();
		
		ValueProvider<SnapshotWismaDetail, BigDecimal> amountDisburse();
	}

	@Override
	public Widget asWidget() {

		final SnapshotServiceAsync service = GWT.create(SnapshotService.class);

		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<SnapshotWismaDetail>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<SnapshotWismaDetail>>() {

			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<SnapshotWismaDetail>> callback) {
				service.getSnapshotWismaDetail(loadConfig, dateFilter, callback);
			}
		};

		SnapshotWismaDetailProperties props = GWT.create(SnapshotWismaDetailProperties.class);

		ListStore<SnapshotWismaDetail> store = new ListStore<SnapshotWismaDetail>(
				new ModelKeyProvider<SnapshotWismaDetail>() {
					@Override
					public String getKey(SnapshotWismaDetail item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<SnapshotWismaDetail>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<SnapshotWismaDetail>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);
		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, SnapshotWismaDetail, PagingLoadResult<SnapshotWismaDetail>>(
				store));

		final PagingToolBar toolBar = new PagingToolBar(
				DashboardConstant.PAGE_SIZE);
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolBar.bind(loader);

		final NumberFormat numberFormat = NumberFormat
				.getFormat(DashboardConstant.INTEGER_FORMAT);

		ColumnConfig<SnapshotWismaDetail, Integer> noColumn = new ColumnConfig<SnapshotWismaDetail, Integer>(
				props.no(), DashboardConstant.SIZE_NO, "No");
		noColumn.setSortable(false);
		ColumnConfig<SnapshotWismaDetail, String> wismaColumn = new ColumnConfig<SnapshotWismaDetail, String>(
				props.wisma(), 50, "Wisma");
		ColumnConfig<SnapshotWismaDetail, String> wismaNameColumn = new ColumnConfig<SnapshotWismaDetail, String>(
				props.wismaName(), 120, "Wisma Name");
		ColumnConfig<SnapshotWismaDetail, String> kcsColumn = new ColumnConfig<SnapshotWismaDetail, String>(
				props.kcs(), 30, "Kcs");
//		ColumnConfig<SnapshotWismaDetail, String> kcsNameColumn = new ColumnConfig<SnapshotWismaDetail, String>(
//				props.kcsName(), 120, "Kcs Name");
		ColumnConfig<SnapshotWismaDetail, Integer> customerColumn = new ColumnConfig<SnapshotWismaDetail, Integer>(
				props.totalCustomer(), 70, "Total Customer");
		customerColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}

		});

		customerColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<SnapshotWismaDetail, Integer> groupColumn = new ColumnConfig<SnapshotWismaDetail, Integer>(
				props.totalGroup(), 60, "Total Group");
		groupColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}

		});

		groupColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotWismaDetail, Integer> sentraColumn = new ColumnConfig<SnapshotWismaDetail, Integer>(
				props.totalSentra(), 60, "Total Sentra");
		sentraColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		sentraColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<SnapshotWismaDetail, Integer> savingColumn = new ColumnConfig<SnapshotWismaDetail, Integer>(
				props.totalSaving(), 60, "Total Saving");
		savingColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		savingColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<SnapshotWismaDetail, BigDecimal> amountSavingColumn = new ColumnConfig<SnapshotWismaDetail, BigDecimal>(
				props.amountSaving(), 100, "Amount Saving");
		amountSavingColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		amountSavingColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<SnapshotWismaDetail, Integer> loanColumn = new ColumnConfig<SnapshotWismaDetail, Integer>(
				props.totalLoan(), 60, "Total Loan");
		loanColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		loanColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<SnapshotWismaDetail, BigDecimal> amountOsColumn = new ColumnConfig<SnapshotWismaDetail, BigDecimal>(
				props.amountOs(), 100, "Amount Os");
		amountOsColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		amountOsColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<SnapshotWismaDetail, BigDecimal> amountDisburseColumn = new ColumnConfig<SnapshotWismaDetail, BigDecimal>(
				props.amountDisburse(), 100, "Amount Disburse");
		amountDisburseColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		amountDisburseColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		List<ColumnConfig<SnapshotWismaDetail, ?>> l = new ArrayList<ColumnConfig<SnapshotWismaDetail, ?>>();
		l.add(noColumn);
		l.add(kcsColumn);
		//l.add(kcsNameColumn);
		l.add(wismaColumn);
		l.add(wismaNameColumn);
		l.add(customerColumn);
		l.add(groupColumn);
		l.add(sentraColumn);
		l.add(savingColumn);
		l.add(amountSavingColumn);
		l.add(loanColumn);
		l.add(amountOsColumn);
		l.add(amountDisburseColumn);
		
		ColumnModel<SnapshotWismaDetail> cm = new ColumnModel<SnapshotWismaDetail>(l);

		Grid<SnapshotWismaDetail> grid = new Grid<SnapshotWismaDetail>(store, cm) {
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

		StringFilter<SnapshotWismaDetail> kcsFilter = new StringFilter<SnapshotWismaDetail>(
				props.kcs());
//		StringFilter<SnapshotWismaDetail> kcsNameFilter = new StringFilter<SnapshotWismaDetail>(
//				props.kcsName());
		StringFilter<SnapshotWismaDetail> wismaFilter = new StringFilter<SnapshotWismaDetail>(
				props.wisma());
		StringFilter<SnapshotWismaDetail> wismaNameFilter = new StringFilter<SnapshotWismaDetail>(
				props.wismaName());
		NumericFilter<SnapshotWismaDetail, Integer> customerFilter = new NumericFilter<SnapshotWismaDetail, Integer>(
				props.totalCustomer(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotWismaDetail, Integer> groupFilter = new NumericFilter<SnapshotWismaDetail, Integer>(
				props.totalGroup(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotWismaDetail, Integer> savingFilter = new NumericFilter<SnapshotWismaDetail, Integer>(
				props.totalSaving(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotWismaDetail, BigDecimal> amountSavingFilter = new NumericFilter<SnapshotWismaDetail, BigDecimal>(
				props.amountSaving(),
				new NumberPropertyEditor.BigDecimalPropertyEditor());
		NumericFilter<SnapshotWismaDetail, Integer> loanFilter = new NumericFilter<SnapshotWismaDetail, Integer>(
				props.totalLoan(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotWismaDetail, BigDecimal> amountOsFilter = new NumericFilter<SnapshotWismaDetail, BigDecimal>(
				props.amountOs(),
				new NumberPropertyEditor.BigDecimalPropertyEditor());
		NumericFilter<SnapshotWismaDetail, BigDecimal> amountDisburseFilter = new NumericFilter<SnapshotWismaDetail, BigDecimal>(
				props.amountDisburse(),
				new NumberPropertyEditor.BigDecimalPropertyEditor());

		GridFilters<SnapshotWismaDetail> filters = new GridFilters<SnapshotWismaDetail>(loader);

		filters.initPlugin(grid);
		filters.addFilter(kcsFilter);
		//filters.addFilter(kcsNameFilter);
		filters.addFilter(wismaFilter);
		filters.addFilter(wismaNameFilter);
		filters.addFilter(customerFilter);
		filters.addFilter(groupFilter);
		filters.addFilter(savingFilter);
		filters.addFilter(amountSavingFilter);
		filters.addFilter(loanFilter);
		filters.addFilter(amountOsFilter);
		filters.addFilter(amountDisburseFilter);

		grid.setContextMenu(new ExportContextMenu(DateTimeFormat.getFormat(
				DashboardConstant.DATE_FORMAT).format(dateFilter) + "_rpt_portfolio.xls"));
		
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.add(grid, new VerticalLayoutData(1, DashboardConstant.getHeightGrid(Window.getClientHeight(), true)));
		con.add(toolBar, new VerticalLayoutData(1, -1));
		
		return con;
	}
}