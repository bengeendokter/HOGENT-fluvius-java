package main;

import domein.AanmeldController;

public class StartUpConsole
{
	
	public static void main(String[] args)
	{
		AanmeldController aanmeldController = new AanmeldController();
		aanmeldController.meldAan("yeet", "123456789");
	}
	
}
