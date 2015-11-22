package com.btpns.Dashboard.client.panel.ftp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.ftp.FtpChartTrx;
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
import com.sencha.gxt.data.shared.loader.PagingLoadConfig;
import com.sencha.gxt.data.shared.loader.PagingLoadResult;
import com.sencha.gxt.data.shared.loader.PagingLoader;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;

public class FtpChartTrxDataPanel extends FtpDetailTrxPanel {
	
	public FtpChartTrxDataPanel(String title, ImageResource icon,
			Date dateFilter) {
		super(title, icon, dateFilter);
	}

	interface FtpChartTrxProperties extends PropertyAccess<FtpChartTrx> {
		ModelKeyProvider<FtpChartTrx> id();

		ValueProvider<FtpChartTrx, Integer> no();
		
		ValueProvider<FtpChartTrx, String> timeReference();

		ValueProvider<FtpChartTrx, Integer> countIncoming();

		ValueProvider<FtpChartTrx, Integer> countOutgoing();
	}

	@Override
	public Widget asWidget() {

		final FtpServiceAsync service = GWT.create(FtpService.class);

		RpcProxy<PagingLoadConfig, PagingLoadResult<FtpChartTrx>> proxy = new RpcProxy<PagingLoadConfig, PagingLoadResult<FtpChartTrx>>() {

			@Override
			public void load(PagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<FtpChartTrx>> callback) {
				service.getFtpChartTrxData(loadConfig, dateFilter, callback);
			}
		};

		FtpChartTrxProperties props = GWT.create(FtpChartTrxProperties.class);

		ListStore<FtpChartTrx> store = new ListStore<FtpChartTrx>(
				new ModelKeyProvider<FtpChartTrx>() {
					@Override
					public String getKey(FtpChartTrx item) {
						return "" + item.getId();
					}
				});

		final PagingLoader<PagingLoadConfig, PagingLoadResult<FtpChartTrx>> loader = new PagingLoader<PagingLoadConfig, PagingLoadResult<FtpChartTrx>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);
		loader.addLoadHandler(new LoadResultListStoreBinding<PagingLoadConfig, FtpChartTrx, PagingLoadResult<FtpChartTrx>>(
				store));
		
		final NumberFormat numberFormat = NumberFormat
				.getFormat(DashboardConstant.INTEGER_FORMAT);

		ColumnConfig<FtpChartTrx, Integer> noColumn = new ColumnConfig<FtpChartTrx, Integer>(
				props.no(), DashboardConstant.SIZE_NO, "no");
		noColumn.setSortable(false);
		ColumnConfig<FtpChartTrx, String> timeColumn = new ColumnConfig<FtpChartTrx, String>(
				props.timeReference(), 50, "Time");
		ColumnConfig<FtpChartTrx, Integer> incomingColumn = new ColumnConfig<FtpChartTrx, Integer>(
				props.countIncoming(), 100, "Total Incoming");
		incomingColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}

		});

		incomingColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);

		ColumnConfig<FtpChartTrx, Integer> outgoingColumn = new ColumnConfig<FtpChartTrx, Integer>(
				props.countOutgoing(), 100, "Total Outgoing");
		outgoingColumn.setCell(new AbstractCell<Integer>() {

			@Override
			public void render(com.google.gwt.cell.client.Cell.Context context,
					Integer value, SafeHtmlBuilder sb) {
				String v = numberFormat.format(value);
				sb.appendHtmlConstant(v);
			}
		});
		outgoingColumn.setAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		List<ColumnConfig<FtpChartTrx, ?>> l = new ArrayList<ColumnConfig<FtpChartTrx, ?>>();
		l.add(noColumn);
		l.add(timeColumn);
		l.add(incomingColumn);
		l.add(outgoingColumn);
		
		ColumnModel<FtpChartTrx> cm = new ColumnModel<FtpChartTrx>(l);

		Grid<FtpChartTrx> grid = new Grid<FtpChartTrx>(store, cm) {
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
		
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.add(grid, new VerticalLayoutData(1,DashboardConstant.getHeightGrid(Window.getClientHeight(), true)
				+DashboardConstant.HEIGHT_PAGING_TOOLBAR));
		
		return con;
	}
}