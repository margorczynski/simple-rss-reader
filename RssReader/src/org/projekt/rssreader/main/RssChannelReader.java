package org.projekt.rssreader.main;

import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.LinkedList;
 
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RssChannelReader
{
	public RssChannelReader(String feedURL)
	{
		XmlReader reader = null;
		
		try
		{
			URL url = new URL(feedURL);
	
			reader = new XmlReader(url);
			
		    feed = new SyndFeedInput().build(reader);
		    
		    feedTitle = feed.getTitle();
		    
		    feedEntries = feed.getEntries();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (reader != null) reader.close();
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public String getFeedTitle()
	{
		return feedTitle;
	}
	
	public String getFeedDescription()
	{
		return feedDescription;
	}
	
	public List<SyndEntry> getFeedEntries()
	{
		return feedEntries;
	}
	
	public void refresh()
	{
		feedTitle = feed.getTitle();
		
		feedDescription = feed.getDescription();
	    
	    feedEntries = feed.getEntries();
	}
	
	
	private SyndFeed feed = null;
	
	private List<SyndEntry> feedEntries = new LinkedList<SyndEntry>();
	
	private String feedTitle;
	private String feedDescription;
}
