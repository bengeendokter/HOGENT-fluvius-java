package domein;

import java.util.List;

public class DTOMVODoelstelling
{
	
	// Attributen
	public String naam;
	public String icon;
	public double doelwaarde;
	public List<Rol> rollen;
	public SdGoal sdGoal;
	public Datasource datasource;
	public List<Doelstelling> subDoelstellingen;
	public Bewerking bewerking;
	
	// Constructor
	public DTOMVODoelstelling(String naam, String icon, double doelwaarde, List<Rol> rollen, SdGoal goal, Datasource datasource, List<Doelstelling> subDoelstellingen, Bewerking bewerking)
	{
		this.naam = naam;
		this.icon = icon;
		this.doelwaarde = doelwaarde;
		this.rollen = rollen;
		this.sdGoal = goal;
		this.datasource = datasource;
		this.subDoelstellingen = subDoelstellingen;
		this.bewerking = bewerking;
	}
	
}
