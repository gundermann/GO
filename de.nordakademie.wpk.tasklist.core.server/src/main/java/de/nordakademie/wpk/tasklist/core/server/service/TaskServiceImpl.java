package de.nordakademie.wpk.tasklist.core.server.service;

import java.util.List;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;

public class TaskServiceImpl implements TaskService {

	public TaskServiceImpl() {
	}

	public List<TaskList> loadAll(ProviderSetting setting) throws ServiceException {
		return ProviderContainer.getInstance().getProvider(setting).loadAll();
	}


	public void updateTaskList(TaskList tasklist, ProviderSetting setting) throws ServiceException {
		ProviderContainer.getInstance().getProvider(setting).updateTaskList(tasklist);

	}

	public void addTaskList(TaskList tasklist, ProviderSetting setting) throws ServiceException {
		ProviderContainer.getInstance().getProvider(setting).addTaskList(tasklist);
	}

	public void deleteTask(String task, String tasklist, ProviderSetting setting) throws ServiceException {
		ProviderContainer.getInstance().getProvider(setting).deleteTask(task, tasklist);
	}

	public void deleteTaskList(String tasklistId, ProviderSetting setting) throws ServiceException {
		ProviderContainer.getInstance().getProvider(setting).deleteTaskList(tasklistId);
	}

	public Task loadTask(String taskId, String tasklistId,
			ProviderSetting setting) throws ServiceException {
		return ProviderContainer.getInstance().getProvider(setting).loadTask(taskId, tasklistId);
	}

	public void addTask(Task task, String tasklistId, ProviderSetting setting) throws ServiceException {
		ProviderContainer.getInstance().getProvider(setting).addTask(task, tasklistId);
	}

	public void updateTask(Task task, String tasklistId, ProviderSetting setting) throws ServiceException {
		ProviderContainer.getInstance().getProvider(setting).updateTask(task, tasklistId);
	}

}
