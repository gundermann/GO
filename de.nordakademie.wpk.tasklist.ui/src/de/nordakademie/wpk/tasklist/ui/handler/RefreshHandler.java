 
package de.nordakademie.wpk.tasklist.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.ui.jobs.LoadAllJob;

public class RefreshHandler {
	
	@Inject
	TaskService taskService;
	@Execute
	public void execute() {
		new LoadAllJob(taskService).schedule();
	}
	
	@CanExecute
	public boolean canExecute(){
		return true;
	}
		
}