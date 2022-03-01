package testen;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

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
		   // Alles klaarzetten
		   final String CATEGORIENAAM = "CategorieTest";
	       Fluvius fluvius = new Fluvius();   
	       Categorie eenCategorie = new Categorie(CATEGORIENAAM);
	       
	       // Het mock object trainen
	       Mockito.when(fluviusRepo.findAll()).thenReturn(Arrays.asList(fluvius));
	       Mockito.when(categorieRepo.getByNaam(CATEGORIENAAM)).thenReturn(eenCategorie);
	       
	       // Uitvoeren
	       assertFalse(fluvius.geefCategorien().contains(eenCategorie));
	       dc.voegCategorieToe(CATEGORIENAAM);
	       assertTrue(fluvius.geefCategorien().contains(eenCategorie));
	       
	       // Na de test verifiëren
	       Mockito.verify(fluviusRepo).findAll();
	       Mockito.verify(categorieRepo).getByNaam(CATEGORIENAAM);
	       
	}
	
	/**
	 * Categorie aanmaken
	 * Foutieve scenario's:
	 * Categorie aanmaken met een naam die al bestaat in de databank
	 * Categorie aanmaken zonder een naam
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "        ", "CategorieTest" })
	public void maakCategorie_foutieveNaam_exception(String naam)
	{

		   // Alles klaarzetten
		   final String CATEGORIENAAM = "CategorieTest";
	       Fluvius fluvius = new Fluvius();   
	       Categorie eenCategorie = new Categorie(CATEGORIENAAM);
	       
	       // Het mock object trainen
	       Mockito.when(fluviusRepo.findAll()).thenReturn(Arrays.asList(fluvius));
	       Mockito.when(categorieRepo.findAll()).thenReturn(Arrays.asList(eenCategorie));

	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class, () -> dc.voegCategorieToe(naam));
	       
	       // Na de test verifiëren
	       Mockito.verify(fluviusRepo).findAll();
	       Mockito.verify(categorieRepo).findAll();

	}
	
	/**
	 * Categorie wijzigen
	 * Correcte scenario:
	 * Categorie wijzigen met een correcte naam die nog niet bestaat in de databank
	 */
	public void wijzigCategorie_correcteNaam_gewijzigd()
	{
		   // Alles klaarzetten
		   final String CATEGORIENAAMOLD = "CategorieTest";
		   final String CATEGORIENAAMNEW = "CategorieTestNew";
	       Fluvius fluvius = new Fluvius();   
	       Categorie eenCategorie = new Categorie(CATEGORIENAAMOLD);
	       
	       // Het mock object trainen
	       Mockito.when(fluviusRepo.findAll()).thenReturn(Arrays.asList(fluvius));
	       Mockito.when(categorieRepo.findAll()).thenReturn(Arrays.asList(eenCategorie));

	       // Uitvoeren
	       Assertions.assertDoesNotThrow(() -> {
				dc.wijzigCategorieNaam(CATEGORIENAAMOLD, CATEGORIENAAMNEW);
			});
	       
	       // Na de test verifiëren
	       Mockito.verify(fluviusRepo).findAll();
	       Mockito.verify(categorieRepo).findAll();
	}
	
	/**
	 * Categorie wijzigen
	 * Foutieve scenario's:
	 * Categorie wijzigen met een naam die al bestaat in de databank
	 * Categorie wijzigen zonder een naam
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "        ", "TestCategorie" })
	public void wijzigCategorie_foutieveNaam_exception(String naam)
	{
		   // Alles klaarzetten
		   final String CATEGORIENAAMOLD = "CategorieTest";
		   final String CATEGORIENAAMNEW = naam;
	       Fluvius fluvius = new Fluvius();   
	       Categorie eenCategorie = new Categorie(CATEGORIENAAMOLD);
	       Categorie tweedeCategorie = new Categorie("TestCategorie");
	       
	       // Het mock object trainen
	       Mockito.when(fluviusRepo.findAll()).thenReturn(Arrays.asList(fluvius));
	       Mockito.when(categorieRepo.findAll()).thenReturn(Arrays.asList(eenCategorie, tweedeCategorie));

	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class, 
	    		   () -> dc.wijzigCategorieNaam(CATEGORIENAAMOLD, CATEGORIENAAMNEW));
	       
	       // Na de test verifiëren
	       Mockito.verify(fluviusRepo).findAll();
	       Mockito.verify(categorieRepo).findAll();
	}
	
	/**
	 * Categorie verwijderen
	 * Correcte scenario:
	 * De categorie die verwijdert moet worden, is niet de enigste categorie in de databank
	 */
	public void verwijderCategorie_nietEnigste_verwijderd()
	{
		   // Alles klaarzetten
		   final String CATEGORIENAAM1 = "CategorieTest";
		   final String CATEGORIENAAM2 = "TestCategorie";
	       Fluvius fluvius = new Fluvius();   
	       Categorie eenCategorie = new Categorie(CATEGORIENAAM1);
	       Categorie tweedeCategorie = new Categorie(CATEGORIENAAM2);
	       
	       // Het mock object trainen
	       Mockito.when(fluviusRepo.findAll()).thenReturn(Arrays.asList(fluvius));
	       Mockito.when(categorieRepo.findAll()).thenReturn(Arrays.asList(eenCategorie, tweedeCategorie));

	       // Uitvoeren
			Assertions.assertDoesNotThrow(() -> {
			dc.verwijderCategorie(CATEGORIENAAM2);
			});
	       
	       // Na de test verifiëren
	       Mockito.verify(fluviusRepo).findAll();
	       Mockito.verify(categorieRepo).findAll();
	}
	
	/**
	 * Categorie verwijderen
	 * Foutieve scenario:
	 * De categorie die verwijdert moet worden, is wel de enigste categorie in de databank
	 */
	public void verwijderCategorie_enigste_exception()
	{
		   // Alles klaarzetten
		   final String CATEGORIENAAM1 = "CategorieTest";
	       Fluvius fluvius = new Fluvius();   
	       Categorie eenCategorie = new Categorie(CATEGORIENAAM1);

	       
	       // Het mock object trainen
	       Mockito.when(fluviusRepo.findAll()).thenReturn(Arrays.asList(fluvius));
	       Mockito.when(categorieRepo.findAll()).thenReturn(Arrays.asList(eenCategorie));

	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class, 
	    		   () -> dc.verwijderCategorie(CATEGORIENAAM1));
	       
	       // Na de test verifiëren
	       Mockito.verify(fluviusRepo).findAll();
	       Mockito.verify(categorieRepo).findAll();
	}

}
