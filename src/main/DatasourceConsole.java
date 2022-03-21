package main;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.eclipse.persistence.exceptions.DatabaseException;

import domein.AanmeldController;
import domein.DTODatasource;
import domein.DomeinController;
import domein.MVODatasource;
import repository.DatabaseSelector;
import repository.GenericDaoJpa;
import repository.MVODatasourceDao;
import repository.MVODatasourceDaoJpa;

public class DatasourceConsole {
	
	public static void main(String[] args) throws SQLIntegrityConstraintViolationException, IllegalStateException, IOException {
		/*AanmeldController aanmeldController = new AanmeldController();
		DomeinController dc = aanmeldController.meldAan("JanJansens", "123456789");*/
		
		/*DTODatasource dto = new DTODatasource("testdatabank", "databank", null, "hostname", "username", "sfd");
		
		MVODatasource ds = new MVODatasource(dto);
		
		try
		{
			
			MVODatasourceDao mvoDatasourceRepo = new MVODatasourceDaoJpa();
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("fluvius");
			EntityManager em = emf.createEntityManager();
			
			em.getTransaction().begin();
			
			MVODatasource nieuweDatasource = mvoDatasourceRepo.getByNaam(dto.naam); 
			
			if(nieuweDatasource != null)
			{
				throw new IllegalArgumentException("Er bestaat al een datasource met deze naam");
			}
			
			em.persist(ds);
			
			
			em.getTransaction().commit();
			em.close();
			emf.close();
			
		}

		catch(IllegalArgumentException e) {
			//GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(e.getMessage());
		}
		catch(RollbackException e) {
			throw new IllegalArgumentException(String.format("Datasource met naam '%s' bestaat al", dto.toString()));
		}
		catch(DatabaseException e)
		{
			//GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException(String.format("Datasource met naam '%s' bestaat al", dto.toString()));
		}
		
		
		catch(Exception e)
		{
			System.out.println(e);
			GenericDaoJpa.rollbackTransaction();
			throw new IllegalArgumentException("Er is een probleem opgetreden bij het toevoegen van een Datasource");
		}
		*/
		  
		  //DatasourceTest dt = new DatasourceTest(new DTODatasource("fileXLS", "excel", "src/data/xlsDouble.xls"));
		  
		
		  /*ds = new MVODatasource(new DTODatasource("testxls", "excel", "src/data/xlsDouble.xls", null, null, null));
		  em.persist(ds);
		  
		  ds = new MVODatasource(new DTODatasource("testxlsx", "excel", "src/data/xlsxDouble.xlsx",null, null, null));
		  em.persist(ds);
		  
		  ds = new MVODatasource(new DTODatasource("testdatabank1", "databank", null, null, "userX", "passwordX"));
		  em.persist(ds);*/
		  
		AanmeldController aanmeldController = new AanmeldController(true);
		DomeinController dc = aanmeldController.meldAan("JanJansens", "123456789"); 
		
		DTODatasource d = new DTODatasource("co2 mercedes2", "csv", "src/data/csvDouble.csv",  null, null, null, true, "snel", "uitstoot", 1);
		
		dc.voegMVODatasourceToe(d);
		
		DTODatasource d1 = new DTODatasource("co2 audi2", "excel", "src/data/xlsDouble.xls",  null, null, null, true, "traag", "uitstoot", 2);
		
		dc.voegMVODatasourceToe(d1);
		dc.setCurrentDatasource(dc.getDatasources().get(dc.getDatasources().size()-1));
		dc.wijzigMVODatasource(new DTODatasource("co2 audi3", "excel", "src/data/xlsDouble.xls",  null, null, null, true, "snel", "uitstootje", 1));
		
		dc.verwijderMVODatasource();
		
		/*DTODatasource d2 = new DTODatasource("c0", "databank", null,  "localhosttt", "testtt", "test123tt", true, "traag", "personen", 1);
		
		dc.voegMVODatasourceToe(d2);*/
		
		/*boolean zitErin = dc.getDatasources().stream().map(e -> e.getLink()).collect(Collectors.toList()).contains(d.link);
		if (zitErin) {
			throw new IllegalArgumentException("Er bestaat al een datasource met deze link");
		}*/
		  
		
		/*dc.voegMVODatasourceToe(new DTODatasource("test1", "csv", "file:src/data/dataProject.csv"));
		//dc.setCurrentDatasource(dc.getDatasources().filtered(e-> e.getNaam().equals("test")).get(0));
		List<Double> list = dc.getCurrentDatasource().getData();
		System.out.println(Arrays.toString(list.toArray()));*/
		
		
		//dc.wijzigMVODatasource(new DTODatasource("aantal vrouwen2", "csv", "fluvius.com/1"));
		//dc.verwijderMVODatasource();
	}	
}