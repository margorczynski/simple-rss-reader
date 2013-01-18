package org.projekt.rssreader.gui.dialog;

import org.projekt.rssreader.gui.ChannelGroupTree;
import org.projekt.rssreader.content.tree.ChannelGroup;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class AddChannelDialog extends Dialog
{
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public AddChannelDialog(Shell parentShell, ChannelGroupTree treeRef)
	{
		super(parentShell);
		
		this.treeRef = treeRef;
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
		
		combo = new Combo(container, SWT.READ_ONLY);
		FormData fd_combo = new FormData();
		fd_combo.bottom = new FormAttachment(100, -21);
		fd_combo.left = new FormAttachment(0, 10);
		combo.setLayoutData(fd_combo);

		channelGroups  = treeRef.getChannelGroups();
		
		for(ChannelGroup group : channelGroups)
		{
			combo.add(group.getName());
		}
		
		combo.select(0);
		

		text = new Text(container, SWT.BORDER);
		fd_combo.right = new FormAttachment(100, -272);
		FormData fd_text = new FormData();
		fd_text.top = new FormAttachment(combo, 0, SWT.TOP);
		fd_text.left = new FormAttachment(combo, 6);
		fd_text.right = new FormAttachment(100, -10);
		text.setLayoutData(fd_text);
		
		Label lblChannelGroupName = new Label(container, SWT.NONE);
		fd_combo.top = new FormAttachment(lblChannelGroupName, 6);
		lblChannelGroupName.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		FormData fd_lblChannelGroupName = new FormData();
		fd_lblChannelGroupName.left = new FormAttachment(0, 10);
		lblChannelGroupName.setLayoutData(fd_lblChannelGroupName);
		lblChannelGroupName.setText("Channel group name");
		
		Label lblChannelUrl = new Label(container, SWT.NONE);
		fd_lblChannelGroupName.right = new FormAttachment(lblChannelUrl, -45);
		fd_lblChannelGroupName.top = new FormAttachment(lblChannelUrl, 0, SWT.TOP);
		lblChannelUrl.setForeground(SWTResourceManager.getColor(SWT.COLOR_INFO_FOREGROUND));
		FormData fd_lblChannelUrl = new FormData();
		fd_lblChannelUrl.top = new FormAttachment(0, 10);
		fd_lblChannelUrl.left = new FormAttachment(0, 182);
		lblChannelUrl.setLayoutData(fd_lblChannelUrl);
		lblChannelUrl.setText("Channel URL");

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
		return new Point(450, 154);
	}
	
	public void okPressed()
    {		
        treeRef.addChannel(combo.getText(), text.getText());
        
        this.close();
    }
	
	
	private ChannelGroupTree treeRef;
	
	private List<ChannelGroup> channelGroups;
	
	private Combo combo;
	
	private Text text;
}
