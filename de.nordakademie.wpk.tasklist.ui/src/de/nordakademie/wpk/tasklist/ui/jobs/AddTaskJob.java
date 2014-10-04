package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.springframework.remoting.RemoteConnectFailureException;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.ui.Topics;

/**
 * Job, der eine Task hinzufügt
 * @author Niels Gundermann
 *
 */
public class AddTaskJob extends Job {

	private Task task;
	private TaskService taskService;
	private String tasklistId;
	private IEventBroker eventBroker;
	private ProviderSetting setting;

	public AddTaskJob(Task task, String tasklistId,
			TaskService taskService, IEventBroker eventBroker,
			ProviderSetting setting) {
		super("Füge Task hinzu");
		this.task = task;
		this.tasklistId = tasklistId;
		this.taskService = taskService;
		this.eventBroker = eventBroker;
		this.setting = setting;
		setUser(true);
		setRule(new AddTaskSchedulingRule());
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			String taskId = taskService.addTask(task, tasklistId, setting);
			task.setId(taskId);
			eventBroker.post(Topics.TASK_SAVED, task);
		} catch (ServiceException e) {
			eventBroker.post(Topics.SERVER_EXCEPTION_THROWN, e.getMessage());
		}catch (RemoteConnectFailureException e) {
			eventBroker.post(Topics.SERVER_EXCEPTION_THROWN,
					"Keine Verbindung zum Server");
		}
		new LoadAllJob(taskService, eventBroker).schedule();
		return Status.OK_STATUS;
	}

}
