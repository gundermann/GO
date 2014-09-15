package de.nordakademie.wpk.tasklist.settings.ui;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class EditSettingsDialog extends TitleAreaDialog{
	private Text txtEmail;
	private Text txtEmail_1;
	private Text txtPasswort;
	
	
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
		gd_container.heightHint = 34;
		gd_container.widthHint = 63;
		container.setLayoutData(gd_container);
		
		txtEmail = new Text(container, SWT.BORDER);
		txtEmail.setText("E-Mail");
		txtEmail.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Button btnGoogletaskAnbindungHinzufgen = new Button(container, SWT.NONE);
		btnGoogletaskAnbindungHinzufgen.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnGoogletaskAnbindungHinzufgen.setText("Google-Task Anbindung hinzuf\u00FCgen");
		
		txtEmail_1 = new Text(container, SWT.BORDER);
		txtEmail_1.setText("E-Mail");
		txtEmail_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		txtPasswort = new Text(container, SWT.BORDER);
		txtPasswort.setText("Passwort");
		txtPasswort.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnWunderlistAnbindungHinzufgen = new Button(container, SWT.NONE);
		btnWunderlistAnbindungHinzufgen.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		btnWunderlistAnbindungHinzufgen.setText("Wunderlist Anbindung hinzuf\u00FCgen");
		return container;
		
	}

}
