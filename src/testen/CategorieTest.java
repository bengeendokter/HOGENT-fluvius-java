package testen;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import domein.AanmeldController;
import domein.Categorie;
import domein.DomeinController;
import domein.Fluvius;
import repository.CategorieDao;
import repository.GenericDao;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class CategorieTest {

	@Mock
    private GenericDao<Fluvius> fluviusRepo;
	@Mock
    private CategorieDao  categorieRepo;
	@InjectMocks
	private static DomeinController dc;
	
	/**
	 * Voordat alle testen uitgevoerd worden, wordt deze methode aangeroepen
	 * Om een categorie te kunnnen beheren, moet de gebruiker natuurlijk aangemeld zijn
	 */
	@BeforeAll
	public static void beforeAll() {
		// Object van AanmeldController aanmaken
		AanmeldController aanmeldController = new AanmeldController();
		
		// Aanmelden met een juiste gebruiker
		dc = aanmeldController.meldAan("JanJansens", "123456789");
	}
	
	/**
	 * Na het uitvoeren van alle testen moet de persistentielaag afgesloten worden
	 */
	@AfterAll
	public static void after()
	{
		//dc.sluitPersistentie();
	}

	/**
	 * Categorie aanmaken
	 * Correcte scenario:
	 * Categorie aanmaken met een correcte naam die nog niet bestaat in de databank
	 */
	@Test
	public void maakCategorie_correcteNaam_aangemaakt()
	{
	
		   final String CATEGORIENAAM = "CategorieTest";

	       Fluvius fluvius = new Fluvius();   
	       Categorie eenCategorie = new Categorie(CATEGORIENAAM);
	       
	       Mockito.when(fluviusRepo.findAll()).thenReturn(Arrays.asList(fluvius));
	       Mockito.when(categorieRepo.getByNaam(CATEGORIENAAM)).thenReturn(eenCategorie);
	       
	       assertFalse(fluvius.geefCategorien().contains(eenCategorie));
	       
	       dc.voegCategorieToe(CATEGORIENAAM);
	       
	       assertTrue(fluvius.geefCategorien().contains(eenCategorie));
	       
	       Mockito.verify(fluviusRepo).findAll();
	       Mockito.verify(categorieRepo).getByNaam(CATEGORIENAAM);
	       
	}
	
	/**
	 * Categorie aanmaken
	 * Foutieve scenario's:
	 * Categorie aanmaken met een naam die al bestaat in de databank
	 * Categorie aanmaken zonder een naam
	 */
	public void maakCategorie_foutieveNaam_exception()
	{
//		Categorie c = new Categorie(null, null, null);
//		
//		Assertions.assertThrows(IllegalArgumentException.class, () -> dc.voegCategorieToe(c));

	}
	
	/**
	 * Categorie wijzigen
	 * Correcte scenario:
	 * Categorie wijzigen met een correcte naam die nog niet bestaat in de databank
	 */
	public void wijzigCategorie_correcteNaam_gewijzigd()
	{
//		Categorie c = new Categorie(null, null, null);
//		
//		Assertions.assertDoesNotThrow(() -> {
//			dc.wijzigCategorieNaam(c, null);
//		});
	}
	
	/**
	 * Categorie wijzigen
	 * Foutieve scenario's:
	 * Categorie wijzigen met een naam die al bestaat in de databank
	 * Categorie wijzigen zonder een naam
	 */
	public void wijzigCategorie_foutieveNaam_exception()
	{
//		Categorie c = new Categorie(null, null, null);
//		
//		Assertions.assertThrows(IllegalArgumentException.class, () -> dc.wijzigCategorieNaam(c, null));
	}
	
	/**
	 * Categorie verwijderen
	 * Correcte scenario:
	 * De categorie die verwijdert moet worden, is niet de enigste categorie in de databank
	 */
	public void verwijderCategorie_nietEnigste_verwijderd()
	{
//		Categorie c = new Categorie(null, null, null);
//		
//		Assertions.assertDoesNotThrow(() -> {
//			dc.verwijderCategorie(c);
//		});
	}
	
	/**
	 * Categorie verwijderen
	 * Foutieve scenario:
	 * De categorie die verwijdert moet worden, is wel de enigste categorie in de databank
	 */
	public void verwijderCategorie_enigste_exception()
	{
//		Categorie c = new Categorie(null, null, null);
//		
//		Assertions.assertThrows(IllegalArgumentException.class, () -> dc.verwijderCategorie(c));
	}

}
