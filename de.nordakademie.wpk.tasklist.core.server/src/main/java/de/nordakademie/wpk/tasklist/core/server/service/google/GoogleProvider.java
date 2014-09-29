package de.nordakademie.wpk.tasklist.core.server.service.google;

import java.util.List;

import com.google.api.services.tasks.Tasks;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.server.service.ProviderService;

public class GoogleProvider implements ProviderService {

	private GoogleConverter googleConverter;
	private ProviderSetting setting;

	public GoogleProvider(ProviderSetting setting) {
		this.setting = setting;
		googleConverter = new GoogleConverter();
	}

	/* (non-Javadoc)
	 * @see de.nordakademie.wpk.tasklist.core.server.service.google.ProviderService#loadAll(de.nordakademie.wpk.tasklist.core.api.ProviderSetting)
	 */
	public List<TaskList> loadAll() {
		return googleConverter.convertTaskLists(getTaskService(setting));
	}

	private Tasks getTaskService(ProviderSetting setting) {
		GoogleConnection googleConnection = new GoogleConnection(setting);
		Tasks tasksService = googleConnection.getTasksService();
		return tasksService;
	}

	public void updateTaskList(TaskList tasklist) {
		googleConverter.updateTaskList(getTaskService(setting), tasklist);
	}

	public void addTaskList(TaskList tasklist) {
		googleConverter.addTasklist(getTaskService(setting), tasklist);
	}

	public void updateTask(Task task, String tasklistId) {
		googleConverter.updateTask(getTaskService(setting), task, tasklistId);
	}

	public void addTask(Task task, String tasklistId) {
		googleConverter.addTask(getTaskService(setting), task, tasklistId);
	}

	public Task loadTask(String taskId, String tasklistId) {
		return googleConverter.convertTask(getTaskService(setting), taskId, tasklistId);
	}

	/* (non-Javadoc)
	 * @see de.nordakademie.wpk.tasklist.core.server.service.google.ProviderService#deleteTaskList(java.lang.String, de.nordakademie.wpk.tasklist.core.api.ProviderSetting)
	 */
	public void deleteTaskList(String tasklistId) {
		googleConverter.deleteTasklist(getTaskService(setting), tasklistId);
	}

	/* (non-Javadoc)
	 * @see de.nordakademie.wpk.tasklist.core.server.service.google.ProviderService#deleteTask(java.lang.String, java.lang.String, de.nordakademie.wpk.tasklist.core.api.ProviderSetting)
	 */
	public void deleteTask(String task, String tasklist) {
		googleConverter.deleteTask(getTaskService(setting), task, tasklist);
	}

}
