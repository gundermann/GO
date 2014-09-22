 
package de.nordakademie.wpk.tasklist.settings.ui;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class SettingHandler {
	
	@Execute
	public void execute(Shell shell) throws ExecutionException {
		
		EditSettingsDialog dialog = new EditSettingsDialog(shell, shell.getStyle());
		
		int result = dialog.open();
		
		if(result == IStatus.OK){
			
			// Einstellungen werden gespeichert
			MessageDialog.openInformation(shell, "Nicht implementiert", "Speichern der Einstellungen noch nicht implementiert!");
		}
		else{
			MessageDialog.openError(shell, "Abgebrochen", "Einstellungen wurden nicht gespeichert");
		}
		
	}
		
	
	@CanExecute
	public boolean canExecute(){
		return true;
	}}