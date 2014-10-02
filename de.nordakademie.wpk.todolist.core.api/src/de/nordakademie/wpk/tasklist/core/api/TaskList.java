package de.nordakademie.wpk.tasklist.core.api;

import java.io.Serializable;
import java.util.List;

public class TaskList implements Serializable{

	private static final long serialVersionUID = 3L;
	private String id;
	private String name;
	private Provider provider;
	private List<Task> tasks;

	public String getId() {
		return id;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
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
