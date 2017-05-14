package com.sp.core.backend;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
	public static DateTimeFormatter dateTimeFormatterShort = DateTimeFormatter.ofPattern("yyyy.MM.dd");
	
	public static String ofDate(int year, int mounth, int day) {
		return LocalDate.of(year, mounth, day).format(dateTimeFormatterShort);
	}
}
