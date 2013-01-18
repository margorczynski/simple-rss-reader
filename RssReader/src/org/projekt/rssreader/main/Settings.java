package org.projekt.rssreader.main;

/*
 * Class used for creating objects that hold the readers settings
 */
public class Settings
{	
	/*
	 * Return the default URL
	 * 
	 * @return the default URL
	 */
	public String getDefaultUrl()
	{
		return defaultUrl;
	}
	
	/*
	 * Sets the default URL
	 * 
	 * @param defaultUrl the default URL
	 */
	public void setDefaultUrl(String defaultUrl)
	{
		this.defaultUrl = defaultUrl;
	}
	
	/*
	 * Returns true/false depending if the readers should load the tree with data at startup
	 * 
	 * @return returns true if it should load the default groups at startup, else false
	 */
	public boolean isLoadAtStart()
	{
		return isLoadAtStart;
	}
	
	/*
	 * Tells the reader if it should load the tree with data at startup
	 * 
	 * @param if it should then true, else false
	 */
	public void setLoadAtStart(boolean isLoadAtStart)
	{
		this.isLoadAtStart = isLoadAtStart;
	}
	
	
	private String defaultUrl = "www.google.pl";
	private boolean isLoadAtStart = true;
}
