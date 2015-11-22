package com.btpns.Dashboard.client.panel.ftp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.ftp.FtpFailureDetail;
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

public class FtpFailureDetailPanel extends FtpFailurePanel {
		
	public FtpFailureDetailPanel(String title, ImageResource icon,
			Date dateFilter) {
		super(title, icon, dateFilter);
	}

	interface FtpFailureDetailProperties extends PropertyAccess<FtpFailureDetail> {
		ModelKeyProvider<FtpFailureDetail> id();

		ValueProvider<FtpFailureDetail, Integer> no();

		ValueProvider<FtpFailureDetail, String> wisma();

		ValueProvider<FtpFailureDetail, String> wismaName();

		ValueProvider<FtpFailureDetail, Integer> fileReceived();

		ValueProvider<FtpFailureDetail, Integer> recordReceived();
		
		ValueProvider<FtpFailureDetail, Integer> hasBeenProcessed();
		
		ValueProvider<FtpFailureDetail, Integer> fileProcessed();
		
		ValueProvider<FtpFailureDetail, Integer> totalRecord();
		
		ValueProvider<FtpFailureDetail, Integer> totalRecordSuccess();
		
		ValueProvider<FtpFailureDetail, Integer> totalRecordFailure();
	}

	@Override
	public Widget asWidget() {

		final FtpServiceAsync service = GWT.create(FtpService.class);

		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<FtpFailureDetail>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<FtpFailureDetail>>() {

			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<FtpFailureDetail>> callback) {
				service.getFtpFailureDetail(loadConfig, dateFilter, callback);
			}
		};

		FtpFailureDetailProperties props = GWT.create(FtpFailureDetailProperties.class);

		ListStore<FtpFailureDetail> store = new ListStore<FtpFailureDetail>(
				new ModelKeyProvider<FtpFailureDetail>() {
					@Override
					public String getKey(FtpFailureDetail item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<FtpFailureDetail>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<FtpFailureDetail>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);
		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, FtpFailureDetail, PagingLoadResult<FtpFailureDetail>>(
				store));

		final PagingToolBar toolBar = new PagingToolBar(
				DashboardConstant.PAGE_SIZE);
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolBar.bind(loader);

		final NumberFormat numberFormat = NumberFormat
				.getFormat(DashboardConstant.INTEGER_FORMAT);

		ColumnConfig<FtpFailureDetail, Integer> noColumn = new ColumnConfig<FtpFailureDetail, Integer>(
				props.no(), DashboardConstant.SIZE_NO, "No");
		noColumn.setSortable(false);
		ColumnConfig<FtpFailureDetail, String> wismaColumn = new ColumnConfig<FtpFailureDetail, String>(
				props.wisma(), 50, "Wisma");
		ColumnConfig<FtpFailureDetail, String> wismaNameColumn = new ColumnConfig<FtpFailureDetail, String>(
				props.wismaName(), 150, "Wisma Name");
		ColumnConfig<FtpFailureDetail, Integer> fileReceivedColumn = new ColumnConfig<FtpFailureDetail, Integer>(
				props.fileProcessed(), 100, "File Received");
		fileReceivedColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}

		});

		fileReceivedColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<FtpFailureDetail, Integer> recordReceivedColumn = new ColumnConfig<FtpFailureDetail, Integer>(
				props.recordReceived(), 100, "Record Received");
		recordReceivedColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		recordReceivedColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
