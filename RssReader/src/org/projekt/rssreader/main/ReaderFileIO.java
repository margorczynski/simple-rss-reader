package org.projekt.rssreader.main;

import org.projekt.rssreader.content.tree.ChannelGroup;
import org.projekt.rssreader.content.tree.Channel;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import java.util.LinkedList;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Attribute;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.input.SAXBuilder;

/*
 * Class used for the readers file IO, can save/load settings and groups with channels to/from files in XML format
 * Uses the JDOM library for XML IO
 */
public class ReaderFileIO
{
	/*
	 * Saves channel groups to a file (groups.xml by default)
	 * 
	 * @param channelGroups a reference to a list containing the channel groups
	 * @see   ChannelGroup
	 */
	public void saveChannelGroupsToFile(List<ChannelGroup> channelGroups)
	{
		int j = 0;
		
		Element rssReaderElement = new Element("rssReader");
		Document doc = new Document(rssReaderElement);
		doc.setRootElement(rssReaderElement);
		
		for(ChannelGroup group : channelGroups)
		{
			Element channelGroupElement = new Element("channelGroup");
			channelGroupElement.setAttribute(new Attribute("name", group.getName()));
			
			for(Channel channel : group.getChannels())
			{
				channelGroupElement.addContent(new Element("feedUrl" + j).setText(channel.getUrl()));
				
				j++;
			}
			
			channelGroupElement.addContent(new Element("numberOfUrls").setText(String.valueOf(j)));
			
			doc.getRootElement().addContent(channelGroupElement);
			
			j = 0;
		}
		
		XMLOutputter xmlOutput = new XMLOutputter();
		
		try
		{
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("./groups.xml"));
		}catch(IOException io)
		{
			io.printStackTrace();
		}
	}
	
	/*
	 * Returns a list of channel groups loaded from a chosen file
	 * 
	 * @param  filename the absolute path to the file
	 * @return return a list containing the channel groups
	 * @see    ChannelGroup
	 */
	public List<ChannelGroup> loadChannelGroupsFromFile(String filename)
	{
		List<ChannelGroup> channelGroups = new LinkedList<ChannelGroup>();
		
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File(filename);
		
		try 
		{
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			List list = rootNode.getChildren("channelGroup");
	 
			for (int i = 0; i < list.size(); i++) 
			{
			   Element node = (Element) list.get(i);
			   
			   ChannelGroup group = new ChannelGroup(node.getAttributeValue("name"));
			   
			   for(int j = 0; j < Integer.parseInt(node.getChildText("numberOfUrls")); j++)
			   {
				   Channel channel = new Channel(node.getChildText("feedUrl"+j), group);
				   
				   group.getChannels().add(channel);
			   }
			   
			   channelGroups.add(group);
			}
		}catch(Exception e)
		{
			 e.printStackTrace();
		}
		
		return channelGroups;
	}
	
	/*
	 * Saves the application settings passed as a parameter into an settings.xml file
	 * 
	 * @param settings the reference to the object of class Settings that contains the information
	 * @see   Settings
	 */
	public void saveSettingsToFile(Settings settings)
	{
		Element rssReaderElement = new Element("rssReader");
		Document doc = new Document(rssReaderElement);
		doc.setRootElement(rssReaderElement);
		
		rssReaderElement.addContent(new Element("defaultUrl").setText(settings.getDefaultUrl()));
		rssReaderElement.addContent(new Element("loadAtStart").setText(String.valueOf(settings.isLoadAtStart())));
		
		XMLOutputter xmlOutput = new XMLOutputter();
		
		try
		{
			xmlOutput.setFormat(Format.getPrettyFormat());
			xmlOutput.output(doc, new FileWriter("./settings.xml"));
		}catch(IOException io)
		{
			io.printStackTrace();
		}
	}
	
	/*
	 * Return a reference to an Settings class object that contains information loaded from the settings.xml file
	 * 
	 * @return reference to the object with the settings
	 * @see    Settings
	 */
	public Settings loadSettingsFromFile()
	{
		Settings settings = new Settings();
		
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("./settings.xml");
		
		if(!xmlFile.exists())
		{
			Settings defaultSettings = new Settings();
			
			defaultSettings.setDefaultUrl("www.google.pl");
			defaultSettings.setLoadAtStart(false);
			
			saveSettingsToFile(defaultSettings);
		}
		
		try 
		{
			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();
			
			settings.setDefaultUrl(rootNode.getChildText("defaultUrl"));
			
			settings.setLoadAtStart(Boolean.parseBoolean(rootNode.getChildText("loadAtStart")));

		}catch(Exception e)
		{
			 e.printStackTrace();
		}
		
		
		return settings;
	}
	
}
