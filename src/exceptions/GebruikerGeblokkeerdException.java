package exceptions;

public class GebruikerGeblokkeerdException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public GebruikerGeblokkeerdException() {
		super("Gebruiker is geblokkeerd");
	}

	public GebruikerGeblokkeerdException(String s) {
		super(s);
	}
	
	

}
