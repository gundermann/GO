 
package de.nordakademie.wpk.tasklist.product.handler;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;

import de.nordakademie.wpk.tasklist.settings.ui.jobs.LoadAllJob;

public class RefreshHandler {
	
	@Execute
	public void execute() {
		//TODO INJECT
		new LoadAllJob().schedule();
	}
	
	@CanExecute
	public boolean canExecute(){
		return true;
	}
		
}