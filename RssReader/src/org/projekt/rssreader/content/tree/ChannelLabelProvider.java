package org.projekt.rssreader.content.tree;

import org.eclipse.jface.viewers.LabelProvider;

import org.eclipse.swt.graphics.Image;

/**
 * The label provider class of the tree. It extends the LabelProvider class.
 * Check the LabelProvider docs for more detailed information.
 * 
 * @see LabelProvider
 */
public class ChannelLabelProvider extends LabelProvider
{  
	  @Override
	  public String getText(Object element) 
	  {
	    if (element instanceof ChannelGroup) 
	    {
	    	ChannelGroup group = (ChannelGroup) element;
	    	
	        return group.getName();
	    }
	    
	    return ((Channel) element).getTitle();
	  }

	  @Override
	  public Image getImage(Object element) 
	  {
		  return null;
	  }
}
