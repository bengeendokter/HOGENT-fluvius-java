package domein;

import java.util.Collection;
import java.util.List;

public class Categorie
{

	private Collection<MvoDoelstelling> mvoDoelstellingen;
	private Collection<Rol> rollen;
	private String naam;

	public Categorie(String naam, List<domein.MvoDoelstelling> doelstelingen, List<domein.Rol> rollen)
	{
		throw new UnsupportedOperationException();
	}

	public void wijzigRollen(List<String> rollen)
	{
		throw new UnsupportedOperationException();
	}

	public void wijzigDoelstellingen(List<domein.MvoDoelstelling> doelstellingen)
	{
		throw new UnsupportedOperationException();
	}
	
}
