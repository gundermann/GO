package de.nordakademie.wpk.tasklist.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.osgi.framework.Constants;

import de.nordakademie.wpk.todolist.core.api.Task;
import de.nordakademie.wpk.todolist.core.api.TaskService;

public class TaskEditor {

	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());
	private Text txtName;
	private Text txtDescription;
	private Text txtResponseable;

	@Inject
	private MPart editorPart;

	@Inject
	private IEventBroker eventBroker;

	@Inject
	private TaskService todoService;
	private Combo comboPriority;
	private Task todo;

	@PostConstruct
	public void createPartControl(Composite parent) {
		Section sctnNewSection = formToolkit.createSection(parent,
				Section.TWISTIE | Section.TITLE_BAR);
		formToolkit.paintBordersFor(sctnNewSection);
		sctnNewSection.setText("Edit details");
		sctnNewSection.setExpanded(true);

		Composite composite = formToolkit.createComposite(sctnNewSection,
				SWT.NONE);
		formToolkit.paintBordersFor(composite);
		sctnNewSection.setClient(composite);
		composite.setLayout(new GridLayout(2, false));

		Label lblName = formToolkit.createLabel(composite, "Name:",
				SWT.NONE);

		txtName = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtName.setText("");
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblDescription = formToolkit.createLabel(composite,
				"Bezeichnung:", SWT.NONE);
		GridData gd_lblDescription = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblDescription.heightHint = 19;
		lblDescription.setLayoutData(gd_lblDescription);

		txtDescription = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtDescription.setText("");
		txtDescription.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblPriority = formToolkit.createLabel(composite,
				"Priorit\u00E4t:", SWT.NONE);

		comboPriority = new Combo(composite, SWT.NONE);
		comboPriority.setItems(new String[] { "0", "1", "2", "3", "4", "5" });
		comboPriority.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1,
				1));
		formToolkit.adapt(comboPriority);
		formToolkit.paintBordersFor(comboPriority);
		comboPriority.select(0);

		Label lblResponseable = formToolkit.createLabel(composite,
				"Verantwortlicher:", SWT.NONE);
		lblResponseable.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));

		txtResponseable = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtResponseable.setText("Niels");
		txtResponseable.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);

		Button btnCreate = formToolkit.createButton(composite, "Create",
				SWT.NONE);
		new Label(composite, SWT.NONE);

		initInput();

//		txtName.addModifyListener(new ChangeListener(editorPart));
//		txtDescription.addModifyListener(new ChangeListener(editorPart));
//		comboPriority.addModifyListener(new ChangeListener(editorPart));
//		txtResponseable.addModifyListener(new ChangeListener(editorPart));
	}

	private void initInput() {
//		String todoUri = editorPart.getPersistedState().get(
//				Constants.RESOURCE_URI_KEY);
//		String[] split = todoUri.split("/");
//		Long id = Long.parseLong(split[2]);
//		todo = todoService.loadTodo(id);
//		if (todo != null) {
//			txtNewText.setText(todo.getName());
//			txtNewText_1.setText(todo.getDescription());
//			combo.select(todo.getPriority());
//			txtNewText_3.setText(todo.getResponsablePerson());
//		}
	}

	@Focus
	public void onFocus() {
		txtName.setFocus();
	}

	@Persist
	public void save() {
//		todoService.saveTodo(todo.getId(), txtNewText.getText(),
//				txtNewText_1.getText(), combo.getSelectionIndex(),
//				txtNewText_3.getText());
//		editorPart.setDirty(false);
//		eventBroker.post(Topics.TODO_CHANGED, todo);
	}
}
