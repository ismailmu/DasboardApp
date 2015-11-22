package com.btpns.Dashboard.client;

import java.util.Date;
import java.util.List;

import com.btpns.Dashboard.client.model.menu.MenuToolbar;
import com.btpns.Dashboard.client.panel.DashboardPanel;
import com.btpns.Dashboard.client.panel.dtur.DturEtlPanel;
import com.btpns.Dashboard.client.panel.eod.EodDailyPanel;
import com.btpns.Dashboard.client.panel.eod.EodDurationPanel;
import com.btpns.Dashboard.client.panel.ftp.FtpDetailTrxPanel;
import com.btpns.Dashboard.client.panel.ftp.FtpFailurePanel;
import com.btpns.Dashboard.client.panel.helpdesk.HelpdeskDailyPanel;
import com.btpns.Dashboard.client.panel.home.HomePanel;
import com.btpns.Dashboard.client.panel.snapshot.SnapshotDailyPanel;
import com.btpns.Dashboard.client.panel.snapshot.SnapshotWismaPanel;
import com.btpns.Dashboard.client.panel.wisma.WismaPanel;
import com.btpns.Dashboard.client.resources.Resources;
import com.btpns.Dashboard.client.service.menu.MenuService;
import com.btpns.Dashboard.client.service.menu.MenuServiceAsync;
import com.btpns.Dashboard.shared.DashboardConstant;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.DateTimePropertyEditor;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.LabelToolItem;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public class DashboardShell2 extends BorderLayoutContainer {

	// private ContentPanel west;
	private final SimpleContainer center = new SimpleContainer();
	private final DateField dateField = new DateField();
	private final TextButton searchButton = new TextButton();
	private final LabelToolItem searchLabel = new LabelToolItem("Set date :");
	private String panelId = "";
	private String panelTitle = "";

	final ToolBar toolBar = new ToolBar();

	
	public DashboardShell2() {

		dateField.setValue(new Date());
		
		monitorWindowResize = true;
		Window.enableScrolling(false);
		setPagePosition(0, 0);
		setPixelSize(Window.getClientWidth(), Window.getClientHeight());

		StringBuffer sb = new StringBuffer();
		sb.append("<div id='demo-theme'></div><div id=demo-title>IT INTERNAL DASHBOARD</div>");

		final VerticalLayoutContainer vlc = new VerticalLayoutContainer();

		HtmlLayoutContainer northPanel = new HtmlLayoutContainer(sb.toString());
		northPanel.setId("demo-header");
		vlc.add(northPanel);

		final SelectionHandler<Item> selectionHandler = new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				dateField.setValue(new Date());

				MenuItem item = (MenuItem) event.getSelectedItem();
				panelId = item.getId();
				panelTitle = item.getText();
				showPanel();
			}
		};
		
		final SelectHandler selectHandler = new SelectHandler() {

			@Override
			public void onSelect(SelectEvent event) {
				TextButton btn = (TextButton) event.getSource();
				if (btn.getItemId().equalsIgnoreCase("parentOnly")) {
					panelId = btn.getId();
					panelTitle = btn.getText();
					showPanel();
				}else if (btn.getId().equalsIgnoreCase("searchButton")) {
					showPanel();
				}
				
			}
		};
		
		final MenuServiceAsync serviceMenu = GWT.create(MenuService.class);
		final MenuServiceAsync serviceChildMenu = GWT.create(MenuService.class);
		
		serviceMenu.getMenuToolbar("from MenuToolbarModel e where e.parentMenu is null Order By e.ordinalPosition", new AsyncCallback<List<MenuToolbar>>() {

			@Override
			public void onFailure(Throwable caught) {
				System.out.println("Error callback menu = " + caught.getMessage());
			}

			@Override
			public void onSuccess(List<MenuToolbar> result) {
				int i=0;
				toolBar.setVerticalSpacing(15);
				toolBar.setSpacing(3);
				for (MenuToolbar menu  : result) {
					final TextButton btn = new TextButton(menu.getText());
					btn.setId(menu.getAction());
					btn.setIcon(Resources.getInstance().getImage(menu.getIconCls()));
					btn.addSelectHandler(selectHandler);
					
					toolBar.insert(btn, i);
					serviceChildMenu.getMenuToolbar("from MenuToolbarModel e where e.parentMenu = " + menu.getId() + " Order By e.ordinalPosition",  new AsyncCallback<List<MenuToolbar>>() {

						@Override
						public void onFailure(Throwable caughtChild) {
							System.out.println("Error callback child menu = " + caughtChild.getMessage());
						}

						@Override
						public void onSuccess(List<MenuToolbar> resultChild) {
							if (resultChild.size()>0) {
								int y=0;
								Menu menuComp = new Menu();
								menuComp.addSelectionHandler(selectionHandler);
								for (MenuToolbar menuChild : resultChild) {
									MenuItem item = new MenuItem(menuChild.getText());
									item.setIcon(Resources.getInstance().getImage(menuChild.getIconCls()));
									item.setId(menuChild.getAction());
									menuComp.insert(item, y);
									y++;
								}
								btn.setMenu(menuComp);
							}else {
								btn.setItemId("parentOnly");
							}
						}
					
					});
					i++;
					toolBar.insert(new SeparatorToolItem(),i);
					i++;
					toolBar.forceLayout();
				}
				toolBar.add(new FillToolItem());
				toolBar.add(searchLabel);
		
				dateField.setId("dateFilter");
				dateField.setWidth(100);
				dateField.setAllowBlank(false);
				dateField.setEditable(false);
				dateField.setPropertyEditor(new DateTimePropertyEditor(DateTimeFormat
						.getFormat(DashboardConstant.DATE_FORMAT)));
				toolBar.add(dateField);
		
				searchButton.setIcon(Resources.IMAGES.search16());
				searchButton.addSelectHandler(selectHandler);
				searchButton.setId("searchButton");
				toolBar.add(searchButton);
				toolBar.add(new SeparatorToolItem());
				vlc.add(toolBar);
			}
		
		});
		
