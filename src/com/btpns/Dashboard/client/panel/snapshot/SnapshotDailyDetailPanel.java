package com.btpns.Dashboard.client.panel.snapshot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.snapshot.SnapshotDailyDetail;
import com.btpns.Dashboard.client.service.snapshot.SnapshotService;
import com.btpns.Dashboard.client.service.snapshot.SnapshotServiceAsync;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.safehtml.shared.SafeHtml;
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

public class SnapshotDailyDetailPanel extends SnapshotDailyPanel {
	
	public SnapshotDailyDetailPanel(String title, ImageResource icon,
			Date dateFilter) {
		super(title, icon, dateFilter);
	}

	interface SnapshotDailyDetailProperties extends PropertyAccess<SnapshotDailyDetail> {
		ModelKeyProvider<SnapshotDailyDetail> id();

		ValueProvider<SnapshotDailyDetail, Integer> no();
		
		ValueProvider<SnapshotDailyDetail, String> kcs();

		ValueProvider<SnapshotDailyDetail, String> kcsName();

		ValueProvider<SnapshotDailyDetail, String> wisma();

		ValueProvider<SnapshotDailyDetail, String> wismaName();

		ValueProvider<SnapshotDailyDetail, Integer> countNewSaving();

		ValueProvider<SnapshotDailyDetail, Integer> countDeposit();
		
		ValueProvider<SnapshotDailyDetail, BigDecimal> sumDeposit();
		
		ValueProvider<SnapshotDailyDetail, Integer> countWithdraw();
		
		ValueProvider<SnapshotDailyDetail, BigDecimal> sumWithdraw();
		
		ValueProvider<SnapshotDailyDetail, Integer> countNewLoan();
		
		ValueProvider<SnapshotDailyDetail, Integer> countDisburse();
		
		ValueProvider<SnapshotDailyDetail, BigDecimal> sumDisburse();
		
		ValueProvider<SnapshotDailyDetail, Integer> countRepayment();
		
		ValueProvider<SnapshotDailyDetail, BigDecimal> sumRepayment();
		
		ValueProvider<SnapshotDailyDetail, Integer> countEarlyPayment();
		
		ValueProvider<SnapshotDailyDetail, BigDecimal> sumEarlyPayment();
		
		ValueProvider<SnapshotDailyDetail, Integer> countCc();
		
		ValueProvider<SnapshotDailyDetail, Integer> countCoverIn();
		
		ValueProvider<SnapshotDailyDetail, BigDecimal> sumCoverIn();
		
		ValueProvider<SnapshotDailyDetail, Integer> countCoverOut();
		
		ValueProvider<SnapshotDailyDetail, BigDecimal> sumCoverOut();
	}

	@Override
	public Widget asWidget() {

		final SnapshotServiceAsync service = GWT.create(SnapshotService.class);

		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<SnapshotDailyDetail>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<SnapshotDailyDetail>>() {

			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<SnapshotDailyDetail>> callback) {
				service.getSnapshotDailyDetail(loadConfig, dateFilter, callback);
			}
		};

		SnapshotDailyDetailProperties props = GWT.create(SnapshotDailyDetailProperties.class);

