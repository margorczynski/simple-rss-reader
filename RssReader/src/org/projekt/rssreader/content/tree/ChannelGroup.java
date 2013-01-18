package org.projekt.rssreader.content.tree;

import java.util.List;
import java.util.LinkedList;

import java.io.Serializable;

public class ChannelGroup implements Serializable
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

	static final long serialVersionUID = 1L;
	
	private List<Channel> channels = new LinkedList<Channel>();
	
	private int sort;
	
	private String name;
}
