package exceptions;

public class OngeldigeWachtwoordException extends IllegalArgumentException
{
	
	private static final long serialVersionUID = 1L;
	
	public OngeldigeWachtwoordException()
	{
		super("Het gegeven wachtwoord is onjuist");
	}
	
	public OngeldigeWachtwoordException(String s)
	{
		super(s);
	}
	
}
