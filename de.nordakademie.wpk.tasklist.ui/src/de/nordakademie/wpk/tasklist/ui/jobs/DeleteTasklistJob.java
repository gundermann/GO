package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.springframework.remoting.RemoteConnectFailureException;

import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.ui.Topics;


/**
 * Job, der eine Taksliste löscht
 * @author Niels Gundermann
 *
 */
public class DeleteTasklistJob extends Job {

	private String tasklistId;
	private TaskService taskService;
	private IEventBroker eventBroker;
	private ProviderSetting setting;

	public DeleteTasklistJob(String tasklistId, TaskService taskService,
			IEventBroker eventBroker, ProviderSetting setting) {
		super("Lösche Taskliste");
		this.tasklistId = tasklistId;
		this.taskService = taskService;
		this.eventBroker = eventBroker;
		this.setting = setting;
		setUser(true);
		setRule(new DeleteTasklistSchedulingRule());
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		try {
			taskService.deleteTaskList(tasklistId, setting);
		} catch (ServiceException e) {
			eventBroker.post(Topics.SERVER_EXCEPTION_THROWN, e.getMessage());
		} catch (RemoteConnectFailureException e) {
			eventBroker.post(Topics.SERVER_EXCEPTION_THROWN,
					"Keine Verbindung zum Server");
		}
		new LoadAllJob(taskService, eventBroker).schedule();
		return Status.OK_STATUS;
	}

}
