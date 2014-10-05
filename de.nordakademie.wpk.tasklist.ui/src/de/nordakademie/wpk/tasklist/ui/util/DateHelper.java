package de.nordakademie.wpk.tasklist.ui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Helper-Klasse für den Umgabe mit Datums innerhalb des Bundles.
 * 
 * @author Niels Gundermann
 *
 */
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
					.append(cal.get(Calendar.MONTH) + 1).append(".")
					.append(cal.get(Calendar.YEAR));
			return sb.toString();
		}
		return "";
	}

	public static Date getDateFromString(String dateString) {
		if (dateString.equals(""))
			return null;
		Date parse = null;
		try {
			parse = SimpleDateFormat.getDateInstance().parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parse;
	}

	public static int getYear(Date dateOfDue) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateOfDue);
		return cal.get(Calendar.YEAR);
	}

	public static int getMonth(Date dateOfDue) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateOfDue);
		return cal.get(Calendar.MONTH);
	}

	public static int getDay(Date dateOfDue) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateOfDue);
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	public static Date getDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}

	public static String getDateTimeAsSting(Date date) {
		StringBuilder dateAsSting = new StringBuilder(getDateAsSting(date));
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		dateAsSting.append(" - ").append(cal.get(Calendar.HOUR_OF_DAY))
				.append(":").append(cal.get(Calendar.MINUTE)).append(":")
				.append(cal.get(Calendar.SECOND));
		return dateAsSting.toString();
	}

}
