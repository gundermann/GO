package de.nordakademie.wpk.tasklist.settings.ui;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class EditSettingsDialog extends TitleAreaDialog{
	private Text txtGoogleEmail;
	private Text txtWunderlistEmail;
	private Text txtWunderlistPasswort;
	
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public EditSettingsDialog(Shell parent, int style ){
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
		container.setLayout(new GridLayout(3, false));
		GridData gd_container = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_container.heightHint = 83;
		gd_container.widthHint = 63;
		container.setLayoutData(gd_container);
		
		Label lblGoogletaskanbindung = new Label(container, SWT.NONE);
		lblGoogletaskanbindung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblGoogletaskanbindung.setText("GoogleTask Anbindung");
		
		txtGoogleEmail = new Text(container, SWT.BORDER);
		txtGoogleEmail.setText("E-Mail");
		txtGoogleEmail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Label lblWunderlistAnbindung = new Label(container, SWT.NONE);
		lblWunderlistAnbindung.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblWunderlistAnbindung.setText("Wunderlist Anbindung");
		
		txtWunderlistEmail = new Text(container, SWT.BORDER);
		txtWunderlistEmail.setText("E-Mail");
		txtWunderlistEmail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		txtWunderlistPasswort = new Text(container, SWT.PASSWORD | SWT.BORDER);
		txtWunderlistPasswort.setText("Passwort");
		txtWunderlistPasswort.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		return container;
		
	}

}
