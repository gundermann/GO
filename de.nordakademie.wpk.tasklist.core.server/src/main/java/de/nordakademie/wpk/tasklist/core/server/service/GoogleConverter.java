package de.nordakademie.wpk.tasklist.core.server.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.tasks.Tasks;
import com.google.api.services.tasks.model.TaskLists;

import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;

public class GoogleConverter {

	public List<TaskList> convertTaskLists(Tasks tasksService) {
		List<TaskList> convertedTasklists = new ArrayList<TaskList>();
		TaskLists taskLists = null;
		try {
			taskLists = tasksService.tasklists().list().execute();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(taskLists.getItems().size() + " lists found");
		for (com.google.api.services.tasks.model.TaskList googleTaskList : taskLists
				.getItems()) {
			TaskList taskList = new TaskList();
			taskList.setId(googleTaskList.getId());
			taskList.setName(googleTaskList.getTitle());
			System.out.println(taskList.getName());
			taskList.setProvider(Provider.GOOGLE);
			try {
				taskList.setTasks(convertGoogleTasks(tasksService.tasks().list(
						googleTaskList.getId())));
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				convertedTasklists.add(taskList);
			}
		}
		for (TaskList taskList : convertedTasklists) {
			System.out.println(taskList.getName());
		}
		return convertedTasklists;
	}

	private List<Task> convertGoogleTasks(
			com.google.api.services.tasks.Tasks.TasksOperations.List list) {
		List<Task> convertedTasks = new ArrayList<Task>();
		try {
			for (com.google.api.services.tasks.model.Task googleTask : list
					.execute().getItems()) {
				Task task = new Task();
				task.setId(googleTask.getId());
				task.setTitle(googleTask.getTitle());
				System.out.println("Task " + task.getTitle() + " converted");
				convertedTasks.add(task);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertedTasks;
	}

}
