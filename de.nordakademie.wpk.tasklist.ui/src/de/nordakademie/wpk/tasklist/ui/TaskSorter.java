package de.nordakademie.wpk.tasklist.ui;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import de.nordakademie.wpk.tasklist.core.api.Task;

public class TaskSorter extends ViewerSorter {

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof Task && e2 instanceof Task) {
			return ((Task) e1).getPosition().compareTo(
					((Task) e2).getPosition());
		}
		return 0;
	}
}
