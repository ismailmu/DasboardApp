package com.btpns.Dashboard.client.panel.wisma;

import java.util.ArrayList;
import java.util.List;

import com.btpns.Dashboard.client.model.wisma.Wisma;
import com.btpns.Dashboard.client.panel.DashboardLayoutPanel;
import com.btpns.Dashboard.client.resources.Resources;
import com.btpns.Dashboard.client.service.wisma.WismaService;
import com.btpns.Dashboard.client.service.wisma.WismaServiceAsync;
import com.btpns.Dashboard.client.window.wisma.WismaEmployeeWindow;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.TextButtonCell;
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
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.filters.GridFilters;
import com.sencha.gxt.widget.core.client.grid.filters.StringFilter;
import com.sencha.gxt.widget.core.client.toolbar.PagingToolBar;

public class WismaPanel extends DashboardLayoutPanel {

	public WismaPanel(String title, ImageResource icon) {
		super(title, icon);
	}

	interface WismaProperties extends PropertyAccess<Wisma> {
		ModelKeyProvider<Wisma> id();

		ValueProvider<Wisma, Integer> no();

		ValueProvider<Wisma, String> wisma();

		ValueProvider<Wisma, String> wismaName();

		ValueProvider<Wisma, String> kcs();

		ValueProvider<Wisma, String> address();

		ValueProvider<Wisma, String> rtrw();

		ValueProvider<Wisma, String> kelurahan();

		ValueProvider<Wisma, String> kecamatan();

		ValueProvider<Wisma, String> kabupaten();

		ValueProvider<Wisma, String> propinsi();

		ValueProvider<Wisma, String> zipcode();

		ValueProvider<Wisma, String> telephone();
	}

