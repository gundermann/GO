package de.nordakademie.wpk.tasklist.ui.jobs;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.events.IEventBroker;

import de.nordakademie.wpk.tasklist.core.api.GoogleSetting;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.ui.Topics;

public class LoadAllJob extends Job {

	TaskService taskService;
	private IEventBroker eventBroker;
	private ProviderSetting setting;
	


	public LoadAllJob(TaskService taskService, IEventBroker eventBroker, ProviderSetting setting) {
		super("Lade Tasklisten");
		this.taskService = taskService;
		this.eventBroker = eventBroker;
		this.setting = setting;
		setUser(true);
		setRule(new LoadAllSchedulingRule());
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		monitor.beginTask("Lade Tasklisten", 1);
		List<TaskList> loadAll = null;
			try {
				loadAll = taskService.loadAll(new GoogleSetting());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
			}
		monitor.worked(1);
		eventBroker.post(Topics.ALL_TASKS_UPDATED, loadAll);
		return Status.OK_STATUS;
	}

}
