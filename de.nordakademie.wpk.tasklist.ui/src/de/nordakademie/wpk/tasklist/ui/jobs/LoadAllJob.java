package de.nordakademie.wpk.tasklist.ui.jobs;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.events.IEventBroker;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.TaskList;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.core.client.ProviderSettingContainer;
import de.nordakademie.wpk.tasklist.ui.Topics;

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
		List<TaskList> loadAll = new ArrayList<TaskList>();
		List<ProviderSetting> activeProviderSettings = ProviderSettingContainer
				.getInstance().getAllActiveProviderSettings();
		monitor.beginTask("Lade Tasklisten", activeProviderSettings.size());
		int w = 0;
		for(ProviderSetting providerSetting : activeProviderSettings) {
			try {
				loadAll.addAll(taskService.loadAll(providerSetting));
			} catch (ServiceException e) {
				eventBroker
						.post(Topics.SERVER_EXCEPTION_THROWN, e.getMessage());
				continue;
			}
			monitor.worked(++w);
		}
		eventBroker.post(Topics.ALL_TASKS_UPDATED, loadAll);
		return Status.OK_STATUS;
	}

}
