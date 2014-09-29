package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.events.IEventBroker;

import de.nordakademie.wpk.tasklist.core.api.GoogleSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.TaskService;

public class DeleteTaskJob extends Job {

	private String taskId;
	private TaskService taskService;
	private String tasklistId;
	private IEventBroker eventBroker;

	public DeleteTaskJob(String tasklistId, String taskId, TaskService taskService, IEventBroker eventBroker) {
		super("Lösche Task");
		this.tasklistId = tasklistId;
		this.taskService = taskService;
		this.taskId = taskId;
		this.eventBroker = eventBroker;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			taskService.deleteTask(taskId, tasklistId, new GoogleSetting());
		} catch (ServiceException e) {
			//TODO geht das?
			return Status.CANCEL_STATUS;
		}finally{
			new LoadAllJob(taskService, eventBroker, new GoogleSetting()).schedule();
		}
		
		return Status.OK_STATUS;
	}

}
