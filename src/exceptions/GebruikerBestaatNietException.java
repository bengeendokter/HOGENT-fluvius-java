package exceptions;

public class GebruikerBestaatNietException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public GebruikerBestaatNietException() {
		super("Het gegeven gebruikersnaam bestaat niet");
	}

	public GebruikerBestaatNietException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	
	
}
