package org.projekt.rssreader.content.tree;

import java.util.List;
import java.util.LinkedList;

public class ChannelModel
{
	public ChannelModel()
	{
		ChannelGroup basicGroup = new ChannelGroup("Test grupa");
		
		groups.add(basicGroup);
		
		Channel basicChannel1 = new Channel("http://wiadomosci.wp.pl/ver,rss,rss.xml", basicGroup);
		
		Channel basicChannel2 = new Channel("http://tygodnik.onet.pl/1,kategoria.rss", basicGroup);
		
		basicGroup.getChannels().add(basicChannel1);
		
		basicGroup.getChannels().add(basicChannel2);
	}
	
	public List<ChannelGroup> getGroups()
	{	
		return groups;
	}
	
	public void addChannelGroup(String name)
	{
		groups.add(new ChannelGroup(name));
	}
	
	public void addChannel(String channelGroupName, String url)
	{
		ChannelGroup group = groups.get(groups.indexOf(new ChannelGroup(channelGroupName)));
		
		group.getChannels().add(new Channel(url, group));
	}
	
	private List<ChannelGroup> groups = new LinkedList<ChannelGroup>();
}
