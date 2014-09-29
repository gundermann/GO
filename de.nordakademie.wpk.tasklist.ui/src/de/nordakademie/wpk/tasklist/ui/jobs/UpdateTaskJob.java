package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.events.IEventBroker;

import de.nordakademie.wpk.tasklist.core.api.GoogleSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.ui.Topics;

public class UpdateTaskJob extends Job {

	private Task task;
	private String tasklistId;
	private TaskService taskService;
	private IEventBroker eventBroker;

	public UpdateTaskJob(Task task, String tasklistId, TaskService taskService,
			IEventBroker eventBroker) {
		super("Aktualisiere Task");
		this.task = task;
		this.tasklistId = tasklistId;
		this.taskService = taskService;
		this.eventBroker = eventBroker;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {

		try {
			taskService.updateTask(task, tasklistId, new GoogleSetting());
		} catch (ServiceException e) {
			eventBroker.post(Topics.SERVER_EXCEPTION_THROWN, e.getMessage());
		}
		new LoadAllJob(taskService, eventBroker, new GoogleSetting())
				.schedule();
		return Status.OK_STATUS;
	}

}