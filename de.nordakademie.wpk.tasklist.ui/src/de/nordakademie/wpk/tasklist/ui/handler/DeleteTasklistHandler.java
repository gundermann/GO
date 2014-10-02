package de.nordakademie.wpk.tasklist.ui.handler;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.swt.widgets.Shell;

import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.core.client.NoSettingFoundException;
import de.nordakademie.wpk.tasklist.core.client.ProviderSettingContainer;
import de.nordakademie.wpk.tasklist.core.client.ProviderSettingNotActiveException;
import de.nordakademie.wpk.tasklist.ui.jobs.DeleteTasklistJob;

public class DeleteTasklistHandler {

	@Inject
	private ESelectionService selectionService;

	@Inject
	private IEventBroker eventBroker;

	@Inject
	private TaskService taskService;

	private String message = "Wollen Sie die Taskliste wirklich löschen? Alle Tasks in der Taskliste werden mit gelöscht!";

	@Execute
	public void execute(Shell shell) throws ExecutionException {
		if (canExecute()) {
			boolean confirmed = MessageDialog.openQuestion(shell, "Wirklich?",
					message);
			if (confirmed) {
				ITreeSelection selection = (ITreeSelection) selectionService
						.getSelection();
				TaskList tasklist = (TaskList) selection.getFirstElement();
				try {
					new DeleteTasklistJob(tasklist.getId(), taskService,
							eventBroker, ProviderSettingContainer.getInstance()
									.getActiveProviderSetting(
											tasklist.getProvider())).schedule();
				} catch (NoSettingFoundException e) {
					MessageDialog.openError(shell, "Taskliste nicht gelöscht",
							e.getMessage());
				} catch (ProviderSettingNotActiveException e) {
					MessageDialog.openError(shell, "Taskliste nicht gelöscht",
							e.getMessage());
				}
			}
		}
	}

	@CanExecute
	public boolean canExecute() {
		if (selectionService.getSelection() instanceof ITreeSelection) {
			return ((ITreeSelection) selectionService.getSelection())
					.getFirstElement() instanceof TaskList;
		}
		return false;
	}

}