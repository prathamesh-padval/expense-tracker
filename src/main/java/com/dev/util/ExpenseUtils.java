package com.dev.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExpenseUtils {

	public static Date convertDate(String dateStr) throws ParseException {
		return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").parse(dateStr);

	}

	public static String fetchMonth(Date date) {
		return new SimpleDateFormat("MMM").format(date);
	}

	public static String fetchYear(Date date) {
		return new SimpleDateFormat("yyyy").format(date);
	}
	
	
	public static void main(String[] args) throws ParseException {
		System.out.println(convertDate("28-12-2020 16:29:00"));
	}
}
