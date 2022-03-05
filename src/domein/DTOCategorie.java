package domein;

import java.util.List;

public class DTOCategorie {
	public int categorieID;
	public String naam;
	public String icon;
	public List<SdGoal> sdgoals;
	
	
	public DTOCategorie(int categorieID, String naam, String icon, List<SdGoal> sdgoals) {
		this.categorieID = categorieID;
		this.naam = naam;
		this.icon = icon;
		this.sdgoals = sdgoals;
	}
	
	
}
