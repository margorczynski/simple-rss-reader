package org.projekt.rssreader.content.tree;

import java.util.List;
import java.util.LinkedList;

public class ChannelGroup
{
	public ChannelGroup(String name)
	{
		this.name = name;
	}
	
	public int getSort()
	{
		return sort;
	}

	public void setSort(int sort)
	{
		this.sort = sort;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Channel> getChannels()
	{
		return channels;
	}


	private List<Channel> channels = new LinkedList<Channel>();
	
	private int sort;
	
	private String name;
}
