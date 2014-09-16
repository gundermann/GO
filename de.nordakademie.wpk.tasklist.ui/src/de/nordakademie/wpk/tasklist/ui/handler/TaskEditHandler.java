 
package de.nordakademie.wpk.tasklist.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;

import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.ui.TaskEditorInput;

public class TaskEditHandler extends AbstractEditorHandler{
	@Inject
	private ESelectionService selectionService;
	
	@CanExecute
	public boolean canExecute(){
		return selectionService.getSelection() instanceof Task;
	}
	
	
	@Execute
	public void execute() {
		if(canExecute()){
			 Task task = (Task) selectionService.getSelection() ;
			openEditor(new TaskEditorInput(task), "de.nordakademie.wpk.tasklist.ui");
		}
	}
		
}