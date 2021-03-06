package de.nordakademie.wpk.tasklist.ui.editors;

import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskList;

/**
 * �bergibt die Eingaben f�r den TaskEditor.
 * @author Kathrin Kurtz
 *
 */
public class TaskEditorInput implements IEditorInput<Task> {

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
		return task.getComment() != null ? task.getComment().substring(
				0,
				task.getComment().length() > 10 ? 10 : task.getComment()
						.length()) : "";
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
