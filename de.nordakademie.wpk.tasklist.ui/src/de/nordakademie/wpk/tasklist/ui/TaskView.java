package de.nordakademie.wpk.tasklist.ui;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.ui.provider.TaskListTreeLabelProvider;
import de.nordakademie.wpk.tasklist.ui.provider.TasklistTreeContentProvider;

/**
 * Zeigt die Task View an.
 * @author Kathrin Kurtz
 *
 */
public class TaskView {

	private TreeViewer treeViewer;

	@Inject
	private EMenuService menuService;

	@Inject
	private ESelectionService selectionService;

	private Composite parent;

	public TaskView() {
	}

	@PostConstruct
	public void createControls(Composite parent) {
		this.parent = parent;
		treeViewer = new TreeViewer(parent, SWT.BORDER);
		treeViewer.setContentProvider(new TasklistTreeContentProvider());
		treeViewer.setLabelProvider(new TaskListTreeLabelProvider());
		treeViewer.setInput(getInitinalInput());
		treeViewer.expandAll();

		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof ITreeSelection) {
					ITreeSelection selection = (ITreeSelection) event
							.getSelection();
					selectionService.setSelection(selection);
				}
			}
		});
		
		treeViewer.setSorter(new TaskSorter());

		registerContextMenu();
	}

	private void registerContextMenu() {
		menuService.registerContextMenu(treeViewer.getTree(),
				"popup:taskContextMenu");
	}

	private Object getInitinalInput() {
		TreeRootItem root = new TreeRootItem(new TreeTasklistsItem());
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

	@Inject
	@Optional
	private void handleServerException(
			@UIEventTopic(Topics.SERVER_EXCEPTION_THROWN) String message) {
		MessageDialog.openInformation(parent.getShell(), "Fehler auf dem Server", message);
	}
	
	@Inject
	@Optional
	private void handleTaskHandlingImpossible(
			@UIEventTopic(Topics.TASK_HANDLING_IMPOSSIBILE) String message ) {
		MessageDialog.openInformation(parent.getShell(), "Aktion nicht m�glich", message);
	}
	
	private void refreshInput(List<TaskList> tasklists) {
		TreeTasklistsItem tasklistsItem = new TreeTasklistsItem();
		TreeRootItem root = new TreeRootItem(tasklistsItem);

		for (TaskList taskList : tasklists) {
			tasklistsItem.addTasklist(taskList);
		}
		treeViewer.setInput(root);
		treeViewer.expandToLevel(1);
	}

}
