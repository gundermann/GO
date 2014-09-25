 
package de.nordakademie.wpk.tasklist.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ITreeSelection;

import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.ui.TaskEditorInput;

public class AddTaskHandler  extends AbstractEditorHandler {
	
	@Inject
	private ESelectionService selectionService;
	
	@CanExecute
	public boolean canExecute(){
		if (selectionService.getSelection() instanceof ITreeSelection) {
			return ((ITreeSelection) selectionService.getSelection())
					.getFirstElement() instanceof TaskList;
		}
		return false;
		
	}
	
	@Execute
	public void execute() {
		if (canExecute()) {
			ITreeSelection selection = (ITreeSelection) selectionService.getSelection();
			TaskList tasklist = (TaskList) selection.getFirstElement();
			Task task = new Task();
			openEditor(new TaskEditorInput(task , tasklist),
					"de.nordakademie.wpk.tasklist.ui");
		}
	}
		
}