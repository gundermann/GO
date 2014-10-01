package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.events.IEventBroker;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.ui.Topics;

public class DeleteTaskJob extends Job {

	private String taskId;
	private TaskService taskService;
	private String tasklistId;
	private IEventBroker eventBroker;
	private ProviderSetting setting;

	public DeleteTaskJob(String tasklistId, String taskId, TaskService taskService, IEventBroker eventBroker, ProviderSetting setting) {
		super("Lösche Task");
		this.tasklistId = tasklistId;
		this.taskService = taskService;
		this.taskId = taskId;
		this.eventBroker = eventBroker;
		this.setting = setting;
		setUser(true);
		setRule(new DeleteTaskSchedulingRule());
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			taskService.deleteTask(taskId, tasklistId, setting);
		} catch (ServiceException e) {
			eventBroker.post(Topics.SERVER_EXCEPTION_THROWN, e.getMessage());
		}
		new LoadAllJob(taskService, eventBroker).schedule();
		
		return Status.OK_STATUS;
	}

}
