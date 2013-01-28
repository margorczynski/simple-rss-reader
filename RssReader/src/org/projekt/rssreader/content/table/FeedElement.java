package org.projekt.rssreader.content.table;

import com.sun.syndication.feed.synd.SyndEntry;

import java.util.Date;

/**
 * Class used for creating container objects for the feed entries.
 * Stores the head line, received date, publish date and URL.
 * 
 * @see SyndEntry
 */
public class FeedElement
{
	/**
	 * The constructor of the entry. Sets the fields using the SyndEntry object reference passed as a parameter and the current date.
	 * 
	 * @see Date
	 */
	public FeedElement(SyndEntry entry)
	{
		this.headline = entry.getTitle();
		this.receivedDate = (new Date()).toString();
		this.publishedDate = entry.getPublishedDate().toString();
		this.url = entry.getUri();
	}
	
	public String getHeadline()
	{
		return headline;
	}
	
	public String getReceivedDate()
	{
		return receivedDate;
	}
	
	public String getPublishedDate()
	{
		return publishedDate;
	}
	
	public String getUrl()
	{
		return url;
	}
	
	
	private String headline;
	private String receivedDate;
	private String publishedDate;
	private String url;
}
