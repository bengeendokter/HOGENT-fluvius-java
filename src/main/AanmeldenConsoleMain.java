package main;

import domein.DomeinController;

public class AanmeldenConsoleMain {

	public static void main(String[] args) {
		DomeinController dc = new DomeinController();
		dc.meldAan("JanJansens", "123456789");
		dc.close();
	}

}
