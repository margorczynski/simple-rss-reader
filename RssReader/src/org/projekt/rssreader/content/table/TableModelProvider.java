package org.projekt.rssreader.content.table;

import java.util.List;
import java.util.LinkedList;

import com.sun.syndication.feed.synd.SyndEntry;

public enum TableModelProvider
{
	INSTANCE;
	
	private TableModelProvider()
	{
		feedElements = new LinkedList<FeedElement>();
	}
	
	public List<FeedElement> getFeedElements()
	{
		return feedElements;
	}
	
	public void setFeedElements(List<SyndEntry> feedElements)
	{	
		this.feedElements.clear();
		
		for(SyndEntry entry : feedElements)
		{
			this.feedElements.add(new FeedElement(entry));
		}
	}
	
	public void clear()
	{
		feedElements.clear();
	}
	
	private List<FeedElement> feedElements;
}
