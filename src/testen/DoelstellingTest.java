package testen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import domein.Average;
import domein.Bewerking;
import domein.Component;
import domein.Composite;
import domein.DTODatasource;
import domein.DTOMVODoelstelling;
import domein.Datasource;
import domein.Doelstelling;
import domein.Fluvius;
import domein.Leaf;
import domein.MVODatasource;
import domein.Rol;
import domein.SdGoal;
import domein.Som;
import repository.CategorieDaoJpa;
import repository.MVODatasourceDaoJpa;
import repository.MVODoelstellingDaoJpa;
import repository.SdGoalDaoJpa;

@SuppressWarnings("unused")
@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.Silent.class)
public class DoelstellingTest {
	
	@Mock
    private CategorieDaoJpa categorieRepo;
	
	@Mock
    private SdGoalDaoJpa sdgoalRepo;
	
	@Mock
    private MVODoelstellingDaoJpa mvoDoelstellingRepo;
	
	@Mock
    private MVODatasourceDaoJpa dataRepo;
	
	@InjectMocks
	private  static Fluvius fluvius;
	
	/**
	 * Doelstelling composite aanmaken 
	 * Correcte scenario:
	 * Doelstelling aanmaken met correcte parrameters
	 * @throws IOException 
	 */
	@Test
	public void maakDoelstellingComposite_Correct_aangemaakt() throws IOException
	{
		   // Alles klaarzetten
		   String naam = "Doelstelling";
		   String icon = "csv";
		   double doelwaarde = 1000.0;
		   List<Rol> rollen = new ArrayList<>();
		   rollen.add(new Rol("MVO Coordinator"));
		   SdGoal sdGoal = new SdGoal("sdg 1");
		   Bewerking formule = new Average();
		   List<Doelstelling> subDoelstellingen = new ArrayList<>();
		   Datasource datasource = new MVODatasource(new DTODatasource("CategorieTest", "csv", "src/data/csvDouble.csv", "hostnaam", "usernaam", "paswoord", false, "snel","uitstoot",1));
		 
	       
	       // Controle
	       Assertions.assertDoesNotThrow(() -> {
	    	   new Composite(new DTOMVODoelstelling(naam, icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule, 2020));
	       });
	}
	
