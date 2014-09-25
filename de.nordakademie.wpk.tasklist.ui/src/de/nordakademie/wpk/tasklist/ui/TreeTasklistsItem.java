package de.nordakademie.wpk.tasklist.ui;

import java.util.ArrayList;
import java.util.List;

import de.nordakademie.wpk.tasklist.core.api.TaskList;

/**
 * Hier können die Tasklisten direkt eingefügt werden und werden vom
 * Content-Provider als Baumstruktur ausgelesen.
 * 
 * @author Niels Gundermann
 *
 */
public class TreeTasklistsItem {

	List<TaskList> tasklists;

	public Object[] getTaskLists() {
		return tasklists.toArray();
	}

	public TreeTasklistsItem() {
		tasklists = new ArrayList<TaskList>();
	}

	public String toString() {
		return "Tasklisten";
	}

	public void addTasklist(TaskList taskList) {
		tasklists.add(taskList);
	}
}
