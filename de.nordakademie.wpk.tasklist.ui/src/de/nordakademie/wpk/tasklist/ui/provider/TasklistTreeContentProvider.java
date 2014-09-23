package de.nordakademie.wpk.tasklist.ui.provider;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.ui.TreeRootItem;

public class TasklistTreeContentProvider implements ITreeContentProvider {

	private boolean treeInputSet = false;    
	
	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	@Override
	public Object[] getElements(Object inputElement) {
		 if (inputElement instanceof TreeRootItem)
		    {
		        if (treeInputSet)
		        {
		        	TreeRootItem model = (TreeRootItem) inputElement;
		            return model.getTaskLists();
		        }
		        else
		        {
		            treeInputSet = true;
		            return new Object[] { inputElement };
		        }
		    }
		    return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof TreeRootItem) {
			TreeRootItem parentNode = (TreeRootItem) parentElement;
			return parentNode.getTaskLists();
		}
		else if(parentElement instanceof TaskList){
			TaskList parentNode = (TaskList) parentElement;
			return parentNode.getTasks().toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof TreeRootItem) {
			TreeRootItem node = (TreeRootItem) element;
			return node.getTaskLists().length > 0;
		}
		else if(element instanceof TaskList){
			TaskList node = (TaskList) element;
			return node.getTasks().size() > 0;
		}
		return false;
	}

}