package org.projekt.rssreader.main;

import org.projekt.rssreader.gui.MainWindow;

/*
 * The entry-point class for the application
 */
public class Main
{
	/*
	 * The main method of the entry class. Creates the main window of the application and opens it
	 */
	public static void main(String[] args)
	{
		try
		{
			MainWindow window = new MainWindow();
			window.open();
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
