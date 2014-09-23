package de.nordakademie.wpk.tasklist.ui;

import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;

public class TaskEditorInput implements EditorInput<Task> {

	private Task task;
	private TaskList tasklist;

	public TaskEditorInput(Task task, TaskList tasklist) {
		this.task = task;
		this.tasklist = tasklist;
	}

	@Override
	public String getLabel() {
		return task.getTitle();
	}

	@Override
	public String getTooltip() {
		return task.getComment() != null ? task.getComment().substring(0, 10)
				: "";
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
		return String.format("%s#%s", TaskEditor.class.getName(), task.getId());
	}

	@Override
	public String getResourceURIString() {
		return String.format("resource:/%s/%s/%s", tasklist.getProvider()
				.toString(), tasklist.getId(), task.getId());
	}

	@Override
	public String getIconURIString() {
		return null;
	}

}
