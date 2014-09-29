package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

import de.nordakademie.wpk.tasklist.core.api.TaskList;

public class TasklistLoadSchedulingRule implements ISchedulingRule {

	private TaskList tasklist;

	public TasklistLoadSchedulingRule(TaskList tasklist) {
		this.tasklist = tasklist;
	}

	@Override
	public boolean contains(ISchedulingRule rule) {
		return rule == this;
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		if (rule instanceof TasklistLoadSchedulingRule) {
			return tasklist.getId().equals(
					((TasklistLoadSchedulingRule) rule).tasklist.getId());
		}
		return false;
	}

}