//		TextButton homeButton = new TextButton("Home");
//		homeButton.setIcon(Resources.IMAGES.home16());
//		homeButton.setId("homeButton");
//		homeButton.addSelectHandler(selectHandler);
//		toolBar.add(homeButton);
//		toolBar.add(new SeparatorToolItem());
//
//		TextButton eodButton = new TextButton("EOD");
//		eodButton.setIcon(Resources.IMAGES.process16());
//		
//		Menu menu = new Menu();
//		menu.addSelectionHandler(selectionHandler);
//
//		MenuItem item = new MenuItem("Eod Duration");
//		item.setIcon(Resources.IMAGES.table16());
//		item.setId("eodDuration");
//		menu.add(item);
//
//		item = new MenuItem("Eod Daily");
//		item.setIcon(Resources.IMAGES.table16());
//		item.setId("eodDaily");
//		menu.add(item);
//
//		eodButton.setMenu(menu);
//
//		toolBar.add(eodButton);
//		toolBar.add(new SeparatorToolItem());
//
//		TextButton ftpButton = new TextButton("FTP");
//		ftpButton.setIcon(Resources.IMAGES.ftp16());
//		menu = new Menu();
//		menu.addSelectionHandler(selectionHandler);
//
//		item = new MenuItem("Detail Trx Processed");
//		item.setId("detailTrx");
//		item.setIcon(Resources.IMAGES.chart16());
//		menu.add(item);
//
//		item = new MenuItem("Daily Synchronization Log");
//		item.setIcon(Resources.IMAGES.table16());
//		item.setId("dailySynchronizationLog");
//		menu.add(item);
//
//		ftpButton.setMenu(menu);
//
//		toolBar.add(ftpButton);
//		toolBar.add(new SeparatorToolItem());
//
//		TextButton helpdeskButton = new TextButton("Helpdesk");
//		helpdeskButton.setIcon(Resources.IMAGES.helpdesk16());
//		menu = new Menu();
//		menu.addSelectionHandler(selectionHandler);
//
//		item = new MenuItem("Helpdesk daily");
//		item.setIcon(Resources.IMAGES.table16());
//		item.setId("helpDeskDaily");
//		menu.add(item);
//		helpdeskButton.setMenu(menu);
//		toolBar.add(helpdeskButton);
//
//		toolBar.add(new SeparatorToolItem());
//
//		TextButton snapshotButton = new TextButton("Snapshot");
//		snapshotButton.setIcon(Resources.IMAGES.snapshot16());
//		menu = new Menu();
//		menu.addSelectionHandler(selectionHandler);
//
//		item = new MenuItem("Portfolio Snapshot Accumulation");
//		item.setIcon(Resources.IMAGES.table16());
//		item.setId("snapshotWisma");
//		menu.add(item);
//
//		item = new MenuItem("Portfolio Snapshot Daily");
//		item.setIcon(Resources.IMAGES.table16());
//		item.setId("snapshotPortfolio");
//		menu.add(item);
//
//		snapshotButton.setMenu(menu);
//
//		toolBar.add(snapshotButton);
//		toolBar.add(new SeparatorToolItem());
//
//		TextButton wisma = new TextButton("Wisma List");
//		wisma.setIcon(Resources.IMAGES.office16());
//		wisma.setId("wismaList");
//		wisma.addSelectHandler(selectHandler);
//		toolBar.add(wisma);
//
//		toolBar.add(new SeparatorToolItem());
//
//		TextButton dturEtlButton = new TextButton("DTUR");
//		dturEtlButton.setIcon(Resources.IMAGES.db16());
//		menu = new Menu();
//		menu.addSelectionHandler(selectionHandler);
//
//		item = new MenuItem("ETL Log");
//		item.setIcon(Resources.IMAGES.table16());
//		item.setId("etlLog");
//		menu.add(item);
//		dturEtlButton.setMenu(menu);
//		toolBar.add(dturEtlButton);
//
//		toolBar.add(new SeparatorToolItem());

