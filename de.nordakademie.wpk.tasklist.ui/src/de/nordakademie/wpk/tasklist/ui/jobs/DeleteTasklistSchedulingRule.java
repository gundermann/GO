package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

/**
 * Scheduling-rule f�r den Job, der eine Taskliste l�scht.
 * Zwei Instanzen dieses Jobs sollten nicht parallel laufen.
 * @author Niels Gundermann
 *
 */
public class DeleteTasklistSchedulingRule implements ISchedulingRule {

	@Override
	public boolean contains(ISchedulingRule rule) {
		return rule == this;
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		if(rule instanceof DeleteTasklistSchedulingRule)
			return true;
		return false;
	}

}
