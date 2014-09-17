package de.nordakademie.wpk.tasklist.ui.jobs;

import javax.inject.Inject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import de.nordakademie.wpk.tasklist.core.api.NoSettingFoundException;
import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.ProviderSettingContainer;
import de.nordakademie.wpk.tasklist.core.api.TaskService;

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
		try {
			taskService.loadAll(ProviderSettingContainer.getInstance()
					.getSettings(Provider.GOOGLE));
		} catch (NoSettingFoundException e) {
			monitor.done();
			e.printStackTrace();
		}
		monitor.worked(1);
		return Status.OK_STATUS;
	}

}