	@Override
	public Widget asWidget() {
		final TextButtonCell viewButtonCell = new TextButtonCell();
		
		final WismaServiceAsync service = GWT.create(WismaService.class);

		RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Wisma>> proxy = new RpcProxy<FilterPagingLoadConfig, PagingLoadResult<Wisma>>() {

			@Override
			public void load(FilterPagingLoadConfig loadConfig,
					AsyncCallback<PagingLoadResult<Wisma>> callback) {
				service.getWisma(loadConfig, callback);
			}
		};

		WismaProperties props = GWT.create(WismaProperties.class);

		final ListStore<Wisma> store = new ListStore<Wisma>(
				new ModelKeyProvider<Wisma>() {
					@Override
					public String getKey(Wisma item) {
						return "" + item.getId();
					}
				});

		viewButtonCell.setIcon(Resources.IMAGES.view16());
		viewButtonCell.addSelectHandler(new SelectHandler() {
			
			@Override
			public void onSelect(SelectEvent event) {
				Context c = event.getContext();
				int row = c.getIndex();
				Wisma wisma = store.get(row);
				final WismaEmployeeWindow wismaEmployee = new WismaEmployeeWindow(wisma);
				wismaEmployee.setModal(true);
				wismaEmployee.show();
			}
		});
		
		final PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Wisma>> loader = new PagingLoader<FilterPagingLoadConfig, PagingLoadResult<Wisma>>(
				proxy) {
			protected FilterPagingLoadConfig newLoadConfig() {
				return new FilterPagingLoadConfigBean();
			}
		};
		loader.setRemoteSort(true);
		loader.addLoadHandler(new LoadResultListStoreBinding<FilterPagingLoadConfig, Wisma, PagingLoadResult<Wisma>>(
				store));

		final PagingToolBar toolBar = new PagingToolBar(
				DashboardConstant.PAGE_SIZE);
		toolBar.getElement().getStyle().setProperty("borderBottom", "none");
		toolBar.bind(loader);

		ColumnConfig<Wisma, Integer> noColumn = new ColumnConfig<Wisma, Integer>(
				props.no(), DashboardConstant.SIZE_NO, "No");
		noColumn.setSortable(false);
		ColumnConfig<Wisma, String> kscColumn = new ColumnConfig<Wisma, String>(
				props.kcs(), 30, "Kcs");
		ColumnConfig<Wisma, String> wismaColumn = new ColumnConfig<Wisma, String>(
				props.wisma(), 50, "Wisma");
		wismaColumn.setCell(viewButtonCell);
		ColumnConfig<Wisma, String> wismaNameColumn = new ColumnConfig<Wisma, String>(
				props.wismaName(), 150, "Wisma Name");
		ColumnConfig<Wisma, String> addressColumn = new ColumnConfig<Wisma, String>(
				props.address(), 150, "Address");
		ColumnConfig<Wisma, String> rtrwColumn = new ColumnConfig<Wisma, String>(
				props.rtrw(), 50, "RT/RW");
		ColumnConfig<Wisma, String> kelurahanColumn = new ColumnConfig<Wisma, String>(
				props.kelurahan(), 50, "Kelurahan");
		ColumnConfig<Wisma, String> kecamatanColumn = new ColumnConfig<Wisma, String>(
				props.kecamatan(), 50, "Kecamatan");
		ColumnConfig<Wisma, String> kabupatenColumn = new ColumnConfig<Wisma, String>(
				props.kabupaten(), 50, "Kabupaten");
		ColumnConfig<Wisma, String> propinsiColumn = new ColumnConfig<Wisma, String>(
				props.propinsi(), 50, "Propinsi");
		ColumnConfig<Wisma, String> zipColumn = new ColumnConfig<Wisma, String>(
				props.zipcode(), 50, "Zip Code");
		ColumnConfig<Wisma, String> telpColumn = new ColumnConfig<Wisma, String>(
				props.telephone(), 50, "Telephone");
		
		List<ColumnConfig<Wisma, ?>> l = new ArrayList<ColumnConfig<Wisma, ?>>();
		l.add(noColumn);
		l.add(kscColumn);
		l.add(wismaColumn);
		l.add(wismaNameColumn);
		l.add(addressColumn);
		l.add(rtrwColumn);
		l.add(kelurahanColumn);
		l.add(kecamatanColumn);
		l.add(kabupatenColumn);
		l.add(propinsiColumn);
		l.add(zipColumn);
		l.add(telpColumn);
		
		ColumnModel<Wisma> cm = new ColumnModel<Wisma>(l);

		Grid<Wisma> grid = new Grid<Wisma>(store, cm) {
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
		
//		TextArea addressTextArea = new TextArea();
//		addressTextArea.setHeight(60);
//		final GridEditing<Wisma> editing = new GridInlineEditing<Wisma>(grid);
//	    editing.addEditor(wismaNameColumn, new TextField());
//	    editing.addEditor(addressColumn,addressTextArea );
//	    editing.addEditor(rtrwColumn, new TextField());
//	    editing.addEditor(kelurahanColumn, new TextField());
//	    editing.addEditor(kecamatanColumn, new TextField());
//	    editing.addEditor(kabupatenColumn, new TextField());
//	    editing.addEditor(propinsiColumn, new TextField());
//	    editing.addEditor(zipColumn, new TextField());
//	    editing.addEditor(telpColumn, new TextField());
	    
		grid.getView().setForceFit(true);
		grid.setLoadMask(true);
		grid.setLoader(loader);

		StringFilter<Wisma> kcsFilter = new StringFilter<Wisma>(props.kcs());
		StringFilter<Wisma> wismaFilter = new StringFilter<Wisma>(props.wisma());
		StringFilter<Wisma> wismaNameFilter = new StringFilter<Wisma>(
				props.wismaName());
		StringFilter<Wisma> addressFilter = new StringFilter<Wisma>(
				props.address());
		StringFilter<Wisma> rtrwFilter = new StringFilter<Wisma>(props.rtrw());
		StringFilter<Wisma> kelurahanFilter = new StringFilter<Wisma>(
				props.kelurahan());
		StringFilter<Wisma> kecamatanFilter = new StringFilter<Wisma>(
				props.kecamatan());
		StringFilter<Wisma> kabupatenFilter = new StringFilter<Wisma>(
				props.kabupaten());
		StringFilter<Wisma> propinsiFilter = new StringFilter<Wisma>(
				props.propinsi());
		StringFilter<Wisma> zipFilter = new StringFilter<Wisma>(props.zipcode());
		StringFilter<Wisma> telpFilter = new StringFilter<Wisma>(
				props.propinsi());

		GridFilters<Wisma> filters = new GridFilters<Wisma>(loader);

		filters.initPlugin(grid);
		filters.addFilter(kcsFilter);
		filters.addFilter(wismaFilter);
		filters.addFilter(wismaNameFilter);
		filters.addFilter(addressFilter);
		filters.addFilter(rtrwFilter);
		filters.addFilter(kelurahanFilter);
		filters.addFilter(kecamatanFilter);
		filters.addFilter(kabupatenFilter);
		filters.addFilter(propinsiFilter);
		filters.addFilter(zipFilter);
		filters.addFilter(telpFilter);

		con.add(grid, new VerticalLayoutData(1, DashboardConstant.getHeightGrid(Window.getClientHeight(), false)));
		con.add(toolBar, new VerticalLayoutData(1, -1));

		//setWidget(con);
		
		return super.asWidget();
	}
}