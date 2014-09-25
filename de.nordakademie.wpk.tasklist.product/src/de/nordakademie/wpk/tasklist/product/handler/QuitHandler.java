 
package de.nordakademie.wpk.tasklist.product.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * 
 * @author Kathrin Kurtz
 *
 */
public class QuitHandler {
	@Execute
	public void execute(IWorkbench workbench, Shell shell) {
		if (MessageDialog.openConfirm(shell, "Confirmation",
				"Wollen Sie die Anwendung wirklich schlieﬂen?")) {
			workbench.close();
		}
	}
		
}