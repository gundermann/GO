package de.nordakademie.wpk.tasklist.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

	public static String getCurrentDateAsString() {
		return getDateAsSting(Calendar.getInstance().getTime());
	}

	public static String getDateAsSting(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			StringBuilder sb = new StringBuilder();
			sb.append(cal.get(Calendar.DAY_OF_MONTH)).append(".")
					.append(cal.get(Calendar.MONTH)+1).append(".")
					.append(cal.get(Calendar.YEAR));
			return sb.toString();
		}
		return "";
	}

	public static Date getDateFromString(String dateString) {
		if(dateString.equals(""))
			return null;
		Calendar cal = Calendar.getInstance();
		Date parse = null;
		try {
			parse = SimpleDateFormat.getDateInstance().parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
//		String[] seperatedDateString = dateString.split(".");
//
//		cal.set(Integer.parseInt(seperatedDateString[2]),
//				Integer.parseInt(seperatedDateString[1]),
//				Integer.parseInt(seperatedDateString[0]));
		return parse;
	}

}
