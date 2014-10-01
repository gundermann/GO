package de.nordakademie.wpk.tasklist.core.server.service.wunderlist;

import in.co.madhur.wunderjava.api.model.WList;
import in.co.madhur.wunderjava.api.model.WTask;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;

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
			try {
				return DateFormat.getInstance().parse((String) completed_at);
			} catch (ParseException e) {
				return null;
			}
		} else
			return null;
	}

	public String convertJavaDate(Date dateOfDue) {
		return SimpleDateFormat.getInstance().format(dateOfDue);
	}

}
