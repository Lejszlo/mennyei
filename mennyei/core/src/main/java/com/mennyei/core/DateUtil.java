package com.mennyei.core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	public static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
	
	public static String ofDate(int year, int mounth, int day) {
		return LocalDate.of(1988, 11, 19).format(dateTimeFormatter);
	}
}
