package de.nordakademie.wpk.tasklist.core.client;

import java.util.HashSet;
import java.util.Set;

import de.nordakademie.wpk.tasklist.core.api.TaskList;

public class TaskListContainer {

	private static TaskListContainer _instance;
	
	private Set<TaskList> taskLists;
	
	public TaskListContainer(){
		taskLists = new HashSet<TaskList>();
	}
	
	public static TaskListContainer getInstance(){
		if(_instance == null)
			_instance  =new TaskListContainer();
		return _instance;
	}

	public Set<TaskList> getTaskLists() {
		return taskLists;
	}

	public void setTaskLists(Set<TaskList> taskLists) {
		this.taskLists = taskLists;
	}
	
	
}
