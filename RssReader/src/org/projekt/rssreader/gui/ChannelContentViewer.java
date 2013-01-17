package org.projekt.rssreader.gui;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.browser.*;

public class ChannelContentViewer
{
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
		
		browser.setUrl("www.google.pl");
	}
	
	public void setContentUrl(String url)
	{
		browser.setUrl(url);
	}
	
	private Browser browser;
}
