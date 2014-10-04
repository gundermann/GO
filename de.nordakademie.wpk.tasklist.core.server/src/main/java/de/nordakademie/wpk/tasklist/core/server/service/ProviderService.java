package de.nordakademie.wpk.tasklist.core.server.service;

import java.util.List;

import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;

/**
 * Einheitliches API für die unterschliedlichen Quellen aus denen Tasks
 * bearbeitet werden können
 * 
 * @author Niels Gundermann
 *
 */
public interface ProviderService {

	List<TaskList> loadAll() throws ServiceException;

	void updateTaskList(TaskList tasklist) throws ServiceException;

	void addTaskList(TaskList tasklist) throws ServiceException;

	void updateTask(Task task, String tasklistId) throws ServiceException;

	String addTask(Task task, String tasklistId) throws ServiceException;

	Task loadTask(String taskId, String tasklistId) throws ServiceException;

	void deleteTaskList(String tasklistId) throws ServiceException;

	void deleteTask(String task, String tasklist) throws ServiceException;

}