package de.nordakademie.wpk.tasklist.ui;

import java.util.HashSet;
import java.util.Set;

import de.nordakademie.wpk.tasklist.core.api.TaskList;

public class TreeRootItem {

	Set<TaskList> tasklists;

	public Object[] getTaskLists() {
		return tasklists.toArray();
	}

	public TreeRootItem() {
		tasklists = new HashSet<TaskList>();
	}

	public String toString() {
		return "Tasklisten";
	}

	public void addTasklist(TaskList taskList) {
		tasklists.add(taskList);
	}
}
