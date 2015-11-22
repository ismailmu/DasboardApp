package com.btpns.Dashboard.client.panel.ftp;

import java.util.Date;
import java.util.List;

import org.moxieapps.gwt.highcharts.client.Chart;
import org.moxieapps.gwt.highcharts.client.Series;
import org.moxieapps.gwt.highcharts.client.XAxis.TickmarkPlacement;

import com.btpns.Dashboard.client.model.ftp.FtpChartTrx;
import com.btpns.Dashboard.client.service.ftp.FtpService;
import com.btpns.Dashboard.client.service.ftp.FtpServiceAsync;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent.StoreDataChangeHandler;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.ListReader;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;


//versi 2 dari FtpChartTrxPanel
//memakai komponen chart dari www.highchart.com
//jauh lebih bagus dari chart bawaan gxt
public class FtpChartTrx2Panel extends FtpDetailTrxPanel {

	public FtpChartTrx2Panel(String title, ImageResource icon, Date dateFilter) {
		super(title, icon, dateFilter);
	}

	@Override
	public Widget asWidget() {
		final Chart chart = new Chart();
		
		//FtpChartTrxProperties props = GWT.create(FtpChartTrxProperties.class);

		final FtpServiceAsync service = GWT.create(FtpService.class);

		final ListStore<FtpChartTrx> store = new ListStore<FtpChartTrx>(
				new ModelKeyProvider<FtpChartTrx>() {
					@Override
					public String getKey(FtpChartTrx item) {
						return "" + item.getId();
					}
				});
		
		RpcProxy<ListLoadConfig, List<FtpChartTrx>> proxy = new RpcProxy<ListLoadConfig, List<FtpChartTrx>>() {

			@Override
			public void load(ListLoadConfig loadConfig,
					AsyncCallback<List<FtpChartTrx>> callback) {
				service.getFtpChartTrxDetail(loadConfig, dateFilter, callback);
			}
		};
		
		ListLoader<ListLoadConfig, ListLoadResult<FtpChartTrx>> loader = new ListLoader<ListLoadConfig, ListLoadResult<FtpChartTrx>>(
				proxy, new ListReader<FtpChartTrx>());
		
		loader.addLoadHandler(new LoadResultListStoreBinding<ListLoadConfig, FtpChartTrx, ListLoadResult<FtpChartTrx>>(
				store));
		loader.load();
		
		store.addStoreDataChangeHandler(new StoreDataChangeHandler<FtpChartTrx>() {

			@Override
			public void onDataChange(StoreDataChangeEvent<FtpChartTrx> event) {
				Series seriesIncoming = chart.createSeries().setName("Incoming");
				Series seriesOutgoing = chart.createSeries().setName("Outgoing");
				
				List<FtpChartTrx> listData = store.getAll();
				
				String[] dataCategories = new String[listData.size()+1];
				
				for(int i=0;i<listData.size();i++) {
					FtpChartTrx ftpChartTrx = listData.get(i);
					seriesIncoming.addPoint(ftpChartTrx.getCountIncoming());
					seriesOutgoing.addPoint(ftpChartTrx.getCountOutgoing());
					dataCategories[i] = ftpChartTrx.getTimeReference();
				}
				chart.getXAxis()
					.setCategories(dataCategories)
					.setTickmarkPlacement(TickmarkPlacement.ON)
					.setAxisTitleText("Time");
				
				chart.getYAxis()
					.setAxisTitleText("Count file(s)");
				
				chart.addSeries(seriesIncoming);
				chart.addSeries(seriesOutgoing);
			}
		});

		chart.setBorderWidth(0);
		chart.setType(Series.Type.SPLINE);
		chart.setChartTitleText("Detail Trx Processed");
		chart.setChartSubtitleText("Date : " + DateTimeFormat.getFormat(
				DashboardConstant.DATE_FORMAT).format(dateFilter));
		
		chart.setHeight(DashboardConstant.getHeightGrid(Window.getClientHeight(), true)
				+DashboardConstant.HEIGHT_PAGING_TOOLBAR);
		
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.add(chart, new VerticalLayoutData(1,DashboardConstant.getHeightGrid(Window.getClientHeight(), true)
				+DashboardConstant.HEIGHT_PAGING_TOOLBAR));
		
		return con;
	}
}