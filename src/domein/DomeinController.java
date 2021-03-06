package domein;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import javafx.collections.ObservableList;
import repository.CategorieDaoJpa;
import repository.MVODatasourceDaoJpa;
import repository.MVODoelstellingDaoJpa;
import repository.SdGoalDaoJpa;

public class DomeinController {
	// ATTRIBUTEN
	// ______________________________________________________________________________________________

	private Gebruiker aangemeldeGebruiker;
	private Fluvius fluvius;

	// CONSTRUCTOR
	// ______________________________________________________________________________________________

	public DomeinController(Gebruiker aangemeldeGebruiker) {
		this.aangemeldeGebruiker = aangemeldeGebruiker;
		setFluvius(new Fluvius(new CategorieDaoJpa(), new SdGoalDaoJpa(), new MVODoelstellingDaoJpa(),
				new MVODatasourceDaoJpa()));

	}

	// FLUVIUS
	// ______________________________________________________________________________________________

	public void setFluvius(Fluvius fluvius) {
		this.fluvius = fluvius;
	}

	// AANGEMELDE GEBRUIKER
	// ______________________________________________________________________________________________

	public Gebruiker getAangemeldeGebruiker() {
		return aangemeldeGebruiker;
	}

	// SDG
	// ______________________________________________________________________________________________

	public ObservableList<SdGoal> getBeschikbareSdgs() {
		return fluvius.getBeschikbareSdgs();
	}

	public ObservableList<SdGoal> getSdgs() {
		return fluvius.getSdgs();
	}

	// CATEGORIE BEHEREN
	// ______________________________________________________________________________________________

	public Categorie getCurrentCategorie() {
		return fluvius.getCurrentCategorie();
	}

	public void setCurrentCategorie(Categorie categorie) {
		fluvius.setCurrentCategorie(categorie);
	}

	public ObservableList<Categorie> getCategorien() {
		return fluvius.getCategorien();
	}

	public void voegCategorieToe(DTOCategorie categorie) {
		fluvius.voegCategorieToe(categorie);
	}

	public void verwijderCategorie() {
		fluvius.verwijderCategorie();
	}

	public void wijzigCategorie(DTOCategorie categorie) {
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

	public ObservableList<Doelstelling> getDoelstellingen() {
		return fluvius.getDoelstellingen();
	}

	public List<Doelstelling> geefDoelstellingenDieGeenSubsHebben() {
		return fluvius.geefDoelstellingenDieGeenSubsHebben();
	}

	public List<Doelstelling> geefDoelstellingenDieSubsHebben() {
		return fluvius.geefDoelstellingenDieSubsHebben();
	}

	public void voegMVODoelstellingToeMetSubs(DTOMVODoelstelling doelstelling) {
		fluvius.voegMVODoelstellingToeMetSubs(doelstelling);
	}

	public void voegMVODoelstellingToeZonderSubs(DTOMVODoelstelling doelstelling) {
		fluvius.voegMVODoelstellingToeZonderSubs(doelstelling);
	}

	public void verwijderMVODoelstelling() {
		fluvius.verwijderMVODoelstelling();
	}

	public void wijzigMVODoelstelling(DTOMVODoelstelling doelstelling) {
		fluvius.wijzigMVODoelstelling(doelstelling);
	}

	// DATASOURCE BEHEREN
	// ______________________________________________________________________________________________
	public ObservableList<Datasource> getDatasources() {
		return fluvius.getDatasources();
	}

	public Datasource getCurrentDatasource() {
		return fluvius.getCurrentDatasource();
	}

	public void setCurrentDatasource(Datasource datasource) {
		fluvius.setCurrentDatasource(datasource);
	}

	public void voegMVODatasourceToe(DTODatasource datasource)
			throws SQLIntegrityConstraintViolationException, IllegalStateException {
		fluvius.voegMVODatasourceToe(datasource);
	}

	public void verwijderMVODatasource() {
		fluvius.verwijderMVODatasource();

	}

	public void wijzigMVODatasource(DTODatasource datasource) {
		fluvius.wijzigMVODatasource(datasource);
	}

}