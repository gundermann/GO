package de.nordakademie.wpk.tasklist.core.api;

import java.util.List;

public interface TaskService {

	/**
	 * Return a list of all tasks and tasklists found on the provider.
	 * 
	 * @return
	 */
	List<TaskList> loadAll(ProviderSetting setting) throws ServiceException;

	void updateTask(Task task, String tasklistId, ProviderSetting setting) throws ServiceException;

	void updateTaskList(TaskList tasklist, ProviderSetting setting) throws ServiceException;

	void addTaskList(TaskList tasklist,ProviderSetting setting) throws ServiceException;

	void addTask(Task task, String tasklistId, ProviderSetting setting) throws ServiceException;

	void deleteTask(String taskId, String tasklistId, ProviderSetting setting) throws ServiceException;

	void deleteTaskList(TaskList tasklist, ProviderSetting setting) throws ServiceException;

	Task loadTask(String taskId, String tasklistId, ProviderSetting setting) throws ServiceException;
}
