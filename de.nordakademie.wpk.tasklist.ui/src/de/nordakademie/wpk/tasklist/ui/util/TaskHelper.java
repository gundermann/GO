package de.nordakademie.wpk.tasklist.ui.util;

import java.util.Calendar;
import java.util.Date;

import de.nordakademie.wpk.tasklist.core.api.Task;


/**
 * Helper-Klasse für bestimmte Eigenschaften von Tasks innerhalb des Bundles.
 * @author Niels Gundermann
 *
 */
public class TaskHelper {

	public static boolean isTaskDueWithinThreeDays(Task task) {
		if (task.getDateOfDue() != null) {
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DAY_OF_MONTH, 3);
			Date dateInThreeDays = cal.getTime();

			if (dateInThreeDays.compareTo(task.getDateOfDue()) >= 0) {
				return true;
			}
		}
		return false;
	}

	


}
