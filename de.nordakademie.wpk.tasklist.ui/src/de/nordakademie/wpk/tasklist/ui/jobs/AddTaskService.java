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

public class AddTaskService extends Job {

	private Task task;
	private TaskService taskService;
	private String tasklistId;
	private IEventBroker eventBroker;

	public AddTaskService(Task task, String tasklistId, TaskService taskService, IEventBroker eventBroker) {
		super("Füge Task hinzu");
		this.task = task;
		this.tasklistId = tasklistId;
		this.taskService = taskService;
		this.eventBroker = eventBroker;
		setUser(true);
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		
		try {
			taskService.addTask(task, tasklistId, new GoogleSetting());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
		}
		new LoadAllJob(taskService, eventBroker, new GoogleSetting())
		.schedule();
		// TODO Auto-generated method stub
		return Status.OK_STATUS;
	}

}
