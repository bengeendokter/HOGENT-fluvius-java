package testen;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domein.AanmeldController;
import exceptions.GebruikerBestaatNietException;
import exceptions.GebruikerGeblokkeerdException;
import exceptions.OngeldigeWachtwoordException;

public class DomeinTest
{
	private static AanmeldController aanmeldController = new AanmeldController();
	
	@AfterAll
	public static void after()
	{
		aanmeldController.sluitPersistentie();
	}
	
	@ParameterizedTest
	@CsvSource({"JanJansens, 123456789"})
	public void meldAan_bestaandGebruikerJuisteGegevens_Login(String naam, String paswoord)
	{
		Assertions.assertDoesNotThrow(() -> {
			aanmeldController.meldAan(naam, paswoord);
		});
	}
	
	@ParameterizedTest
	@CsvSource({"JanJansens, 1234"})
	public void meldAan_bestaandGebruikerFouteGegevens_Exception(String naam, String paswoord)
	{
		
		Assertions.assertThrows(OngeldigeWachtwoordException.class, () -> aanmeldController.meldAan(naam, paswoord));
	}
	
	@ParameterizedTest
	@CsvSource({"block, 123456789"})
	public void meldAan_geblokkeerdGebruiker_Exception(String naam, String paswoord)
	{
		Assertions.assertThrows(GebruikerGeblokkeerdException.class, () -> aanmeldController.meldAan(naam, paswoord));
	}
	
	@ParameterizedTest
	@CsvSource({"Jan, 1234"})
	public void meldAan_onbestaandGebruiker_Exception(String naam, String paswoord)
	{
		Assertions.assertThrows(GebruikerBestaatNietException.class, () -> {
			aanmeldController.meldAan(naam, paswoord);
		});
	}
}
