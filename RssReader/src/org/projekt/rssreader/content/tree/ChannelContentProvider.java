package org.projekt.rssreader.content.tree;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class ChannelContentProvider implements ITreeContentProvider
{
	public ChannelContentProvider(ChannelModel model)
	{
		this.model = model;
	}

	@Override
	public void dispose()
	{
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
	{
	}

	@Override
	public Object[] getChildren(Object parentElement)
	{
		if (parentElement instanceof ChannelGroup) 
		{
			ChannelGroup group = (ChannelGroup) parentElement;
		      
		    return group.getChannels().toArray();
		}
		
		return null;
	}

	@Override
	public Object[] getElements(Object inputElement)
	{
		return model.getGroups().toArray();
	}

	@Override
	public Object getParent(Object element)
	{
		return null;
	}

	@Override
	public boolean hasChildren(Object element)
	{
		if (element instanceof ChannelGroup) 
		{
			return true;
		}
		
		return false;
	}

	
	private ChannelModel model;// = new ChannelModel();
}
