package de.nordakademie.wpk.tasklist.ui;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;

import de.nordakademie.wpk.tasklist.core.api.Task;

/**
 * Vergleicht die Tasks miteinander und sortiert sie anhand ihrer Position.
 * @author Kathrin Kurtz
 *
 */
public class TaskSorter extends ViewerSorter {

	@Override
	/**
	 * Vergleicht Tasks, wenn die Task keine Position hat wird mit der Position 0 gearbeitet.
	 */
	public int compare(Viewer viewer, Object e1, Object e2) {
		
		if (e1 instanceof Task && e2 instanceof Task) {
			Long pos1 = ((Task) e1).getPosition();
			Long pos2 = ((Task) e2).getPosition();
			if(pos1 == null){
				pos1 = 0L;
			}
			if(pos2 == null){
				pos2=0L;
			}
			
			return (pos1.compareTo(pos2));
		}
		return 0;
	}

}
