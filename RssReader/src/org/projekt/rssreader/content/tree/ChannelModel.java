package org.projekt.rssreader.content.tree;

import java.util.List;
import java.util.LinkedList;

/*
 * Class representing the model of the tree. It holds all data needed by the tree.
 */
public class ChannelModel
{
	/*
	 * The constructor of the model. It adds a basic example group and channels at creation.
	 */
	public ChannelModel()
	{
		ChannelGroup basicGroup = new ChannelGroup("Test grupa");
		
		groups.add(basicGroup);
		
		Channel basicChannel1 = new Channel("http://wiadomosci.wp.pl/ver,rss,rss.xml", basicGroup);
		
		Channel basicChannel2 = new Channel("http://tygodnik.onet.pl/1,kategoria.rss", basicGroup);
		
		basicGroup.getChannels().add(basicChannel1);
		
		basicGroup.getChannels().add(basicChannel2);
	}
	
	/*
	 * Returns the reference to the list of channel groups.
	 * 
	 * @return the reference to the channel group list
	 */
	public List<ChannelGroup> getGroups()
	{	
		return groups;
	}
	
	/*
	 * Adds a channel group to the model.
	 * 
	 * @param name the name of the new group
	 */
	public void addChannelGroup(String name)
	{
		groups.add(new ChannelGroup(name));
	}
	
	/*
	 * Adds a channel to a group.
	 * 
	 * @param channelGroupName the name of the group 
	 * @param the URL of the added channel
	 */
	public void addChannel(String channelGroupName, String url)
	{
		ChannelGroup group = groups.get(groups.indexOf(new ChannelGroup(channelGroupName)));
		
		group.getChannels().add(new Channel(url, group));
	}
	
	private List<ChannelGroup> groups = new LinkedList<ChannelGroup>();
}