//		toolBar.add(new FillToolItem());
//		toolBar.add(searchLabel);
//
//		dateField.setId("dateFilter");
//		dateField.setWidth(100);
//		dateField.setAllowBlank(false);
//		dateField.setEditable(false);
//		dateField.setPropertyEditor(new DateTimePropertyEditor(DateTimeFormat
//				.getFormat(DashboardConstant.DATE_FORMAT)));
//		toolBar.add(dateField);
//
//		searchButton.setIcon(Resources.IMAGES.search16());
//		searchButton.addSelectHandler(selectHandler);
//		searchButton.setId("searchButton");
//
//		// searchLayout.add(searchLabel);
//		// searchLayout.add(dateField);
//		// searchLayout.add(searchButton);
//		// searchLayout.setBorders(false);
//		// searchLayout.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
//
//		// toolBar.add(searchLayout);
//		toolBar.add(searchButton);
//		toolBar.add(new SeparatorToolItem());

//		vlc.add(toolBar);

		BorderLayoutData northData = new BorderLayoutData(
				DashboardConstant.HEIGHT_TOOLBAR);
		setNorthWidget(vlc, northData);

		MarginData centerData = new MarginData();
		centerData.setMargins(new Margins(0));

		visibleSearch(true);

		panelId = "homeButton";
		panelTitle = "Home";
		showPanel();

		setCenterWidget(center, centerData);

//		Timer timer = new Timer() {
//
//			@Override
//			public void run() {
//				Info.display("Session", "Session has expired");
//				
//				showLogin(this);
//				this.cancel();
//			}
//		};
//		//timer.scheduleRepeating(10000);// 10 detik
//		timer.schedule(100); 
//		//timer.run();
	}

