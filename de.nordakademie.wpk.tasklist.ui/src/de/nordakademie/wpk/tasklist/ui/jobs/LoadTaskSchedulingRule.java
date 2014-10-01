package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

/**
 * Scheduling-rule für den Job, der eine konkrete Task vom Server holt.
 * @author Niels Gundermann
 *
 */
public class LoadTaskSchedulingRule implements ISchedulingRule {


	@Override
	public boolean contains(ISchedulingRule rule) {
		return rule == this;
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		if (rule instanceof LoadTaskSchedulingRule) {
			return true;
		}
		return false;
	}

}
