package de.nordakademie.wpk.tasklist.core.server.service.wunderlist;

import in.co.madhur.wunderjava.api.model.WList;
import in.co.madhur.wunderjava.api.model.WTask;

import java.util.ArrayList;
import java.util.Calendar;
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

	private Task convertTask(WTask wTask) {
		Task task = new Task();
		task.setId(wTask.getId());
		task.setTitle(wTask.getTitle());
		task.setComment(wTask.getNote());
		task.setLastSync(Calendar.getInstance().getTime());
		// HIER FEHLEN EIGENSCHAFTEN
		return task;
	}

}
