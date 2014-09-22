package de.nordakademie.wpk.tasklist.ui;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TreeItem;

import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.ui.jobs.Topics;
import de.nordakademie.wpk.tasklist.ui.provider.TaskListTreeLabelProvider;
import de.nordakademie.wpk.tasklist.ui.provider.TasklistTreeContentProvider;

public class TaskView {
	
	TreeItem tasklists;
	private TreeViewer treeViewer;
	
	public TaskView() {
	}

	@PostConstruct
	public void createControls(Composite parent) {
		
		
		treeViewer = new TreeViewer(parent,  SWT.BORDER);
//		treeViewer.setContentProvider(new TasklistTreeContentProvider());
		treeViewer.setLabelProvider(new TaskListTreeLabelProvider());
		treeViewer.expandAll();
		
//		tree = new Tree(parent, SWT.BORDER);
//		tree.setHeaderVisible(true);
//		
//		

	}	

	public TreeItem addTasklist(TaskList tasklistObject){
		TreeItem tasklist = new TreeItem(tasklists, SWT.NONE);
		tasklist.setText(tasklistObject.getName());
		tasklist.setExpanded(true);
		return tasklist;
	}
	
	public void addTask(TreeItem tasklist, Task taskObject){
		TreeItem task = new TreeItem(tasklist, SWT.NONE); 
		task.setText(taskObject.getTitle());
		task.setExpanded(true);
	}

	@PreDestroy
	public void dispose() {
	}

	@Focus
	public void setFocus() {
		treeViewer.getTree().setFocus();
	}
	
	@Inject
	@Optional
	private void handleChangeEvent(@UIEventTopic(Topics.ALL_TASKS_UPDATED) Set<TaskList> tasklists){
		refreshInput(tasklists);
	}

	private void refreshInput(Set<TaskList> tasklists) {
		this.tasklists.clearAll(true);
		for (TaskList taskList : tasklists) {
			TreeItem addTasklist = addTasklist(taskList);
			for (Task task : taskList.getTasks()) {
				addTask(addTasklist, task);
			};
		}
		
	}

}
