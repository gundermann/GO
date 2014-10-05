package de.nordakademie.wpk.tasklist.core.server.service;

import java.util.List;

import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;

/**
 * Einheitliches API f�r die unterschliedlichen Quellen aus denen Tasks
 * bearbeitet werden k�nnen
 * 
 * @author Niels Gundermann
 *
 */
public interface ProviderService {

	/**
	 * L�dt alle Tasklisten vom Provider
	 * @return List<TaskList>
	 * @throws ServiceException
	 */
	List<TaskList> loadAll() throws ServiceException;

	/**
	 * Aktualisiert eine Taskliste beim Provider
	 * @param tasklist
	 * @throws ServiceException
	 */
	void updateTaskList(TaskList tasklist) throws ServiceException;

	/**
	 * F�gt eine neue Taskliste in eine Taskliste beim Provider hinzu
	 * @param tasklist
	 * @throws ServiceException
	 */
	void addTaskList(TaskList tasklist) throws ServiceException;

	/**
	 * Aktualisiert eine Task beim Provider
	 * @param task
	 * @param tasklistId
	 * @throws ServiceException
	 */
	void updateTask(Task task, String tasklistId) throws ServiceException;

	/**
	 * F�gt eine neue Task in eine Taskliste beim Provider hinzu
	 * @param task
	 * @param tasklistId
	 * @return
	 * @throws ServiceException
	 */
	String addTask(Task task, String tasklistId) throws ServiceException;

	/**
	 * L�dt eine Task vom Provider
	 * @param taskId
	 * @param tasklistId
	 * @return
	 * @throws ServiceException
	 */
	Task loadTask(String taskId, String tasklistId) throws ServiceException;

	/**
	 * L�scht eine Taskliste beim Provider
	 * @param tasklistId
	 * @throws ServiceException
	 */
	void deleteTaskList(String tasklistId) throws ServiceException;

	/**
	 * L�scht eine Task beim Provider
	 * @param task
	 * @param tasklist
	 * @throws ServiceException
	 */
	void deleteTask(String task, String tasklist) throws ServiceException;

}