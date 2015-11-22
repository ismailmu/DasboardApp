package com.btpns.Dashboard.client.panel.snapshot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.snapshot.SnapshotDailySummary;
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
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class SnapshotDailySummaryPanel extends SnapshotDailyPanel {

	public SnapshotDailySummaryPanel(String title, ImageResource icon,
			Date dateFilter) {
		super(title, icon, dateFilter);
	}

	interface SnapshotDailySummaryProperties extends
			PropertyAccess<SnapshotDailySummary> {
		ModelKeyProvider<SnapshotDailySummary> id();

		ValueProvider<SnapshotDailySummary, Integer> totalWisma();

		ValueProvider<SnapshotDailySummary, Integer> totalCountNewSaving();

		ValueProvider<SnapshotDailySummary, Integer> totalCountDeposit();

		ValueProvider<SnapshotDailySummary, BigDecimal> totalSumDeposit();

		ValueProvider<SnapshotDailySummary, Integer> totalCountWithdraw();

		ValueProvider<SnapshotDailySummary, BigDecimal> totalSumWithdraw();

		ValueProvider<SnapshotDailySummary, Integer> totalCountNewLoan();

		ValueProvider<SnapshotDailySummary, Integer> totalCountDisburse();

		ValueProvider<SnapshotDailySummary, BigDecimal> totalSumDisburse();

		ValueProvider<SnapshotDailySummary, Integer> totalCountRepayment();

		ValueProvider<SnapshotDailySummary, BigDecimal> totalSumRepayment();

		ValueProvider<SnapshotDailySummary, Integer> totalCountEarlyPayment();

		ValueProvider<SnapshotDailySummary, BigDecimal> totalSumEarlyPayment();

		ValueProvider<SnapshotDailySummary, Integer> totalCountCc();

		ValueProvider<SnapshotDailySummary, Integer> totalCountCoverIn();

		ValueProvider<SnapshotDailySummary, BigDecimal> totalSumCoverIn();

		ValueProvider<SnapshotDailySummary, Integer> totalCountCoverOut();

		ValueProvider<SnapshotDailySummary, BigDecimal> totalSumCoverOut();
	}

	@Override
	public Widget asWidget() {

		final SnapshotServiceAsync service = GWT.create(SnapshotService.class);

		RpcProxy<PagingLoadConfig, PagingLoadResult<SnapshotDailySummary>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<SnapshotDailySummary>>() {

			@Override
			public void load(
					PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<SnapshotDailySummary>> callback) {
				service.getSnapshotDailySummary(loadConfig, dateFilter, callback);
				
			}
		};

		SnapshotDailySummaryProperties props = GWT
				.create(SnapshotDailySummaryProperties.class);

		ListStore<SnapshotDailySummary> store = new ListStore<SnapshotDailySummary>(
				new ModelKeyProvider<SnapshotDailySummary>() {
					@Override
					public String getKey(SnapshotDailySummary item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<PagingLoadConfig, PagingLoadResult<SnapshotDailySummary>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<SnapshotDailySummary>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(false);
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, SnapshotDailySummary, PagingLoadResult<SnapshotDailySummary>>(
				store));

		final NumberFormat numberFormat = NumberFormat
				.getFormat(DashboardConstant.INTEGER_FORMAT);

		ColumnConfig<SnapshotDailySummary, Integer> wismaColumn = new ColumnConfig<SnapshotDailySummary, Integer>(
				props.totalWisma(), 40);
		wismaColumn.setSortable(false);
		wismaColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Total<br/>Wisma";
			}
		});
		wismaColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}

		});

		wismaColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotDailySummary, Integer> newSavingColumn = new ColumnConfig<SnapshotDailySummary, Integer>(
				props.totalCountNewSaving(), 40);
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

		ColumnConfig<SnapshotDailySummary, Integer> countDepositColumn = new ColumnConfig<SnapshotDailySummary, Integer>(
				props.totalCountDeposit(), 50);
		countDepositColumn.setSortable(false);
		countDepositColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Trx<br/>Deposito";
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

		ColumnConfig<SnapshotDailySummary, BigDecimal> sumDepositColumn = new ColumnConfig<SnapshotDailySummary, BigDecimal>(
				props.totalSumDeposit(), 50);
		sumDepositColumn.setSortable(false);
		sumDepositColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Total<br/>Deposito";
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

		ColumnConfig<SnapshotDailySummary, Integer> countWithdrawColumn = new ColumnConfig<SnapshotDailySummary, Integer>(
				props.totalCountWithdraw(), 50);
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

		ColumnConfig<SnapshotDailySummary, BigDecimal> sumWithdrawColumn = new ColumnConfig<SnapshotDailySummary, BigDecimal>(
				props.totalSumWithdraw(), 50);
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

		ColumnConfig<SnapshotDailySummary, Integer> newLoanColumn = new ColumnConfig<SnapshotDailySummary, Integer>(
				props.totalCountNewLoan(), 30);
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

		ColumnConfig<SnapshotDailySummary, Integer> countDisburseColumn = new ColumnConfig<SnapshotDailySummary, Integer>(
				props.totalCountDisburse(), 50);
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

		ColumnConfig<SnapshotDailySummary, BigDecimal> sumDisburseColumn = new ColumnConfig<SnapshotDailySummary, BigDecimal>(
				props.totalSumDisburse(), 50);
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

		ColumnConfig<SnapshotDailySummary, Integer> countRepaymentColumn = new ColumnConfig<SnapshotDailySummary, Integer>(
				props.totalCountRepayment(), 50);
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

		ColumnConfig<SnapshotDailySummary, BigDecimal> sumRepaymentColumn = new ColumnConfig<SnapshotDailySummary, BigDecimal>(
				props.totalSumRepayment(), 50);
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

		ColumnConfig<SnapshotDailySummary, Integer> countEarlyPaymentColumn = new ColumnConfig<SnapshotDailySummary, Integer>(
				props.totalCountEarlyPayment(), 50);
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

		ColumnConfig<SnapshotDailySummary, BigDecimal> sumEarlyPaymentColumn = new ColumnConfig<SnapshotDailySummary, BigDecimal>(
				props.totalSumEarlyPayment(), 50);
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

		ColumnConfig<SnapshotDailySummary, Integer> countCcColumn = new ColumnConfig<SnapshotDailySummary, Integer>(
				props.totalCountCc(), 40);
		countCcColumn.setSortable(false);
		countCcColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Cancel /<br/>Close";
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

		ColumnConfig<SnapshotDailySummary, Integer> countCoverInColumn = new ColumnConfig<SnapshotDailySummary, Integer>(
				props.totalCountCoverIn(), 40);
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

		ColumnConfig<SnapshotDailySummary, BigDecimal> sumCoverInColumn = new ColumnConfig<SnapshotDailySummary, BigDecimal>(
				props.totalSumCoverIn(), 40);
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

		ColumnConfig<SnapshotDailySummary, Integer> countCoverOutColumn = new ColumnConfig<SnapshotDailySummary, Integer>(
				props.totalCountCoverOut(),40);
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

		ColumnConfig<SnapshotDailySummary, BigDecimal> sumCoverOutColumn = new ColumnConfig<SnapshotDailySummary, BigDecimal>(
				props.totalSumCoverOut(), 40);
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

		List<ColumnConfig<SnapshotDailySummary, ?>> l = new ArrayList<ColumnConfig<SnapshotDailySummary, ?>>();
		l.add(wismaColumn);
		
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

		ColumnModel<SnapshotDailySummary> cm = new ColumnModel<SnapshotDailySummary>(l);
		
