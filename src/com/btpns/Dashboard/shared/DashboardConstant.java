package com.btpns.Dashboard.shared;

import java.math.BigDecimal;

public class DashboardConstant {
	public static Integer HEIGHT_PAGING_TOOLBAR = 35;
	public static Integer HEIGHT_TOOLBAR = 70;
	public static Integer HEIGHT_TAB_GRID = 90;
	public static Integer HEIGHT_GRID = 62;
	
	public static Integer PAGE_SIZE = 50;
	public static String DATE_FORMAT = "yyyy-MM-dd";
	public static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static Integer SIZE_NO = 25;
	public static String INTEGER_FORMAT = "#,##0";
	public static String DECIMAL_FORMAT = "#,##0.00";
	public static BigDecimal BILLION_ROUND = new BigDecimal(1000000000);
	public static String EMPTY_STRING = "";
	
	public static String FOLDER_EXCEL = "excel/";
	
	public static Integer getHeightGrid(Integer heightWindow,Boolean hasTabPanel) {
		Integer  height_with_tab = HEIGHT_TOOLBAR+HEIGHT_TAB_GRID;
		Integer  height_no_tab = HEIGHT_TOOLBAR+HEIGHT_GRID;
		
		Integer height=0;
		Integer state=0;
		if (hasTabPanel) {
			state=height_with_tab;
		}else {
			state=height_no_tab;
		}
		
		height=heightWindow-state;
		return height;
	}
}
