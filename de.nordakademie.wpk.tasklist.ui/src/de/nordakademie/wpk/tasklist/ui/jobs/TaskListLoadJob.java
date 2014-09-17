package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;

import de.nordakademie.wpk.tasklist.core.api.TaskList;

public class TaskListLoadJob extends Job{

	private TaskList tasklist;

	public TaskListLoadJob(TaskList tasklist) {
		super("Lade Taskliste: "+tasklist.getName());
		this.tasklist = tasklist;
		setUser(true);
		setRule(new TasklistLoadSchedulingRule(tasklist));
	}

	@Override
	protected IStatus run(IProgressMonitor monitor) {
		
		// TODO Auto-generated method stub
		return null;
	}

}
