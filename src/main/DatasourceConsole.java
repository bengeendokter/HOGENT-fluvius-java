package main;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domein.AanmeldController;
import domein.DTODatasource;
import domein.DomeinController;
import domein.MVODatasource;
import repository.DatabaseSelector;
import repository.GenericDaoJpa;

public class DatasourceConsole {
	
	public static void main(String[] args) throws SQLIntegrityConstraintViolationException, IllegalStateException, IOException {
		/*AanmeldController aanmeldController = new AanmeldController();
		DomeinController dc = aanmeldController.meldAan("JanJansens", "123456789");*/
		
		  EntityManagerFactory emf = Persistence.createEntityManagerFactory("fluvius");
		  EntityManager em = emf.createEntityManager();
		
		  em.getTransaction().begin();
		  //DatasourceTest dt = new DatasourceTest(new DTODatasource("fileXLS", "excel", "src/data/xlsDouble.xls"));
		  
		  MVODatasource ds = new MVODatasource(new DTODatasource("CSV2", "csv", "src/data/csvDouble.csv", null, null, null, null));
		  em.persist(ds);
		  
		  ds = new MVODatasource(new DTODatasource("S2", "excel", "src/data/xlsDouble.xls", "8Gb", null, null, null));
		  em.persist(ds);
		  
		  ds = new MVODatasource(new DTODatasource("X2", "excel", "src/data/xlsxDouble.xlsx", "26Gb", null, null, null));
		  em.persist(ds);
		  
		  ds = new MVODatasource(new DTODatasource("databank2", "databank", null, null, "hostname2", "user2", "password2"));
		  em.persist(ds);
		  
		  em.getTransaction().commit();
		  em.close();
		  emf.close();
		  
		
		/*dc.voegMVODatasourceToe(new DTODatasource("test1", "csv", "file:src/data/dataProject.csv"));
		//dc.setCurrentDatasource(dc.getDatasources().filtered(e-> e.getNaam().equals("test")).get(0));
		List<Double> list = dc.getCurrentDatasource().getData();
		System.out.println(Arrays.toString(list.toArray()));*/
		
		
		//dc.wijzigMVODatasource(new DTODatasource("aantal vrouwen2", "csv", "fluvius.com/1"));
		//dc.verwijderMVODatasource();
	}	
}