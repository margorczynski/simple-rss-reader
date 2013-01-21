package org.projekt.rssreader.content.tree;

import java.util.List;

import org.projekt.rssreader.main.RssChannelReader;

import com.sun.syndication.feed.synd.SyndEntry;

/*
 * Class used to store data about the feed channels. Contains the title, description and URL of the channel. Stores a list of entries and a reference to the group it belongs.
 * 
 * @see SyndEntry
 * @see ChannelGroup
 */
public class Channel
{
	/*
	 * The constructor of the channel object. Creates a RssChannelReader object with an URL passed to the constructor and sets the fields with data it pulls from it.
	 * Also sets the parent group reference from the reference passed as an parameter.
	 * 
	 *  @see RssChannelReader
	 */
	public Channel(String url, ChannelGroup groupRef)
	{
		this.groupRef = groupRef;
		
		this.url = url;
		
		RssChannelReader reader = new RssChannelReader(url);
		
		title = reader.getFeedTitle();
		
		description = reader.getFeedDescription();
		
		feedEntries = reader.getFeedEntries();
	}
	
	/*
	 * Returns the reference to the list with the entries.
	 */
	public List<SyndEntry> getFeedEntries()
	{
		return feedEntries;
	}
	
	/*
	 * Returns the reference to the parent group.
	 */
	public ChannelGroup getGroupRef()
	{
		return groupRef;
	}
	
	/*
	 * Returns the title of the channel.
	 */
	public String getTitle()
	{
		return title;
	}
	
	/*
	 * Returns the description of the channel.
	 */
	public String getDescription()
	{
		return description;
	}
	
	/*
	 * Returns the URL of the channel.
	 */
	public String getUrl()
	{
		return url;
	}

	private List<SyndEntry> feedEntries;
	
	private ChannelGroup groupRef;
	
	private String title;
	private String description;
	private String url;
}