		ListStore<SnapshotDailyDetail> store = new ListStore<SnapshotDailyDetail>(
				new ModelKeyProvider<SnapshotDailyDetail>() {
					@Override
					public String getKey(SnapshotDailyDetail item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<SnapshotDailyDetail>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<SnapshotDailyDetail>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);
		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, SnapshotDailyDetail, PagingLoadResult<SnapshotDailyDetail>>(
				store));

		final PagingToolBar toolBar = new PagingToolBar(
				DashboardConstant.PAGE_SIZE);
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolBar.bind(loader);

		final NumberFormat numberFormat = NumberFormat
				.getFormat(DashboardConstant.INTEGER_FORMAT);

		ColumnConfig<SnapshotDailyDetail, Integer> noColumn = new ColumnConfig<SnapshotDailyDetail, Integer>(
				props.no(), DashboardConstant.SIZE_NO, "No");
		noColumn.setSortable(false);
		ColumnConfig<SnapshotDailyDetail, String> wismaColumn = new ColumnConfig<SnapshotDailyDetail, String>(
				props.wisma(), 30, "Wisma");
		ColumnConfig<SnapshotDailyDetail, String> wismaNameColumn = new ColumnConfig<SnapshotDailyDetail, String>(
				props.wismaName(), 70, "Wisma Name");
		ColumnConfig<SnapshotDailyDetail, String> kcsColumn = new ColumnConfig<SnapshotDailyDetail, String>(
				props.kcs(), 30, "Kcs");
//		ColumnConfig<SnapshotDailyDetail, String> kcsNameColumn = new ColumnConfig<SnapshotDailyDetail, String>(
//				props.kcsName(), 120, "Kcs Name");
		ColumnConfig<SnapshotDailyDetail, Integer> newSavingColumn = new ColumnConfig<SnapshotDailyDetail, Integer>(
				props.countNewSaving(), 40);
		newSavingColumn.setSortable(false);
		newSavingColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "New<br/>Saving";
			}
		});
		newSavingColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}

		});

		newSavingColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, Integer> countDepositColumn = new ColumnConfig<SnapshotDailyDetail, Integer>(
				props.countDeposit(), 40);
		countDepositColumn.setSortable(false);
		countDepositColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Trx<br/>Deposit";
			}
		});
		countDepositColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}

		});

		countDepositColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, BigDecimal> sumDepositColumn = new ColumnConfig<SnapshotDailyDetail, BigDecimal>(
				props.sumDeposit(), 50);
		sumDepositColumn.setSortable(false);
		sumDepositColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Total<br/>Deposit";
			}
		});
		sumDepositColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		sumDepositColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, Integer> countWithdrawColumn = new ColumnConfig<SnapshotDailyDetail, Integer>(
				props.countWithdraw(), 50);
		countWithdrawColumn.setSortable(false);
		countWithdrawColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Trx<br/>Withdraw";
			}
		});
		countWithdrawColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		countWithdrawColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, BigDecimal> sumWithdrawColumn = new ColumnConfig<SnapshotDailyDetail, BigDecimal>(
				props.sumWithdraw(), 50);
		sumWithdrawColumn.setSortable(false);
		sumWithdrawColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Total<br/>Withdraw";
			}
		});
		sumWithdrawColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		sumWithdrawColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, Integer> newLoanColumn = new ColumnConfig<SnapshotDailyDetail, Integer>(
				props.countNewLoan(), 40);
		newLoanColumn.setSortable(false);
		newLoanColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "New<br/>Loan";
			}
		});
		newLoanColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		newLoanColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, Integer> countDisburseColumn = new ColumnConfig<SnapshotDailyDetail, Integer>(
				props.countDisburse(), 40);
		countDisburseColumn.setSortable(false);
		countDisburseColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Trx<br/>Disburse";
			}
		});
		countDisburseColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		countDisburseColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, BigDecimal> sumDisburseColumn = new ColumnConfig<SnapshotDailyDetail, BigDecimal>(
				props.sumDisburse(), 40);
		sumDisburseColumn.setSortable(false);
		sumDisburseColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Total<br/>Disburse";
			}
		});
		sumDisburseColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		sumDisburseColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, Integer> countRepaymentColumn = new ColumnConfig<SnapshotDailyDetail, Integer>(
				props.countRepayment(),50);
		countRepaymentColumn.setSortable(false);
		countRepaymentColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Trx<br/>Re<br/>payment";
			}
		});
		countRepaymentColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		countRepaymentColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, BigDecimal> sumRepaymentColumn = new ColumnConfig<SnapshotDailyDetail, BigDecimal>(
				props.sumRepayment(), 50);
		sumRepaymentColumn.setSortable(false);
		sumRepaymentColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Total<br/>Re<br/>payment";
			}
		});
		sumRepaymentColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		sumRepaymentColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, Integer> countEarlyPaymentColumn = new ColumnConfig<SnapshotDailyDetail, Integer>(
				props.countEarlyPayment(), 50);
		countEarlyPaymentColumn.setSortable(false);
		countEarlyPaymentColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Trx<br/>Early<br/>Payment";
			}
		});
		countEarlyPaymentColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		countEarlyPaymentColumn
				.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, BigDecimal> sumEarlyPaymentColumn = new ColumnConfig<SnapshotDailyDetail, BigDecimal>(
				props.sumEarlyPayment(), 50);
		sumEarlyPaymentColumn.setSortable(false);
		sumEarlyPaymentColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Total<br/>Early<br/>Payment";
			}
		});
		sumEarlyPaymentColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		
		sumEarlyPaymentColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, Integer> countCcColumn = new ColumnConfig<SnapshotDailyDetail, Integer>(
				props.countCc(), 40);
		countCcColumn.setSortable(false);
		countCcColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Cancel<br/>Close";
			}
		});
		countCcColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		countCcColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, Integer> countCoverInColumn = new ColumnConfig<SnapshotDailyDetail, Integer>(
				props.countCoverIn(),40);
		countCoverInColumn.setSortable(false);
		countCoverInColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Trx<br/>Cover<br/>In";
			}
		});
		countCoverInColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		countCoverInColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, BigDecimal> sumCoverInColumn = new ColumnConfig<SnapshotDailyDetail, BigDecimal>(
				props.sumCoverIn(), 40);
		sumCoverInColumn.setSortable(false);
		sumCoverInColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Total<br/>Cover<br/>In";
			}
		});
		sumCoverInColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		sumCoverInColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, Integer> countCoverOutColumn = new ColumnConfig<SnapshotDailyDetail, Integer>(
				props.countCoverOut(), 30);
		countCoverOutColumn.setSortable(false);
		countCoverOutColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Trx<br/>Cover<br/>Out";
			}
		});
		countCoverOutColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		countCoverOutColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailyDetail, BigDecimal> sumCoverOutColumn = new ColumnConfig<SnapshotDailyDetail, BigDecimal>(
				props.sumCoverOut(), 30);
		sumCoverOutColumn.setSortable(false);
		sumCoverOutColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Total<br/>Cover<br/>Out";
			}
		});
		sumCoverOutColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		sumCoverOutColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		List<ColumnConfig<SnapshotDailyDetail, ?>> l = new ArrayList<ColumnConfig<SnapshotDailyDetail, ?>>();
		
		l.add(noColumn);
		l.add(kcsColumn);
		l.add(wismaColumn);
		l.add(wismaNameColumn);
		//l.add(kcsNameColumn);
		
		l.add(newSavingColumn);
		l.add(countDepositColumn);
		l.add(sumDepositColumn);
		l.add(countWithdrawColumn);
		l.add(sumWithdrawColumn);
		
		
		l.add(newLoanColumn);
		l.add(countDisburseColumn);
		l.add(sumDisburseColumn);
		l.add(countRepaymentColumn);
		l.add(sumRepaymentColumn);
		l.add(countEarlyPaymentColumn);
		l.add(sumEarlyPaymentColumn);
		l.add(countCcColumn);
		
		l.add(countCoverInColumn);
		l.add(sumCoverInColumn);
		l.add(countCoverOutColumn);
		l.add(sumCoverOutColumn);
		
		ColumnModel<SnapshotDailyDetail> cm = new ColumnModel<SnapshotDailyDetail>(l);
		
