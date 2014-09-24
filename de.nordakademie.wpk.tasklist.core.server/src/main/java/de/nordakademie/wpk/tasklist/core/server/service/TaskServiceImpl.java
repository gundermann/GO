package de.nordakademie.wpk.tasklist.core.server.service;

import java.util.List;

import com.google.api.services.tasks.Tasks;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;

public class TaskServiceImpl implements TaskService {

	public TaskServiceImpl() {
	}

	public List<TaskList> loadAll(ProviderSetting setting) {
		GoogleConverter googleConverter = new GoogleConverter();
		return googleConverter.convertTaskLists(getTaskService(setting));
	}

	private Tasks getTaskService(ProviderSetting setting) {
		GoogleConnection googleConnection = new GoogleConnection(setting);
		Tasks tasksService = googleConnection.getTasksService();
		return tasksService;
	}

	public void updateTaskList(TaskList tasklist) {
		// TODO Auto-generated method stub

	}

	public void addTaskList(TaskList tasklist) {
		// TODO Auto-generated method stub

	}

	public void deleteTask(Task task, TaskList tasklist) {
		// TODO Auto-generated method stub

	}

	public void deleteTaskList(TaskList tasklist) {
		// TODO Auto-generated method stub

	}

	public Task loadTask(String taskId, String tasklistId,
			ProviderSetting setting) {
		GoogleConverter googleConverter = new GoogleConverter();
		return googleConverter.convertTask(getTaskService(setting), taskId, tasklistId);
	}

	public void addTask(Task task, String tasklistId, ProviderSetting setting) {
		// TODO Auto-generated method stub
		
	}

	public void updateTask(Task task, String tasklistId, ProviderSetting setting) {
		GoogleConverter googleConverter = new GoogleConverter();
		googleConverter.updateTask(getTaskService(setting), task, tasklistId);
	}

}
