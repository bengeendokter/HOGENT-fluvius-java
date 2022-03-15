package domein;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class DomeinController
{
	// ATTRIBUTEN
	// ______________________________________________________________________________________________
	
	private Gebruiker aangemeldeGebruiker;
	private Fluvius fluvius;

	
	// CONSTRUCTOR
	// ______________________________________________________________________________________________
	
	public DomeinController(Gebruiker aangemeldeGebruiker)
	{
		this.aangemeldeGebruiker = aangemeldeGebruiker;
		setFluvius(new Fluvius());
		
	}
	
	// FLUVIUS
	// ______________________________________________________________________________________________
	
	public void setFluvius(Fluvius fluvius) {
		this.fluvius = fluvius;
	}
	
	// AANGEMELDE GEBRUIKER
	// ______________________________________________________________________________________________

	public Gebruiker getAangemeldeGebruiker()
	{
		return aangemeldeGebruiker;
	}

	// SDG
	// ______________________________________________________________________________________________

	public ObservableList<SdGoal> getBeschikbareSdgs()
	{
		return fluvius.getBeschikbareSdgs();
	}
	
	public ObservableList<SdGoal> getSdgs()
	{
		//return fluvius.getSdgs();
		return null;
	}
	
	// CATEGORIE BEHEREN
	// ______________________________________________________________________________________________
	
	public Categorie getCurrentCategorie()
	{
		return fluvius.getCurrentCategorie();
	}
	
	public void setCurrentCategorie(Categorie categorie)
	{
		fluvius.setCurrentCategorie(categorie);
	}
	
	public ObservableList<Categorie> getCategorien()
	{
		return fluvius.getCategorien();
	}
	
	public void voegCategorieToe(DTOCategorie categorie)
	{
		fluvius.voegCategorieToe(categorie);
	}
	
	public void voegCategorieObserverToe(ListChangeListener<Categorie> listener)
	{
		fluvius.voegCategorieObserverToe(listener);
	}
	
	public void verwijderCategorie()
	{
		fluvius.verwijderCategorie();
	}
	
	public void wijzigCategorie(DTOCategorie categorie)
	{
		fluvius.wijzigCategorie(categorie);
	}
	
	// MVO DOELSTELLING BEHEREN
	// ______________________________________________________________________________________________
	
	public Doelstelling getCurrentDoelstelling() {
		return fluvius.getCurrentDoelstelling();
	}
	
	public void setCurrentDoelstelling(Doelstelling doelstelling) {
		fluvius.setCurrentDoelstelling(doelstelling);
	}
	
	public ObservableList<Doelstelling> getDoelstellingen(){
		return fluvius.getDoelstellingen();
	}
	
	public List<Component> geefDoelstellingenDieGeenSubsHebben(){
		return fluvius.geefDoelstellingenDieGeenSubsHebben();
	}
	
	public List<Component> geefDoelstellingenDieSubsHebben(){
		return fluvius.geefDoelstellingenDieSubsHebben();
	}
	
	public void voegMVODoelstellingToe(DTOMVODoelstelling doelstelling) {
		fluvius.voegMVODoelstellingToe(doelstelling);
	}
	
	public void verwijderMVODoelstelling() {
		fluvius.verwijderMVODoelstelling();
	}
	
	public void wijzigMVODoelstelling(DTOMVODoelstelling doelstelling) {
		fluvius.wijzigMVODoelstelling(doelstelling);
	}
	
	// DATASOURCE BEHEREN
	// ______________________________________________________________________________________________
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
	
	public void voegMVODatasourceToe(DTODatasource datasource) throws SQLIntegrityConstraintViolationException, IllegalStateException
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