//		ColumnConfig<FtpFailureDetail, Integer> hasBeenProcessedColumn = new ColumnConfig<FtpFailureDetail, Integer>(
//				props.hasBeenProcessed(), 100, "Has Been Processed");
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
		
		ColumnConfig<FtpFailureDetail, Integer> fileProcessedColumn = new ColumnConfig<FtpFailureDetail, Integer>(
				props.fileProcessed(), 100, "File Processed");
		fileProcessedColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		fileProcessedColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<FtpFailureDetail, Integer> totalRecordColumn = new ColumnConfig<FtpFailureDetail, Integer>(
				props.totalRecord(), 100, "Record Processed");
		totalRecordColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		totalRecordColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<FtpFailureDetail, Integer> totalRecordSuccessColumn = new ColumnConfig<FtpFailureDetail, Integer>(
				props.totalRecordSuccess(), 100, "Record Success");
		totalRecordSuccessColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		totalRecordSuccessColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		ColumnConfig<FtpFailureDetail, Integer> totalRecordFailureColumn = new ColumnConfig<FtpFailureDetail, Integer>(
				props.totalRecordFailure(), 100, "Record Failed");
		totalRecordFailureColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		totalRecordFailureColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		List<ColumnConfig<FtpFailureDetail, ?>> l = new ArrayList<ColumnConfig<FtpFailureDetail, ?>>();
		l.add(noColumn);
		l.add(wismaColumn);
		l.add(wismaNameColumn);
		l.add(fileReceivedColumn);
		l.add(recordReceivedColumn);
//		l.add(hasBeenProcessedColumn);
		l.add(fileProcessedColumn);
		l.add(totalRecordColumn);
		l.add(totalRecordSuccessColumn);
		l.add(totalRecordFailureColumn);
		
		ColumnModel<FtpFailureDetail> cm = new ColumnModel<FtpFailureDetail>(l);

		Grid<FtpFailureDetail> grid = new Grid<FtpFailureDetail>(store, cm) {
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

		StringFilter<FtpFailureDetail> wismaFilter = new StringFilter<FtpFailureDetail>(
				props.wisma());
		StringFilter<FtpFailureDetail> wismaNameFilter = new StringFilter<FtpFailureDetail>(
				props.wismaName());
		NumericFilter<FtpFailureDetail, Integer> fileReceivedFilter = new NumericFilter<FtpFailureDetail, Integer>(
				props.fileReceived(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<FtpFailureDetail, Integer> recordReceivedFilter = new NumericFilter<FtpFailureDetail, Integer>(
				props.recordReceived(),
				new NumberPropertyEditor.IntegerPropertyEditor());
//		NumericFilter<FtpFailureDetail, Integer> hasBeenProcessedFilter = new NumericFilter<FtpFailureDetail, Integer>(
//				props.hasBeenProcessed(),
//				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<FtpFailureDetail, Integer> fileProcessedFilter = new NumericFilter<FtpFailureDetail, Integer>(
				props.fileProcessed(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<FtpFailureDetail, Integer> totalRecordFilter = new NumericFilter<FtpFailureDetail, Integer>(
				props.totalRecord(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<FtpFailureDetail, Integer> totalRecordSuccessFilter = new NumericFilter<FtpFailureDetail, Integer>(
				props.totalRecordSuccess(),
				new NumberPropertyEditor.IntegerPropertyEditor());
		NumericFilter<FtpFailureDetail, Integer> totalRecordFailureFilter = new NumericFilter<FtpFailureDetail, Integer>(
				props.totalRecordFailure(),
				new NumberPropertyEditor.IntegerPropertyEditor());

		GridFilters<FtpFailureDetail> filters = new GridFilters<FtpFailureDetail>(loader);

		filters.initPlugin(grid);
		filters.addFilter(wismaFilter);
		filters.addFilter(wismaNameFilter);
		filters.addFilter(fileReceivedFilter);
		filters.addFilter(recordReceivedFilter);
//		filters.addFilter(hasBeenProcessedFilter);
		filters.addFilter(fileProcessedFilter);
		filters.addFilter(totalRecordFilter);
		filters.addFilter(totalRecordSuccessFilter);
		filters.addFilter(totalRecordFailureFilter);
		
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.add(grid, new VerticalLayoutData(1, DashboardConstant.getHeightGrid(Window.getClientHeight(), true)));
		con.add(toolBar, new VerticalLayoutData(1, -1));
		
		return con;
	}
}
