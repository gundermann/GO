package de.nordakademie.wpk.tasklist.ui;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.ui.jobs.LoadAllJob;
import de.nordakademie.wpk.tasklist.ui.jobs.Topics;

public class TaskView {
	
	Tree tree;
	TreeItem tasklists;
	
	public TaskView() {
	}

	@PostConstruct
	public void createControls(Composite parent) {
		
		tree = new Tree(parent, SWT.BORDER);
		tree.setHeaderVisible(true);
		
		tasklists = new TreeItem(tree, SWT.NONE);
		tasklists.setText("Tasklisten");
		
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
		tree.setFocus();
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
