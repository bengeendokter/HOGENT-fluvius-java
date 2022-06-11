package testen;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.AanmeldController;
import domein.Gebruiker;
import exceptions.GebruikerBestaatNietException;
import exceptions.GebruikerGeblokkeerdException;
import exceptions.OngeldigeWachtwoordException;
import repository.GebruikerDaoJpa;

@SuppressWarnings("unused")
@ExtendWith(MockitoExtension.class)
public class AanmeldenTest
{
	
	
	@Mock
    private GebruikerDaoJpa gebruikerjpa;
	
	@InjectMocks
	private  static AanmeldController aanmeldController;
	
	@ParameterizedTest
	@CsvSource({"JanJansens, 123456789"})
	public void meldAan_bestaandGebruikerJuisteGegevens_Login(String naam, String paswoord)
	{
		Gebruiker gebruiker = new Gebruiker("JanJansens", "123456789", "MVO-coördinator", "ACTIEF");
		Mockito.when(gebruikerjpa.getByName("JanJansens")).thenReturn(gebruiker);
		Assertions.assertDoesNotThrow(() -> {
			aanmeldController.meldAan(naam, paswoord);
		});
		Mockito.verify(gebruikerjpa).getByName("JanJansens");
	}
	
	@ParameterizedTest
	@CsvSource({"JanJansens, 1234"})
	public void meldAan_bestaandGebruikerFouteGegevens_Exception(String naam, String paswoord)
	{
		Gebruiker gebruiker = new Gebruiker(naam, paswoord, "MVO-coördinator", "ACTIEF");
		Mockito.when(gebruikerjpa.getByName(naam)).thenThrow(new OngeldigeWachtwoordException());
		
		Assertions.assertThrows(OngeldigeWachtwoordException.class, () -> aanmeldController.meldAan(naam, paswoord));
		Mockito.verify(gebruikerjpa).getByName(naam);
	}
	
	@ParameterizedTest
	@CsvSource({"block, 123456789"})
	public void meldAan_geblokkeerdGebruiker_Exception(String naam, String paswoord)
	{
		Gebruiker gebruiker = new Gebruiker(naam, paswoord, "MVO-coördinator", "ACTIEF");
		Mockito.when(gebruikerjpa.getByName(naam)).thenThrow(new GebruikerGeblokkeerdException());
		Assertions.assertThrows(GebruikerGeblokkeerdException.class, () -> aanmeldController.meldAan(naam, paswoord));
		Mockito.verify(gebruikerjpa).getByName(naam);
	}
	
	@ParameterizedTest
	@CsvSource({"Jan, 1234"})
	public void meldAan_onbestaandGebruiker_Exception(String naam, String paswoord)
	{
		Gebruiker gebruiker = new Gebruiker(naam, paswoord, "MVO-coördinator", "ACTIEF");
		Mockito.when(gebruikerjpa.getByName(naam)).thenThrow(new GebruikerBestaatNietException());
		Assertions.assertThrows(GebruikerBestaatNietException.class, () -> {
			aanmeldController.meldAan(naam, paswoord);
		});
		Mockito.verify(gebruikerjpa).getByName(naam);
	}
}
