package org.projekt.rssreader.gui;

import org.projekt.rssreader.gui.dialog.*;

import org.eclipse.swt.widgets.Shell;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.FileDialog;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.window.Window;


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
		
		MenuItem mntmAddChannelGroup = new MenuItem(menu_2, SWT.NONE);
		mntmAddChannelGroup.setText("Add channel group");
		mntmAddChannelGroup.addSelectionListener(new AddChannelGroupItemListener());
		
		MenuItem mntmAddChannel = new MenuItem(menu_2, SWT.NONE);
		mntmAddChannel.setText("Add channel");
		mntmAddChannel.addSelectionListener(new AddChannelItemListener());
		
		new MenuItem(menu_2, SWT.SEPARATOR);
		
		MenuItem mntmSettings = new MenuItem(menu_2, SWT.NONE);
		mntmSettings.setText("Settings");
		mntmSettings.addSelectionListener(new SettingsItemListener());
		
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
	        String[] filterExt = { "*.xml"};
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
	
	private class AddChannelGroupItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			InputDialog addGroupDialog = new InputDialog(shl, "Enter group name", "Enter 3-16 characters", "", new LengthValidator());
			
			if (addGroupDialog.open() == Window.OK) 
			{
				treeRef.addChannelGroup(addGroupDialog.getValue());
			}        
		}
		
		private class LengthValidator implements IInputValidator 
		{
			public String isValid(String input)
			{
			    int len = input.length();

			    if (len < 3) return "Too short";
			    if (len > 16) return "Too long";

			    return null;
			  }	  
		}
	}
	
	private class AddChannelItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			AddChannelDialog addChannelDialog = new AddChannelDialog(shl, treeRef);
			
			addChannelDialog.open();
		}
	}
	
	private class SettingsItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(SelectionEvent e)
		{
			SettingsDialog settingsDialog = new SettingsDialog(shl, windowRef);
			
			settingsDialog.open();
		}
	}
	
	
	public void setTreeRef(ChannelGroupTree treeRef)
	{
		this.treeRef = treeRef;
	}
	
	private MainWindow windowRef;
	private ChannelGroupTree treeRef;
	
	private Shell shl; 
}
