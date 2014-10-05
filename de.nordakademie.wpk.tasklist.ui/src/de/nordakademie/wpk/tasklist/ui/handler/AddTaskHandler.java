package de.nordakademie.wpk.tasklist.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ITreeSelection;

import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.ui.Topics;
import de.nordakademie.wpk.tasklist.ui.editors.TaskEditorInput;

/**
 * Handler zum Hinzufügen einer Task
 * @author Niels Gundermann
 *
 */
public class AddTaskHandler extends AbstractEditorHandler {

	@Inject
	private ESelectionService selectionService;
	@Inject
	private IEventBroker eventBroker;

	@CanExecute
	public boolean canExecute() {
		if (selectionService.getSelection() instanceof ITreeSelection) {
			return ((ITreeSelection) selectionService.getSelection())
					.getFirstElement() instanceof TaskList;
		}
		return false;

	}

	@Execute
	public void execute() {
		if (canExecute()) {
			ITreeSelection selection = (ITreeSelection) selectionService
					.getSelection();
			TaskList tasklist = (TaskList) selection.getFirstElement();
			Task task = new Task();
			TaskEditorInput taskEditorInput = new TaskEditorInput(task,
					tasklist);
			if (partService.findPart(taskEditorInput.getPartId()) != null
					&& partService.findPart(taskEditorInput.getPartId())
							.isToBeRendered()) {
				eventBroker.post(Topics.TASK_HANDLING_IMPOSSIBILE,
						"Eine neue Task wird bereits erstellt");
			} else {
				openEditor(new TaskEditorInput(task, tasklist),
						"de.nordakademie.wpk.tasklist.ui");
			}
		}
	}

}
