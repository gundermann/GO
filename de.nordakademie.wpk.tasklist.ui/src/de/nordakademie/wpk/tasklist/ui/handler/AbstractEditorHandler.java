package de.nordakademie.wpk.tasklist.ui.handler;


import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

import de.nordakademie.wpk.tasklist.ui.Constants;
import de.nordakademie.wpk.tasklist.ui.editors.IEditorInput;

/**
 * Ermöglicht das öffnen mehrerer Editoren
 * Quelle: Vorlesung
 * @author Niels Gundermann
 *
 */
public abstract class AbstractEditorHandler {

	private static final String EDITOR_STACK_ID = "de.nordakademie.wpk.tasklist.ui.editors";
	@Inject
	private MApplication application;
	@Inject
	private EModelService modelService;
	@Inject
	private EPartService partService;

	protected void openEditor(IEditorInput<?> input, String bundleSymbolicName) {
		MPart part = partService.findPart(input.getPartId());
		if (part == null) {
			part = MBasicFactory.INSTANCE.createPart();
			part.setElementId(input.getPartId());
			part.setLabel(input.getLabel());
			part.setTooltip(input.getTooltip());
			part.setContributionURI(String.format(
					"bundleclass://%s/%s",bundleSymbolicName,	
					input.getEditorClass().getName()));
			part.getPersistedState().put(Constants.RESOURCE_URI_KEY,
					input.getResourceURIString());
			part.setIconURI(input.getIconURIString());
			part.setCloseable(true);
			MPartStack editorStack = (MPartStack) modelService.find(
					EDITOR_STACK_ID, application);
			editorStack.getChildren().add(part);
		}
		partService.showPart(part, PartState.ACTIVATE);
	}
}