//		cm.addHeaderGroup(0, 1, new HeaderGroupConfig("Saving Transaction", 1, 5));
		//cm.addHeaderGroup(1, 1, new HeaderGroupConfig("New Acct", 2, 1));
//		cm.addHeaderGroup(0, 2, new HeaderGroupConfig("Deposit", 1, 2));
//		cm.addHeaderGroup(0, 4, new HeaderGroupConfig("Withdraw", 1, 2));
		
//		cm.addHeaderGroup(0, 6, new HeaderGroupConfig("Loan Transaction", 1, 8));
		//cm.addHeaderGroup(1, 6, new HeaderGroupConfig("New Acct", 2, 1));
//		cm.addHeaderGroup(1, 5, new HeaderGroupConfig("Disburse", 1, 2));
//		cm.addHeaderGroup(1, 7, new HeaderGroupConfig("Repayment", 1, 2));
//		cm.addHeaderGroup(1, 9, new HeaderGroupConfig("Early Payment", 1, 2));
//		//cm.addHeaderGroup(1, 13, new HeaderGroupConfig("Cancel/Close", 2, 1));
//		
//		cm.addHeaderGroup(0, 14, new HeaderGroupConfig("Cover In", 1, 2));
//		cm.addHeaderGroup(0, 16, new HeaderGroupConfig("Cover Out", 1, 2));
		
		Grid<SnapshotDailySummary> grid = new Grid<SnapshotDailySummary>(store,
				cm) {
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
}