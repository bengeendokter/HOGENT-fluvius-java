package testen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Categorie;
import domein.Fluvius;
import domein.SdGoal;
import repository.CategorieDao;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class CategorieTest{

	@Mock
    private CategorieDao  categorieRepo;
	
	@InjectMocks
	private static Fluvius fluvius;
	
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
	       SdGoal sdg1 = new SdGoal("sdg 1");
	       SdGoal sdg2 = new SdGoal("sdg 2");
	       List<SdGoal> sdgs = new ArrayList<>(Arrays.asList(sdg1,sdg2));

	       // Controle
	       Assertions.assertDoesNotThrow(() -> {
	    	   new Categorie(CATEGORIENAAM, sdgs);
			});
	}
	
	/**
	 * Categorie aanmaken
	 * Foutieve scenario:
	 * Categorie aanmaken zonder een naam
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "        "})
	public void maakCategorie_foutieveNaamSdg_exception(String naam)
	{
		   // Alles klaarzetten
		   final String CATEGORIENAAMNEW = naam;
	       SdGoal sdg1 = new SdGoal("sdg 1");
	       SdGoal sdg2 = new SdGoal("sdg 2");
	       List<SdGoal> sdgs = new ArrayList<>(Arrays.asList(sdg1,sdg2));

	       // Controle
	       Assertions.assertThrows(IllegalArgumentException.class, 
		   () -> new Categorie(CATEGORIENAAMNEW, sdgs));
	       
	}
	
	/**
	 * Categorie wijzigen
	 * Correcte scenario:
	 * Categorie wijzigen met een correcte naam die nog niet bestaat in de databank
	 * Met sdg's
	 */
	@Test
	public void wijzigCategorie_correcteNaamSdg_gewijzigd()
	{
		   // Alles klaarzetten
		   final String CATEGORIENAAMOLD = "CategorieTest";
		   final String CATEGORIENAAMNEW = "CategorieTestNew";
	       SdGoal sdg1 = new SdGoal("sdg 1");
	       SdGoal sdg2 = new SdGoal("sdg 2");
	       List<SdGoal> sdgs = new ArrayList<>(Arrays.asList(sdg1, sdg2));
	       Categorie eenCategorie = new Categorie(CATEGORIENAAMOLD, sdgs);

	       // Het mock object trainen
	       Mockito.when(categorieRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(eenCategorie)));
	       Mockito.when(categorieRepo.getByNaam(CATEGORIENAAMOLD)).thenReturn(eenCategorie);
	       
	       // Uitvoeren
	       Assertions.assertDoesNotThrow(() -> {
				fluvius.wijzigCategorieNaam(CATEGORIENAAMOLD, CATEGORIENAAMNEW);
			});
	       
	       // Na de test verifiëren
	       Mockito.verify(categorieRepo).findAll();
	       Mockito.verify(categorieRepo).getByNaam(CATEGORIENAAMOLD);

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
	       SdGoal sdg1 = new SdGoal("sdg 1");
	       SdGoal sdg2 = new SdGoal("sdg 2");
	       List<SdGoal> sdgs1 = new ArrayList<>(Arrays.asList(sdg1));
	       List<SdGoal> sdgs2 = new ArrayList<>(Arrays.asList(sdg2));
	       Categorie eenCategorie = new Categorie(CATEGORIENAAMOLD, sdgs1);
	       Categorie tweedeCategorie = new Categorie("TestCategorie", sdgs2);

	       // Het mock object trainen
	       Mockito.when(categorieRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(eenCategorie, tweedeCategorie)));
	       Mockito.when(categorieRepo.getByNaam(CATEGORIENAAMOLD)).thenReturn(eenCategorie);
	       
	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class, 
	    		   () -> fluvius.wijzigCategorieNaam(CATEGORIENAAMOLD, CATEGORIENAAMNEW));
	       
	       // Na de test verifiëren
	       Mockito.verify(categorieRepo).findAll();
	       Mockito.verify(categorieRepo).getByNaam(CATEGORIENAAMOLD);
	}
	
	/**
	 * Categorie verwijderen
	 * Correcte scenario:
	 * De categorie die verwijdert moet worden, is niet de enigste categorie in de databank
	 * Met sdg's
	 */
	@Test
	public void verwijderCategorie_nietEnigsteSdg_verwijderd()
	{
		   // Alles klaarzetten
		   final String CATEGORIENAAM1 = "CategorieTest";
		   final String CATEGORIENAAM2 = "TestCategorie";
	       SdGoal sdg1 = new SdGoal("sdg 1");
	       SdGoal sdg2 = new SdGoal("sdg 2");
	       List<SdGoal> sdgs1 = new ArrayList<>(Arrays.asList(sdg1));
	       List<SdGoal> sdgs2 = new ArrayList<>(Arrays.asList(sdg2));
	       Categorie eenCategorie = new Categorie(CATEGORIENAAM1, sdgs1);
	       Categorie tweedeCategorie = new Categorie(CATEGORIENAAM2, sdgs2);

	       // Het mock object trainen
	       Mockito.when(categorieRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(eenCategorie, tweedeCategorie)));
	       Mockito.when(categorieRepo.getByNaam(CATEGORIENAAM2)).thenReturn(eenCategorie);
	       
	       // Uitvoeren
	       Assertions.assertDoesNotThrow(() -> {
				fluvius.verwijderCategorie(CATEGORIENAAM2);
			});
	       
	       // Na de test verifiëren
	       Mockito.verify(categorieRepo).findAll();
	       Mockito.verify(categorieRepo).getByNaam(CATEGORIENAAM2);
	}
	
	/**
	 * Categorie verwijderen
	 * Foutieve scenario:
	 * De categorie die verwijdert moet worden, is wel de enigste categorie in de databank
	 * Met sdg's
	 */
	@Test
	public void verwijderCategorie_enigsteMetSdg_exception()
	{
		   // Alles klaarzetten
		   final String CATEGORIENAAM1 = "CategorieTest";
	       SdGoal sdg1 = new SdGoal("sdg 1");
	       SdGoal sdg2 = new SdGoal("sdg 2");
	       List<SdGoal> sdgs1 = new ArrayList<>(Arrays.asList(sdg1,sdg2));
	       Categorie eenCategorie = new Categorie(CATEGORIENAAM1, sdgs1);


	       // Het mock object trainen
	       Mockito.when(categorieRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(eenCategorie)));
	       Mockito.when(categorieRepo.getByNaam(CATEGORIENAAM1)).thenReturn(eenCategorie);
	       
	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class, 
	    		   () -> fluvius.verwijderCategorie(CATEGORIENAAM1));
	       
	       // Na de test verifiëren
	       Mockito.verify(categorieRepo).findAll();
	       Mockito.verify(categorieRepo).getByNaam(CATEGORIENAAM1);
	}

}
