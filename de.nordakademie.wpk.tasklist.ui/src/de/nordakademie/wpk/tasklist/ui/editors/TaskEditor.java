package de.nordakademie.wpk.tasklist.ui.editors;

import java.util.Date;

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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import de.nordakademie.wpk.tasklist.core.api.GoogleSetting;
import de.nordakademie.wpk.tasklist.core.api.ServiceException;
import de.nordakademie.wpk.tasklist.core.api.Task;
import de.nordakademie.wpk.tasklist.core.api.TaskService;
import de.nordakademie.wpk.tasklist.ui.ChangeListener;
import de.nordakademie.wpk.tasklist.ui.Constants;
import de.nordakademie.wpk.tasklist.ui.jobs.LoadAllJob;
import de.nordakademie.wpk.tasklist.ui.util.DateHelper;

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
	private DateTime dateTime;
	private Button btnCheckDateDue;
	private Label lblDateOfCompletion;
	private Label lblLastSync;

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
				editorPart.setDirty(true);
				if (btnFertig.getSelection())
					lblDateOfCompletion.setText(DateHelper
							.getCurrentDateAsString());
				else
					lblDateOfCompletion.setText("");
			}
		});
		formToolkit.adapt(btnFertig, true, true);
		btnFertig.setText("Erledigt am:");

		lblDateOfCompletion = new Label(composite, SWT.NONE);
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

		Composite composite_1 = new Composite(composite, SWT.NONE);
		formToolkit.adapt(composite_1);
		formToolkit.paintBordersFor(composite_1);
		composite_1.setLayout(new GridLayout(2, false));

		btnCheckDateDue = new Button(composite_1, SWT.CHECK);
		btnCheckDateDue.setBounds(0, 0, 111, 20);
		btnCheckDateDue.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dateTime.setEnabled(btnCheckDateDue.getSelection());
				editorPart.setDirty(true);
			}
		});

		formToolkit.adapt(btnCheckDateDue, true, true);

		dateTime = new DateTime(composite_1, SWT.BORDER);
		formToolkit.adapt(dateTime);
		formToolkit.paintBordersFor(dateTime);

		Label lblLetzteAktualisierung = new Label(composite, SWT.NONE);
		formToolkit.adapt(lblLetzteAktualisierung, true, true);
		lblLetzteAktualisierung.setText("Letzte Aktualisierung:");

		lblLastSync = new Label(composite, SWT.NONE);
		formToolkit.adapt(lblLastSync, true, true);
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
		dateTime.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				editorPart.setDirty(true);
			}
		});
	}

	private void initInput() {
		String todoUri = editorPart.getPersistedState().get(
				Constants.RESOURCE_URI_KEY);
		String[] split = todoUri.split("/");
		String providerName = split[1];
		tasklistId = split[2];
		if (split.length > 3) {
			String taskId = split[3];
			try {
				task = taskService
						.loadTask(taskId, tasklistId, new GoogleSetting());
			} catch (ServiceException e) {
				// TODO Auto-generated catch block
			}
			if (task != null) {
				txtName.setText(task.getTitle());
				txtComment.setText(task.getComment());
				btnFertig.setSelection(task.getStatus());
				lblDateOfCompletion.setText(DateHelper.getDateAsSting(task
						.getDateOfCompletion()));
				Date dateOfDue = task.getDateOfDue();
				if (dateOfDue == null) {
					btnCheckDateDue.setSelection(false);
					dateTime.setEnabled(false);
				} else {
					btnCheckDateDue.setSelection(true);
					dateTime.setEnabled(true);
					dateTime.setDate(DateHelper.getYear(dateOfDue),
							DateHelper.getMonth(dateOfDue),
							DateHelper.getDay(dateOfDue));
				}
			}
			lblLastSync.setText(DateHelper.getDateAsSting(task.getLastSync()));
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
		setupTask();
		try {
			taskService.addTask(task, tasklistId, new GoogleSetting());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
		}
		editorPart.setDirty(false);
		new LoadAllJob(taskService, eventBroker, new GoogleSetting())
				.schedule();
		// eventBroker.post(Topics.TASK_UPDATED, task);
	}

	private void setupTask() {
		task.setTitle(txtName.getText());
		task.setComment(txtComment.getText());
		task.setStatus(btnFertig.getSelection());
			task.setDateOfCompletion(DateHelper
					.getDateFromString(lblDateOfCompletion.getText()));
		if (btnCheckDateDue.getSelection()) {
			task.setDateOfDue(DateHelper.getDate(dateTime.getYear(),
					dateTime.getMonth(), dateTime.getDay()));
		}
		else
			task.setDateOfDue(null);		
	}

	private void updateTask() {
		setupTask();
		try {
			taskService.updateTask(task, tasklistId, new GoogleSetting());
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
		}
		editorPart.setDirty(false);
	}
}
