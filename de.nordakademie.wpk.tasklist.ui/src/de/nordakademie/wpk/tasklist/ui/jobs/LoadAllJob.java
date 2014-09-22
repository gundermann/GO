package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import de.nordakademie.wpk.tasklist.core.api.GoogleSetting;
import de.nordakademie.wpk.tasklist.core.api.NoSettingFoundException;
import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ProviderSettingContainer;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.core.client.TaskListContainer;

public class LoadAllJob extends Job {

	TaskService taskService;


	public LoadAllJob(TaskService taskService) {
		super("Lade Tasklisten");
		this.taskService = taskService;
		setUser(true);
		setRule(new LoadAllSchedulingRule());
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		monitor.beginTask("Lade Tasklisten", 1);
//		try {
//			ProviderSetting settings = ProviderSettingContainer.getInstance()
//			.getSettings(Provider.GOOGLE);
			TaskListContainer.getInstance().setTaskLists(taskService.loadAll(new GoogleSetting()));
//		} catch (NoSettingFoundException e) {
//			monitor.done();
//			e.printStackTrace();
//		}
		monitor.worked(1);
		return Status.OK_STATUS;
	}

}
