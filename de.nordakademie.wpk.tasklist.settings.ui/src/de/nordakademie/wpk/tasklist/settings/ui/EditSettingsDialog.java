package de.nordakademie.wpk.tasklist.settings.ui;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.nordakademie.wpk.tasklist.core.api.NoSettingFoundException;
import de.nordakademie.wpk.tasklist.core.api.Provider;
import de.nordakademie.wpk.tasklist.core.api.ProviderSetting;
import de.nordakademie.wpk.tasklist.core.client.ProviderSettingImpl;
import de.nordakademie.wpk.tasklist.core.client.SettingLoader;
import de.nordakademie.wpk.tasklist.core.client.SettingSaver;

import org.eclipse.swt.widgets.Button;

/**
 * Zeigt den Dialog zum Eingeben von Verbindungseinstellungen an.
 * Für alle Provider außer Google.
 * @author Kathrin Kurtz
 *
 */
public class EditSettingsDialog extends TitleAreaDialog {
	private Text txtWunderlistEmail;
	private Text txtWunderlistPasswort;
	private Button checkBoxWunderlist;

	/**
	 * Create the dialog.
	 * 
	 * @param parent
	 * @param style
	 */
	public EditSettingsDialog(Shell parent, int style) {
		super(parent);
		setTitle("SWTDialog");
	}

	@Override
	protected void setShellStyle(int newShellStyle) {
		super.setShellStyle(newShellStyle | SWT.RESIZE);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Einstellungen");
		setHelpAvailable(false);
	}

	/**
	 * Create contents of the dialog.
	 */
	public Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(4, false));
		GridData gd_container = new GridData(SWT.FILL, SWT.FILL, true, true, 1,
				1);
		gd_container.heightHint = 83;
		gd_container.widthHint = 63;
		container.setLayoutData(gd_container);

		checkBoxWunderlist = new Button(container, SWT.CHECK);
		checkBoxWunderlist.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtWunderlistEmail.setEnabled(checkBoxWunderlist.getSelection());
				txtWunderlistPasswort.setEnabled(checkBoxWunderlist
						.getSelection());
			}
		});

		Label lblWunderlistAnbindung = new Label(container, SWT.NONE);
		lblWunderlistAnbindung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				false, false, 1, 1));
		lblWunderlistAnbindung.setText("Wunderlist Anbindung");

		txtWunderlistEmail = new Text(container, SWT.BORDER);
		txtWunderlistEmail.setText("E-Mail");
		txtWunderlistEmail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));
		txtWunderlistEmail.setEnabled(false);

		txtWunderlistPasswort = new Text(container, SWT.PASSWORD | SWT.BORDER);
		txtWunderlistPasswort.setText("Passwort");
		txtWunderlistPasswort.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));
		txtWunderlistPasswort.setEnabled(false);

		if (WunderlistIsActive()) {
			checkBoxWunderlist.setSelection(true);
			txtWunderlistEmail.setEnabled(true);
			txtWunderlistPasswort.setEnabled(true);
		}
		fillWunderlistInformation();

		return container;

	}

	/**
	 * Gibt true zurück wenn die Wunderlist-Verbindung beim letzten Speichern aktiv(angehakt) war.
	 * @return
	 */
	private boolean WunderlistIsActive() {
		boolean active = false;
		ProviderSetting wlSetting = getProviderInformation(Provider.WUNDERLIST);
		if (wlSetting != null) {
			active = wlSetting.isActive();
		}
		return active;
	}

	/**
	 * Füllt die Checkbox, Mailadresse und Passwort-Box für Wunderlist
	 */
	private void fillWunderlistInformation() {
		ProviderSetting setting = getProviderInformation(Provider.WUNDERLIST);
		txtWunderlistEmail.setText(setting.getUsername());
		txtWunderlistPasswort.setText(setting.getPassword());
	}

	private ProviderSetting getProviderInformation(Provider provider) {
		SettingLoader loader = new SettingLoader();
		ProviderSetting setting = null;
		try {
			setting = loader.loadFromFile(provider);
		} catch (NoSettingFoundException e) {
		}
		return setting;
	}

	@Override
	protected void okPressed() {
		SettingSaver saver = new SettingSaver();
		if (checkBoxWunderlist.getSelection()) {
			saveWunderlistSettings(saver, true);
		} else {
			saveWunderlistSettings(saver, false);
		}
		super.okPressed();
	}

	private void saveWunderlistSettings(SettingSaver saver, Boolean isActive) {
		ProviderSettingImpl wlSetting = new ProviderSettingImpl();
		wlSetting.setActive(isActive);
		wlSetting.setPassword(txtWunderlistPasswort.getText());
		wlSetting.setUsername(txtWunderlistEmail.getText());
		wlSetting.setProvider(Provider.WUNDERLIST);
		saver.saveSetting(wlSetting);
	}

}
