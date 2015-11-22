package com.btpns.Dashboard.shared;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateTimeUtil {
	
	private static DateTimeUtil instance = null;
	
	private SimpleDateFormat dateFormat;
	private DateTimeUtil() {
		dateFormat = new SimpleDateFormat(DashboardConstant.DATE_FORMAT);
	}
	
	public static DateTimeUtil getInstance() {
		if (instance==null) {
			instance = new DateTimeUtil();
		}
		return instance;
	}
	
	public Date getDate(String dateString) {
		Date dateRet = new Date();
		try {
			dateRet = dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateRet;
	}
	
	public Date getDate(String dateString,String pattern) {
		Date dateRet = new Date();
		try {
			dateFormat = new SimpleDateFormat(pattern);
			dateRet = dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateRet;
	}
	
	public String getDateString(Date date) {
		return dateFormat.format(date);
	}
	
	public String getDateString(Date date,String pattern) {
		dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}
	
	public Date getDateWithoutTime(Date date) {
		Date dateRet = new Date();
		try {
			dateRet = dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
			try {
				dateRet = dateFormat.parse(dateFormat.format(dateRet));
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return dateRet;
	}
	
	public String DurationInString(Integer hour,Integer minute,Integer second) {
		StringBuilder stringBuilder = new StringBuilder();
		String hourLabel=(hour>1)? " Hours ":" Hour ";
		String minuteLabel=(minute>1)?" Minutes ":" Minute ";
		String secondLabel=(second==0)?"":(second>1)?" Seconds":" Second";
		
		if (hour>0 && minute>0) {
			stringBuilder.append(hour + hourLabel + minute + minuteLabel);
		}else if (hour>0 && minute==0) {
			stringBuilder.append(hour + hourLabel);
		}else if (minute>0){
			stringBuilder.append(minute + minuteLabel);
		}else {
			stringBuilder.append(second + secondLabel);
		}
		
		return stringBuilder.toString();
	}
}
