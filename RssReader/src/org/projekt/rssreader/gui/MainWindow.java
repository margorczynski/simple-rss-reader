package org.projekt.rssreader.gui;

import java.util.List;
import java.io.File;

import org.projekt.rssreader.content.tree.ChannelGroup;
import org.projekt.rssreader.main.ReaderFileIO;
import org.projekt.rssreader.main.Settings;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.graphics.Image;

/*
 * The main window and shell of the reader
 * All of the GUI was made using SWT/JFace
 * 
 * @see SWT
 * @see JFace
 */
public class MainWindow
{
	/**
	 * Open the window.
	 */
	public void open()
	{
		Display display = Display.getDefault();
		settings = readerFileIO.loadSettingsFromFile();
		smallIcon = new Image(display, "media/rss16.png");
		largeIcon = new Image(display, "media/rss32.png");
		createContents();
		shlSimpleRssReader.open();
		shlSimpleRssReader.layout();
		while (!shlSimpleRssReader.isDisposed())
		{
			if (!display.readAndDispatch())
			{
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents()
	{
		shlSimpleRssReader = new Shell();
		shlSimpleRssReader.setSize(850, 600);
		shlSimpleRssReader.setText("Simple RSS Reader");
		shlSimpleRssReader.setLayout(new FormLayout());
		shlSimpleRssReader.setImages(new Image[] {smallIcon, largeIcon});
		
		/*
		 * The toolbar with the menu's
		 */
		
		chnToolbar = new ReaderMenuToolbar(shlSimpleRssReader, this);
		
		/*
		 * The tree with the channel groups and channels
		 */
		
		chnTree = new ChannelGroupTree(shlSimpleRssReader);
		chnToolbar.setTreeRef(chnTree);
		
		/*
		 * Table with a list of news pulled from the selected channel
		 */
		
		chnTable = new ChannelListTable(shlSimpleRssReader, chnTree.getTree());
		chnTree.setTableRef(chnTable);
		
		/*
		 * Text viewer with news content
		 */
		
		chnViewer = new ChannelContentViewer(shlSimpleRssReader, chnTree.getTree(), chnTable.getTableComposite(), chnTree.getTreeFormData(), chnTable.getTableFormData());
		chnTable.setContentRef(chnViewer);
		
		if(settings.isLoadAtStart() && (new File("default.xml")).exists()) openFromFile("default.xml");
		
		chnViewer.setDefaultUrl(settings.getDefaultUrl());
		
		chnViewer.setContentUrl(settings.getDefaultUrl());
	}
	
	/*
	 * Resets the GUI and it's contents
	 */
	public void resetAll()
	{
		chnViewer.reset();
		
		chnTree.reset();
		
		chnTable.reset();
	}
	
	/*
	 * Resets everything and loads groups with channels from a file
	 * 
	 * @param filename the absolute path to the file
	 */
	public void openFromFile(String filename)
	{
		resetAll();
		
		chnTree.addChannelGroup(readerFileIO.loadChannelGroupsFromFile(filename));
	}
	
	/*
	 * Saves the groups in the tree's model to a file
	 */
	public void saveToFile()
	{
		List<ChannelGroup> groups = chnTree.getChannelGroups();
		
		readerFileIO.saveChannelGroupsToFile(groups);
	}
	
	/*
	 * Saves the current settings to a file
	 */
	public void saveSettingsToFile()
	{
		readerFileIO.saveSettingsToFile(settings);
	}
	
	/*
	 * Sets the current settings with values in the parameters
	 * 
	 * @param defaultUrl    the default URL loaded in the browser at startup and reset with New
	 * @param isLoadAtStart boolean value that decides if the default channel groups should be loaded at startup
	 */
	public void setSettings(String defaultUrl, boolean isLoadAtStart)
	{
		settings.setDefaultUrl(defaultUrl);
		settings.setLoadAtStart(isLoadAtStart);
	}
	
	protected Shell	shlSimpleRssReader;
	
	private Settings settings;
	
	private ReaderFileIO readerFileIO = new ReaderFileIO();
	
	private ReaderMenuToolbar chnToolbar;
	private ChannelGroupTree chnTree;
	private ChannelListTable chnTable;
	private ChannelContentViewer chnViewer;
	
	private Image smallIcon, largeIcon;
}
