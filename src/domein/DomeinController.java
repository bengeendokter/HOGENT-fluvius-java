package domein;

import java.util.List;

import org.eclipse.persistence.exceptions.DatabaseException;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import repository.GenericDaoJpa;
import repository.MVODatasourceDao;

public class DomeinController
{
	private Gebruiker aangemeldeGebruiker;
	
	private Fluvius fluvius;

	public DomeinController(Gebruiker aangemeldeGebruiker)
	{
		this.aangemeldeGebruiker = aangemeldeGebruiker;
		setFluvius(new Fluvius());
		
	}
	
	public void setFluvius(Fluvius fluvius) {
		this.fluvius = fluvius;
	}

	public Gebruiker getAangemeldeGebruiker()
	{
		return aangemeldeGebruiker;
	}

	public void voegCategorieToe(DTOCategorie categorie)
	{
		fluvius.voegCategorieToe(categorie);
	}

	public void verwijderCategorie()
	{
		fluvius.verwijderCategorie();
	}

	public ObservableList<Categorie> getCategorien()
	{
		return fluvius.getCategorien();
	}

	public void voegCategorieObserverToe(ListChangeListener<Categorie> listener)
	{
		fluvius.voegCategorieObserverToe(listener);
	}

	public ObservableList<SdGoal> getBeschikbareSdgs()
	{
		return fluvius.getBeschikbareSdgs();
	}

	public void wijzigCategorie(DTOCategorie categorie)
	{
		fluvius.wijzigCategorie(categorie);
	}

	public void setCurrentCategorie(Categorie categorie)
	{
		fluvius.setCurrentCategorie(categorie);
	}

	public Categorie getCurrentCategorie()
	{
		return fluvius.getCurrentCategorie();
	}
	
	//Datasource deel
	public ObservableList<Datasource> getDatasources()
	{
		return fluvius.getDatasources();
	}
	
	public Datasource getCurrentDatasource()
	{
		return fluvius.getCurrentDatasource();
	}
	
	public void setCurrentDatasource(Datasource datasource)
	{
		fluvius.setCurrentDatasource(datasource);
	}
	
	public void voegMVODatasourceToe(DTODatasource datasource)
	{
		fluvius.voegMVODatasourceToe(datasource);
	}
	
	public void verwijderMVODatasource()
	{
		fluvius.verwijderMVODatasource();
		
	}
	
	public void wijzigMVODatasource(DTODatasource datasource)
	{
		fluvius.wijzigMVODatasource(datasource);
	}

}