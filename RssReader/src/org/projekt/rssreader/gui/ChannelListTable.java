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

public class ChannelListTable
{
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
	
	public void updateFeedEntries(List<SyndEntry> feedEntries)
	{
		TableModelProvider.INSTANCE.setFeedElements(feedEntries);
		
		tableViewer.setInput(TableModelProvider.INSTANCE.getFeedElements());
		
		tableViewer.refresh();
	}
	
	public Composite getTableComposite()
	{
		return tableComposite;
	}
	
	public FormData getTableFormData()
	{
		return fdTable;
	}
	
	public void setContentRef(ChannelContentViewer contentRef)
	{
		this.contentRef = contentRef;
	}
	
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
