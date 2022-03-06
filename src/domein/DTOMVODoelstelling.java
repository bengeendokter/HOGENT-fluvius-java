package domein;

import java.util.List;

public class DTOMVODoelstelling {
	
	// Attributen
	public String naam;
	public String icon;
	public double doelwaarde;
	public String type;
	public List<Rol> rollen;
	public List<MVODatasource> datasources;
	
	// Constructor
	public DTOMVODoelstelling(String naam, String icon, double doelwaarde, String type, List<Rol> rollen,
			List<MVODatasource> datasources) {
		this.naam = naam;
		this.icon = icon;
		this.doelwaarde = doelwaarde;
		this.type = type;
		this.rollen = rollen;
		this.datasources = datasources;
	}
	
	
	
	
	

}