//	private void showLogin(final Timer timer) {
//		final com.sencha.gxt.widget.core.client.Window w = new com.sencha.gxt.widget.core.client.Window();
//		w.setModal(true);
//		w.setBlinkModal(true);
//		w.setBorders(true);
//		w.setClosable(false);
//		w.setHeaderVisible(true);
//		w.setHeadingText("Login");
//		w.setOnEsc(false);
//		w.setShadow(true);
//		
//		center.clear();
//		
//		VerticalLayoutContainer p = new VerticalLayoutContainer();
//		
//		
//		final TextField userName = new TextField();
//		userName.setAllowBlank(false);
//		p.add(new FieldLabel(userName, "Name"), new VerticalLayoutData(
//				1, -1));
//
//		final PasswordField pass = new PasswordField();
//		pass.setAllowBlank(false);
//		p.add(new FieldLabel(pass, "Password"), new VerticalLayoutData(1,
//				-1));
//
//		w.add(p);
//		
//		TextButton loginButton = new TextButton("Login");
//		loginButton.addSelectHandler(new SelectHandler() {
//			
//			@Override
//			public void onSelect(SelectEvent event) {
//				if (pass.getText().equals("ismail")) {
//					w.hide();
//					panelId = "homeButton";
//					panelTitle = "Home";
//					showPanel();
//					Window.setTitle("Welcome " + userName.getText());
//					timer.schedule(20000);
//				}else {
//					Info.display("Login", "Invalid name or password !!!");
//				}
//			}
//		});
//		w.addButton(loginButton);
//		
//		w.show();
//	}

	private void showPanel() {
		center.clear();
		visibleSearch(true);

		if (panelId.equals("homeButton")) {
			center.add(new HomePanel(panelTitle, Resources.IMAGES.home16(),
					dateField.getCurrentValue()).asWidget());
		}

		if (panelId.equals("eodDaily")) {
			DashboardPanel panel = new EodDailyPanel(panelTitle,
					Resources.IMAGES.table16(), dateField.getCurrentValue());

			center.add(panel.asWidget());
		}

		if (panelId.equals("eodDuration")) {
			visibleSearch(false);
			center.add(new EodDurationPanel(panelTitle, Resources.IMAGES
					.table16()).asWidget());
		}

		if (panelId.equals("detailTrx")) {
			center.add(new FtpDetailTrxPanel(panelTitle, Resources.IMAGES
					.chart16(), dateField.getCurrentValue()).asWidget());
		}

		if (panelId.equals("dailySynchronizationLog")) {
			center.add(new FtpFailurePanel(panelTitle, Resources.IMAGES
					.table16(), dateField.getCurrentValue()).asWidget());
		}

		if (panelId.equals("helpdeskDaily")) {
			center.add(new HelpdeskDailyPanel(panelTitle, Resources.IMAGES
					.table16(), dateField.getCurrentValue()).asWidget());
		}

		if (panelId.equals("snapshotWisma")) {
			center.add(new SnapshotWismaPanel(panelTitle, Resources.IMAGES
					.table16(), dateField.getCurrentValue()).asWidget());
		}

		if (panelId.equals("snapshotPortfolio")) {
			center.add(new SnapshotDailyPanel(panelTitle, Resources.IMAGES
					.table16(), dateField.getCurrentValue()).asWidget());
		}

		if (panelId.equals("wismaList")) {
			visibleSearch(false);

			center.add(new WismaPanel(panelTitle, Resources.IMAGES.table16())
					.asWidget());
		}

		if (panelId.equals("etlLog")) {
			center.add(new DturEtlPanel(panelTitle, Resources.IMAGES.table16(),
					dateField.getCurrentValue()).asWidget());
		}
	}

	private void visibleSearch(boolean state) {
		searchLabel.setVisible(state);
		dateField.setVisible(state);
		searchButton.setVisible(state);
		// searchLayout.setVisible(state);
	}

	@Override
	protected void onWindowResize(int width, int height) {
		setPixelSize(width, height);
	}
}

// Calendar di gwt = import com.google.gwt.user.datepicker.client.CalendarUtil;
