package de.nordakademie.wpk.tasklist.ui.jobs;

import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.events.IEventBroker;

import de.nordakademie.wpk.tasklist.core.api.GoogleSetting;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.core.client.TaskListContainer;

public class LoadAllJob extends Job {

	TaskService taskService;
	private IEventBroker eventBroker;
	


	public LoadAllJob(TaskService taskService, IEventBroker eventBroker) {
		super("Lade Tasklisten");
		this.taskService = taskService;
		this.eventBroker = eventBroker;
		setUser(true);
		setRule(new LoadAllSchedulingRule());
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		monitor.beginTask("Lade Tasklisten", 1);
//		try {
//			ProviderSetting settings = ProviderSettingContainer.getInstance()
//			.getSettings(Provider.GOOGLE);
		Set<TaskList> loadAll = taskService.loadAll(new GoogleSetting());
			TaskListContainer.getInstance().setTaskLists(loadAll);
//		} catch (NoSettingFoundException e) {
//			monitor.done();
//			e.printStackTrace();
//		}
		monitor.worked(1);
		eventBroker.post(Topics.ALL_TASKS_UPDATED, loadAll);
		return Status.OK_STATUS;
	}

}
