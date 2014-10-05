package de.nordakademie.wpk.tasklist.core.server.service.wunderlist;

import in.co.madhur.wunderjava.api.model.WList;
import in.co.madhur.wunderjava.api.model.WTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;

/**
 * Konverterklasse für den Wunderlistprovider
 * 
 * @author Niels Gundermann
 *
 */
public class WunderlistConverter {

	public List<TaskList> convertLists(List<WList> wLists, List<WTask> wTasks) {
		List<TaskList> tasklists = new ArrayList<TaskList>();
		for (WList wList : wLists) {
			TaskList convertTasklist = convertTasklist(wList, wTasks);
			tasklists.add(convertTasklist);
		}
		return tasklists;
	}

	private TaskList convertTasklist(WList wList, List<WTask> wTasks) {
		TaskList tasklist = new TaskList();
		tasklist.setId(wList.getId());
		tasklist.setName(wList.getTitle());
		tasklist.setProvider(Provider.WUNDERLIST);
		List<Task> convertedTasks = new ArrayList<Task>();
		for (WTask wTask : wTasks) {
			if (wTask.getList_id().equals(tasklist.getId())) {
				Task convertTask = convertTask(wTask);
				convertedTasks.add(convertTask);
			}
		}
		tasklist.setTasks(convertedTasks);
		return tasklist;
	}

	public Task convertTask(WTask wTask) {
		Task task = new Task();
		task.setId(wTask.getId());
		task.setTitle(wTask.getTitle());
		task.setComment(wTask.getNote());
		task.setLastSync(Calendar.getInstance().getTime());
		task.setDateOfCompletion(convertDateToJava(wTask.getCompleted_at()));
		task.setStatus(wTask.getCompleted_at() != null);
		task.setDateOfDue(convertDateToJava(wTask.getDue_date()));
		task.setPosition(wTask.getPosition() != null ? wTask.getPosition()
				.longValue() : null);
		return task;
	}

	private Date convertDateToJava(Object completed_at) {
		if (completed_at instanceof String) {
			System.out.println(completed_at);
			String[] splitDate = ((String) completed_at).split("-");
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(splitDate[0]));
			cal.set(Calendar.MONTH, Integer.parseInt(splitDate[1])-1);
			if (splitDate.length > 2) {
				String day = splitDate[2].split("T")[0];
				cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));
			} else {
				cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(splitDate[2]));
			}
			return cal.getTime();
		} else
			return null;
	}

	public String convertJavaDate(Date dateOfDue) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dateOfDue);
		StringBuilder sb = new StringBuilder();
		sb.append(cal.get(Calendar.YEAR)).append("-")
				.append(cal.get(Calendar.MONTH)+1).append("-")
				.append(cal.get(Calendar.DAY_OF_MONTH));
		return sb.toString();
	}

}
