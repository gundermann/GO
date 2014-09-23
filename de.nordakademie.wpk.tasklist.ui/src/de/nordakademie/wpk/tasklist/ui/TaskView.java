package de.nordakademie.wpk.tasklist.ui;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.ui.jobs.Topics;
import de.nordakademie.wpk.tasklist.ui.provider.TaskListTreeLabelProvider;
import de.nordakademie.wpk.tasklist.ui.provider.TasklistTreeContentProvider;

public class TaskView {

	private TreeViewer treeViewer;

	public TaskView() {
	}

	@PostConstruct
	public void createControls(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.BORDER);
		treeViewer.setContentProvider(new TasklistTreeContentProvider());
		treeViewer.setLabelProvider(new TaskListTreeLabelProvider());
		treeViewer.setInput(getInitinalInput());
		treeViewer.expandAll();
	}

	private Object getInitinalInput() {
		TreeRootItem root = new TreeRootItem();
		return root;
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
	private void handleChangeEvent(
			@UIEventTopic(Topics.ALL_TASKS_UPDATED) List<TaskList> tasklists) {
		refreshInput(tasklists);
	}

	private void refreshInput(List<TaskList> tasklists) {
		TreeRootItem root = new TreeRootItem();
		for (TaskList taskList : tasklists) {
			root.addTasklist(taskList);
		}
		treeViewer.setInput(root);

	}

}
