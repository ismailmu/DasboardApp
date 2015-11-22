package com.btpns.Dashboard.client.panel.ftp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.ftp.FtpFailureSummary;
import com.btpns.Dashboard.client.service.ftp.FtpService;
import com.btpns.Dashboard.client.service.ftp.FtpServiceAsync;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.resources.client.ImageResource;
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

public class FtpFailureSummaryPanel extends FtpFailurePanel {
	
	public FtpFailureSummaryPanel(String title, ImageResource icon,
			Date dateFilter) {
		super(title, icon, dateFilter);
	}

	interface FtpFailureSummaryProperties extends PropertyAccess<FtpFailureSummary> {
		ModelKeyProvider<FtpFailureSummary> id();

		ValueProvider<FtpFailureSummary, Integer> totalWisma();

		ValueProvider<FtpFailureSummary, Integer> totalFileReceived();

		ValueProvider<FtpFailureSummary, Integer> totalRecordReceived();
		
		ValueProvider<FtpFailureSummary, Integer> totalHasBeenProcessed();
		
		ValueProvider<FtpFailureSummary, Integer> totalFileProcessed();
		
		ValueProvider<FtpFailureSummary, Integer> totalRecord();
		
		ValueProvider<FtpFailureSummary, Integer> totalRecordSuccess();
		
		ValueProvider<FtpFailureSummary, Integer> totalRecordFailure();
	}

	@Override
	public Widget asWidget() {

		final FtpServiceAsync service = GWT.create(FtpService.class);

		RpcProxy<PagingLoadConfig, PagingLoadResult<FtpFailureSummary>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<FtpFailureSummary>>() {

			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<FtpFailureSummary>> callback) {
				service.getFtpFailureSummary(loadConfig, dateFilter, callback);
			}
		};

		FtpFailureSummaryProperties props = GWT.create(FtpFailureSummaryProperties.class);

		ListStore<FtpFailureSummary> store = new ListStore<FtpFailureSummary>(
				new ModelKeyProvider<FtpFailureSummary>() {
					@Override
					public String getKey(FtpFailureSummary item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<PagingLoadConfig, PagingLoadResult<FtpFailureSummary>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<FtpFailureSummary>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(false);
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, FtpFailureSummary, PagingLoadResult<FtpFailureSummary>>(
				store));
		
		final NumberFormat numberFormat = NumberFormat
				.getFormat(DashboardConstant.INTEGER_FORMAT);

		ColumnConfig<FtpFailureSummary, Integer> wismaColumn = new ColumnConfig<FtpFailureSummary, Integer>(
				props.totalWisma(), 100, "Total Wisma");
		ColumnConfig<FtpFailureSummary, Integer> fileReceivedColumn = new ColumnConfig<FtpFailureSummary, Integer>(
				props.totalFileProcessed(), 100, "Total File Received");
		fileReceivedColumn.setSortable(false);
		fileReceivedColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}

		});

		fileReceivedColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<FtpFailureSummary, Integer> recordReceivedColumn = new ColumnConfig<FtpFailureSummary, Integer>(
				props.totalRecordReceived(), 100, "Total Record Received");
		recordReceivedColumn.setSortable(false);
		recordReceivedColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		recordReceivedColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
//		ColumnConfig<FtpFailureSummary, Integer> hasBeenProcessedColumn = new ColumnConfig<FtpFailureSummary, Integer>(
//				props.totalHasBeenProcessed(), 100, "Total Has Been Processed");
//		hasBeenProcessedColumn.setSortable(false);
//		hasBeenProcessedColumn.setCell(new AbstractCell<Integer>() {
//
//			@Override
//			public void render(com.google.gwt.cell.client.Cell.Context context,
//					Integer value, SafeHtmlBuilder sb) {
//				String v = numberFormat.format(value);
//				sb.appendHtmlConstant(v);
//			}
//		});
//		hasBeenProcessedColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<FtpFailureSummary, Integer> fileProcessedColumn = new ColumnConfig<FtpFailureSummary, Integer>(
				props.totalFileProcessed(), 100, "Total File Processed");
		fileProcessedColumn.setSortable(false);
		fileProcessedColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		fileProcessedColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<FtpFailureSummary, Integer> totalRecordColumn = new ColumnConfig<FtpFailureSummary, Integer>(
				props.totalRecord(), 100, "Total Record Processed");
		totalRecordColumn.setSortable(false);
		totalRecordColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		totalRecordColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<FtpFailureSummary, Integer> totalRecordSuccessColumn = new ColumnConfig<FtpFailureSummary, Integer>(
				props.totalRecordSuccess(), 100, "Total Record Success");
		totalRecordSuccessColumn.setSortable(false);
		totalRecordSuccessColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		totalRecordSuccessColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<FtpFailureSummary, Integer> totalRecordFailureColumn = new ColumnConfig<FtpFailureSummary, Integer>(
				props.totalRecordFailure(), 100, "Total Record Failed");
		totalRecordFailureColumn.setSortable(false);
		totalRecordFailureColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		totalRecordFailureColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		List<ColumnConfig<FtpFailureSummary, ?>> l = new ArrayList<ColumnConfig<FtpFailureSummary, ?>>();
		l.add(wismaColumn);
		l.add(fileReceivedColumn);
		l.add(recordReceivedColumn);
//		l.add(hasBeenProcessedColumn);
		l.add(fileProcessedColumn);
		l.add(totalRecordColumn);
		l.add(totalRecordSuccessColumn);
		l.add(totalRecordFailureColumn);
		
		ColumnModel<FtpFailureSummary> cm = new ColumnModel<FtpFailureSummary>(l);

		Grid<FtpFailureSummary> grid = new Grid<FtpFailureSummary>(store, cm) {
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