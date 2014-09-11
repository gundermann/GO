package de.nordakademie.wpk.tasklist.ui;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.TreeColumn;

public class TodoView {
	Tree tree;
	TreeItem tasklists;

	public TodoView() {
	}

	/**
	 * Create contents of the view part.
	 */
	@PostConstruct
	public void createControls(Composite parent) {
		
		tree = new Tree(parent, SWT.BORDER);
		tree.setHeaderVisible(true);
		
		tasklists = new TreeItem(tree, SWT.NONE);
		tasklists.setText("Tasklisten");
		
		//TreeItem trtmTestitem = new TreeItem(tasklists, SWT.NONE);
		//trtmTestitem.setText("testitem");
		//tasklists.setExpanded(true);
		
		TreeColumn emptyColumn = new TreeColumn(tree, SWT.NONE);
		emptyColumn.setWidth(79);
		emptyColumn.setText(" ");
		
		TreeColumn tasklistsColumn = new TreeColumn(tree, SWT.NONE);
		tasklistsColumn.setWidth(93);
		tasklistsColumn.setText("Tasklisten");
		
		TreeColumn tasksColumn = new TreeColumn(tree, SWT.NONE);
		tasksColumn.setWidth(100);
		tasksColumn.setText("Tasks");
	}
	
	public void addTasklist(){
		TreeItem tasklist = new TreeItem(tasklists, SWT.NONE);
		tasklist.setText("Tasklistname");
		tasklist.setExpanded(true);
	}
	
	public void addTask(TreeItem tasklist){
		TreeItem task = new TreeItem(tasklist, SWT.NONE); 
		task.setText("Taskname");
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
