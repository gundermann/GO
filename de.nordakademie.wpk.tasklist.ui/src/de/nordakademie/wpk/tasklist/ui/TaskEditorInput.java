package de.nordakademie.wpk.tasklist.ui;

import de.nordakademie.wpk.tasklist.core.api.Task;

public class TaskEditorInput implements EditorInput<Task> {

	private Task task;

	public TaskEditorInput(Task task) {
		this.task = task;
	}

	@Override
	public String getLabel() {
		return task.getTitle();
	}

	@Override
	public String getTooltip() {
		return task.getComment() != null ? task.getComment().substring(0, 10) : "";
	}

	@Override
	public Task getInputObject() {
		return task;
	}

	@Override
	public Class<?> getEditorClass() {
		return TaskEditor.class;
	}

	@Override
	public String getPartId() {
		return String.format("%s#%d", TaskEditor.class.getName(), task.getId());
	}

	@Override
	public String getResourceURIString() {
		return String.format("resource:/task/%d", task.getId());
	}

	@Override
	public String getIconURIString() {
		return null;
	}

}
