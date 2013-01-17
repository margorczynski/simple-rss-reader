package org.projekt.rssreader.gui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;

public class MainWindow
{

	protected Shell	shlSimpleRssReader;

	/**
	 * Open the window.
	 */
	public void open()
	{
		Display display = Display.getDefault();
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
		shlSimpleRssReader.setSize(450, 300);
		shlSimpleRssReader.setText("Simple RSS Reader");
		shlSimpleRssReader.setLayout(new FormLayout());
		
		/*
		 * The toolbar with the menu's
		 */
		
		ReaderMenuToolbar readerToolbar = new ReaderMenuToolbar(shlSimpleRssReader);
		
		/*
		 * The tree with the channel groups and channels
		 */
		
		chnTree = new ChannelGroupTree(shlSimpleRssReader);
		
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
	
	
	private ChannelGroupTree chnTree;
	private ChannelListTable chnTable;
	private ChannelContentViewer chnViewer;
}
