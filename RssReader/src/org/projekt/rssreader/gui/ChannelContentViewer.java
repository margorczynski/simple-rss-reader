package org.projekt.rssreader.gui;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.browser.*;

/**
 * Class used for creation of the object representing the browser of the main window
 */
public class ChannelContentViewer
{
	/**
	 * The constructor of the browser. Takes the shell, table composite, tree and table form data as parameters. Creates  a basic browser area with basic configuration
	 * 
	 * @param shl  			 the reference to the shell object
	 * @param tree           the reference to the tree object
	 * @param tableComposite the reference to the tables composite object
	 * @param fdTree         the reference to the tree's form data object
	 * @param fdTable        the reference to the table's form data object
	 * 
	 * @see Tree
	 * @see Compsite
	 * @see FormData
	 * @see Browser
	 */
	public ChannelContentViewer(Shell shl, Tree tree, Composite tableComposite, FormData fdTree, FormData fdTable)
	{
		browser = new Browser(shl, SWT.BORDER);
		
		fdTable.right = new FormAttachment(browser, 0, SWT.RIGHT);
		fdTree.right = new FormAttachment(28, -25);
		
		FormData fd_styledText = new FormData();
		
		fd_styledText.bottom = new FormAttachment(100, -10);
		fd_styledText.top = new FormAttachment(tableComposite, 5);
		fd_styledText.left = new FormAttachment(tree, 6);
		fd_styledText.right = new FormAttachment(100, -10);
		browser.setLayoutData(fd_styledText);
	}
	
	/**
	 * Sets the URL of the browser
	 * 
	 * @param url string containing the URL
	 */
	public void setContentUrl(String url)
	{
		browser.setUrl(url);
	}
	
	/**
	 * Sets the deafult URL of the browser
	 * 
	 * @param defaultUrl string containing the default URL
	 */
	public void setDefaultUrl(String defaultUrl)
	{
		this.defaultUrl = defaultUrl;
	}
	
	/**
	 * Resets the browser by loading the page under the default URL
	 */
	public void reset()
	{
		browser.setUrl(defaultUrl);
	}
	
	
	private Browser browser;
	
	private String defaultUrl = "www.google.pl";
}
