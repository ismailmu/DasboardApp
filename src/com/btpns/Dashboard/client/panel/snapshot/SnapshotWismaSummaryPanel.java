package com.btpns.Dashboard.client.panel.snapshot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.snapshot.SnapshotWismaSummary;
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

public class SnapshotWismaSummaryPanel extends SnapshotWismaPanel {

	public SnapshotWismaSummaryPanel(String title, ImageResource icon,
			Date dateFilter) {
		super(title, icon, dateFilter);
	}

	interface SnapshotWismaSummaryProperties extends PropertyAccess<SnapshotWismaSummary> {
		ModelKeyProvider<SnapshotWismaSummary> id();

		ValueProvider<SnapshotWismaSummary, Integer> totalWisma();
		
		ValueProvider<SnapshotWismaSummary, Integer> totalCustomer();

		ValueProvider<SnapshotWismaSummary, Integer> totalGroup();

		ValueProvider<SnapshotWismaSummary, Integer> totalSentra();
		
		ValueProvider<SnapshotWismaSummary, Integer> totalSaving();
		
		ValueProvider<SnapshotWismaSummary, BigDecimal> totalAmountSaving();
		
		ValueProvider<SnapshotWismaSummary, Integer> totalLoan();
		
		ValueProvider<SnapshotWismaSummary, BigDecimal> totalAmountOs();
		
		ValueProvider<SnapshotWismaSummary, BigDecimal> totalAmountDisburse();
	}

	@Override
	public Widget asWidget() {

		final SnapshotServiceAsync service = GWT.create(SnapshotService.class);

		RpcProxy<PagingLoadConfig, PagingLoadResult<SnapshotWismaSummary>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<SnapshotWismaSummary>>() {

			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<SnapshotWismaSummary>> callback) {
				service.getSnapshotWismaSummary(loadConfig, dateFilter, callback);
			}
		};

		SnapshotWismaSummaryProperties props = GWT.create(SnapshotWismaSummaryProperties.class);

		ListStore<SnapshotWismaSummary> store = new ListStore<SnapshotWismaSummary>(
				new ModelKeyProvider<SnapshotWismaSummary>() {
					@Override
					public String getKey(SnapshotWismaSummary item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<PagingLoadConfig, PagingLoadResult<SnapshotWismaSummary>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<SnapshotWismaSummary>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(false);
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, SnapshotWismaSummary, PagingLoadResult<SnapshotWismaSummary>>(
				store));
		
		final NumberFormat integerFormat = NumberFormat
				.getFormat(DashboardConstant.INTEGER_FORMAT);
		
		final NumberFormat decimalFormat = NumberFormat
				.getFormat(DashboardConstant.DECIMAL_FORMAT);

		ColumnConfig<SnapshotWismaSummary, Integer> wismaColumn = new ColumnConfig<SnapshotWismaSummary, Integer>(
				props.totalWisma(), 100, "Total Wisma");
		wismaColumn.setSortable(false);
		wismaColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = integerFormat.format(value);
				sb.appendHtmlConstant(v);
			}

		});

		wismaColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<SnapshotWismaSummary, Integer> customerColumn = new ColumnConfig<SnapshotWismaSummary, Integer>(
				props.totalCustomer(), 100, "Total Customer");
		customerColumn.setSortable(false);
		customerColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = integerFormat.format(value);
				sb.appendHtmlConstant(v);
			}

		});

		customerColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<SnapshotWismaSummary, Integer> groupColumn = new ColumnConfig<SnapshotWismaSummary, Integer>(
				props.totalGroup(), 100, "Total Group");
		groupColumn.setSortable(false);
		groupColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = integerFormat.format(value);
				sb.appendHtmlConstant(v);
			}

		});

		groupColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<SnapshotWismaSummary, Integer> sentraColumn = new ColumnConfig<SnapshotWismaSummary, Integer>(
				props.totalSentra(), 100, "Total Sentra");
		sentraColumn.setSortable(false);
		sentraColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = integerFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		sentraColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<SnapshotWismaSummary, Integer> savingColumn = new ColumnConfig<SnapshotWismaSummary, Integer>(
				props.totalSaving(), 100, "Total Saving");
		savingColumn.setSortable(false);
		savingColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = integerFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		savingColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<SnapshotWismaSummary, BigDecimal> amountSavingColumn = new ColumnConfig<SnapshotWismaSummary, BigDecimal>(
				props.totalAmountSaving(), 100);
		amountSavingColumn.setSortable(false);
		amountSavingColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Total Amount Saving<br/>(Billion)";
			}
		});
		
		amountSavingColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = decimalFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		amountSavingColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<SnapshotWismaSummary, Integer> loanColumn = new ColumnConfig<SnapshotWismaSummary, Integer>(
				props.totalLoan(), 100, "Total Loan");
		loanColumn.setSortable(false);
		loanColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = integerFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		loanColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<SnapshotWismaSummary, BigDecimal> amountOsColumn = new ColumnConfig<SnapshotWismaSummary, BigDecimal>(
				props.totalAmountOs(), 100);
		amountOsColumn.setSortable(false);
		amountOsColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Total Amount Os<br/>(Billion)";
			}
		});
		amountOsColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = decimalFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		amountOsColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<SnapshotWismaSummary, BigDecimal> amountDisburseColumn = new ColumnConfig<SnapshotWismaSummary, BigDecimal>(
				props.totalAmountDisburse(), 100);
		amountDisburseColumn.setSortable(false);
		amountDisburseColumn.setHeader(new SafeHtml() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String asString() {
				return "Total Amount Disburse<br/>(Billion)";
			}
		});
		amountDisburseColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = decimalFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		amountDisburseColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		List<ColumnConfig<SnapshotWismaSummary, ?>> l = new ArrayList<ColumnConfig<SnapshotWismaSummary, ?>>();
		l.add(wismaColumn);
		l.add(customerColumn);
		l.add(groupColumn);
		l.add(sentraColumn);
		l.add(savingColumn);
		l.add(amountSavingColumn);
		l.add(loanColumn);
		l.add(amountOsColumn);
		l.add(amountDisburseColumn);
		
		ColumnModel<SnapshotWismaSummary> cm = new ColumnModel<SnapshotWismaSummary>(l);

		Grid<SnapshotWismaSummary> grid = new Grid<SnapshotWismaSummary>(store, cm) {
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
