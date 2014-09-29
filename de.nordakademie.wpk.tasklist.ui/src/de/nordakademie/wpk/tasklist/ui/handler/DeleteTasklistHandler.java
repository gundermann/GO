 
package de.nordakademie.wpk.tasklist.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ITreeSelection;

import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.ui.editors.TaskEditorInput;
import de.nordakademie.wpk.tasklist.ui.jobs.DeleteTasklistJob;

public class DeleteTasklistHandler {
	
	@Inject
	private ESelectionService selectionService;
	
	@Inject
	private IEventBroker eventBroker;
	
	@Inject
	private TaskService taskService;
	
	@Execute
	public void execute() {
		if (canExecute()) {
			ITreeSelection selection = (ITreeSelection) selectionService.getSelection();
			TaskList tasklist = (TaskList) selection.getFirstElement();
			new DeleteTasklistJob(tasklist.getId(), taskService, eventBroker).schedule();
		}
	}
	
	
	@CanExecute
	public boolean canExecute() {
		if (selectionService.getSelection() instanceof ITreeSelection) {
			return ((ITreeSelection) selectionService.getSelection())
					.getFirstElement() instanceof TaskList;
		}
		return false;
	}
		
}