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
		return ProviderContainer.getInstance().delegateLoadAll(setting);
	}


	public void updateTaskList(TaskList tasklist, ProviderSetting setting) throws ServiceException {
		ProviderContainer.getInstance().delegateUpdateTaskList(tasklist, setting);

	}

	public void addTaskList(TaskList tasklist, ProviderSetting setting) throws ServiceException {
		ProviderContainer.getInstance().delegateAddTaskList(tasklist, setting);
	}

	public void deleteTask(String task, String tasklist, ProviderSetting setting) throws ServiceException {
		ProviderContainer.getInstance().delegateDeleteTask(task, tasklist, setting);
	}

	public void deleteTaskList(String tasklistId, ProviderSetting setting) throws ServiceException {
		ProviderContainer.getInstance().delegateDeleteTaskList(tasklistId, setting);
	}

	public Task loadTask(String taskId, String tasklistId,
			ProviderSetting setting) throws ServiceException {
		return ProviderContainer.getInstance().delegateLoadTask(taskId, tasklistId, setting);
	}

	public void addTask(Task task, String tasklistId, ProviderSetting setting) throws ServiceException {
		ProviderContainer.getInstance().delegateAddTask(task, tasklistId, setting);
	}

	public void updateTask(Task task, String tasklistId, ProviderSetting setting) throws ServiceException {
		ProviderContainer.getInstance().delegateUpdateTask(task, tasklistId, setting);
	}

}
