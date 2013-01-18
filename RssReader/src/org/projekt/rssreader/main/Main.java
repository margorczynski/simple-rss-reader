package org.projekt.rssreader.main;

import org.projekt.rssreader.gui.MainWindow;

public class Main
{
	/**
	 * @param args
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
