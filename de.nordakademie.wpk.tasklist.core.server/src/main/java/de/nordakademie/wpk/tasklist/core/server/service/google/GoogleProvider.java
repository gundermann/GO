package de.nordakademie.wpk.tasklist.core.server.service.google;

import java.util.List;

import com.google.api.services.tasks.Tasks;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.TaskList;

public class GoogleProvider {
	
	private GoogleConverter googleConverter;

	public GoogleProvider(){
		googleConverter = new GoogleConverter();
	}

	public List<TaskList> loadAll(ProviderSetting setting) {
		 return googleConverter.convertTaskLists(getTaskService(setting));
	}
	
	private Tasks getTaskService(ProviderSetting setting) {
		GoogleConnection googleConnection = new GoogleConnection(setting);
		Tasks tasksService = googleConnection.getTasksService();
		return tasksService;
	}

}
