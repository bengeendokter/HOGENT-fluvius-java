package domein;

import java.util.List;

public class DTOCategorie {
	public String naam;
	public String icon;
	public List<SdGoal> sdgoals;
	
	
	public DTOCategorie(String naam, String icon, List<SdGoal> sdgoals) {
		this.naam = naam;
		this.icon = icon;
		this.sdgoals = sdgoals;
	}
	
	
}
