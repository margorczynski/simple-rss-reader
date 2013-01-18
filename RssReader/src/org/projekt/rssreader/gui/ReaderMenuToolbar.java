package org.projekt.rssreader.gui;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;


public class ReaderMenuToolbar
{
	public ReaderMenuToolbar(Shell shl, MainWindow windowRef)
	{
		this.shl = shl;
		this.windowRef = windowRef;
		
		Menu menu = new Menu(shl, SWT.BAR);
		shl.setMenuBar(menu);
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");
		
		Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);
		
		MenuItem mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.setText("New");
		mntmNewItem.addSelectionListener(new NewItemListener());
		
		MenuItem mntmSave = new MenuItem(menu_1, SWT.NONE);
		mntmSave.setText("Save");
		mntmSave.addSelectionListener(new SaveItemListener());
		
		MenuItem mntmOpen = new MenuItem(menu_1, SWT.NONE);
		mntmOpen.setText("Open");
		mntmOpen.addSelectionListener(new OpenItemListener());
		
		new MenuItem(menu_1, SWT.SEPARATOR);
		
		MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);
		mntmExit.setText("Exit");
		mntmExit.addSelectionListener(new ExitItemListener());
		
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
	
	private class NewItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			windowRef.resetAll();
		}
	}
	
	private class OpenItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			FileDialog fd = new FileDialog(shl, SWT.OPEN);
			
	        fd.setText("Open");
	        fd.setFilterPath("./");
	        String[] filterExt = { "*.group", "*.*" };
	        fd.setFilterExtensions(filterExt);
	        
	        String selected = fd.open();
	        
	        if(selected != null) windowRef.openFromFile(selected);
		}
	}
	
	private class SaveItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			windowRef.saveToFile();
		}
	}
	
	private class ExitItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			shl.close();
		}
	}
	
	private MainWindow windowRef;
	
	private Shell shl; 
}
