package org.projekt.rssreader.main;

public class Settings
{	
	public String getDefaultUrl()
	{
		return defaultUrl;
	}
	public void setDefaultUrl(String defaultUrl)
	{
		this.defaultUrl = defaultUrl;
	}
	public boolean isLoadAtStart()
	{
		return isLoadAtStart;
	}
	public void setLoadAtStart(boolean isLoadAtStart)
	{
		this.isLoadAtStart = isLoadAtStart;
	}
	
	
	private String defaultUrl = "www.google.pl";
	private boolean isLoadAtStart = true;
}
