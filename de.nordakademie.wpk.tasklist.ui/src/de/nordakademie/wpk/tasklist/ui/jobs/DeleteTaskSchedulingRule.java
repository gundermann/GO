package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.jobs.ISchedulingRule;
/**
 * Scheduling-rule für den Job, der eine Task löscht.
 * Zwei Instanzen dieses Jobs sollten nicht parallel laufen.
 * @author Niels Gundermann
 *
 */
public class DeleteTaskSchedulingRule implements ISchedulingRule {

	@Override
	public boolean contains(ISchedulingRule rule) {
		return rule == this;
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		if(rule instanceof DeleteTaskSchedulingRule)
			return true;
		return false;
	}

}
