package de.nordakademie.wpk.tasklist.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;

import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.ui.jobs.LoadAllJob;

/**
 * Startet den Job zum akualisieren aller Tasklisten
 * @author Niels Gundermann
 *
 */
public class RefreshHandler {
	
	@Inject 
	TaskService taskService;
	
	@Inject
	IEventBroker eventBroker;
	
	@Execute
	public void execute() {
			new LoadAllJob(taskService, eventBroker, null).schedule();
	}
	
	@CanExecute
	public boolean canExecute(){
		return true;
	}
		
}