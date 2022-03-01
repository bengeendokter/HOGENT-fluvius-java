package testen;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
import domein.SdGoal;
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
	 * En met sdg's
	 */
	@Test
	public void maakCategorie_correcteNaamSdg_aangemaakt()
	{
		   // Alles klaarzetten
		   final String CATEGORIENAAM = "CategorieTest";
	       Fluvius fluvius = new Fluvius();   
//	       SdGoal sdg1 = new SdGoal("sdg 1");
//	       SdGoal sdg2 = new SdGoal("sdg 2");
//	       List<SdGoal> sdgs = new ArrayList<>(Arrays.asList(sdg1, sdg2));
//	       Categorie eenCategorie = new Categorie(CATEGORIENAAM, sdgs);
	       Categorie eenCategorie = new Categorie(CATEGORIENAAM);
	       
	       // Het mock object trainen
	       Mockito.when(fluviusRepo.findAll()).thenReturn(Arrays.asList(fluvius));
//	       Mockito.when(fluvius.geefSdGoals()).thenReturn(sdgs.stream().map(s -> s.toString()).collect(Collectors.toList()));
	       Mockito.when(categorieRepo.getByNaam(CATEGORIENAAM)).thenReturn(eenCategorie);
	       
	       // Uitvoeren
	       assertFalse(fluvius.geefCategorien().contains(eenCategorie));
	       Assertions.assertDoesNotThrow(() -> {
	    	   dc.voegCategorieToe(CATEGORIENAAM);
			});
//	       Assertions.assertDoesNotThrow(() -> {
//	    	   dc.voegCategorieToe(CATEGORIENAAM, sdgs);
//			});
	       assertTrue(fluvius.geefCategorien().contains(eenCategorie));
	       
	       // Na de test verifiëren
	       Mockito.verify(fluviusRepo).findAll();
	       Mockito.verify(categorieRepo).getByNaam(CATEGORIENAAM);
	       // hier moet nog iets
	}
	
	/**
	 * Categorie aanmaken
	 * Foutieve scenario's:
	 * Categorie aanmaken met een naam die al bestaat in de databank
	 * Categorie aanmaken zonder een naam
	 * En zonder sdg's
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "        ", "CategorieTest" })
	public void maakCategorie_foutieveNaam_exception(String naam)
	{

		   // Alles klaarzetten
		   final String CATEGORIENAAM = "CategorieTest";
	       Fluvius fluvius = new Fluvius();
//	       List<SdGoal> sdgs = new ArrayList<>();
//	       Categorie eenCategorie = new Categorie(CATEGORIENAAM, sdgs);
	       Categorie eenCategorie = new Categorie(CATEGORIENAAM);
	       
	       // Het mock object trainen
	       Mockito.when(fluviusRepo.findAll()).thenReturn(Arrays.asList(fluvius));
//	       Mockito.when(fluvius.geefSdGoals()).thenReturn(sdgs.stream().map(s -> s.toString()).collect(Collectors.toList()));
	       Mockito.when(categorieRepo.findAll()).thenReturn(Arrays.asList(eenCategorie));

	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class, () -> dc.voegCategorieToe(naam));
//	       Assertions.assertThrows(IllegalArgumentException.class, () -> dc.voegCategorieToe(naam, sdgs));
	       
	       // Na de test verifiëren
	       Mockito.verify(fluviusRepo).findAll();
	       Mockito.verify(categorieRepo).findAll();
	       // hier moet nog iets

	}
	
	/**
	 * Categorie wijzigen
	 * Correcte scenario:
	 * Categorie wijzigen met een correcte naam die nog niet bestaat in de databank
	 * Met sdg's
	 */
	public void wijzigCategorie_correcteNaamSdg_gewijzigd()
	{
		   // Alles klaarzetten
		   final String CATEGORIENAAMOLD = "CategorieTest";
		   final String CATEGORIENAAMNEW = "CategorieTestNew";
//	       SdGoal sdg1 = new SdGoal("sdg 1");
//	       SdGoal sdg2 = new SdGoal("sdg 2");
//	       List<SdGoal> sdgs = new ArrayList<>(Arrays.asList(sdg1, sdg2));
//	       Categorie eenCategorie = new Categorie(CATEGORIENAAMOLD, sdgs);
	       Fluvius fluvius = new Fluvius();   
	       Categorie eenCategorie = new Categorie(CATEGORIENAAMOLD);
	       
	       // Het mock object trainen
	       Mockito.when(fluviusRepo.findAll()).thenReturn(Arrays.asList(fluvius));
//	       Mockito.when(fluvius.geefSdGoals()).thenReturn(sdgs.stream().map(s -> s.toString()).collect(Collectors.toList()));
	       Mockito.when(categorieRepo.findAll()).thenReturn(Arrays.asList(eenCategorie));

	       // Uitvoeren
	       Assertions.assertDoesNotThrow(() -> {
				dc.wijzigCategorieNaam(CATEGORIENAAMOLD, CATEGORIENAAMNEW);
			});
	       
	       // Na de test verifiëren
	       Mockito.verify(fluviusRepo).findAll();
	       Mockito.verify(categorieRepo).findAll();
	       // hier moet nog iets
	}
	
	/**
	 * Categorie wijzigen
	 * Foutieve scenario's:
	 * Categorie wijzigen met een naam die al bestaat in de databank
	 * Categorie wijzigen zonder een naam
	 * Met sdg's
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "        ", "TestCategorie" })
	public void wijzigCategorie_foutieveNaamSdg_exception(String naam)
	{
		   // Alles klaarzetten
		   final String CATEGORIENAAMOLD = "CategorieTest";
		   final String CATEGORIENAAMNEW = naam;
//	       SdGoal sdg1 = new SdGoal("sdg 1");
//	       SdGoal sdg2 = new SdGoal("sdg 2");
//	       List<SdGoal> sdgs = new ArrayList<>(Arrays.asList(sdg1, sdg2));
//	       Categorie eenCategorie = new Categorie(CATEGORIENAAMOLD, sdgs);
	       Fluvius fluvius = new Fluvius();   
	       Categorie eenCategorie = new Categorie(CATEGORIENAAMOLD);
	       Categorie tweedeCategorie = new Categorie("TestCategorie");
	       
	       // Het mock object trainen
	       Mockito.when(fluviusRepo.findAll()).thenReturn(Arrays.asList(fluvius));
//	       Mockito.when(fluvius.geefSdGoals()).thenReturn(sdgs.stream().map(s -> s.toString()).collect(Collectors.toList()));
	       Mockito.when(categorieRepo.findAll()).thenReturn(Arrays.asList(eenCategorie, tweedeCategorie));

	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class, 
	    		   () -> dc.wijzigCategorieNaam(CATEGORIENAAMOLD, CATEGORIENAAMNEW));
	       
	       // Na de test verifiëren
	       Mockito.verify(fluviusRepo).findAll();
	       Mockito.verify(categorieRepo).findAll();
	       // hier moet nog iets
	}
	
	/**
	 * Categorie verwijderen
	 * Correcte scenario:
	 * De categorie die verwijdert moet worden, is niet de enigste categorie in de databank
	 * Met sdg's
	 */
	public void verwijderCategorie_nietEnigsteSdg_verwijderd()
	{
		   // Alles klaarzetten
		   final String CATEGORIENAAM1 = "CategorieTest";
		   final String CATEGORIENAAM2 = "TestCategorie";
	       Fluvius fluvius = new Fluvius();   
//	       SdGoal sdg1 = new SdGoal("sdg 1");
//	       SdGoal sdg2 = new SdGoal("sdg 2");
//	       List<SdGoal> sdgs1 = new ArrayList<>(Arrays.asList(sdg1));
//	       List<SdGoal> sdgs2 = new ArrayList<>(Arrays.asList(sdg2));
//	       Categorie eenCategorie = new Categorie(CATEGORIENAAM1, sdgs1);
//	       Categorie tweedeCategorie = new Categorie(CATEGORIENAAM2, sdgs2);
	       Categorie eenCategorie = new Categorie(CATEGORIENAAM1);
	       Categorie tweedeCategorie = new Categorie(CATEGORIENAAM2);
	       
	       // Het mock object trainen
	       Mockito.when(fluviusRepo.findAll()).thenReturn(Arrays.asList(fluvius));
//	       Mockito.when(fluvius.geefSdGoals()).thenReturn(sdgs2.stream().map(s -> s.toString()).collect(Collectors.toList()));
	       Mockito.when(categorieRepo.findAll()).thenReturn(Arrays.asList(eenCategorie, tweedeCategorie));

	       // Uitvoeren
			Assertions.assertDoesNotThrow(() -> {
			dc.verwijderCategorie(CATEGORIENAAM2);
			});
	       
	       // Na de test verifiëren
	       Mockito.verify(fluviusRepo).findAll();
	       Mockito.verify(categorieRepo).findAll();
	       // hier moet nog iets
	}
	
	/**
	 * Categorie verwijderen
	 * Foutieve scenario:
	 * De categorie die verwijdert moet worden, is wel de enigste categorie in de databank
	 * Met sdg's
	 */
	public void verwijderCategorie_enigsteMetSdg_exception()
	{
		   // Alles klaarzetten
		   final String CATEGORIENAAM1 = "CategorieTest";
	       Fluvius fluvius = new Fluvius();   
//	       SdGoal sdg1 = new SdGoal("sdg 1");
//	       SdGoal sdg2 = new SdGoal("sdg 2");
//	       List<SdGoal> sdgs = new ArrayList<>(Arrays.asList(sdg1));
//	       Categorie eenCategorie = new Categorie(CATEGORIENAAM1, sdgs);
	       Categorie eenCategorie = new Categorie(CATEGORIENAAM1);

	       
	       // Het mock object trainen
	       Mockito.when(fluviusRepo.findAll()).thenReturn(Arrays.asList(fluvius));
//	       Mockito.when(fluvius.geefSdGoals()).thenReturn(sdgs2.stream().map(s -> s.toString()).collect(Collectors.toList()));
	       Mockito.when(categorieRepo.findAll()).thenReturn(Arrays.asList(eenCategorie));

	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class, 
	    		   () -> dc.verwijderCategorie(CATEGORIENAAM1));
	       
	       // Na de test verifiëren
	       Mockito.verify(fluviusRepo).findAll();
	       Mockito.verify(categorieRepo).findAll();
	       // Hier moet nog iets
	}

}
