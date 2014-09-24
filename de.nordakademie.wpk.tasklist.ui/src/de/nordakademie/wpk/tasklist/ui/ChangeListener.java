package de.nordakademie.wpk.tasklist.ui;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;

public class ChangeListener implements ModifyListener {

	private MPart editorPart;

	public ChangeListener(MPart editorPart) {
		this.editorPart = editorPart;
	}

	@Override
	public void modifyText(ModifyEvent e) {
		editorPart.setDirty(true);
	}

}
