package org.projekt.rssreader.content.table;

import java.util.List;
import java.util.LinkedList;

import com.sun.syndication.feed.synd.SyndEntry;

/**
 * The content provider enum of the table. For more information check the SWT docs.
 * It is used as an single instance.
 * 
 * @see Table 
 */
public enum TableModelProvider
{
	INSTANCE;
	
	/**
	 * The constructor of the provider. It creates a new linked list referenced by feedElements.
	 * 
	 * @see LinkedList
	 */
	private TableModelProvider()
	{
		feedElements = new LinkedList<FeedElement>();
	}
	
	/**
	 * Returns the reference to the list containing the feed elements
	 */
	public List<FeedElement> getFeedElements()
	{
		return feedElements;
	}
	
	/**
	 * Sets the feed elements by clearing the actual ones and repopulating the list with a list of SyndEntry objects passed as an parameter.
	 * 
	 * @see FeedElement
	 */
	public void setFeedElements(List<SyndEntry> feedElements)
	{	
		this.feedElements.clear();
		
		for(SyndEntry entry : feedElements)
		{
			this.feedElements.add(new FeedElement(entry));
		}
	}
	
	/**
	 * Clears the list of entries
	 */
	public void clear()
	{
		feedElements.clear();
	}
	
	private List<FeedElement> feedElements;
}
