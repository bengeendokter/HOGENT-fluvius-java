package domein;

import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import repository.CategorieDao;
import repository.CategorieDaoJpa;
import repository.GenericDaoJpa;
import repository.SdGoalDao;
import repository.SdGoalDaoJpa;

public class Fluvius
{
	private ObservableList<Categorie> categorien = FXCollections.observableArrayList();
	private ObservableList<SdGoal> sdGoals = FXCollections.observableArrayList();
	
	private CategorieDao categorieRepo;
	private SdGoalDao sdGoalsRepo;
	
	public Fluvius()
	{
		setCategorieRepo(new CategorieDaoJpa());
		setMvoDoelstellingRepo(new SdGoalDaoJpa());
		
		setCategorien();
		setSdGoals();
	}
	
	public void setCategorieRepo(CategorieDao mock)
	{
		categorieRepo = mock;
	}
	
	public void setMvoDoelstellingRepo(SdGoalDao mock)
	{
		sdGoalsRepo = mock;
	}
	
	private void setCategorien()
	{
		categorien.clear();
		categorien.addAll(categorieRepo.findAll());
	}
	
	public ObservableList<Categorie> getCategorien()
	{
		System.out.println("Alle Categoriën ophalen");
		return FXCollections.unmodifiableObservableList(categorien);
	}
	
	public List<String> geefCategorien()
	{
        return getCategorien().stream().map(Categorie::toString).collect(Collectors.toList());
	}
	
	public void setSdGoals()
	{
		sdGoals.clear();
		sdGoals.addAll(sdGoalsRepo.findAll());
	}

	public ObservableList<SdGoal> getSdGoals()
	{
		return FXCollections.unmodifiableObservableList(sdGoals);
	}
	
	public List<String> geefSdGoals()
	{
        return getSdGoals().stream().map(SdGoal::toString).collect(Collectors.toList());
	}
	
	public void voegCategorieObserverToe(ListChangeListener<Categorie> listener)
	{
		categorien.addListener(listener);
	}
	
	public void voegDoelstellingObserverToe(ListChangeListener<SdGoal> listener)
	{
		sdGoals.addListener(listener);
	}
	
	public void voegCategorieToe(Categorie categorie)
	{
		System.out.printf("Categorie %s inserten in databank%n", categorie.toString());
		GenericDaoJpa.startTransaction();
		categorieRepo.insert(categorie);
		GenericDaoJpa.commitTransaction();
		
		setCategorien();
	}

	public void voegCategorieToe(String naam)
	{
		System.out.printf("Categorie %s aanmaken in java%n", naam);
		Categorie categorie = new Categorie(naam);
		System.out.printf("Categorie %s is aangemaakt in Java%n", categorie.toString());
		voegCategorieToe(categorie);
	}
	
	public void verwijderCategorie(Categorie categorie)
	{
		System.out.printf("Categorie %s verwijderen uit databank%n", categorie.toString());
		GenericDaoJpa.startTransaction();
		categorieRepo.delete(categorie);
		GenericDaoJpa.commitTransaction();
		
		setCategorien();
	}

	public void verwijderCategorie(String naam)
	{
		System.out.printf("Categorie %s zoeken in databank%n", naam);
		Categorie categorie = categorieRepo.getByNaam(naam);		
		verwijderCategorie(categorie);		
	}

	public void wijzigCategorieNaam(Categorie categorie, String nieuweNaam)
	{
		categorie.setNaam(nieuweNaam);
		GenericDaoJpa.startTransaction();
		categorieRepo.update(categorie);
		GenericDaoJpa.commitTransaction();
		
		setCategorien();
	}
	
	public void wijzigCategorieNaam(String oldName, String newName)
	{
		Categorie categorie = categorieRepo.getByNaam(oldName);
		wijzigCategorieNaam(categorie, newName);
	}
	
//	public void wijzigCategorieRollen(Categorie categorie, List<Rol> rollen)
//	{
//		categorie.wijzigRollen(rollen);
//		getCategorien();
//	}
	
	public void wijzigCategorieDoelstellingen(Categorie categorie, List<SdGoal> sdGoals)
	{
		categorie.wijzigDoelstellingen(sdGoals);
		GenericDaoJpa.startTransaction();
		categorieRepo.update(categorie);
		GenericDaoJpa.commitTransaction();
		
		setCategorien();
	}

	public void wijzigCategorieDoelstellingen(String naam, List<String> sdGoalsNamen)
	{
		Categorie categorie = categorieRepo.getByNaam(naam);
		List<SdGoal> sdGoals = sdGoalsNamen.stream().map(sdgNaam -> sdGoalsRepo.getByNaam(sdgNaam)).collect(Collectors.toList());
		
		wijzigCategorieDoelstellingen(categorie, sdGoals);
	}
}
