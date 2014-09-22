package de.nordakademie.wpk.tasklist.core.api;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TaskList extends HashSet<Task> implements Serializable{

	private static final long serialVersionUID = 2L;
	private String id;
	private String name;
	private Provider provider;
	private Set<Task> tasks;

	public String getId() {
		return id;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
