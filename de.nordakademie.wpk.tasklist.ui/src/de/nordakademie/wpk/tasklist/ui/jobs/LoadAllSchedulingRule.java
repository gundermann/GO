package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

/**
 * Scheduling-rule für den Job, der alle Tasklisten vom Server holt.
 * Zwei Instanzen dieses Jobs sollten nicht parallel laufen.
 * @author Niels Gundermann
 *
 */
public class LoadAllSchedulingRule implements ISchedulingRule {


	public LoadAllSchedulingRule() {
	}

	@Override
	public boolean contains(ISchedulingRule rule) {
		return rule == this;
	}

	@Override
	public boolean isConflicting(ISchedulingRule rule) {
		if (rule instanceof LoadAllSchedulingRule) {
			return true;
		}
		return false;
	}

}
