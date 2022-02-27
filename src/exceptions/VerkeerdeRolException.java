package exceptions;

public class VerkeerdeRolException extends IllegalArgumentException
{
	
	private static final long serialVersionUID = 1L;
	
	public VerkeerdeRolException()
	{
		super("Deze gebruiker heeft niet de juiste rol");
	}
	
	public VerkeerdeRolException(String s)
	{
		super(s);
	}
	
}
