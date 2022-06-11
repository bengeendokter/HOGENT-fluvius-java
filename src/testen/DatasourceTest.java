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
import repository.CategorieDaoJpa;
import repository.MVODatasourceDaoJpa;
import repository.MVODoelstellingDaoJpa;
import repository.SdGoalDaoJpa;

@SuppressWarnings("unused")
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class DatasourceTest {
	
	@Mock
	private CategorieDaoJpa categorieRepo;
	
	@Mock
	private SdGoalDaoJpa sdGoalsRepo;
	
	@Mock
	private MVODoelstellingDaoJpa mvoDoelstellingRepo;
	
	@Mock
	private MVODatasourceDaoJpa datasourceRepo;
	
	
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
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";
		   final int KOLOM = 1;

	       
	       // Controle
	       Assertions.assertDoesNotThrow(() -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
	       });
	}
	
	/**
	 * Datasource aanmaken
	 * Foute scenario:
	 * Datasource aanmaken met een bestaande naam
	 * En met type en link
	 * @throws IOException 
	 */
	@Test
	public void maakDatasource_BestaandeNaam_aangemaakt() throws IOException
	{
		   // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";
		   final int KOLOM = 1;
		   
		   DTODatasource datasource = new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM);
		   MVODatasource mvoDatasource = new MVODatasource(datasource);
		   mvoDatasource.setDatasourceID(1);
		   
		   //train mock object
		   Mockito.when(datasourceRepo.getByNaam(DATASOURCENAAM)).thenReturn(mvoDatasource);
		   
	       // Controle
		   Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   fluvius.voegMVODatasourceToe(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
	       });
		   
		   // Na de test verifiëren
	       Mockito.verify(datasourceRepo).getByNaam(DATASOURCENAAM);
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
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";
		   final int KOLOM = 1;
	       
	       // Controle
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
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
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";
		   final int KOLOM = 1;

	       
	       // Controle
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
	       });
	}
	
	/**
	 * Datasource aanmaken
	 * Foutieve scenario:
	 * Datasource aanmaken fout Type
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "        ","docx"})
	public void maakDatasource_FoutType_exception(String type)
	{
		// Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = type;
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";
		   final int KOLOM = 1;
	       
	       // Controle
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
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
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";
		   final int KOLOM = 1;
		   
		   final String DATASOURCENAAMNEW = "DatasourceTest2";
		   final String TYPENEW = "excel";
		   final String LINKNEW = "src/data/xlsxDouble.xlsx";
		   final boolean CORRUPTNEW = true;
		   final String WIJZIGBAARHEIDNEW = "snel";
		   final String MAATNEW = "test1";
		   final int KOLOMNEW = 2;
		   
	       MVODatasource eenDatasource =   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
	       
	       eenDatasource.setDatasourceID(1);

	       fluvius.setCurrentDatasource(eenDatasource);
	       // Het mock object trainen
	       Mockito.when(datasourceRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(eenDatasource)));
	       Mockito.when(datasourceRepo.getByNaam(DATASOURCENAAMNEW)).thenReturn(null);

	       
	       // Uitvoeren
	       Assertions.assertDoesNotThrow(() -> {
	    	   fluvius.wijzigMVODatasource(new DTODatasource(DATASOURCENAAMNEW, TYPENEW, LINKNEW,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
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
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";
		   final int KOLOM = 1;
		   
		   final String DATASOURCENAAMNEW = naam;
		   final String TYPENEW = "csv";
		   final String LINKNEW = "src/data/csvDouble.csv";
		   final boolean CORRUPTNEW = true;
		   final String WIJZIGBAARHEIDNEW = "snel";
		   final String MAATNEW = "test1";
		   final int KOLOMNEW = 2;
		   
	       MVODatasource eenDatasource =   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));

	       eenDatasource.setDatasourceID(1);
	       
	       fluvius.setCurrentDatasource(eenDatasource);
	       // Het mock object trainen
	       Mockito.lenient().when(datasourceRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(eenDatasource)));
	       Mockito.lenient().when(datasourceRepo.getByNaam(DATASOURCENAAMNEW)).thenReturn(null);
	       
	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   fluvius.wijzigMVODatasource(new DTODatasource(DATASOURCENAAMNEW, TYPENEW, LINKNEW,"","","", CORRUPTNEW, WIJZIGBAARHEIDNEW, MAATNEW, KOLOMNEW));
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
	@ValueSource(strings = { "        ","docx"})
	public void wijzigDatasource_foutType_gewijzigd(String type) throws IOException
	{
		 // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";
		   final int KOLOM = 1;
		   
		   final String DATASOURCENAAMNEW = "DatasourceTest2";
		   final String TYPENEW = type;
		   final String LINKNEW = "src/data/csvDouble.csv";
		   final boolean CORRUPTNEW = true;
		   final String WIJZIGBAARHEIDNEW = "snel";
		   final String MAATNEW = "test1";
		   final int KOLOMNEW = 2;
		   
	       MVODatasource eenDatasource =   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));

	       eenDatasource.setDatasourceID(1);

	       
	       fluvius.setCurrentDatasource(eenDatasource);
	       // Het mock object trainen
	       Mockito.lenient().when(datasourceRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(eenDatasource)));
	       Mockito.lenient().when(datasourceRepo.getByNaam(DATASOURCENAAMNEW)).thenReturn(null);
	       
	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   fluvius.wijzigMVODatasource(new DTODatasource(DATASOURCENAAMNEW, TYPENEW, LINKNEW,"","","", CORRUPTNEW, WIJZIGBAARHEIDNEW, MAATNEW, KOLOMNEW));
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
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";
		   final int KOLOM = 1;
		   
		   final String DATASOURCENAAMNEW = "DatasourceTest2";
		   final String TYPENEW = "csv";
		   final String LINKNEW = link;
		   final boolean CORRUPTNEW = true;
		   final String WIJZIGBAARHEIDNEW = "snel";
		   final String MAATNEW = "test1";
		   final int KOLOMNEW = 2;
		   
	       MVODatasource eenDatasource =   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));

	       eenDatasource.setDatasourceID(1);
	       
	       fluvius.setCurrentDatasource(eenDatasource);
	       // Het mock object trainen
	       Mockito.lenient().when(datasourceRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(eenDatasource)));
	       Mockito.lenient().when(datasourceRepo.getByNaam(DATASOURCENAAMNEW)).thenReturn(null);
	       
	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   fluvius.wijzigMVODatasource(new DTODatasource(DATASOURCENAAMNEW, TYPENEW, LINKNEW,"","","", CORRUPTNEW, WIJZIGBAARHEIDNEW, MAATNEW, KOLOMNEW));
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
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";
		   final int KOLOM = 1;
		   
		   final String DATASOURCENAAM2 = "DatasourceTest2";
		   final String TYPE2 = "csv";
		   final String LINK2 = "src/data/csvDouble.csv";
	       MVODatasource datasource =   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","" ,  CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
	       MVODatasource dataSource2 =   new MVODatasource(new DTODatasource(DATASOURCENAAM2, TYPE2, LINK2,"","","",   CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
	       
	       datasource.setDatasourceID(1);
	       dataSource2.setDatasourceID(2);
	       
	       fluvius.setCurrentDatasource(datasource);
	       // Het mock object trainen
	       Mockito.when(datasourceRepo.getByNaam(DATASOURCENAAM2)).thenReturn(dataSource2);
	       
	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   fluvius.wijzigMVODatasource(new DTODatasource(DATASOURCENAAM2, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
			});
	       
	       // Na de test verifiëren
	       Mockito.verify(datasourceRepo).getByNaam(DATASOURCENAAM2);
	}
	
	//eventueel controle voor status, maat en kolom
	
	/**
	 * Datasource aanmaken
	 * Foutief scenario:
	 * Datasource aanmaken met een te grote kolom (CSV)
	 */
	@ParameterizedTest
	@ValueSource(ints={4,5,6,7,8,9,10, Integer.MAX_VALUE})
	public void maakDatasource_CSV_TeGroteKolomNummer(int KOLOM)
	{
		   // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";

	       
	       // Controle
	       Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
	       });
	}
	
	/**
	 * Datasource aanmaken
	 * Foutief scenario:
	 * Datasource aanmaken met een te kleine kolom (CSV)
	 */
	@ParameterizedTest
	@ValueSource(ints={Integer.MIN_VALUE, -10, -8, -4, -2, -1})
	public void maakDatasource_CSV_TeKleineKolomNummer(int KOLOM)
	{
		   // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";

	       
	       // Controle
	       Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
	       });
	}
	
	/**
	 * Datasource aanmaken
	 * Foutief scenario:
	 * Datasource aanmaken met een te grote kolom (XLSX)
	 */
	@ParameterizedTest
	@ValueSource(ints={4,5,6,7,8,9,10, Integer.MAX_VALUE})
	public void maakDatasource_XLSX_TeGroteKolomNummer(int KOLOM)
	{
		   // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "excel";
		   final String LINK = "src/data/xlsxDouble.xlsx";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";

	       
	       // Controle
	       Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
	       });
	}
	
	/**
	 * Datasource aanmaken
	 * Foutief scenario:
	 * Datasource aanmaken met een te kleine kolom (XLSX)
	 */
	@ParameterizedTest
	@ValueSource(ints={Integer.MIN_VALUE, -10, -8, -4, -2, -1})
	public void maakDatasource_XLSX_TeKleineKolomNummer(int KOLOM)
	{
		   // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";

	       
	       // Controle
	       Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
	       });
	}
	
	/**
	 * Datasource aanmaken
	 * Foutief scenario:
	 * Datasource aanmaken met een te grote kolom (XLS)
	 */
	@ParameterizedTest
	@ValueSource(ints={4,5,6,7,8,9,10, Integer.MAX_VALUE})
	public void maakDatasource_XLS_TeGroteKolomNummer(int KOLOM)
	{
		   // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "excel";
		   final String LINK = "src/data/xlsDouble.xls";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";

	       
	       // Controle
	       Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
	       });
	}
	
	/**
	 * Datasource aanmaken
	 * Foutief scenario:
	 * Datasource aanmaken met een te kleine kolom (XLS)
	 */
	@ParameterizedTest
	@ValueSource(ints={Integer.MIN_VALUE, -10, -8, -4, -2, -1})
	public void maakDatasource_XLS_TeKleineKolomNummer(int KOLOM)
	{
		   // Alles klaarzetten
		   final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = "src/data/xlsDouble.xls";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";

	       
	       // Controle
	       Assertions.assertThrows(IllegalArgumentException.class, () -> {
	    	   new MVODatasource(new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM));
	       });
	}
	
	/**
	 * Datasource verwijderen
	 * Correcte scenario:
	 * De datasource die verwijdert moet worden, is niet de enigste datasource in de databank
	 * @throws IOException 
	 */
	@Test
	public void verwijderDatasource_nietEnigste_verwijderd() throws IOException
	{
		   // Alles klaarzetten
			final String DATASOURCENAAM = "DatasourceTest";
			final String DATASOURCENAAM2 = "DatasourceTest2";
		   final String TYPE = "csv";
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";
		   final int KOLOM = 1;
		   
		   DTODatasource datasource = new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM);
		   MVODatasource mvoDatasource = new MVODatasource(datasource);
		   DTODatasource datasource2 = new DTODatasource(DATASOURCENAAM2, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM);
		   MVODatasource mvoDatasource2 = new MVODatasource(datasource2);
		
	       fluvius.setCurrentDatasource(mvoDatasource);

	       // Het mock object trainen
	       Mockito.lenient().when(datasourceRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(mvoDatasource, mvoDatasource2)));
	       Mockito.lenient().when(datasourceRepo.getByNaam(DATASOURCENAAM)).thenReturn(mvoDatasource);
	       
	       // Uitvoeren
	       Assertions.assertDoesNotThrow(
	    		   () -> fluvius.verwijderMVODatasource());
	       
	       // Na de test verifiëren

	       Mockito.verify(datasourceRepo, Mockito.atLeast(2)).findAll();
	       Mockito.verify(datasourceRepo, Mockito.times(0)).getByNaam(DATASOURCENAAM);
	}
	
	
	/**
	 * Datasource verwijderen
	 * Foutieve scenario:
	 * De datasource die verwijdert moet worden, is wel de enigste datasource in de databank
	 * @throws IOException 
	 */
	@Test
	public void verwijderDatasource_enigste_exception() throws IOException
	{
		   // Alles klaarzetten
			final String DATASOURCENAAM = "DatasourceTest";
		   final String TYPE = "csv";
		   final String LINK = "src/data/csvDouble.csv";
		   final boolean CORRUPT = false;
		   final String WIJZIGBAARHEID = "traag";
		   final String MAAT = "test";
		   final int KOLOM = 1;
		   
		   DTODatasource datasource = new DTODatasource(DATASOURCENAAM, TYPE, LINK,"","","", CORRUPT, WIJZIGBAARHEID, MAAT, KOLOM);
		   MVODatasource mvoDatasource = new MVODatasource(datasource);
		
	       fluvius.setCurrentDatasource(mvoDatasource);

	       // Het mock object trainen
	       Mockito.lenient().when(datasourceRepo.findAll()).thenReturn(new ArrayList<>(Arrays.asList(mvoDatasource)));
	       Mockito.lenient().when(datasourceRepo.getByNaam(DATASOURCENAAM)).thenReturn(mvoDatasource);
	       
	       // Uitvoeren
	       Assertions.assertThrows(IllegalArgumentException.class, 
	    		   () -> fluvius.verwijderMVODatasource());
	       
	       // Na de test verifiëren
	       Mockito.verify(datasourceRepo, Mockito.atLeast(1)).findAll();
	       Mockito.verify(datasourceRepo, Mockito.times(0)).getByNaam(DATASOURCENAAM);
	}
}