	/**
	 * Doelstelling  aanmaken 
	 * Foutief scenario:
	 * Doelstelling aanmaken met een bestaande naam
	 * @throws IOException 
	 */
	@Test
	public void maakDoelstelling_BestaandeNaam_foutmelding() throws IOException
	{
		   // Alles klaarzetten
		   String naam = "Doelstelling";
		   String icon = "csv";
		   double doelwaarde = 1000.0;
		   List<Rol> rollen = new ArrayList<>();
		   rollen.add(new Rol("MVO Coordinator"));
		   SdGoal sdGoal = new SdGoal("sdg 1");
		   Bewerking formule = new Average();
		   List<Doelstelling> subDoelstellingen = new ArrayList<>();
		   Datasource datasource = new MVODatasource(new DTODatasource("CategorieTest", "csv", "src/data/csvDouble.csv", "hostnaam", "usernaam", "paswoord", false, "snel","uitstoot",1));
		   Component doelstelling = new Composite(new DTOMVODoelstelling(naam, icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule, 2020));
		 
		   Mockito.when(mvoDoelstellingRepo
				   .getByNaam(naam)).thenReturn(doelstelling);
	       // Controle
		   Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	  fluvius.voegMVODoelstellingToeZonderSubs(new DTOMVODoelstelling(naam, icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule, 2020));
	       });
	}
	
	
	
	/**
	 * Doelstelling leaf aanmaken 
	 * Correcte scenario:
	 * Doelstelling aanmaken met correcte parrameters
	 * @throws IOException 
	 */
	@Test
	public void maakDoelstellingLeaf_Correct_aangemaakt() throws IOException
	{
		   // Alles klaarzetten
		   String naam = "Doelstelling";
		   String icon = "csv";
		   double doelwaarde = 1000.0;
		   List<Rol> rollen = new ArrayList<>();
		   rollen.add(new Rol("MVO Coordinator"));
		   SdGoal sdGoal = new SdGoal("sdg 1");
		   Bewerking formule = new Average();
		   List<Doelstelling> subDoelstellingen = new ArrayList<>();
		   Datasource datasource = new MVODatasource(new DTODatasource("CategorieTest", "csv", "src/data/csvDouble.csv", "hostnaam", "usernaam", "paswoord", false, "snel","uitstoot",1));
			
	       
	       // Controle
	       Assertions.assertDoesNotThrow(() -> {
	    	   new Leaf(new DTOMVODoelstelling(naam, icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule, 2020));
	       });
	}
	
	/**
	 * Doelstelling composite aanmaken met subdoelstellingen(composite en leaf)
	 * Correcte scenario:
	 * Doelstelling aanmaken met correcte parrameters
	 * @throws IOException 
	 */
	@Test
	public void maakDoelstellingMetSub_Correct_aangemaakt() throws IOException
	{
		   // Alles klaarzetten
		   String naam = "Doelstelling";
		   String icon = "csv";
		   double doelwaarde = 1000.0;
		   List<Rol> rollen = new ArrayList<>();
		   rollen.add(new Rol("MVO Coordinator"));
		   SdGoal sdGoal = new SdGoal("sdg 1");
		   Bewerking formule = new Average();
		   List<Doelstelling> subDoelstellingen = new ArrayList<>();
		   Datasource datasource = new MVODatasource(new DTODatasource("CategorieTest", "csv", "src/data/csvDouble.csv", "hostnaam", "usernaam", "paswoord", false, "snel","uitstoot",1));
		   Doelstelling doelstellingComposite = new Composite(new DTOMVODoelstelling("Composite", icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule, 2020));
		   Doelstelling doesltellingLeaf = new Leaf(new DTOMVODoelstelling("Leaf", icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule, 2020));
		   subDoelstellingen.add(doelstellingComposite);
		   subDoelstellingen.add(doesltellingLeaf);
		   Doelstelling doelstelling = new Composite(new DTOMVODoelstelling(naam, icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule, 2020));
	 
	    
		   // Controle
	       Assertions.assertArrayEquals(doelstelling.getComponents().toArray(), subDoelstellingen.toArray());
	}
	
	
	/**
	 * Doelstelling  aanmaken 
	 * Foutief scenario:
	 * Lege naam
	 * @throws IOException 
	 */
	@ParameterizedTest
	@NullAndEmptySource
	@ValueSource(strings = { "        "})
	public void maakDoelstelling_legeNaam_foutmelding(String naam) throws IOException
	{
		   // Alles klaarzetten
		   String icon = "csv";
		   double doelwaarde = 1000.0;
		   List<Rol> rollen = new ArrayList<>();
		   rollen.add(new Rol("MVO Coordinator"));
		   SdGoal sdGoal = new SdGoal("sdg 1");
		   Bewerking formule = new Average();
		   List<Doelstelling> subDoelstellingen = new ArrayList<>();
		   Datasource datasource = new MVODatasource(new DTODatasource("CategorieTest", "csv", "src/data/csvDouble.csv", "hostnaam", "usernaam", "paswoord", false, "snel","uitstoot",1));
		 
	       
	       // Controle
		   Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   new Composite(new DTOMVODoelstelling(naam, icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule, 2020));
	       });
	}
	
	
	/**
	 * Doelstelling composite aanmaken 
	 * Foutief scenario:
	 * geen sdGoal
	 * @throws IOException 
	 */
	@Test
	public void maakDoelstelling_zonderSdGoal_foutmelding() throws IOException
	{
		   // Alles klaarzetten
		   String naam = "Doelstelling";
		   String icon = "csv";
		   double doelwaarde = 1000.0;
		   List<Rol> rollen = new ArrayList<>();
		   rollen.add(new Rol("MVO Coordinator"));
		   SdGoal sdGoal = null;
		   Bewerking formule = new Average();
		   List<Doelstelling> subDoelstellingen = new ArrayList<>();
		   Datasource datasource = new MVODatasource(new DTODatasource("CategorieTest", "csv", "src/data/csvDouble.csv", "hostnaam", "usernaam", "paswoord", false, "snel","uitstoot",1));
		 
	       
		   // Controle
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   new Composite(new DTOMVODoelstelling(naam, icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule, 2020));
	       });
	}
	
	/**
	 * Doelstelling composite aanmaken 
	 * Foutief scenario:
	 * geen bewerking
	 * @throws IOException 
	 */
	@Test
	public void maakDoelstelling_zonderBewerking_foutmelding() throws IOException
	{
		   // Alles klaarzetten
		   String naam = "Doelstelling";
		   String icon = "csv";
		   double doelwaarde = 1000.0;
		   List<Rol> rollen = new ArrayList<>();
		   rollen.add(new Rol("MVO Coordinator"));
		   SdGoal sdGoal = new SdGoal("sdg 1");
		   Bewerking formule = null;
		   List<Doelstelling> subDoelstellingen = new ArrayList<>();
		   Datasource datasource = new MVODatasource(new DTODatasource("CategorieTest", "csv", "src/data/csvDouble.csv", "hostnaam", "usernaam", "paswoord", false, "snel","uitstoot",1));
		 
	       
		   // Controle
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   new Composite(new DTOMVODoelstelling(naam, icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule, 2020));
	       });
	}
	
	private static Stream<Arguments> fouteRollen(){
		  List<Rol> rollen = new ArrayList<>();
		   rollen.add(new Rol("MVO Coordinator"));
		   rollen.add(new Rol("MVO Coordinator"));
		return Stream.of(
				Arguments.of(new ArrayList<Rol>()),
				Arguments.of(rollen)
				);	
	}
	
	/**
	 * Doelstelling  aanmaken 
	 * Foutief scenario:
	 * Lege rollen, dubbele rol
	 * @throws IOException 
	 */
	@ParameterizedTest
	@NullSource
	@MethodSource("fouteRollen")
	public void maakDoelstelling_fouteRollen_foutmelding(List<Rol> rollen) throws IOException
	{
		   // Alles klaarzetten
		   String naam = "Doelstelling";
		   String icon = "csv";
		   double doelwaarde = 1000.0;
		   SdGoal sdGoal = new SdGoal("sdg 1");
		   Bewerking formule = new Average();
		   List<Doelstelling> subDoelstellingen = new ArrayList<>();
		   Datasource datasource = new MVODatasource(new DTODatasource("CategorieTest", "csv", "src/data/csvDouble.csv", "hostnaam", "usernaam", "paswoord", false, "snel","uitstoot",1));
		 
	       
	       // Controle
		   Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   new Composite(new DTOMVODoelstelling(naam, icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule, 2020));
	       });
	}
	
	/**
	 * Doelstelling composite aanmaken met 4 lagen subdoelstellingen
	 * Foutief scenario:
	 * Max 3 lagen subdoelstellingen
	 * @throws IOException 
	 */
	@Test
	public void maakDoelstellingMetSub_4Lagen_IllegalArgumentException() throws IOException
	{
		   // Alles klaarzetten
		   String naam = "Doelstelling";
		   String icon = "csv";
		   double doelwaarde = 1000.0;
		   List<Rol> rollen = new ArrayList<>();
		   rollen.add(new Rol("MVO Coordinator"));
		   SdGoal sdGoal = new SdGoal("sdg 1");
		   Bewerking formule = new Average();
		   List<Doelstelling> subDoelstellingen = new ArrayList<>();
		   Datasource datasource = new MVODatasource(new DTODatasource("CategorieTest", "csv", "src/data/csvDouble.csv", "hostnaam", "usernaam", "paswoord", false, "snel","uitstoot",1));
		   Doelstelling doelstellingComposite1 = new Composite(new DTOMVODoelstelling("Composite1", icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule, 2020));
		   Doelstelling doelstellingComposite2 = new Composite(new DTOMVODoelstelling("Composite2", icon, doelwaarde, rollen, sdGoal, datasource, Arrays.asList(doelstellingComposite1), formule, 2020));
		   Doelstelling doelstellingComposite3 = new Composite(new DTOMVODoelstelling("Composite3", icon, doelwaarde, rollen, sdGoal, datasource, Arrays.asList(doelstellingComposite2), formule, 2020));
	    
		   // Controle
	       Assertions.assertThrows(IllegalArgumentException.class,() -> {
	    	   new Composite(new DTOMVODoelstelling("Composite4", icon, doelwaarde, rollen, sdGoal, datasource, Arrays.asList(doelstellingComposite3), formule, 2020));
	       });
	}
	
	
	/**
	 * Doelstelling leaf bewerking uitvoeren
	 * Correcte scenario:
	 * Bewerking is juist
	 * @throws IOException 
	 */
	@Test
	public void maakDoelstellingLeaf_bewerking_correct() throws IOException
	{
		   // Alles klaarzetten
		   String naam = "Doelstelling";
		   String icon = "csv";
		   double doelwaarde = 1000.0;
		   List<Rol> rollen = new ArrayList<>();
		   rollen.add(new Rol("MVO Coordinator"));
		   SdGoal sdGoal = new SdGoal("sdg 1");
		   Bewerking formule = new Som();
		   List<Doelstelling> subDoelstellingen = new ArrayList<>();
		   Datasource datasource = new MVODatasource(new DTODatasource("CategorieTest", "csv", "src/data/csvDouble.csv", "hostnaam", "usernaam", "paswoord", false, "snel","uitstoot",1));
		   Doelstelling doesltellingLeaf = new Leaf(new DTOMVODoelstelling("Leaf", icon, doelwaarde, rollen, sdGoal, datasource, subDoelstellingen, formule, 2020));
		 
	    
		   // Controle
		   Assertions.assertEquals(
				   doesltellingLeaf.getBerekendewaarde().values().stream().findFirst().get()
				   , 22.7f
				   ,0.005
				   ); 
	}
	
	
}
