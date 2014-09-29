package de.nordakademie.wpk.tasklist.core.server.service;

import java.util.List;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;

public interface ProviderService {

	List<TaskList> loadAll();

	void updateTaskList(TaskList tasklist);

	void addTaskList(TaskList tasklist);

	void updateTask(Task task, String tasklistId);

	void addTask(Task task, String tasklistId);

	Task loadTask(String taskId, String tasklistId);

	void deleteTaskList(String tasklistId);

	void deleteTask(String task, String tasklist);

}