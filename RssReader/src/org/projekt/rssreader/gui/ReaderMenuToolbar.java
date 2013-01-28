package org.projekt.rssreader.gui;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.projekt.rssreader.gui.dialog.AddChannelDialog;
import org.projekt.rssreader.gui.dialog.SettingsDialog;
import org.projekt.rssreader.main.Settings;

/**
 * Class used for creation of the object representing the top toolbar of the
 * main window
 */
public class ReaderMenuToolbar
{
	/**
	 * The constructor of the toolbar class. Takes the shell and main window
	 * reference as parameters Then creates the toolbars items and sets their
	 * listeners
	 * 
	 * @param shl
	 *            the main shell of the reader
	 * @param windowRef
	 *            the reference to the main window object
	 * @param settings
	 *            the reference to the object holding the settings of the reader
	 * 
	 * @see Menu
	 * @see MenuItem
	 */
	public ReaderMenuToolbar(final Shell shl, final MainWindow windowRef, final Settings settings)
	{
		this.shl = shl;
		this.windowRef = windowRef;

		final Menu menu = new Menu(shl, SWT.BAR);
		shl.setMenuBar(menu);

		final MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");

		final Menu menu_1 = new Menu(mntmFile);
		mntmFile.setMenu(menu_1);

		final MenuItem mntmNewItem = new MenuItem(menu_1, SWT.NONE);
		mntmNewItem.setText("New");
		mntmNewItem.addSelectionListener(new NewItemListener());

		final MenuItem mntmSave = new MenuItem(menu_1, SWT.NONE);
		mntmSave.setText("Save");
		mntmSave.addSelectionListener(new SaveItemListener());

		final MenuItem mntmOpen = new MenuItem(menu_1, SWT.NONE);
		mntmOpen.setText("Open");
		mntmOpen.addSelectionListener(new OpenItemListener());

		new MenuItem(menu_1, SWT.SEPARATOR);

		final MenuItem mntmExit = new MenuItem(menu_1, SWT.NONE);
		mntmExit.setText("Exit");
		mntmExit.addSelectionListener(new ExitItemListener());

		final MenuItem mntmEdit = new MenuItem(menu, SWT.CASCADE);
		mntmEdit.setText("Edit");

		final Menu menu_2 = new Menu(mntmEdit);
		mntmEdit.setMenu(menu_2);

		final MenuItem mntmAddChannelGroup = new MenuItem(menu_2, SWT.NONE);
		mntmAddChannelGroup.setText("Add channel group");
		mntmAddChannelGroup.addSelectionListener(new AddChannelGroupItemListener());

		final MenuItem mntmAddChannel = new MenuItem(menu_2, SWT.NONE);
		mntmAddChannel.setText("Add channel");
		mntmAddChannel.addSelectionListener(new AddChannelItemListener());

		new MenuItem(menu_2, SWT.SEPARATOR);

		final MenuItem mntmSettings = new MenuItem(menu_2, SWT.NONE);
		mntmSettings.setText("Settings");
		mntmSettings.addSelectionListener(new SettingsItemListener());

		final MenuItem mntmAbout = new MenuItem(menu, SWT.NONE);
		mntmAbout.addSelectionListener(new AboutItemListener());
		mntmAbout.setText("About");
	}

	/**
	 * The listener of the About menu item. Creates a simple message box with
	 * information about the application
	 * 
	 * @see SelectionAdapter
	 * @see MessageBox
	 */
	private class AboutItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(final SelectionEvent e)
		{
			final MessageDialog dialog = new MessageDialog(shl, "About", null, "Simple RSS Reader\nVersion 1.0\n\nCreated by Marcin Gorczy�ski\nAll rights reserved", MessageDialog.INFORMATION, new String[] { "OK" }, 0);
			dialog.open();
		}
	}

	/**
	 * The listener of the New item menu item. It just resets the whole
	 * application using the resetAll() method of the main window
	 * 
	 * @see MainWindow
	 * @see SelectionAdapter
	 */
	private class NewItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(final SelectionEvent e)
		{
			windowRef.resetAll();
		}
	}

	/**
	 * The listener of the Open menu item. It creates a file dialog and uses
	 * it's return value to load content to the tree from a file
	 * 
	 * @see FileDialog
	 * @see MainWindow
	 * @see SelectionAdapter
	 */
	private class OpenItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(final SelectionEvent e)
		{
			final FileDialog fd = new FileDialog(shl, SWT.OPEN);

			fd.setText("Open");
			fd.setFilterPath("./");
			final String[] filterExt = { "*.xml" };
			fd.setFilterExtensions(filterExt);

			final String selected = fd.open();

			if (selected != null) windowRef.openFromFile(selected);
		}
	}

	/**
	 * The listener of the Save menu item. It simply calls the saveToFile()
	 * method of the main window to save the tree's content to a file
	 * 
	 * @see SelectionAdapter
	 */
	private class SaveItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(final SelectionEvent e)
		{
			windowRef.saveToFile();
		}
	}

	/**
	 * The listener of the Exit menu item. It just exits the application by
	 * closing the main shell
	 * 
	 * @see SelectionAdapter
	 */
	private class ExitItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(final SelectionEvent e)
		{
			shl.close();
		}
	}

	/**
	 * The listener of the Add channel menu item. It creates a simple input
	 * dialog that's used for entering the created group name and creates it by
	 * using the addChannelGroup() method of the tree The input is validated by
	 * an inner class that implements the IInputValidator interface
	 * 
	 * @see SelectionAdapter
	 * @see InputDialog
	 * @see ChannelGroupTree
	 * @see IInputValidator
	 */
	private class AddChannelGroupItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(final SelectionEvent e)
		{
			final InputDialog addGroupDialog = new InputDialog(shl, "Enter group name", "Enter 3-16 characters", "", new LengthValidator());

			if (addGroupDialog.open() == Window.OK)
			{
				treeRef.addChannelGroup(addGroupDialog.getValue());
			}
		}

		private class LengthValidator implements IInputValidator
		{
			public String isValid(final String input)
			{
				final int len = input.length();

				if (len < 3) return "Too short";
				if (len > 16) return "Too long";

				return null;
			}
		}
	}

	/**
	 * The listener of the Add channel menu item. It creates a custom dialog
	 * used for adding a channel to the tree
	 * 
	 * @see SelectionAdapter
	 * @see AddChannelDialog
	 */
	private class AddChannelItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(final SelectionEvent e)
		{
			final AddChannelDialog addChannelDialog = new AddChannelDialog(shl, treeRef);

			addChannelDialog.open();
		}
	}

	/**
	 * The listener of the Settings menu item. It creates a custom dialog used
	 * for modifying the settings of the application and saving them to a file
	 * 
	 * @see SelectionAdapter
	 * @see SettingsDialog
	 */
	private class SettingsItemListener extends SelectionAdapter
	{
		@Override
		public void widgetSelected(final SelectionEvent e)
		{
			final SettingsDialog settingsDialog = new SettingsDialog(shl, windowRef, settings);

			settingsDialog.open();
		}
	}

	/**
	 * Sets the ChannelGroupTree object reference used for calling it's methods
	 * 
	 * @param treeRef
	 *            the reference to the tree object
	 */
	public void setTreeRef(final ChannelGroupTree treeRef)
	{
		this.treeRef = treeRef;
	}

	private final MainWindow	windowRef;
	private ChannelGroupTree	treeRef;

	private final Settings		settings	= new Settings();

	private final Shell			shl;
}
