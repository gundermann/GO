package de.nordakademie.wpk.tasklist.ui.editors;

/**
 * Schnittstelle für den TaskEditorInput.
 * @author Kathrin Kurtz
 */
public interface IEditorInput<T> {

	String getLabel();

	String getTooltip();

	T getInputObject();

	Class<?> getEditorClass();

	String getPartId();

	String getResourceURIString();

	String getIconURIString();

}