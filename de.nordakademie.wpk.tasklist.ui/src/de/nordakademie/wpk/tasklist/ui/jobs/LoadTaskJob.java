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
 * Job der genau eine Taskl lädt
 * @author Niels Gundermann
 *
 */
public class LoadTaskJob extends Job {

	TaskService taskService;
	private IEventBroker eventBroker;
	private String taskId;
	private ProviderSetting setting;
	private String tasklistId;

	public LoadTaskJob(TaskService taskService, IEventBroker eventBroker,
			String taskId, String tasklistId, ProviderSetting setting) {
		super("Lade Tasklisten");
		setUser(true);
		setRule(new LoadTaskSchedulingRule());
		this.taskService = taskService;
		this.eventBroker = eventBroker;
		this.taskId = taskId;
		this.tasklistId = tasklistId;
		this.setting = setting;
		taskId = tasklistId;
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			Task task = taskService.loadTask(taskId, tasklistId, setting);
			eventBroker.post(Topics.TASK_LOADED, task);
		} catch (ServiceException e) {
			eventBroker.post(Topics.SERVER_EXCEPTION_THROWN, e.getMessage());
		} catch (RemoteConnectFailureException e) {
			eventBroker.post(Topics.SERVER_EXCEPTION_THROWN,
					"Keine Verbindung zum Server");
		}
		return Status.OK_STATUS;
	}

}
