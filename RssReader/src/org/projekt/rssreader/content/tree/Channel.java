package org.projekt.rssreader.content.tree;

import java.util.List;

import java.io.Serializable;

import org.projekt.rssreader.main.RssChannelReader;

import com.sun.syndication.feed.synd.SyndEntry;

public class Channel implements Serializable
{
	public Channel(String url, ChannelGroup groupRef)
	{
		this.groupRef = groupRef;
		
		RssChannelReader reader = new RssChannelReader(url);
		
		title = reader.getFeedTitle();
		
		description = reader.getFeedDescription();
		
		feedEntries = reader.getFeedEntries();
	}
	
	public List<SyndEntry> getFeedEntries()
	{
		return feedEntries;
	}
	
	public ChannelGroup getGroupRef()
	{
		return groupRef;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getDescription()
	{
		return description;
	}

	private static final long serialVersionUID = 1L;
	
	private List<SyndEntry> feedEntries;
	
	private ChannelGroup groupRef;
	
	private String title;
	private String description;
}
