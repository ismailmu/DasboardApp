package com.btpns.Dashboard.client.panel.ftp;

import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.ftp.FtpChartTrx;
import com.btpns.Dashboard.client.service.ftp.FtpService;
import com.btpns.Dashboard.client.service.ftp.FtpServiceAsync;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.chart.client.chart.Chart;
import com.sencha.gxt.chart.client.chart.Chart.Position;
import com.sencha.gxt.chart.client.chart.Legend;
import com.sencha.gxt.chart.client.chart.axis.CategoryAxis;
import com.sencha.gxt.chart.client.chart.axis.NumericAxis;
import com.sencha.gxt.chart.client.chart.series.LineSeries;
import com.sencha.gxt.chart.client.chart.series.Primitives;
import com.sencha.gxt.chart.client.chart.series.Series.LabelPosition;
import com.sencha.gxt.chart.client.chart.series.SeriesLabelConfig;
import com.sencha.gxt.chart.client.draw.Color;
import com.sencha.gxt.chart.client.draw.RGB;
import com.sencha.gxt.chart.client.draw.path.PathSprite;
import com.sencha.gxt.chart.client.draw.sprite.Sprite;
import com.sencha.gxt.chart.client.draw.sprite.TextSprite;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.client.loader.RpcProxy;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent;
import com.sencha.gxt.data.shared.event.StoreDataChangeEvent.StoreDataChangeHandler;
import com.sencha.gxt.data.shared.loader.ListLoadConfig;
import com.sencha.gxt.data.shared.loader.ListLoadResult;
import com.sencha.gxt.data.shared.loader.ListLoader;
import com.sencha.gxt.data.shared.loader.ListReader;
import com.sencha.gxt.data.shared.loader.LoadResultListStoreBinding;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;

public class FtpChartTrxPanel extends FtpDetailTrxPanel {

	public FtpChartTrxPanel(String title, ImageResource icon, Date dateFilter) {
		super(title, icon, dateFilter);
	}

	interface FtpChartTrxProperties extends PropertyAccess<FtpChartTrx> {
		ModelKeyProvider<FtpChartTrx> id();

		ValueProvider<FtpChartTrx, String> timeReference();

		ValueProvider<FtpChartTrx, Integer> countIncoming();

		ValueProvider<FtpChartTrx, Integer> countOutgoing();
	}

	@Override
	public Widget asWidget() {

		FtpChartTrxProperties props = GWT.create(FtpChartTrxProperties.class);

		final FtpServiceAsync service = GWT.create(FtpService.class);

		ListStore<FtpChartTrx> store = new ListStore<FtpChartTrx>(
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
		
		final Chart<FtpChartTrx> chart = new Chart<FtpChartTrx>();
		chart.setStore(store);
		chart.setShadowChart(true);
		chart.setAnimated(true);
		
		store.addStoreDataChangeHandler(new StoreDataChangeHandler<FtpChartTrx>() {

			@Override
			public void onDataChange(StoreDataChangeEvent<FtpChartTrx> event) {
				chart.redrawChart();
			}
		});
		
		NumericAxis<FtpChartTrx> axis = new NumericAxis<FtpChartTrx>();
		axis.setPosition(Position.LEFT);
		axis.addField(props.countIncoming());
		axis.addField(props.countOutgoing());
		TextSprite title = new TextSprite("Count file(s)");
		title.setFontSize(18);
		axis.setTitleConfig(title);
		axis.setMinorTickSteps(1);
		axis.setDisplayGrid(true);
		PathSprite odd = new PathSprite();
		odd.setOpacity(1);
		odd.setFill(new Color("#ddd"));
		odd.setStroke(new Color("#bbb"));
		odd.setStrokeWidth(0.5);
		axis.setGridOddConfig(odd);
		axis.setMinimum(0);
		chart.addAxis(axis);

		CategoryAxis<FtpChartTrx, String> catAxis = new CategoryAxis<FtpChartTrx, String>();
		catAxis.setPosition(Position.BOTTOM);
		catAxis.setField(props.timeReference());
		title = new TextSprite("Time");
		title.setFontSize(18);
		catAxis.setTitleConfig(title);
		chart.addAxis(catAxis);

		SeriesLabelConfig<FtpChartTrx> seriesLabel = new SeriesLabelConfig<FtpChartTrx>();
		seriesLabel.setLabelPosition(LabelPosition.OUTSIDE);
		
		final LineSeries<FtpChartTrx> seriesIncoming = new LineSeries<FtpChartTrx>();
		seriesIncoming.setSmooth(true);
		seriesIncoming.setLegendTitle("Incoming");
		seriesIncoming.setYAxisPosition(Position.LEFT);
		seriesIncoming.setYField(props.countIncoming());
		seriesIncoming.setStroke(new RGB(194, 0, 36));
		seriesIncoming.setShowMarkers(true);
		Sprite marker = Primitives.square(0, 0, 6);
		marker.setFill(new RGB(194, 0, 36));
		seriesIncoming.setMarkerConfig(marker);
		seriesIncoming.setHighlighting(true);
		seriesIncoming.setLabelConfig(seriesLabel);
		chart.addSeries(seriesIncoming);

		final LineSeries<FtpChartTrx> seriesOutgoing = new LineSeries<FtpChartTrx>();
		seriesOutgoing.setSmooth(true);
		seriesOutgoing.setLegendTitle("Outgoing");
		seriesOutgoing.setYAxisPosition(Position.LEFT);
		seriesOutgoing.setYField(props.countOutgoing());
		seriesOutgoing.setStroke(new RGB(240, 165, 10));
		seriesOutgoing.setShowMarkers(true);
		seriesOutgoing.setSmooth(true);
		marker = Primitives.circle(0, 0, 6);
		marker.setFill(new RGB(240, 165, 10));
		seriesOutgoing.setMarkerConfig(marker);
		seriesOutgoing.setHighlighting(true);
		seriesOutgoing.setLabelConfig(seriesLabel);
		chart.addSeries(seriesOutgoing);

		final Legend<FtpChartTrx> legend = new Legend<FtpChartTrx>();
		legend.setPosition(Position.BOTTOM);
		legend.setItemHighlighting(true);
		legend.setItemHiding(true);
		chart.setLegend(legend);
		
		VerticalLayoutContainer con = new VerticalLayoutContainer();
		con.add(chart, new VerticalLayoutData(1,DashboardConstant.getHeightGrid(Window.getClientHeight(), true)
				+DashboardConstant.HEIGHT_PAGING_TOOLBAR));
		
		return con;
	}
}
