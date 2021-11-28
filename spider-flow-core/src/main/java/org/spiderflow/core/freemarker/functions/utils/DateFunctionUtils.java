package org.spiderflow.core.freemarker.functions.utils;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * 时间获取/格式化 工具类 防止NPE 默认格式(yyyy-MM-dd HH:mm:ss)
 * @author Administrator
 *
 */
public class DateFunctionUtils {
	
	private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static String format(Date date) {
		return format(date, DEFAULT_PATTERN);
	}

	public static String format(Long millis) {
		return format(millis, DEFAULT_PATTERN);
	}

	public static String format(Date date, String pattern) {
		return date != null ? DateFormatUtils.format(date, pattern) : null;
	}

	public static String format(Long millis, String pattern) {
		return millis != null ? DateFormatUtils.format(millis, pattern) : null;
	}
	
	public static Date parse(String date) throws ParseException{
		return date != null ? DateUtils.parseDate(date, DEFAULT_PATTERN) : null;
	}

	public static Date parse(String date,String ... patterns) throws ParseException{
		return date != null ? DateUtils.parseDate(date, patterns) : null;
	}
	
	public static Date parse(Long millis){
		return new Date(millis);
	}
	
	public static Date now(){
		return new Date();
	}
	
	public static Date addYears(Date date,int amount){
		return DateUtils.addYears(date, amount);
	}
	
	public static Date addMonths(Date date,int amount){
		return DateUtils.addMonths(date, amount);
	}
	
	public static Date addDays(Date date,int amount){
		return DateUtils.addDays(date, amount);
	}
	
	public static Date addHours(Date date,int amount){
		return DateUtils.addHours(date, amount);
	}
	
	public static Date addMinutes(Date date,int amount){
		return DateUtils.addMinutes(date, amount);
	}
	
	public static Date addSeconds(Date date,int amount){
		return DateUtils.addSeconds(date, amount);
	}

}
