package de.nordakademie.wpk.tasklist.ui.jobs;

import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.events.IEventBroker;

import de.nordakademie.wpk.tasklist.core.api.GoogleSetting;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;

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
//		try {
//			ProviderSetting settings = ProviderSettingContainer.getInstance()
//			.getSettings(Provider.GOOGLE);
		Set<TaskList> loadAll = null;
			loadAll = taskService.loadAll(new GoogleSetting());
//		} catch (NoSettingFoundException e) {
//			monitor.done();
//			e.printStackTrace();
//		}
		monitor.worked(1);
		eventBroker.post(Topics.ALL_TASKS_UPDATED, loadAll);
		return Status.OK_STATUS;
	}

}
