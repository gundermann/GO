package de.nordakademie.wpk.tasklist.settings.ui;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;

public class EditSettingsHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		Shell shell = HandlerUtil.getActiveShell(event);
		EditSettingsDialog dialog = new EditSettingsDialog(shell, shell.getStyle());
		
		int result = dialog.open();
		
		
		if(result == IStatus.OK){
			// Einstellungen werden gespeichert
		}
		else{
			// Einstellungen werden verworfen
		}
		
		return null;
	}

}