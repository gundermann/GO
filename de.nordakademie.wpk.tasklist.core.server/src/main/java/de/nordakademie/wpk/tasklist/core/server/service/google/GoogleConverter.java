package de.nordakademie.wpk.tasklist.core.server.service.google;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.api.client.util.DateTime;
import com.google.api.services.tasks.Tasks;

import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;

public class GoogleConverter {

	public TaskList convertTaskList(
			com.google.api.services.tasks.model.TaskList gList,
			com.google.api.services.tasks.model.Tasks gTasks) {
		TaskList taskList = new TaskList();
		taskList.setId(gList.getId());
		taskList.setName(gList.getTitle());
		taskList.setProvider(Provider.GOOGLE);
		List<Task> tasks = new ArrayList<Task>();
		if (gTasks != null) {
			for (com.google.api.services.tasks.model.Task gTask : gTasks
					.getItems()) {
				Task task = convertTask(gTask);
				tasks.add(task);
			}
		}
		taskList.setTasks(tasks);
		return taskList;
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
				Task task = convertTask(googleTask);
				convertedTasks.add(task);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return convertedTasks;
	}

	public Task convertTask(com.google.api.services.tasks.model.Task googleTask) {
		Task task = new Task();
		task.setId(googleTask.getId());
		task.setTitle(googleTask.getTitle());
		task.setComment(googleTask.getNotes());
		task.setPosition(Long.parseLong(googleTask.getPosition()));
		task.setStatus(convertStatusFromGoogle(googleTask.getStatus()));
		task.setLastSync(Calendar.getInstance().getTime());
		task.setDateOfDue(convertToJavaDate(googleTask.getDue()));
		task.setDateOfCompletion(convertToJavaDate(googleTask.getCompleted()));
		return task;
	}

	private Boolean convertStatusFromGoogle(String status) {
		return status.equals("completed") ? true : false;
	}

	private Date convertToJavaDate(DateTime due) {
		return due != null ? new Date(due.getValue()) : null;
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
		setupGoogleTaskFromTask(googleTask, task);
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

	public void addTask(Tasks taskService, Task task, String tasklistId) {
		com.google.api.services.tasks.model.Task googleTask = new com.google.api.services.tasks.model.Task();
		setupGoogleTaskFromTask(googleTask, task);
		try {
			taskService.tasks().insert(tasklistId, googleTask).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setupGoogleTaskFromTask(
			com.google.api.services.tasks.model.Task googleTask, Task task) {
		googleTask.setTitle(task.getTitle());
		googleTask.setNotes(task.getComment());
		googleTask.setStatus(convertToGoogleStatus(task.getStatus()));
		googleTask.setCompleted(convertJavaToGooleDate(task
				.getDateOfCompletion()));
		googleTask.setDue(convertJavaToGooleDate(task.getDateOfDue()));
		googleTask.setPosition(String.valueOf(task.getPosition()));
	}

	private String convertToGoogleStatus(Boolean status) {
		return status == true ? "completed" : "needsAction";
	}

	private DateTime convertJavaToGooleDate(Date date) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		DateTime dateTime = new DateTime(cal.getTime());
		return dateTime;
	}

	public void deleteTasklist(Tasks taskService, String tasklistId) {
		try {
			taskService.tasklists().delete(tasklistId).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteTask(Tasks taskService, String task, String tasklist) {
		try {
			taskService.tasks().delete(tasklist, task).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public void updateTaskList(Tasks taskService, TaskList tasklist) {
	// com.google.api.services.tasks.model.TaskList googleTasklist;
	// try {
	// googleTasklist = taskService.tasklists().get(tasklist.getId()).execute();
	// googleTasklist.setTitle(tasklist.getName());
	// taskService.tasklists().update(tasklist.getId(),
	// googleTasklist).execute();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

	public void addTasklist(Tasks taskService, TaskList tasklist) {
		com.google.api.services.tasks.model.TaskList googleTasklist = new com.google.api.services.tasks.model.TaskList();
		googleTasklist.setTitle(tasklist.getName());
		try {
			taskService.tasklists().insert(googleTasklist).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public com.google.api.services.tasks.model.TaskList convertTaskList(
			TaskList tasklist,
			com.google.api.services.tasks.model.TaskList gTasklist) {
		gTasklist.setTitle(tasklist.getName());
		return gTasklist;
	}

	public com.google.api.services.tasks.model.TaskList createNewGoogleTaskList(
			TaskList tasklist) {
		com.google.api.services.tasks.model.TaskList gTasklist = new com.google.api.services.tasks.model.TaskList();
		gTasklist.setTitle(tasklist.getName());
		return gTasklist;
	}

	public com.google.api.services.tasks.model.Task convertTask(Task task,
			com.google.api.services.tasks.model.Task gTask) {
		setupGoogleTaskFromTask(gTask, task);
		return gTask;
	}

}
