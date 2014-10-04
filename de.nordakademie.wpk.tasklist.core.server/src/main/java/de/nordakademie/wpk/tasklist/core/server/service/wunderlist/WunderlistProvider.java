package de.nordakademie.wpk.tasklist.core.server.service.wunderlist;

import in.co.madhur.wunderjava.api.AuthException;
import in.co.madhur.wunderjava.api.NetworkException;
import in.co.madhur.wunderjava.api.WunderList;
import in.co.madhur.wunderjava.api.model.WTask;

import java.util.List;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.server.service.ProviderService;
/**
 * Implemetierung für den Service des WunderlistProviders.
 * 
 * @author Niels Gundermann
 *
 */
public class WunderlistProvider implements ProviderService {

	private ProviderSetting setting;
	private WunderlistConverter wunderlistConverter;

	public WunderlistProvider(ProviderSetting setting) throws ServiceException {
		this.setting = setting;
		wunderlistConverter = new WunderlistConverter();

	}

	public List<TaskList> loadAll() throws ServiceException {
		try {
			return wunderlistConverter.convertLists(getWunderlistService()
					.GetLists(), getWunderlistService().GetTasks());
		} catch (AuthException e) {
			throw new ServiceException(e.getMessage());
		} catch (NetworkException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	private WunderList getWunderlistService() throws AuthException,
			NetworkException {
		return WunderList.getInstance(setting.getUsername(),
				setting.getPassword());
	}

	public void updateTaskList(TaskList tasklist) throws ServiceException {
		throw new ServiceException("Update kann auf Wunderlist noch nicht ausgeführt werden.");
	}

	public void addTaskList(TaskList tasklist) throws ServiceException {
		try {
			getWunderlistService().CreateList(tasklist.getName());
		} catch (NetworkException e) {
			throw new ServiceException(e.getMessage());
		} catch (AuthException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	public void updateTask(Task task, String tasklistId) throws ServiceException {
		throw new ServiceException("Update kann auf Wunderlist noch nicht ausgeführt werden.");
	}

	public String addTask(Task task, String tasklistId) throws ServiceException {
		try {
			WTask wTask = getWunderlistService().CreateTask(tasklistId, task.getTitle(), null,
					wunderlistConverter.convertJavaDate(task.getDateOfDue()));
			return wTask.getId();
		} catch (NetworkException e) {
			throw new ServiceException(e.getMessage());
		} catch (AuthException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public Task loadTask(String taskId, String tasklistId)
			throws ServiceException {
		try {
			List<WTask> tasks = getWunderlistService().GetTasks();
			for (WTask wTask : tasks) {
				if (wTask.getId().equals(taskId))
					return wunderlistConverter.convertTask(wTask);
			}
		} catch (AuthException e) {
			throw new ServiceException(e.getMessage());
		} catch (NetworkException e) {
			throw new ServiceException(e.getMessage());
		}
		throw new ServiceException("Task wurde nicht auf dem Server gefunden");
	}

	public void deleteTaskList(String tasklistId) throws ServiceException {
		try {
			getWunderlistService().DeleteList(tasklistId);
		} catch (NetworkException e) {
			throw new ServiceException(e.getMessage());
		} catch (AuthException e) {
			throw new ServiceException(e.getMessage());
		}

	}

	public void deleteTask(String task, String tasklist)
			throws ServiceException {
		throw new ServiceException("löschen einer Task kann auf Wunderlist noch nicht ausgeführt werden.");
	}

}
