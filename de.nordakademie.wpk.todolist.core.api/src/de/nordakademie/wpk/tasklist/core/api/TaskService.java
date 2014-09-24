package de.nordakademie.wpk.tasklist.core.api;

import java.util.List;

public interface TaskService {

	/**
	 * Return a list of all tasks and tasklists found on the provider.
	 * 
	 * @return
	 */
	List<TaskList> loadAll(ProviderSetting setting);

	void updateTask(Task task, String tasklistId, ProviderSetting setting);

	void updateTaskList(TaskList tasklist, ProviderSetting setting);

	void addTaskList(TaskList tasklist,ProviderSetting setting);

	void addTask(Task task, String tasklistId, ProviderSetting setting);

	void deleteTask(Task task, TaskList tasklist, ProviderSetting setting);

	void deleteTaskList(TaskList tasklist, ProviderSetting setting);

	Task loadTask(String taskId, String tasklistId, ProviderSetting setting);
}
