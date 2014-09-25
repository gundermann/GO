package de.nordakademie.wpk.tasklist.core.server.service;

import java.util.List;

import com.google.api.services.tasks.Tasks;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;

public class TaskServiceImpl implements TaskService {

	private GoogleConverter googleConverter;

	public TaskServiceImpl() {
		 googleConverter = new GoogleConverter();
	}

	public List<TaskList> loadAll(ProviderSetting setting) {
		return ProviderContainer.getInstance().delegateLoadAll(setting);
	}

	private Tasks getTaskService(ProviderSetting setting) {
		GoogleConnection googleConnection = new GoogleConnection(setting);
		Tasks tasksService = googleConnection.getTasksService();
		return tasksService;
	}

	public void updateTaskList(TaskList tasklist, ProviderSetting setting) {
		googleConverter.updateTaskList(getTaskService(setting), tasklist);
	}

	public void addTaskList(TaskList tasklist, ProviderSetting setting) {
		googleConverter.addTasklist(getTaskService(setting), tasklist);
	}

	public void deleteTask(Task task, TaskList tasklist, ProviderSetting setting) {
		googleConverter.deleteTask(getTaskService(setting), task, tasklist);

	}

	public void deleteTaskList(TaskList tasklist, ProviderSetting setting) {
		googleConverter.deleteTasklist(getTaskService(setting), tasklist);

	}

	public Task loadTask(String taskId, String tasklistId,
			ProviderSetting setting) {
		return googleConverter.convertTask(getTaskService(setting), taskId, tasklistId);
	}

	public void addTask(Task task, String tasklistId, ProviderSetting setting) {
		googleConverter.addTask(getTaskService(setting), task, tasklistId);
		
	}

	public void updateTask(Task task, String tasklistId, ProviderSetting setting) {
		googleConverter.updateTask(getTaskService(setting), task, tasklistId);
	}

}
