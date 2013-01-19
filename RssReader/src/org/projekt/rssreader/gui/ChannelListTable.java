package org.projekt.rssreader.gui;

import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;

import org.eclipse.jface.layout.TableColumnLayout;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.TableColumn;

import org.projekt.rssreader.content.table.FeedElement;
import org.projekt.rssreader.content.table.TableModelProvider;

/*
 * Class used for creation of the object representing the table with channel names in the main window
 */
public class ChannelListTable
{
	/*
	 * The constructor of the table. Takes the shell and tree references as parameters. Initializes the table with basic configuration and adds listeners to the viewer
	 * 
	 * @param shl  reference to the shell object
	 * @param tree reference to the tree object
	 * 
	 * @see Table
	 * @see TableViewer
	 * @see TableColumnLayout
	 */
	public ChannelListTable(Shell shl, Tree tree)
	{
		tableComposite = new Composite(shl, SWT.NONE);
		tableColumnLayout = new TableColumnLayout();
		tableComposite.setLayout(tableColumnLayout);
		
		tableViewer = new TableViewer(tableComposite, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		
		table = tableViewer.getTable();
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true); 
		
		fdTable = new FormData();
		fdTable.top = new FormAttachment(0, 5);
		fdTable.left = new FormAttachment(tree, 6);
		fdTable.bottom = new FormAttachment(37);
		tableComposite.setLayoutData(fdTable);
		table.setLayoutData(fdTable);
		
		tableViewer.addDoubleClickListener((new TableDoubleClickListener()));
		
		tableViewer.setContentProvider(new ArrayContentProvider());
		
		createColumns();
		
	}
	
	/*
	 * Creates the columns of the table and sets their provider to properly map the data to the cells
	 * 
	 * @see ColumnLabelProvider
	 */
	private void createColumns() 
	{
	    String[] titles = { "Headline", "Received Date", "Publishing Date"};
	    int[] bounds = { 100, 100, 100 };
	    
	    // First column
	    headlineColumn = createTableViewerColumn(titles[0], bounds[0], 0);
	    headlineColumn.setLabelProvider(new ColumnLabelProvider() 
	    {
	      @Override
	      public String getText(Object element) 
	      {
	        FeedElement feedElement = (FeedElement) element;
	        return feedElement.getHeadline();
	      }
	    });
	    
	    authorColumn = createTableViewerColumn(titles[1], bounds[1], 1);
	    authorColumn.setLabelProvider(new ColumnLabelProvider() 
	    {
	      @Override
	      public String getText(Object element) 
	      {
	        FeedElement feedElement = (FeedElement) element;
	        return feedElement.getReceivedDate();
	      }
	    });
	    
	    publishDateColumn = createTableViewerColumn(titles[2], bounds[2], 2);
	    publishDateColumn.setLabelProvider(new ColumnLabelProvider() 
	    {
	      @Override
	      public String getText(Object element) 
	      {
	        FeedElement feedElement = (FeedElement) element;
	        return feedElement.getPublishedDate();
	      }
	    });

	    
	    tableColumnLayout.setColumnData(headlineColumn.getColumn(), new ColumnWeightData(40, 200, true)); 
		tableColumnLayout.setColumnData(authorColumn.getColumn(), new ColumnWeightData(30, 200, true)); 
		tableColumnLayout.setColumnData(publishDateColumn.getColumn(), new ColumnWeightData(30, 200, true)); 
	}
	
	/*
	 * Creates a single column with attributes stated as parameters. Returns the reference to the column object
	 * 
	 * @param title     the title of the column
	 * @param bound     the width of the column
	 * @param colNumber the number of the column
	 * 
	 * @return the reference to the created column object
	 */
	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) 
	{
	    final TableViewerColumn viewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
	    final TableColumn column = viewerColumn.getColumn();
	    column.setText(title);
	    column.setWidth(bound);
	    column.setResizable(true);
	    column.setMoveable(true);
	    return viewerColumn;
	}
	
	/*
	 * Updates the entries in the model with entries from a list which reference is passed as the parameter. Then it refreshes the viewer to see the changes
	 * 
	 * @param feedEntries reference to the list containing the entries
	 * 
	 * @see SyndEntry
	 * @see TableModelProvider
	 */
	public void updateFeedEntries(List<SyndEntry> feedEntries)
	{
		TableModelProvider.INSTANCE.setFeedElements(feedEntries);
		
		tableViewer.setInput(TableModelProvider.INSTANCE.getFeedElements());
		
		tableViewer.refresh();
	}
	
	/*
	 * Returns the reference to the composite object of the table
	 * 
	 * @return the reference to the composite
	 * 
	 * @see Composite
	 */
	public Composite getTableComposite()
	{
		return tableComposite;
	}
	
	/*
	 * Return the reference to the form data object of the table
	 * 
	 * @return the reference to the form data object
	 * 
	 * @see FormData
	 */
	public FormData getTableFormData()
	{
		return fdTable;
	}
	
	/*
	 * Sets the content viewers reference to the reference passed as the parameter
	 * 
	 * @param contentRef the reference to the content viewer object
	 * 
	 *  @see ChannelContentViewer
	 */
	public void setContentRef(ChannelContentViewer contentRef)
	{
		this.contentRef = contentRef;
	}
	
	/*
	 * The double click listener class of the table viewer. Loads the page under the double clicked link to the content viewer
	 * 
	 *  @see IDoubleClickListener
	 *  @see IStructuredSelection
	 */
	private class TableDoubleClickListener implements IDoubleClickListener
	{
	      public void doubleClick(DoubleClickEvent event) 
	      {
	        IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
	        Object selectedNode = thisSelection.getFirstElement();
	
	        if (thisSelection.getFirstElement() instanceof FeedElement)
        	{
	        	FeedElement element = (FeedElement)selectedNode;
	        	
        		contentRef.setContentUrl(element.getUrl());
        	}
	      }
	}
	
	/*
	 * Clears the model and refreshes the table viewer for changes to be visible
	 * 
	 * @see TableModelProvider
	 */
	public void reset()
	{
		TableModelProvider.INSTANCE.clear();
		
		tableViewer.setInput(TableModelProvider.INSTANCE.getFeedElements());
		
		tableViewer.refresh();
	}
	
	
	private TableViewer tableViewer;
	private TableColumnLayout tableColumnLayout;
	private Composite tableComposite;
	private Table table;
	private FormData fdTable;
	
	private TableViewerColumn headlineColumn, authorColumn, publishDateColumn;
	
	private ChannelContentViewer contentRef;
}
