package de.nordakademie.wpk.tasklist.ui.handler;

import javax.inject.Inject;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.swt.widgets.Shell;

import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.core.client.NoSettingFoundException;
import de.nordakademie.wpk.tasklist.core.client.ProviderSettingContainer;
import de.nordakademie.wpk.tasklist.core.client.ProviderSettingNotActiveException;
import de.nordakademie.wpk.tasklist.ui.jobs.DeleteTaskJob;

public class DeleteTaskHandler {

	private String message = "Wollen Sie die Task wirklich löschen?";

	@Inject
	private ESelectionService selectionService;

	@Inject
	private TaskService taskService;

	@Inject
	private IEventBroker eventBorker;

	@Execute
	public void execute(Shell shell) throws ExecutionException {
		if (canExecute()) {
			boolean confirmed = MessageDialog.openQuestion(shell, "Wirklich?", message);
			if (confirmed) {
				ITreeSelection selection = (ITreeSelection) selectionService
						.getSelection();
				Task task = (Task) selection.getFirstElement();
				TaskList tasklist = (TaskList) selection.getPaths()[0]
						.getSegment(1);
				try {
					new DeleteTaskJob(tasklist.getId(), task.getId(), taskService,
							eventBorker, ProviderSettingContainer.getInstance().getActiveProviderSetting(tasklist.getProvider())).schedule();
				} catch (NoSettingFoundException e) {
					MessageDialog.openError(shell, "Task nicht gelöscht", e.getMessage());
				} catch (ProviderSettingNotActiveException e) {
					MessageDialog.openError(shell, "Task nicht gelöscht", e.getMessage());
				}
			}
		}
	}

	@CanExecute
	public boolean canExecute() {
		if (selectionService.getSelection() instanceof ITreeSelection) {
			return ((ITreeSelection) selectionService.getSelection())
					.getFirstElement() instanceof Task;
		}
		return false;
	}

}