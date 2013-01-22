package org.projekt.rssreader.content.tree;

import java.util.List;
import java.util.LinkedList;

import java.io.Serializable;

/*
 * Class used to store data about the channel groups. Contains the list of channels and the name of the group.
 */
public class ChannelGroup
{
	/*
	 * The constructor of the group. Sets the name of the group using a string passed as an argument.
	 */
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
