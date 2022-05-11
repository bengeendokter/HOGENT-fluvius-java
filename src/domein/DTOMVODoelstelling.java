package domein;

import java.util.List;

public class DTOMVODoelstelling
{
	
	// Attributen
	public String naam;
	public String icon;
	public double doelwaarde;
	public boolean isMax;
	public List<Rol> rollen;
	public SdGoal sdGoal;
	public Datasource datasource;
	public List<Doelstelling> subDoelstellingen;
	public Bewerking bewerking;
	
	//historiek
	public int jaar;
	
	// Constructor
	public DTOMVODoelstelling(String naam, String icon, double doelwaarde, List<Rol> rollen, SdGoal goal,  Datasource datasource, List<Doelstelling> subDoelstellingen, Bewerking bewerking
			,int jaar)
	{
		this.naam = naam;
		this.icon = icon;
		this.doelwaarde = doelwaarde;
		this.isMax = true;
		this.rollen = rollen;
		this.sdGoal = goal;
		this.datasource = datasource;
		this.subDoelstellingen = subDoelstellingen;
		this.bewerking = bewerking;
		this.jaar = jaar;
	}
	
	public DTOMVODoelstelling(String naam, String icon, double doelwaarde, boolean isMax, List<Rol> rollen, SdGoal goal,  Datasource datasource, List<Doelstelling> subDoelstellingen, Bewerking bewerking
			,int jaar)
	{
		this.naam = naam;
		this.icon = icon;
		this.doelwaarde = doelwaarde;
		this.isMax = isMax;
		this.rollen = rollen;
		this.sdGoal = goal;
		this.datasource = datasource;
		this.subDoelstellingen = subDoelstellingen;
		this.bewerking = bewerking;
		this.jaar = jaar;
	}
	
	
}
