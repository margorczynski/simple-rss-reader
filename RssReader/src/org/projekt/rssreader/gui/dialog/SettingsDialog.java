package org.projekt.rssreader.gui.dialog;

import org.projekt.rssreader.gui.MainWindow;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SettingsDialog extends Dialog
{
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public SettingsDialog(Shell parentShell, MainWindow windowRef)
	{
		super(parentShell);
		
		this.windowRef = windowRef;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent)
	{
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FormLayout());
		
		text = new Text(container, SWT.BORDER);
		FormData fd_text = new FormData();
		fd_text.right = new FormAttachment(0, 202);
		fd_text.left = new FormAttachment(0, 10);
		text.setLayoutData(fd_text);
		
		Label lblStartupBrowserPage = new Label(container, SWT.NONE);
		FormData fd_lblStartupBrowserPage = new FormData();
		fd_lblStartupBrowserPage.right = new FormAttachment(100, -47);
		lblStartupBrowserPage.setLayoutData(fd_lblStartupBrowserPage);
		lblStartupBrowserPage.setText("Startup browser page");
		
		final Button btnCheckButton = new Button(container, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) 
			{
				checked = btnCheckButton.getSelection();
			}
		});
		FormData fd_btnCheckButton = new FormData();
		fd_btnCheckButton.bottom = new FormAttachment(100, -10);
		fd_btnCheckButton.left = new FormAttachment(0, 10);
		fd_btnCheckButton.top = new FormAttachment(0, 73);
		btnCheckButton.setLayoutData(fd_btnCheckButton);
		btnCheckButton.setText("Load groups from file");
		
		Label lblStartupPage = new Label(container, SWT.NONE);
		fd_text.top = new FormAttachment(lblStartupPage, 6);
		fd_lblStartupBrowserPage.bottom = new FormAttachment(lblStartupPage, -27);
		FormData fd_lblStartupPage = new FormData();
		fd_lblStartupPage.bottom = new FormAttachment(100, -114);
		fd_lblStartupPage.left = new FormAttachment(0, 10);
		lblStartupPage.setLayoutData(fd_lblStartupPage);
		lblStartupPage.setText("Startup page");

		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent)
	{
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize()
	{
		return new Point(218, 228);
	}
	
	public void okPressed()
    {
		windowRef.setSettings(text.getText(), checked);
		
		windowRef.saveSettingsToFile();
		
        this.close();
    }
	

	private MainWindow windowRef;
	
	private Text text;
	
	private boolean checked = false;
}