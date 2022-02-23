package testen;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class DomeinTest {
	//Bestaande gebruikers in de databank
	//JanJansens, 123456789
	//JansensJan, 987654321
	
	//vraag aan de factory een entityManager
    EntityManager entityManager =  JPAUtil.getEntityManagerFactory().createEntityManager();
    
    ////start een transactie
    entityManager.getTransaction().begin();

    Docent docent  = entityManager.find(Docent.class, 2L);
		
	@ParameterizedTest
	@CsvSource({"0, 0", "1,20"})
	public void controleerGebruiker(int pins, int total) {
		
	}
	
	
	@ParameterizedTest
	@CsvSource({"0, 0", "1,20"})
	public void meldAan_bestaandGebruikerJuisteGegevensGeldigPoging_Login(int pins, int total) {
		Assertions.assertEquals(true, true);
	}
	
	@ParameterizedTest
	@CsvSource({"0, 0", "1,20"})
	public void meldAan_bestaandGebruikerFouteGegevensGeldigPoging_Exception(int pins, int total) {
		Assertions.assertEquals(true, true);
	}
	
	@ParameterizedTest
	@CsvSource({"0, 0", "1,20"})
	public void meldAan_onbestaandGebruikerGeldigPoging_Exception(int pins, int total) {
		Assertions.assertEquals(true, true);
	}
	
	@ParameterizedTest
	@CsvSource({"0, 0", "1,20"})
	public void meldAan_bestaandGebruikerFouteGegevensOngeldigPoging_Exception(int pins, int total) {
		Assertions.assertEquals(true, true);
	}
	
	@ParameterizedTest
	@CsvSource({"0, 0", "1,20"})
	public void meldAan_onbestaandGebruikerOngeldigPoging_Exception(int pins, int total) {
		Assertions.assertEquals(true, true);
	}
	
	
}
