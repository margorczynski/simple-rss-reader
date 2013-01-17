package org.projekt.rssreader.content.tree;

import org.projekt.rssreader.main.RssChannelReader;

public class Channel
{
	public Channel(String url)
	{
		reader = new RssChannelReader(url);
		
		title = reader.getFeedTitle();
		
		description = reader.getFeedDescription();
	}
	
	public RssChannelReader getReader()
	{
		return reader;
	}
	public String getTitle()
	{
		return title;
	}
	public String getDescription()
	{
		return description;
	}

	
	private RssChannelReader reader;
	
	private String title;
	private String description;
}