//		cm.addHeaderGroup(0, 4, new HeaderGroupConfig("Saving Transaction", 1, 5));
//		cm.addHeaderGroup(0, 9, new HeaderGroupConfig("Loan Transaction", 1, 8));
//		cm.addHeaderGroup(0, 17, new HeaderGroupConfig("Cover In", 1, 2));
//		cm.addHeaderGroup(0, 19, new HeaderGroupConfig("Cover Out", 1, 2));
		
		Grid<SnapshotDailyDetail> grid = new Grid<SnapshotDailyDetail>(store, cm) {
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

		StringFilter<SnapshotDailyDetail> kcsFilter = new StringFilter<SnapshotDailyDetail>(
				props.kcs());
//		StringFilter<SnapshotDailyDetail> kcsNameFilter = new StringFilter<SnapshotDailyDetail>(
//				props.kcsName());
		StringFilter<SnapshotDailyDetail> wismaFilter = new StringFilter<SnapshotDailyDetail>(
				props.wisma());
		StringFilter<SnapshotDailyDetail> wismaNameFilter = new StringFilter<SnapshotDailyDetail>(
				props.wismaName());
		NumericFilter<SnapshotDailyDetail, Integer> newSavingFilter = new NumericFilter<SnapshotDailyDetail, Integer>(
				props.countNewSaving(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotDailyDetail, Integer> countDepositFilter = new NumericFilter<SnapshotDailyDetail, Integer>(
				props.countDeposit(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotDailyDetail, BigDecimal> sumDepositFilter = new NumericFilter<SnapshotDailyDetail, BigDecimal>(
				props.sumDeposit(),
				new NumberPropertyEditor.BigDecimalPropertyEditor());
		NumericFilter<SnapshotDailyDetail, Integer> countWithdrawFilter = new NumericFilter<SnapshotDailyDetail, Integer>(
				props.countWithdraw(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotDailyDetail, BigDecimal> sumWithdrawFilter = new NumericFilter<SnapshotDailyDetail, BigDecimal>(
				props.sumWithdraw(),
				new NumberPropertyEditor.BigDecimalPropertyEditor());
		NumericFilter<SnapshotDailyDetail, Integer> newLoanFilter = new NumericFilter<SnapshotDailyDetail, Integer>(
				props.countNewLoan(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotDailyDetail, Integer> countDisburseFilter = new NumericFilter<SnapshotDailyDetail, Integer>(
				props.countDisburse(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotDailyDetail, BigDecimal> sumDisburseFilter = new NumericFilter<SnapshotDailyDetail, BigDecimal>(
				props.sumDisburse(),
				new NumberPropertyEditor.BigDecimalPropertyEditor());
		NumericFilter<SnapshotDailyDetail, Integer> countRepaymentFilter = new NumericFilter<SnapshotDailyDetail, Integer>(
				props.countRepayment(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotDailyDetail, BigDecimal> sumRepaymentFilter = new NumericFilter<SnapshotDailyDetail, BigDecimal>(
				props.sumRepayment(),
				new NumberPropertyEditor.BigDecimalPropertyEditor());
		NumericFilter<SnapshotDailyDetail, Integer> countEarlyPaymentFilter = new NumericFilter<SnapshotDailyDetail, Integer>(
				props.countEarlyPayment(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotDailyDetail, BigDecimal> sumEarlyPaymentFilter = new NumericFilter<SnapshotDailyDetail, BigDecimal>(
				props.sumEarlyPayment(),
				new NumberPropertyEditor.BigDecimalPropertyEditor());
		NumericFilter<SnapshotDailyDetail, Integer> countCcFilter = new NumericFilter<SnapshotDailyDetail, Integer>(
				props.countCc(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotDailyDetail, Integer> countCoverInFilter = new NumericFilter<SnapshotDailyDetail, Integer>(
				props.countCoverIn(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotDailyDetail, BigDecimal> sumCoverInFilter = new NumericFilter<SnapshotDailyDetail, BigDecimal>(
				props.sumCoverIn(),
				new NumberPropertyEditor.BigDecimalPropertyEditor());
		NumericFilter<SnapshotDailyDetail, Integer> countCoverOutFilter = new NumericFilter<SnapshotDailyDetail, Integer>(
				props.countCoverOut(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<SnapshotDailyDetail, BigDecimal> sumCoverOutFilter = new NumericFilter<SnapshotDailyDetail, BigDecimal>(
				props.sumCoverOut(),
				new NumberPropertyEditor.BigDecimalPropertyEditor());
		
		GridFilters<SnapshotDailyDetail> filters = new GridFilters<SnapshotDailyDetail>(loader);

		filters.initPlugin(grid);
		filters.addFilter(kcsFilter);
//		filters.addFilter(kcsNameFilter);
		filters.addFilter(wismaFilter);
		filters.addFilter(wismaNameFilter);
		filters.addFilter(newSavingFilter);
		filters.addFilter(countDepositFilter);
		filters.addFilter(sumDepositFilter);
		filters.addFilter(countWithdrawFilter);
		filters.addFilter(sumWithdrawFilter);
		filters.addFilter(newLoanFilter);
		filters.addFilter(countDisburseFilter);
		filters.addFilter(sumDisburseFilter);
		filters.addFilter(countRepaymentFilter);
		filters.addFilter(sumRepaymentFilter);
		filters.addFilter(countEarlyPaymentFilter);
		filters.addFilter(sumEarlyPaymentFilter);
		filters.addFilter(countCcFilter);
		filters.addFilter(countCoverInFilter);
		filters.addFilter(sumCoverInFilter);
		filters.addFilter(countCoverOutFilter);
		filters.addFilter(sumCoverOutFilter);
		
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.add(grid, new VerticalLayoutData(1, DashboardConstant.getHeightGrid(Window.getClientHeight(), true)));
		con.add(toolBar, new VerticalLayoutData(1, -1));
		
		return con;
	}
}