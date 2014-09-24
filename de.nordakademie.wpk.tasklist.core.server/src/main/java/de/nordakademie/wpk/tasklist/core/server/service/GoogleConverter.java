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
			List<Task> convertGoogleTasks = convertGoogleTasks(tasksService,
					taskList.getId());
			taskList.setTasks(convertGoogleTasks);
			convertedTasklists.add(taskList);
		}
		for (TaskList taskList : convertedTasklists) {
			System.out.println(taskList.getName());
		}
		return convertedTasklists;
	}

	private List<Task> convertGoogleTasks(Tasks tasksService, String tasklistId) {
		com.google.api.services.tasks.Tasks.TasksOperations.List list = null;
		try {
			list = tasksService.tasks().list(tasklistId);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		List<Task> convertedTasks = new ArrayList<Task>();
		try {
			for (com.google.api.services.tasks.model.Task googleTask : list
					.execute().getItems()) {
				Task task = new Task();
				task.setId(googleTask.getId());
				task.setTitle(googleTask.getTitle());
				task.setComment(googleTask.getNotes());
				task.setStatus(googleTask.getCompleted() != null);
				System.out.println(googleTask.getCompleted());
				task.setLastSync(null);
				task.setDateOfDue(null);
				task.setDateOfCompletion(null);
				convertedTasks.add(task);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertedTasks;
	}

	public Task convertTask(Tasks tasksService, String taskId, String tasklistId) {
		Task task = null;
		List<Task> convertGoogleTasks = convertGoogleTasks(tasksService,
				tasklistId);
		for (Task taskInTasklist : convertGoogleTasks) {
			if (taskInTasklist.getId().equals(taskId)) {
				task = taskInTasklist;
				break;
			}
		}
		return task;
	}

	public void updateTask(Tasks taskService, Task task, String tasklistId) {
		com.google.api.services.tasks.model.Task googleTask = findGoogleTask(
				taskService, tasklistId, task.getId());
		googleTask.setTitle(task.getTitle());
		googleTask.setNotes(task.getComment());
		// TODO andere Eigenschaften
		try {
			taskService.tasks()
					.update(tasklistId, googleTask.getId(), googleTask)
					.execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private com.google.api.services.tasks.model.Task findGoogleTask(
			Tasks taskService, String tasklistId, String taskId) {
		com.google.api.services.tasks.model.Task foundGoogleTask = null;
		com.google.api.services.tasks.Tasks.TasksOperations.List list = null;
		try {
			list = taskService.tasks().list(tasklistId);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			for (com.google.api.services.tasks.model.Task googleTask : list
					.execute().getItems()) {
				if (googleTask.getId().equals(taskId)) {
					foundGoogleTask = googleTask;
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return foundGoogleTask;
	}

}
