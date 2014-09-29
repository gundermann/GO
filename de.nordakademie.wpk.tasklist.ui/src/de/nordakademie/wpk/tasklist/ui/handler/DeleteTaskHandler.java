 
package de.nordakademie.wpk.tasklist.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ITreeSelection;

import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.ui.jobs.DeleteTaskJob;

public class DeleteTaskHandler {
	@Inject
	private ESelectionService selectionService;
	
	@Inject
	private TaskService taskService;
	
	@Inject 
	private IEventBroker eventBorker;
	
	@Execute
	public void execute() {
		if (canExecute()) {
			ITreeSelection selection = (ITreeSelection) selectionService.getSelection();
			Task task = (Task) selection.getFirstElement();
			TaskList tasklist = (TaskList) selection.getPaths()[0].getSegment(1);
			new DeleteTaskJob(tasklist.getId(), task.getId(), taskService, eventBorker).schedule();
		}
	}
	
	
	@CanExecute
	public boolean canExecute() {
		if (selectionService.getSelection() instanceof ITreeSelection) {
			return ((ITreeSelection) selectionService.getSelection())
					.getFirstElement() instanceof Task;
		}
		return false;
	}
		
}