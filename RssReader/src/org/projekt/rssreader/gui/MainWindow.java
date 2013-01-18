package org.projekt.rssreader.gui;

import java.io.*;

import java.util.List;

import org.projekt.rssreader.content.tree.ChannelGroup;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.graphics.Image;


public class MainWindow
{

	protected Shell	shlSimpleRssReader;

	/**
	 * Open the window.
	 */
	public void open()
	{
		Display display = Display.getDefault();
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
	}
	
	public void resetAll()
	{
		chnViewer.reset();
		
		chnTree.reset();
		
		chnTable.reset();
	}
	
	public void openFromFile(String filename)
	{
		resetAll();
		
		try
	    {
			FileInputStream fileIn = new FileInputStream(filename);
	         
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	                            
	        ChannelGroup group = (ChannelGroup) in.readObject();
	        
	        chnTree.addChannelGroup(group.getName());
	        
	        in.close();
	        fileIn.close();
	    }catch(IOException e)
	    {
	    	e.printStackTrace();        
	    }
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void saveToFile()
	{
		List<ChannelGroup> groups = chnTree.getChannelGroups();
		
		for(ChannelGroup group : groups)
		{
			try
		    {
				FileOutputStream fileOut = new FileOutputStream("./" + group.getName() + ".group");
		         
		        ObjectOutputStream out = new ObjectOutputStream(fileOut);		                           
		  
		        out.writeObject(group);
		        
		        out.close();
		        fileOut.close();
		    }catch(IOException e)
		    {
		    	e.printStackTrace();        
		    }
		}
	}
	
	
	private ReaderMenuToolbar chnToolbar;
	private ChannelGroupTree chnTree;
	private ChannelListTable chnTable;
	private ChannelContentViewer chnViewer;
	
	private Image smallIcon, largeIcon;
}
