package de.nordakademie.wpk.tasklist.ui.provider;

import java.util.Set;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.nordakademie.wpk.tasklist.core.api.TaskList;

public class TasklistTreeContentProvider implements ITreeContentProvider {

	private Set<TaskList> tasklists;

	@Override
	public Object[] getElements(Object inputElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof String)
			return tasklists.toArray();
		else if (parentElement instanceof TaskList) {
			return ((TaskList) parentElement).getTasks().toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof TaskList){
			return "Tasklisten";
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
