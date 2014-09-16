package de.nordakademie.wpk.tasklist.core.server.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.google.api.services.tasks.Tasks;
import com.google.api.services.tasks.model.TaskLists;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;

public class TaskServiceImpl implements TaskService{
	
	public TaskServiceImpl(){
	}

	public Set<TaskList> loadAll(ProviderSetting setting) {
		GoogleConnection googleConnection = new GoogleConnection(setting);
		Tasks tasksService = googleConnection.getTasksService();
		TaskLists tasklist = null;
		try {
			tasklist = tasksService.tasklists().list().execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 List<com.google.api.services.tasks.model.TaskList> items = tasklist.getItems();
		 GoogleConverter googleConverter = new GoogleConverter();
		return googleConverter.convertTaskLists(items);
	}

	public void updateTask(Task task, TaskList tasklist) {

		
	}


	public void updateTaskList(TaskList tasklist) {
		// TODO Auto-generated method stub
		
	}

	public void addTaskList(TaskList tasklist) {
		// TODO Auto-generated method stub
		
	}

	public void addTask(Task task, TaskList tasklist) {
		// TODO Auto-generated method stub
		
	}

	public void deleteTask(Task task, TaskList tasklist) {
		// TODO Auto-generated method stub
		
	}

	public void deleteTaskList(TaskList tasklist) {
		// TODO Auto-generated method stub
		
	}

	

}
