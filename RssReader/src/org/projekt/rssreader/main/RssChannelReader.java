package org.projekt.rssreader.main;

import java.io.IOException;
import java.net.URL;

import java.util.List;
 
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

/**
 * Class used for aggregating RSS feeds and required data from it
 * Uses ROME for RSS service IO
 */
public class RssChannelReader
{
	/**
	 * The constructor of the class. Takes the feeds URL as an parameter and pulls all needed data from the feed
	 * 
	 * @param feedURL String object reference containing the hyperlink adress of the feed
	 * @see   URL
	 * @see   XmlReader
	 * @see   SyndFeed
	 */
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
	
	/*
	 * Returns the feeds title
	 * 
	 * @return the feeds title
	 */
	public String getFeedTitle()
	{
		return feed.getTitle();
	}
	
	/*
	 * Returns the feeds description
	 * 
	 * @return the feeds description
	 */
	public String getFeedDescription()
	{
		return feed.getDescription();
	}
	
	/*
	 * Returns the reference to a list contatining the entries in the feed
	 * 
	 * @param the reference to the list containing entries of the feed
	 * @see   SyndEntry
	 */
	public List<SyndEntry> getFeedEntries()
	{
		return feed.getEntries();
	}
	
	
	private SyndFeed feed = null;
}
