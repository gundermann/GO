package de.nordakademie.wpk.tasklist.core.api;

import java.util.Set;

public interface TaskService {

	/**
	 * Return a list of all tasks and tasklists found on the provider.
	 * 
	 * @return
	 */
	Set<TaskList> loadAll(ProviderSetting setting);

	void updateTask(Task task, TaskList tasklist);

	void updateTaskList(TaskList tasklist);

	void addTaskList(TaskList tasklist);

	void addTask(Task task, TaskList tasklist);

	void deleteTask(Task task, TaskList tasklist);

	void deleteTaskList(TaskList tasklist);
}
