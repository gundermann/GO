package de.nordakademie.wpk.tasklist.core.server.service;

import java.util.List;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.server.service.google.GoogleProvider;

public class ProviderContainer {

	private static ProviderContainer _instance;
	GoogleProvider googleProvider;
	
	private ProviderContainer() {
		googleProvider = new GoogleProvider();
	}
	
	public static ProviderContainer getInstance(){
		if(_instance == null){
			_instance = new ProviderContainer();
		}
		return _instance;
	}

	public List<TaskList> delegateLoadAll(ProviderSetting setting) {
		switch (setting.getProvider()) {
		default:
			return googleProvider.loadAll(setting);
		}
	}

	public void delegateUpdateTaskList(TaskList tasklist,
			ProviderSetting setting) {
		switch (setting.getProvider()) {
		default:
			googleProvider.updateTaskList(tasklist, setting);
		}
		
	}

	public void delegateAddTaskList(TaskList tasklist, ProviderSetting setting) {
		switch (setting.getProvider()) {
		default:
			googleProvider.addTaskList(tasklist, setting);
		}
	}

	public void delegateDeleteTask(String task, String tasklist,
			ProviderSetting setting) {
		switch (setting.getProvider()) {
		default:
			googleProvider.deleteTask(task, tasklist, setting);
		}		
	}

	public void delegateDeleteTaskList(TaskList tasklist,
			ProviderSetting setting) {
		switch (setting.getProvider()) {
		default:
			googleProvider.deleteTaskList(tasklist, setting);
		}		
	}

	public Task delegateLoadTask(String taskId, String tasklistId,
			ProviderSetting setting) {
		switch (setting.getProvider()) {
		default:
			return googleProvider.loadTask(taskId, tasklistId, setting);
		}
	}

	public void delegateAddTask(Task task, String tasklistId,
			ProviderSetting setting) {
		switch (setting.getProvider()) {
		default:
			googleProvider.addTask(task, tasklistId, setting);
		}		
	}

	public void delegateUpdateTask(Task task, String tasklistId,
			ProviderSetting setting) {
		switch (setting.getProvider()) {
		default:
			googleProvider.updateTask(task, tasklistId, setting);
		}		
	}

}
