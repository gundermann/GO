 
package de.nordakademie.wpk.tasklist.ui.handler;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;

import de.nordakademie.wpk.tasklist.core.api.NoSettingFoundException;
import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.core.client.SettingLoader;
import de.nordakademie.wpk.tasklist.ui.jobs.LoadAllJob;

public class RefreshHandler {
	
	@Inject 
	TaskService taskService;
	
	@Inject
	IEventBroker eventBroker;
	
	@Execute
	public void execute() {
		try {
			new LoadAllJob(taskService, eventBroker, new SettingLoader().loadFromFile(Provider.GOOGLE)).schedule();
		} catch (NoSettingFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@CanExecute
	public boolean canExecute(){
		return true;
	}
		
}