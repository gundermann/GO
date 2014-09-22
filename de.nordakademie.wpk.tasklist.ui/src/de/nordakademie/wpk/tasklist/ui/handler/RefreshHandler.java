 
package de.nordakademie.wpk.tasklist.ui.handler;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;

public class RefreshHandler {
	
	@Inject
	TaskService taskService;
	
	@Execute
	public void execute() {
		Set<TaskList> tasklists = new HashSet<TaskList>();
//		new LoadAllJob(taskService, tasklists ).schedule();
	}
	
	@CanExecute
	public boolean canExecute(){
		return true;
	}
		
}