package exceptions;

public class GebruikerBestaatNietException extends IllegalArgumentException
{
	
	private static final long serialVersionUID = 1L;
	
	public GebruikerBestaatNietException()
	{
		super("De gegeven gebruikersnaam bestaat niet");
	}
	
	public GebruikerBestaatNietException(String s)
	{
		super(s);
	}
	
}
