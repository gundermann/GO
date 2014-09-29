package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.events.IEventBroker;

import de.nordakademie.wpk.tasklist.core.api.GoogleSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.TaskService;

public class DeleteTasklistJob extends Job {

	private String tasklistId;
	private TaskService taskService;
	private IEventBroker eventBroker;

	public DeleteTasklistJob(String tasklistId, TaskService taskService,
			IEventBroker eventBroker) {
		super("Lösche Taskliste");
		this.tasklistId = tasklistId;
		this.taskService = taskService;
		this.eventBroker = eventBroker;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			taskService.deleteTaskList(tasklistId, new GoogleSetting());
		} catch (ServiceException e) {
			return Status.CANCEL_STATUS;
		}
		new LoadAllJob(taskService, eventBroker, new GoogleSetting()).schedule();
		return Status.OK_STATUS;
	}

}
