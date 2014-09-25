package de.nordakademie.wpk.tasklist.ui.provider;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.ui.TreeRootItem;
import de.nordakademie.wpk.tasklist.ui.TreeTasklistsItem;

public class TasklistTreeContentProvider implements ITreeContentProvider {

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		    return getChildren(inputElement);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof TreeRootItem) {
			TreeRootItem parentNode = (TreeRootItem) parentElement;
			return parentNode.getChilden();
		}
		else if(parentElement instanceof TreeTasklistsItem){
			TreeTasklistsItem parentNode = (TreeTasklistsItem) parentElement;
			return parentNode.getTaskLists();
		}
		else if(parentElement instanceof TaskList){
			TaskList parentNode = (TaskList) parentElement;
			return parentNode.getTasks().toArray();
		}
		return new String[]{"Tasklisten"};
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof TreeRootItem) {
			TreeRootItem node = (TreeRootItem) element;
			return node.getChilden().length > 0;
		}
		else if(element instanceof TreeTasklistsItem){
			TreeTasklistsItem node = (TreeTasklistsItem) element;
			return node.getTaskLists().length > 0;
		}
		else if(element instanceof TaskList){
			TaskList node = (TaskList) element;
			return node.getTasks().size() > 0;
		}
		return false;
	}

}