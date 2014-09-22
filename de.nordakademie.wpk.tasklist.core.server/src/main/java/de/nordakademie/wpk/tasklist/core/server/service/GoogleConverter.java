package de.nordakademie.wpk.tasklist.core.server.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.google.api.services.tasks.Tasks;
import com.google.api.services.tasks.model.TaskLists;

import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;

public class GoogleConverter {

	public Set<TaskList> convertTaskLists(
			Tasks tasksService) {
		Set<TaskList> convertedTasklists = new HashSet<TaskList>();
		TaskLists taskLists = null;
		try {
			taskLists = tasksService.tasklists().list().execute();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for (com.google.api.services.tasks.model.TaskList googleTaskList : taskLists.getItems()) {
			TaskList taskList = new TaskList();
			taskList.setId(googleTaskList.getId());
			taskList.setName(googleTaskList.getTitle());
			taskList.setProvider(Provider.GOOGLE);
			try {
				taskList.setTasks(convertGoogleTasks(tasksService.tasks().list(googleTaskList.getId())));
			} catch (IOException e) {
				e.printStackTrace();
			}
			convertedTasklists.add(taskList);
		}
		return convertedTasklists;
	}

	private Set<Task> convertGoogleTasks(
			com.google.api.services.tasks.Tasks.TasksOperations.List list) {
		Set<Task> convertedTasks = new HashSet<Task>(); 
		try {
			for (com.google.api.services.tasks.model.Task googleTask : list.execute().getItems()){
				Task task = new Task();
				task.setId(googleTask.getId());
				task.setTitle(googleTask.getTitle());
				convertedTasks.add(task);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertedTasks;
	}

}
