package de.nordakademie.wpk.tasklist.ui;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.core.client.TaskListContainer;
import de.nordakademie.wpk.tasklist.ui.jobs.LoadAllJob;

public class TaskView {
	
	Tree tree;
	TreeItem tasklists;
	
	@Inject 
	TaskService taskSercive;
	
	public TaskView() {
	}

	@PostConstruct
	public void createControls(Composite parent) {
		
		tree = new Tree(parent, SWT.BORDER);
		tree.setHeaderVisible(true);
		
		tasklists = new TreeItem(tree, SWT.NONE);
		tasklists.setText("Tasklisten");
		
		refresh();
		
	}	

	private void refresh() {
		new LoadAllJob(taskSercive).schedule();
		Set<TaskList> taskLists = TaskListContainer.getInstance().getTaskLists();
		for (TaskList taskList : taskLists) {
			addTasklist(taskList);
		}
	}

	public void addTasklist(TaskList tasklistObject){
		TreeItem tasklist = new TreeItem(tasklists, SWT.NONE);
		tasklist.setText(tasklistObject.getName());
		tasklist.setExpanded(true);
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
		tree.setFocus();
	}

}
