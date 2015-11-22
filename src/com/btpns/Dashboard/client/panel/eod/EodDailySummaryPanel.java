package com.btpns.Dashboard.client.panel.eod;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.eod.EodDailySummary;
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

public class EodDailySummaryPanel extends EodDailyPanel {

	public EodDailySummaryPanel(String title, ImageResource icon,
			Date dateFilter) {
		super(title, icon, dateFilter);
	}

	interface EodDailySummaryProperties extends PropertyAccess<EodDailySummary> {
		ModelKeyProvider<EodDailySummary> id();

		ValueProvider<EodDailySummary, Integer> totalWisma();

		ValueProvider<EodDailySummary, BigDecimal> totalSumTrans();

		ValueProvider<EodDailySummary, Integer> totalCountFile();
	}

	@Override
	public Widget asWidget() {

		final EodServiceAsync service = GWT.create(EodService.class);

		RpcProxy<PagingLoadConfig, PagingLoadResult<EodDailySummary>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<EodDailySummary>>() {

			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<EodDailySummary>> callback) {
				service.getEodDailySummary(loadConfig, dateFilter, callback);
			}
		};

		EodDailySummaryProperties props = GWT.create(EodDailySummaryProperties.class);

		ListStore<EodDailySummary> store = new ListStore<EodDailySummary>(
				new ModelKeyProvider<EodDailySummary>() {
					@Override
					public String getKey(EodDailySummary item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<PagingLoadConfig, PagingLoadResult<EodDailySummary>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<EodDailySummary>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(false);
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, EodDailySummary, PagingLoadResult<EodDailySummary>>(
				store));
		
		final NumberFormat numberFormat = NumberFormat
				.getFormat(DashboardConstant.INTEGER_FORMAT);

		ColumnConfig<EodDailySummary, Integer> wismaColumn = new ColumnConfig<EodDailySummary, Integer>(
				props.totalWisma(), 100, "Total Wisma");
		ColumnConfig<EodDailySummary, BigDecimal> sumTransColumn = new ColumnConfig<EodDailySummary, BigDecimal>(
				props.totalSumTrans(), 100, "Total Sum Trans");
		sumTransColumn.setSortable(false);
		sumTransColumn.setCell(new AbstractCell<BigDecimal>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					BigDecimal value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}

		});

		sumTransColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<EodDailySummary, Integer> countFileColumn = new ColumnConfig<EodDailySummary, Integer>(
				props.totalCountFile(), 100, "Total Count File");
		countFileColumn.setSortable(false);
		countFileColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		countFileColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		List<ColumnConfig<EodDailySummary, ?>> l = new ArrayList<ColumnConfig<EodDailySummary, ?>>();
		l.add(wismaColumn);
		l.add(sumTransColumn);
		l.add(countFileColumn);
		
		ColumnModel<EodDailySummary> cm = new ColumnModel<EodDailySummary>(l);

		Grid<EodDailySummary> grid = new Grid<EodDailySummary>(store, cm) {
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
