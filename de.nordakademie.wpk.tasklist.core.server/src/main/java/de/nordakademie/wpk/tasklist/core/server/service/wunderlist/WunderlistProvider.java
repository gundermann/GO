package de.nordakademie.wpk.tasklist.core.server.service.wunderlist;

import in.co.madhur.wunderjava.api.AuthException;
import in.co.madhur.wunderjava.api.NetworkException;
import in.co.madhur.wunderjava.api.WunderList;

import java.util.List;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.server.service.ProviderService;

public class WunderlistProvider implements ProviderService {

	private ProviderSetting setting;
	private WunderlistConverter wunderlistConverter;

	public WunderlistProvider(ProviderSetting setting) throws ServiceException {
		this.setting = setting;
		wunderlistConverter = new WunderlistConverter();

	}

	public List<TaskList> loadAll() throws ServiceException {
		try {
		return wunderlistConverter.convertLists(getWunderlistService().GetLists(), getWunderlistService().GetTasks());
		} catch (AuthException e) {
			throw new ServiceException(e.getMessage());
		} catch (NetworkException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	private WunderList getWunderlistService() throws AuthException, NetworkException  {
			return WunderList.getInstance(setting.getUsername(), setting.getPassword());
	}

	public void updateTaskList(TaskList tasklist) {
		// TODO Auto-generated method stub

	}

	public void addTaskList(TaskList tasklist) {
		// TODO Auto-generated method stub

	}

	public void updateTask(Task task, String tasklistId) {
		// TODO Auto-generated method stub

	}

	public void addTask(Task task, String tasklistId) {
		// TODO Auto-generated method stub

	}

	public Task loadTask(String taskId, String tasklistId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void deleteTaskList(String tasklistId) {
		// TODO Auto-generated method stub

	}

	public void deleteTask(String task, String tasklist) {
		// TODO Auto-generated method stub

	}

}
