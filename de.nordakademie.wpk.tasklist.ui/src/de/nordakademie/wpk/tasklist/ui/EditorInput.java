package de.nordakademie.wpk.tasklist.ui;


public interface EditorInput<T> {

	String getLabel();

	String getTooltip();

	T getInputObject();

	Class<?> getEditorClass();

	String getPartId();

	String getResourceURIString();

	String getIconURIString();

}