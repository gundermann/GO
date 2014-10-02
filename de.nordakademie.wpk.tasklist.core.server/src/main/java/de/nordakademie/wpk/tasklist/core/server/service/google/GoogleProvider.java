package de.nordakademie.wpk.tasklist.core.server.service.google;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.api.services.tasks.Tasks;
import com.google.api.services.tasks.model.TaskLists;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
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

	public List<TaskList> loadAll() throws ServiceException {
		List<TaskList> tasklists = new ArrayList<TaskList>();
		try {
			TaskLists gTasklists = getTaskService(setting).tasklists().list()
					.execute();
			for (com.google.api.services.tasks.model.TaskList gList : gTasklists
					.getItems()) {
				com.google.api.services.tasks.model.Tasks gTasks = getTaskService(
						setting).tasks().list(gList.getId()).execute();
				tasklists.add(googleConverter.convertTaskList(gList, gTasks));
			}
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		return tasklists;
	}

	private Tasks getTaskService(ProviderSetting setting) {
		GoogleConnection googleConnection = new GoogleConnection(setting);
		Tasks tasksService = googleConnection.getTasksService();
		return tasksService;
	}

	public void updateTaskList(TaskList tasklist) throws ServiceException {
		try {
			com.google.api.services.tasks.model.TaskList gTasklist = getTaskService(
					setting).tasklists().get(tasklist.getId()).execute();
			getTaskService(setting)
					.tasklists()
					.update(tasklist.getId(),
							googleConverter
									.convertTaskList(tasklist, gTasklist))
					.execute();
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void addTaskList(TaskList tasklist) throws ServiceException {
		try {
			com.google.api.services.tasks.model.TaskList gTaslist = googleConverter
					.createNewGoogleTaskList(tasklist);
			getTaskService(setting).tasklists().insert(gTaslist).execute();
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void updateTask(Task task, String tasklistId)
			throws ServiceException {
		try {
			com.google.api.services.tasks.model.Task gTask = getTaskService(
					setting).tasks().get(tasklistId, task.getId()).execute();
			gTask = googleConverter.convertTask(task, gTask);
			getTaskService(setting).tasks()
					.update(tasklistId, task.getId(), gTask).execute();
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void addTask(Task task, String tasklistId) throws ServiceException {
		try {
			com.google.api.services.tasks.model.Task gTask = new com.google.api.services.tasks.model.Task();
			getTaskService(setting)
					.tasks()
					.insert(tasklistId,
							googleConverter.convertTask(task, gTask)).execute();
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public Task loadTask(String taskId, String tasklistId) throws ServiceException {
		Task task = null;
		try {
			com.google.api.services.tasks.model.Tasks gTasks = getTaskService(
					setting).tasks().list(tasklistId).execute();
			for (com.google.api.services.tasks.model.Task gTask : gTasks
					.getItems()) {
				if (gTask.getId().equals(taskId)) {
					task = googleConverter.convertTask(gTask);
					break;
				}
			}
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		return task;
	}

	public void deleteTaskList(String tasklistId) throws ServiceException {
		try {
			getTaskService(setting).tasklists().delete(tasklistId).execute();
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void deleteTask(String task, String tasklist) throws ServiceException {
		try {
			getTaskService(setting).tasks().delete(tasklist, task).execute();
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
	}

}
