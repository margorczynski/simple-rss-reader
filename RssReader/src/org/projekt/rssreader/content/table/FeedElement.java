package org.projekt.rssreader.content.table;

import com.sun.syndication.feed.synd.SyndEntry;

import java.util.Date;

public class FeedElement
{
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
