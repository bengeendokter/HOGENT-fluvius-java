package domein;

import java.util.List;

public class DTOMVODoelstelling {
	
	// Attributen
	public String naam;
	public String icon;
	public double doelwaarde;
	public String doelstellingsType;
	public List<Rol> rollen;
	public List<MVODatasource> datasources;
	public SdGoal hoofdSdg;
	public SdGoal subSdg;
	
	// Constructor
	public DTOMVODoelstelling(String naam, String icon, double doelwaarde, String doelstellingsType, List<Rol> rollen,
			List<MVODatasource> datasources, SdGoal hoofdSdg, SdGoal subSdg) {
		this.naam = naam;
		this.icon = icon;
		this.doelwaarde = doelwaarde;
		this.doelstellingsType = doelstellingsType;
		this.rollen = rollen;
		this.datasources = datasources;
		this.hoofdSdg = hoofdSdg;
		this.subSdg = subSdg;
	}
	
	
	
	
	

}
