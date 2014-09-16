package de.nordakademie.wpk.tasklist.core.server.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.nordakademie.wpk.tasklist.core.api.TaskList;

public class GoogleConverter {

	public Set<TaskList> convertTaskLists(
			List<com.google.api.services.tasks.model.TaskList> items) {
		Set<TaskList> tasklists = new HashSet<TaskList>();
		for (com.google.api.services.tasks.model.TaskList googleTaskList : items) {
			TaskList taskList = new TaskList();
			taskList.setId(googleTaskList.getId());
			taskList.setName(googleTaskList.getTitle());
			tasklists.add(taskList);
		}
		return tasklists ;
	}

}
