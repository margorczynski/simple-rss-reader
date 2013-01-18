package org.projekt.rssreader.main;

import java.io.IOException;
import java.net.URL;

import java.util.List;
 
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
		return feed.getTitle();
	}
	
	public String getFeedDescription()
	{
		return feed.getDescription();
	}
	
	public List<SyndEntry> getFeedEntries()
	{
		return feed.getEntries();
	}
	
	
	private SyndFeed feed = null;
}
