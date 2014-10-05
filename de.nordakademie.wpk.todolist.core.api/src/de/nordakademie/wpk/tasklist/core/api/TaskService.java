package de.nordakademie.wpk.tasklist.core.api;

import java.util.List;

/**
 * Service, der auf dem Client vom Server erwartet wird und das Abgleichen der
 * Tasklisten und Tasks ermöglicht.
 * 
 * @author Niels Gundermann
 *
 */
public interface TaskService {

	/**
	 * Lädt alle Tasklisten eine Providers
	 * 
	 * @param setting
	 * @return Tasklisten
	 */
	List<TaskList> loadAll(ProviderSetting setting) throws ServiceException;

	/**
	 * Aktualisiert eine Task
	 * 
	 * @param task
	 * @param tasklistId
	 * @param setting
	 * @throws ServiceException
	 */
	void updateTask(Task task, String tasklistId, ProviderSetting setting)
			throws ServiceException;

	/**
	 * Aktualisiert eine Taskliste
	 * 
	 * @param tasklist
	 * @param setting
	 * @throws ServiceException
	 */
	void updateTaskList(TaskList tasklist, ProviderSetting setting)
			throws ServiceException;

	/**
	 * Fügt eine Taskliste hinzu
	 * 
	 * @param tasklist
	 * @param setting
	 * @throws ServiceException
	 */
	void addTaskList(TaskList tasklist, ProviderSetting setting)
			throws ServiceException;

	/**
	 * Fügt eine Task hinzu
	 * 
	 * @param task
	 * @param tasklistId
	 * @param setting
	 * @return
	 * @throws ServiceException
	 */
	String addTask(Task task, String tasklistId, ProviderSetting setting)
			throws ServiceException;

	/**
	 * Löscht eine Task
	 * 
	 * @param taskId
	 * @param tasklistId
	 * @param setting
	 * @throws ServiceException
	 */
	void deleteTask(String taskId, String tasklistId, ProviderSetting setting)
			throws ServiceException;

	/**
	 * Löscht eine Taskliste
	 * 
	 * @param tasklistId
	 * @param setting
	 * @throws ServiceException
	 */
	void deleteTaskList(String tasklistId, ProviderSetting setting)
			throws ServiceException;

	Task loadTask(String taskId, String tasklistId, ProviderSetting setting)
			throws ServiceException;
}
