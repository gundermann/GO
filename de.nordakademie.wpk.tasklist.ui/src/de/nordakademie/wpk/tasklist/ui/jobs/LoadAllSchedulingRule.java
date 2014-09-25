package de.nordakademie.wpk.tasklist.ui.jobs;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

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
