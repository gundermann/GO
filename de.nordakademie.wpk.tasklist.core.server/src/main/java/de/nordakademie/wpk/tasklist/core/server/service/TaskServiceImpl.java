package de.nordakademie.wpk.tasklist.core.server.service;

import java.util.List;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;

/**
 * Implementation des TaskService, der an den Client übermittelt wird
 * 
 * @author Niels Gundermann
 *
 */
public class TaskServiceImpl implements TaskService {

	public TaskServiceImpl() {
	}

	public List<TaskList> loadAll(ProviderSetting setting)
			throws ServiceException {
		try {
			return ProviderContainer.getInstance().getProvider(setting)
					.loadAll();
		} catch (ProviderNotImplementedException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void updateTaskList(TaskList tasklist, ProviderSetting setting)
			throws ServiceException {
		try {
			ProviderContainer.getInstance().getProvider(setting)
					.updateTaskList(tasklist);
		} catch (ProviderNotImplementedException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public void addTaskList(TaskList tasklist, ProviderSetting setting)
			throws ServiceException {
		try {
			ProviderContainer.getInstance().getProvider(setting)
					.addTaskList(tasklist);
		} catch (ProviderNotImplementedException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void deleteTask(String task, String tasklist, ProviderSetting setting)
			throws ServiceException {
		try {
			ProviderContainer.getInstance().getProvider(setting)
					.deleteTask(task, tasklist);
		} catch (ProviderNotImplementedException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void deleteTaskList(String tasklistId, ProviderSetting setting)
			throws ServiceException {
		try {
			ProviderContainer.getInstance().getProvider(setting)
					.deleteTaskList(tasklistId);
		} catch (ProviderNotImplementedException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Task loadTask(String taskId, String tasklistId,
			ProviderSetting setting) throws ServiceException {
		try {
			return ProviderContainer.getInstance().getProvider(setting)
					.loadTask(taskId, tasklistId);
		} catch (ProviderNotImplementedException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public String addTask(Task task, String tasklistId, ProviderSetting setting)
			throws ServiceException {
		try {
			return ProviderContainer.getInstance().getProvider(setting)
					.addTask(task, tasklistId);
		} catch (ProviderNotImplementedException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void updateTask(Task task, String tasklistId, ProviderSetting setting)
			throws ServiceException {
		try {
			ProviderContainer.getInstance().getProvider(setting)
					.updateTask(task, tasklistId);
		} catch (ProviderNotImplementedException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
