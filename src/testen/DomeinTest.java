package testen;


import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import domein.DomeinController;
import exceptions.GebruikerBestaatNietException;
import exceptions.GebruikerGeblokkeerdException;
import exceptions.OngeldigeWachtwoordException;

public class DomeinTest {
	private static DomeinController dc =  new DomeinController();
	
	
	@AfterAll
	public static void after() {
		dc.sluitPersistentie();
	}
	

	@ParameterizedTest
	@CsvSource({"JanJansens, 123456789"})
	public void meldAan_bestaandGebruikerJuisteGegevens_Login(String naam, String paswoord) {
		System.out.println(new Date());
		Assertions.assertDoesNotThrow(() -> {
			dc.meldAan(naam, paswoord);
			});
	}
	
	@ParameterizedTest
	@CsvSource({"JanJansens, 1234"})
	public void meldAan_bestaandGebruikerFouteGegevens_Exception(String naam, String paswoord) {
		
		Assertions.assertThrows(OngeldigeWachtwoordException.class, () -> dc.meldAan(naam, paswoord));
	}
	
	@ParameterizedTest
	@CsvSource({"JansensJan, 987654321"})
	public void meldAan_geblokkeerdGebruiker_Exception(String naam, String paswoord) {
		Assertions.assertThrows(GebruikerGeblokkeerdException.class, () -> dc.meldAan(naam, paswoord));
	}
	
	@ParameterizedTest
	@CsvSource({"Jan, 1234"})
	public void meldAan_onbestaandGebruiker_Exception(String naam, String paswoord) {
		Assertions.assertThrows(GebruikerBestaatNietException.class, () -> dc.meldAan(naam, paswoord));
	}
}
