package de.nordakademie.wpk.tasklist.ui.provider;

import java.util.Calendar;
import java.util.Date;

import de.nordakademie.wpk.tasklist.core.api.Task;

public class TaskHelper {

	public static boolean isTaskDueWithinThreeDays(Task task) {
		if (task.getDateOfDue() != null) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 3);
			Date dateInThreeDays = cal.getTime();
			
			if(!task.getStatus()){		

				if (dateInThreeDays.compareTo(task.getDateOfDue()) >= 0) {
				return true;
				}
			}
		}
		return false;
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


}
