package org.projekt.rssreader.gui;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ReaderMenuToolbar
{
	public ReaderMenuToolbar(Shell shl)
	{
		this.shl = shl;
		
		Menu menu = new Menu(shl, SWT.BAR);
		shl.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		MenuItem mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.setText("New");
		
		MenuItem mntmSave = new MenuItem(menu_1, SWT.NONE);
		mntmSave.setText("Save");
		
		MenuItem mntmOpen = new MenuItem(menu_1, SWT.NONE);
		mntmOpen.setText("Open");
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);
		mntmExit.setText("Exit");
		
		MenuItem mntmEdit = new MenuItem(menu, SWT.CASCADE);
		mntmEdit.setText("Edit");
		
		Menu menu_2 = new Menu(mntmEdit);
		mntmEdit.setMenu(menu_2);
		
		MenuItem mntmAddGroup = new MenuItem(menu_2, SWT.NONE);
		mntmAddGroup.setText("Add group");
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmSettings = new MenuItem(menu_2, SWT.NONE);
		mntmSettings.setText("Settings");
		
		MenuItem mntmAbout = new MenuItem(menu, SWT.NONE);
		mntmAbout.addSelectionListener(new AboutItemListener());
		mntmAbout.setText("About");
	}
	
	private class AboutItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			MessageDialog dialog = new MessageDialog(shl, "About", null,
				    "Simple RSS Reader\nVersion 1.0\n\nCreated by Marcin Gorczyñski\nAll rights reserved", MessageDialog.INFORMATION, new String[] { "OK"}, 0);
				dialog.open();
		}
	}
	
	
	private Shell shl; 
}
