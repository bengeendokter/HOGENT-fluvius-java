package testen;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Categorie;
import domein.Fluvius;
import repository.CategorieDao;


@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class CategorieTest
{
	
	@Mock
	private CategorieDao categorieRepo;
	
	@InjectMocks
	private Fluvius fluvius;
	
	@Test
	public void maakCategorie_correcteNaamSdg_aangemaakt()
	{
		
		// Alles klaarzetten
		final String CATEGORIENAAM = "CategorieTest";
		
		Categorie eenCategorie = new Categorie(CATEGORIENAAM);
		
		Mockito.when(categorieRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(eenCategorie)));
		Mockito.when(categorieRepo.getByNaam(CATEGORIENAAM)).thenReturn(eenCategorie);
		
		// Uitvoeren
		assertTrue(fluvius.getCategorien().contains(eenCategorie));
		
		Assertions.assertDoesNotThrow(() -> {
			fluvius.voegCategorieToe(CATEGORIENAAM);
		});
		
		assertTrue(fluvius.getCategorien().contains(eenCategorie));
		
		// Na de test verifiëren
		// hier moet nog iets
	}
	
}