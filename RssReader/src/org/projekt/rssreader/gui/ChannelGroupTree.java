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

/**
 * Class used for creation of the object representing the tree of the main window
 */
public class ChannelGroupTree
{
	/**
	 * The constructor of the tree object. It takes the shell reference as a parameter. Creates the TreeViewer and Tree objects and assigns to them providers and listeners
	 * 
	 * @see TreeViewer
	 * @see Tree
	 * @see Shell
	 */
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
	
	/**
	 * Returns the reference to the tree object
	 * 
	 * @return the reference to the tree object
	 */
	public Tree getTree()
	{
		return tree;
	}
	
	/**
	 * Returns the reference to the form data of the tree
	 * 
	 * @return the reference to the form data object
	 */
	public FormData getTreeFormData()
	{
		return fdTree;
	}
	
	/**
	 * The double click listener class of the tree viewer. When a group name is double clicked it expands the group, if a channel then it loads the entries to the table
	 * 
	 * @see IDoubleClickListener
	 * @see IStructuredSelection
	 */
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
	
	/**
	 * The key listener of the tree. On pressing delete depending on the selected item it removes channels or whole groups, on pressing enter it loads the channel entries to the table
	 * 
	 * @see KeyAdapter
	 * @see IStructuredSelection
	 * @see ChannelListTable
	 */
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
	
	/**
	 * Sets the table object reference
	 * 
	 *  @param tableRef the reference to the table object
	 */
	public void setTableRef(ChannelListTable tableRef)
	{
		this.tableRef = tableRef;
	}
	
	/**
	 * Clears the model and refreshes the tree viewer for changes to be visible
	 */
	public void reset()
	{
		model.getGroups().clear();
		
		treeViewer.refresh();
	}
	
	/**
	 * Adds a channel group to the model and refreshes the tree viewer for changes to be visible
	 * 
	 * @param groupName a string containing the name of the group
	 * 
	 * @see ChannelGroup
	 */
	public void addChannelGroup(String groupName)
	{
		ChannelGroup group = new ChannelGroup(groupName);
		
		model.getGroups().add(group);
		
		treeViewer.refresh();
	}
	
	/**
	 * Adds a channel group to the model and refreshes the tree viewer for changes to be visible. Uses a list reference as a parameter
	 * 
	 * @param channelGroups reference to the list containing the channel groups
	 * 
	 * @see ChannelGroup
	 */
	public void addChannelGroup(List<ChannelGroup> channelGroups)
	{
		model.getGroups().addAll(channelGroups);
		
		treeViewer.refresh();
	}
	
	/**
	 * Adds a channel to a group. It takes the channels URL and the group name as parameters
	 * 
	 * @param channelGroupName string containing the names of the group
	 * @param channelUrl       string containing the URL of the channel
	 * 
	 * @see ChannelGroup
	 * @see Channel
	 */
	public void addChannel(String channelGroupName, String channelUrl)
	{
		ChannelGroup channelGroup = null;
		
		for(ChannelGroup group : model.getGroups())
		{
			for(Channel channel : group.getChannels())
			{
				if(channel.getUrl().equals(channelUrl)) return;
			}
			
			if(group.getName().equals(channelGroupName))
			{
				channelGroup = group;
				
				break;
			}
		}
		
		Channel newChannel = new Channel(channelUrl, channelGroup);	
		
		channelGroup.getChannels().add(newChannel);
		
		treeViewer.refresh();
	}
	
	/**
	 * Returns the reference to the list with channel groups
	 * 
	 * @return the reference to the list object with channel groups
	 */
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
