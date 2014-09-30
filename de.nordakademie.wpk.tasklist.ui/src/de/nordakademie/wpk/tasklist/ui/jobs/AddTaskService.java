package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.jface.dialogs.MessageDialog;

import de.nordakademie.wpk.tasklist.core.api.GoogleSetting;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.ui.Topics;

public class AddTaskService extends Job {

	private Task task;
	private TaskService taskService;
	private String tasklistId;
	private IEventBroker eventBroker;
	private ProviderSetting setting;

	public AddTaskService(Task task, String tasklistId,
			TaskService taskService, IEventBroker eventBroker,
			ProviderSetting setting) {
		super("Füge Task hinzu");
		this.task = task;
		this.tasklistId = tasklistId;
		this.taskService = taskService;
		this.eventBroker = eventBroker;
		this.setting = setting;
		setUser(true);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			taskService.addTask(task, tasklistId, setting);
		} catch (ServiceException e) {
			eventBroker.post(Topics.SERVER_EXCEPTION_THROWN, e.getMessage());
		}
		new LoadAllJob(taskService, eventBroker).schedule();
		return Status.OK_STATUS;
	}

}
