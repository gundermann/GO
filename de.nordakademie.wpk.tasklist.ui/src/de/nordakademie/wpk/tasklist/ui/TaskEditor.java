package de.nordakademie.wpk.tasklist.ui;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
import de.nordakademie.wpk.tasklist.ui.jobs.LoadAllJob;

import org.eclipse.swt.widgets.DateTime;

public class TaskEditor {

	private final FormToolkit formToolkit = new FormToolkit(
			Display.getDefault());
	private Text txtName;
	private Text txtComment;

	@Inject
	private MPart editorPart;

	@Inject
	private IEventBroker eventBroker;

	@Inject
	private TaskService taskService;
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

		Label lblName = formToolkit.createLabel(composite, "Name:", SWT.NONE);

		txtName = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtName.setText("");
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));

		Label lblComment = formToolkit.createLabel(composite, "Kommentar:",
				SWT.NONE);
		GridData gd_lblComment = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_lblComment.heightHint = 19;
		lblComment.setLayoutData(gd_lblComment);

		txtComment = formToolkit.createText(composite, "New Text", SWT.NONE);
		txtComment.setText("");
		txtComment.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblFaelligkeit = new Label(composite, SWT.NONE);
		formToolkit.adapt(lblFaelligkeit, true, true);
		lblFaelligkeit.setText("F\u00E4lligkeit:");
		
		DateTime dateTime = new DateTime(composite, SWT.BORDER);
		formToolkit.adapt(dateTime);
		formToolkit.paintBordersFor(dateTime);

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

		Button btnSave = formToolkit.createButton(composite, "Speichern",
				SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				save();
			}
		});
		new Label(composite, SWT.NONE);

		initInput();

		txtName.addModifyListener(new ChangeListener(editorPart));
		txtComment.addModifyListener(new ChangeListener(editorPart));
		// txtDescription.addModifyListener(new ChangeListener(editorPart));
		// comboPriority.addModifyListener(new ChangeListener(editorPart));
		// txtResponseable.addModifyListener(new ChangeListener(editorPart));
	}

	private void initInput() {
		String todoUri = editorPart.getPersistedState().get(
				Constants.RESOURCE_URI_KEY);
		String[] split = todoUri.split("/");
		String providerName = split[1];
		tasklistId = split[2];
		if (split.length > 3) {
			String taskId = split[3];
			task = taskService
					.loadTask(taskId, tasklistId, new GoogleSetting());
			if (task != null) {
				txtName.setText(task.getTitle());
				txtComment.setText(task.getComment());
				btnFertig.setSelection(task.getStatus());
			}
		} else {
			txtName.setText("Neue Task");
			editorPart.setDirty(true);
		}
	}

	@Focus
	public void onFocus() {
		txtName.setFocus();
	}

	@Persist
	public void save() {
		if (task != null) {
			updateTask();
		} else {
			saveNewTask();
		}
		editorPart.setDirty(false);

	}

	private void saveNewTask() {
		task = new Task();
		task.setTitle(txtName.getText());
		taskService.addTask(task, tasklistId, new GoogleSetting());
		task.setComment(txtComment.getText());
		editorPart.setDirty(false);
		new LoadAllJob(taskService, eventBroker, new GoogleSetting())
				.schedule();
		// eventBroker.post(Topics.TASK_UPDATED, task);
	}

	private void updateTask() {
		task.setTitle(txtName.getText());
		task.setComment(txtComment.getText());
		taskService.updateTask(task, tasklistId, new GoogleSetting());
		editorPart.setDirty(false);
	}

}
