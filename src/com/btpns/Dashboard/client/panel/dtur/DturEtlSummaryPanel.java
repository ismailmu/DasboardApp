package com.btpns.Dashboard.client.panel.dtur;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.dtur.DturEtlSummary;
import com.btpns.Dashboard.client.service.dtur.DturService;
import com.btpns.Dashboard.client.service.dtur.DturServiceAsync;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.i18n.shared.DateTimeFormat;
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

public class DturEtlSummaryPanel extends DturEtlPanel {

	public DturEtlSummaryPanel(String title, ImageResource icon, Date dateFilter) {
		super(title, icon, dateFilter);
		// TODO Auto-generated constructor stub
	}

	interface DturEtlSummaryProperties extends PropertyAccess<DturEtlSummary> {
		ModelKeyProvider<DturEtlSummary> id();

		ValueProvider<DturEtlSummary, String> jobName();

		ValueProvider<DturEtlSummary, Integer> countProcess();

		ValueProvider<DturEtlSummary, String> maxDuration();
		
		ValueProvider<DturEtlSummary, Date> startEtl();
		
		ValueProvider<DturEtlSummary, Date> endEtl();
		
		ValueProvider<DturEtlSummary, String> etlDuration();
	}

	@Override
	public Widget asWidget() {

		final DturServiceAsync service = GWT.create(DturService.class);

		RpcProxy<PagingLoadConfig, PagingLoadResult<DturEtlSummary>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<DturEtlSummary>>() {

			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<DturEtlSummary>> callback) {
				service.getDturEtlSummary(loadConfig, dateFilter, callback);
			}
		};

		DturEtlSummaryProperties props = GWT.create(DturEtlSummaryProperties.class);

		ListStore<DturEtlSummary> store = new ListStore<DturEtlSummary>(
				new ModelKeyProvider<DturEtlSummary>() {
					@Override
					public String getKey(DturEtlSummary item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<PagingLoadConfig, PagingLoadResult<DturEtlSummary>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<DturEtlSummary>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(false);
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, DturEtlSummary, PagingLoadResult<DturEtlSummary>>(
				store));
		
//		final NumberFormat numberFormat = NumberFormat
//				.getFormat(DashboardConstant.INTEGER_FORMAT);

		ColumnConfig<DturEtlSummary, Integer> countProcessColumn = new ColumnConfig<DturEtlSummary, Integer>(
				props.countProcess(), 50, "Total Process");
		countProcessColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		ColumnConfig<DturEtlSummary, String> jobNameLongestColumn = new ColumnConfig<DturEtlSummary, String>(
				props.jobName(), 100, "Job Name Longest");
		ColumnConfig<DturEtlSummary, String> jobDurationLongestColumn = new ColumnConfig<DturEtlSummary, String>(
				props.maxDuration(), 100, "Job Longest Duration");
		ColumnConfig<DturEtlSummary, Date> startEtlColumn = new ColumnConfig<DturEtlSummary, Date>(
				props.startEtl(), 100, "Start ETL");
		startEtlColumn.setCell(new AbstractCell<Date>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Date value, SafeHtmlBuilder sb) {
				if (value != null)
					sb.appendHtmlConstant(DateTimeFormat.getFormat(DashboardConstant.DATETIME_FORMAT).format(value));
			}
		});
		ColumnConfig<DturEtlSummary, Date> endEtlColumn = new ColumnConfig<DturEtlSummary, Date>(
				props.endEtl(), 100, "End ETL");
		endEtlColumn.setCell(new AbstractCell<Date>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Date value, SafeHtmlBuilder sb) {
				if (value != null)
					sb.appendHtmlConstant(DateTimeFormat.getFormat(DashboardConstant.DATETIME_FORMAT).format(value));
			}
		});
		ColumnConfig<DturEtlSummary, String> etlDurationColumn = new ColumnConfig<DturEtlSummary, String>(
				props.etlDuration(), 100, "ETL Duration");
		
		List<ColumnConfig<DturEtlSummary, ?>> l = new ArrayList<ColumnConfig<DturEtlSummary, ?>>();
		l.add(countProcessColumn);
		l.add(jobNameLongestColumn);
		l.add(jobDurationLongestColumn);
		l.add(startEtlColumn);
		l.add(endEtlColumn);
		l.add(etlDurationColumn);
		
		ColumnModel<DturEtlSummary> cm = new ColumnModel<DturEtlSummary>(l);

		Grid<DturEtlSummary> grid = new Grid<DturEtlSummary>(store, cm) {
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