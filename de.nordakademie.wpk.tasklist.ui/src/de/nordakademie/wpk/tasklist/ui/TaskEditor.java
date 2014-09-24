package de.nordakademie.wpk.tasklist.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

import de.nordakademie.wpk.tasklist.core.api.GoogleSetting;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskService;

public class TaskEditor {

	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());
	private Text txtName;
	private Text txtComment;
	private Text txtResponseable;

	@Inject
	private MPart editorPart;

	@Inject
	private IEventBroker eventBroker;

	@Inject
	private TaskService taskService;
	private Combo comboPriority;
	private Task task;
	private Button btnFertig;
	private String tasklistId;

	@PostConstruct
	public void createPartControl(Composite parent) {
		Section sctnNewSection = formToolkit.createSection(parent,
				Section.TWISTIE | Section.TITLE_BAR);
		sctnNewSection.setBounds(0, 0, 448, 350);
		formToolkit.paintBordersFor(sctnNewSection);
		sctnNewSection.setText("Edit details");
		sctnNewSection.setExpanded(true);

		Composite composite = formToolkit.createComposite(sctnNewSection,
				SWT.NONE);
		formToolkit.paintBordersFor(composite);
		sctnNewSection.setClient(composite);
		composite.setLayout(new GridLayout(2, false));
		
		btnFertig = new Button(composite, SWT.CHECK);
		btnFertig.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			}
		});
		formToolkit.adapt(btnFertig, true, true);
		btnFertig.setText("Erledigt am:");
		
		Label lblDateOfCompletion = new Label(composite, SWT.NONE);
		formToolkit.adapt(lblDateOfCompletion, true, true);

		Label lblName = formToolkit.createLabel(composite, "Name:",
				SWT.NONE);

		txtName = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtName.setText("");
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblComment = formToolkit.createLabel(composite,
				"Kommentar:", SWT.NONE);
		GridData gd_lblComment = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblComment.heightHint = 19;
		lblComment.setLayoutData(gd_lblComment);

		txtComment = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtComment.setText("");
		txtComment.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
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

		txtResponseable = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtResponseable.setText("");
		txtResponseable.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		
		Label lblFaelligkeit = new Label(composite, SWT.NONE);
		formToolkit.adapt(lblFaelligkeit, true, true);
		lblFaelligkeit.setText("F\u00E4lligkeit:");
		
		Label lblDueTo = new Label(composite, SWT.NONE);
		formToolkit.adapt(lblDueTo, true, true);
		
		Label lblLetzteAktualisierung = new Label(composite, SWT.NONE);
		formToolkit.adapt(lblLetzteAktualisierung, true, true);
		lblLetzteAktualisierung.setText("Letzte Aktualisierung:");
		
		Label lblLastSync = new Label(composite, SWT.NONE);
		formToolkit.adapt(lblLastSync, true, true);

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

		txtName.addModifyListener(new ChangeListener(editorPart));
		txtComment.addModifyListener(new ChangeListener(editorPart));
//		txtDescription.addModifyListener(new ChangeListener(editorPart));
//		comboPriority.addModifyListener(new ChangeListener(editorPart));
//		txtResponseable.addModifyListener(new ChangeListener(editorPart));
	}

	private void initInput() {
		String todoUri = editorPart.getPersistedState().get(
				Constants.RESOURCE_URI_KEY);
		String[] split = todoUri.split("/");
		String providerName = split[1];
		tasklistId = split[2];
		String taskId = split[3];
//		Provider provider = Provider.valueOf(providerName);
		task = taskService.loadTask(taskId, tasklistId, new GoogleSetting());
		if (task != null) {
			txtName.setText(task.getTitle());
			txtComment.setText(task.getComment());
			btnFertig.setSelection(task.getStatus());
		}
	}

	@Focus
	public void onFocus() {
		txtName.setFocus();
	}

	@Persist
	public void save() {
		task.setTitle(txtName.getText());
		task.setComment(txtComment.getText());
		taskService.updateTask(task, tasklistId, new GoogleSetting());
		editorPart.setDirty(false);
//		eventBroker.post(Topics.TASK_UPDATED, task);
	}
}
