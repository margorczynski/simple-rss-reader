package org.projekt.rssreader.gui;

import java.util.List;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;

import org.projekt.rssreader.content.tree.ChannelContentProvider;
import org.projekt.rssreader.content.tree.ChannelGroup;
import org.projekt.rssreader.content.tree.Channel;
import org.projekt.rssreader.content.tree.ChannelModel;
import org.projekt.rssreader.content.tree.ChannelLabelProvider;

public class ChannelGroupTree
{
	public ChannelGroupTree(Shell shl)
	{
		treeViewer = new TreeViewer(shl, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		
		contentProvider = new ChannelContentProvider(model);
		labelProvider = new ChannelLabelProvider();
		
		treeViewer.setContentProvider(contentProvider);
		treeViewer.setLabelProvider(labelProvider);
		
		tree = treeViewer.getTree();
		fdTree = new FormData();
		fdTree.bottom = new FormAttachment(100, -10);
		fdTree.top = new FormAttachment(0, 5);
		fdTree.left = new FormAttachment(0, 5);
		tree.setLayoutData(fdTree);
		tree.setSortDirection(SWT.DOWN);
		
		ChannelGroup gr = new ChannelGroup("TEST");
		
		Channel ch = new Channel("http://wiadomosci.wp.pl/ver,rss,rss.xml", gr);

		gr.getChannels().add(ch);
		
		treeViewer.addDoubleClickListener(new TreeDoubleClickListener());
		
		tree.addKeyListener(new TreeKeyListener());
		
		treeViewer.setInput(gr);
	}
	
	public Tree getTree()
	{
		return tree;
	}
	
	public FormData getTreeFormData()
	{
		return fdTree;
	}
	
	
	private class TreeDoubleClickListener implements IDoubleClickListener
	{
	      public void doubleClick(DoubleClickEvent event) 
	      {
	        TreeViewer viewer = (TreeViewer) event.getViewer();
	        
	        IStructuredSelection thisSelection = (IStructuredSelection) event.getSelection();
	        Object selectedNode = thisSelection.getFirstElement();
	        
	        viewer.setExpandedState(selectedNode, !viewer.getExpandedState(selectedNode));
	        
	        if (thisSelection.getFirstElement() instanceof Channel)
        	{
        		Channel channel = (Channel) thisSelection.getFirstElement();
        		         
        		tableRef.updateFeedEntries(channel.getFeedEntries());
        	}
	      }
	}
	
	private class TreeKeyListener extends KeyAdapter
	{
		public void keyReleased(final KeyEvent e) 
		{
	        if (e.keyCode == SWT.DEL) 
	        {
	        	final IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
	        	
	        	if (selection.getFirstElement() instanceof ChannelGroup)
	        	{
	        		ChannelGroup o = (ChannelGroup) selection.getFirstElement();
	        				            
	        		o.getChannels().clear();
	        		
	        		model.getGroups().remove(o);
	        		
	        		treeViewer.refresh();
	        	}
	        	else if(selection.getFirstElement() instanceof Channel)
	        	{
	        		Channel o = (Channel) selection.getFirstElement();
	        		
	        		o.getGroupRef().getChannels().remove(o);
	        		
	        		treeViewer.refresh();
	        	}
	        }
	        if (e.keyCode == SWT.CR)
	        {
	        	final IStructuredSelection selection = (IStructuredSelection) treeViewer.getSelection();
	        	
	        	if (selection.getFirstElement() instanceof Channel)
	        	{
	        		Channel o = (Channel) selection.getFirstElement();
	        				            
	        		tableRef.updateFeedEntries(o.getFeedEntries());
	        	}
	        }
		}

	}
	
	public void setTableRef(ChannelListTable tableRef)
	{
		this.tableRef = tableRef;
	}
	
	public void reset()
	{
		model.getGroups().clear();
		
		treeViewer.refresh();
	}
	
	public void addChannelGroup(ChannelGroup group)
	{
		model.getGroups().add(group);
		
		treeViewer.refresh();
	}
	
	public void addChannel(ChannelGroup group, Channel channel)
	{
		model.getGroups().get(model.getGroups().indexOf(group)).getChannels().add(channel);
		
		treeViewer.refresh();
	}
	
	public List<ChannelGroup> getChannelGroups()
	{
		return model.getGroups();
	}
	
	
	
	private TreeViewer treeViewer;
	
	private ChannelListTable tableRef;
	
	private Tree tree;
	private FormData fdTree;
	
	private ChannelModel model = new ChannelModel();
	
	private ChannelContentProvider contentProvider;
	private ChannelLabelProvider labelProvider;
}
