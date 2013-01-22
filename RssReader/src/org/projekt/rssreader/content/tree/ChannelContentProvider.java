package org.projekt.rssreader.content.tree;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/*
 * The content provider class for the tree. Uses the ChannelModel class for creating the model.
 * For more information please check the SWT docs on the tree content provider interface.
 * 
 * @see ITreeContentProvider
 * @see ChannelModel
 */
public class ChannelContentProvider implements ITreeContentProvider
{
	/*
	 * The constructor of the provider. Sets the reference to the model using a reference passed as an argument.
	 */
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
	public Object getParent(Object childElement)
	{
		if (childElement instanceof Channel) 
		{
			Channel channel = (Channel) childElement;
		      
		    return channel.getGroupRef();
		}
		
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

	
	private ChannelModel model;
}
