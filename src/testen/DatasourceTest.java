package testen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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

import domein.DTODatasource;
import domein.Fluvius;
import domein.MVODatasource;
import repository.MVODatasourceDao;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class DatasourceTest {
	
	@Mock
    private MVODatasourceDao datasourceRepo;
	
	@InjectMocks
	private static Fluvius fluvius;
	
	
	/**
	 * Datasource aanmaken
	 * Correcte scenario:
	 * Datasource aanmaken met een correcte naam
	 * En met type en link
	 */
	@Test
	public void maakDatasource_OnbestaandeNaam_aangemaakt()
	{
		   // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = "InTeVullen";
	       
	       // Controle
	       Assertions.assertDoesNotThrow(() -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","",""));
	       });
	}
	
	/**
	 * Datasource aanmaken
	 * Foutieve scenario:
	 * Datasource aanmaken zonder een naam
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "        "})
	public void maakDatasource_LegeNaam_exception(String naam)
	{
		 // Alles klaarzetten
		   final String DATASOURCENAAM = naam;
		   final String TYPE = "csv";
		   final String LINK = "InTeVullen";
	       
	       // Controle
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","",""));
	       });
	       
	}
	
	
	/**
	 * Datasource aanmaken
	 * Foutieve scenario:
	 * Datasource aanmaken zonder Link
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "        "})
	public void maakDatasource_zonderLink_exception(String link)
	{
		// Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = link;
	       
	       // Controle
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","",""));
	       });
	}
	
	/**
	 * Datasource aanmaken
	 * Foutieve scenario:
	 * Datasource aanmaken zonder Type
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "        "})
	public void maakDatasource_zonderType_exception(String type)
	{
		// Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = type;
		   final String LINK = "link";
	       
	       // Controle
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","",""));
	       });
	}
	
	
	/**
	 * Datasource wijzigen
	 * Correcte scenario:
	 * Datasource wijzigen met een correcte naam, link en type
	 * @throws IOException 
	 */
	@Test
	public void wijzigDatasource_correcteGegevens_gewijzigd() throws IOException
	{
		 // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = "InTeVullen";
		   
		   final String DATASOURCENAAMNEW = "DatasourceTest2";
		   final String TYPENEW = "excl";
		   final String LINKNEW = "InTeVullen";
	       MVODatasource eenDatasource =   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","",""));

	       fluvius.setCurrentDatasource(eenDatasource);
	       // Het mock object trainen
	       Mockito.when(datasourceRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(eenDatasource)));
	       Mockito.when(datasourceRepo.getByNaam(DATASOURCENAAMNEW)).thenReturn(null);
	       
	       // Uitvoeren
	       Assertions.assertDoesNotThrow(() -> {
	    	   fluvius.wijzigMVODatasource(new DTODatasource(DATASOURCENAAMNEW, TYPENEW, LINKNEW,"","","",""));
			});
	       
	       // Na de test verifiëren
	       Mockito.verify(datasourceRepo).findAll();
	       Mockito.verify(datasourceRepo).getByNaam(DATASOURCENAAMNEW);

	}
	
	/**
	 * Datasource wijzigen
	 * Foutief scenario:
	 * Datasource wijzigen met een foute naam
	 * @throws IOException 
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "        "})
	public void wijzigDatasource_legeNaam_gewijzigd(String naam) throws IOException
	{
		 // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = "InTeVullen";
		   
		   final String DATASOURCENAAMNEW = naam;
		   final String TYPENEW = "csv";
		   final String LINKNEW = "InTeVullen";
	       MVODatasource eenDatasource =   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","",""));

	       fluvius.setCurrentDatasource(eenDatasource);
	       // Het mock object trainen
	       Mockito.lenient().when(datasourceRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(eenDatasource)));
	       Mockito.lenient().when(datasourceRepo.getByNaam(DATASOURCENAAMNEW)).thenReturn(null);
	       
	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   fluvius.wijzigMVODatasource(new DTODatasource(DATASOURCENAAMNEW, TYPENEW, LINKNEW,"","","",""));
			});
	}
	
	/**
	 * Datasource wijzigen
	 * Foutief scenario:
	 * Datasource wijzigen met een fout type
	 * @throws IOException 
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "        "})
	public void wijzigDatasource_leegType_gewijzigd(String type) throws IOException
	{
		 // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = "InTeVullen";
		   
		   final String DATASOURCENAAMNEW = "DatasourceTest";
		   final String TYPENEW = type;
		   final String LINKNEW = "InTeVullen";
	       MVODatasource eenDatasource =   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","",""));

	       fluvius.setCurrentDatasource(eenDatasource);
	       // Het mock object trainen
	       Mockito.lenient().when(datasourceRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(eenDatasource)));
	       Mockito.lenient().when(datasourceRepo.getByNaam(DATASOURCENAAMNEW)).thenReturn(null);
	       
	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   fluvius.wijzigMVODatasource(new DTODatasource(DATASOURCENAAMNEW, TYPENEW, LINKNEW,"","","",""));
			});
	}
	
	/**
	 * Datasource wijzigen
	 * Foutief scenario:
	 * Datasource wijzigen met een foute link
	 * @throws IOException 
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "        "})
	public void wijzigDatasource_legeLink_gewijzigd(String link) throws IOException
	{
		 // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = "InTeVullen";
		   
		   final String DATASOURCENAAMNEW = "DatasourceTest";
		   final String TYPENEW = "csv";
		   final String LINKNEW = link;
	       MVODatasource eenDatasource =   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","",""));

	       fluvius.setCurrentDatasource(eenDatasource);
	       // Het mock object trainen
	       Mockito.lenient().when(datasourceRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(eenDatasource)));
	       Mockito.lenient().when(datasourceRepo.getByNaam(DATASOURCENAAMNEW)).thenReturn(null);
	       
	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   fluvius.wijzigMVODatasource(new DTODatasource(DATASOURCENAAMNEW, TYPENEW, LINKNEW,"","","",""));
			});
	}
	
	
	/**
	 * Datasource wijzigen
	 * Foutief scenario:
	 * Datasource wijzigen met een bestaande naam
	 * @throws IOException 
	 */
	@Test
	public void wijzigDatasource_bestaandeNaam_gewijzigd() throws IOException
	{
		 // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = "InTeVullen";
		   
		   final String DATASOURCENAAM2 = "DatasourceTest2";
		   final String TYPE2 = "csv";
		   final String LINK2 = "InTeVullen";
	       MVODatasource datasource =   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","",""));
	       MVODatasource dataSource2 =   new MVODatasource(new DTODatasource(DATASOURCENAAM2, TYPE2, LINK2,"","","",""));
	       MVODatasource array[] = {datasource, dataSource2};

	       fluvius.setCurrentDatasource(datasource);
	       // Het mock object trainen
	       Mockito.when(datasourceRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(array)) );
	       Mockito.when(datasourceRepo.getByNaam(DATASOURCENAAM2)).thenReturn(dataSource2);
	       
	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   fluvius.wijzigMVODatasource(new DTODatasource(DATASOURCENAAM2, DATASOURCENAAM, DATASOURCENAAM,"","","",""));
			});
	       
	       // Na de test verifiëren
	       Mockito.verify(datasourceRepo).findAll();
	       Mockito.verify(datasourceRepo).getByNaam(DATASOURCENAAM2);
	}
	
	
	
}
