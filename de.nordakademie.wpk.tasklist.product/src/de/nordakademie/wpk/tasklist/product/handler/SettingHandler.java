 
package de.nordakademie.wpk.tasklist.product.handler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

import de.nordakademie.wpk.tasklist.settings.ui.EditSettingsDialog;

public class SettingHandler extends AbstractHandler{
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		Shell shell = HandlerUtil.getActiveShell(event);
		EditSettingsDialog dialog = new EditSettingsDialog(shell, shell.getStyle());
		
		int result = dialog.open();
		
		if(result == IStatus.OK){
			
			// Einstellungen werden gespeichert
			MessageDialog.openInformation(shell, "Nicht implementiert", "Speichern der Einstellungen noch nicht implementiert!");
		}
		else{
			MessageDialog.openError(shell, "Abgebrochen", "Einstellungen wurden nicht gespeichert");
		}
		
		return null;
	}
		
}