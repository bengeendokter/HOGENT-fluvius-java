package domein;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AanmeldPogingId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String gebruiker;
	private Date tijdstip;
	
	public AanmeldPogingId() {
	}

	public AanmeldPogingId(String gebruiker, Date tijdstip) {
		this.gebruiker = gebruiker;
		this.tijdstip = tijdstip;
	}

	@Override
	public int hashCode() {
		return Objects.hash(gebruiker, tijdstip);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AanmeldPogingId other = (AanmeldPogingId) obj;
		return Objects.equals(gebruiker, other.gebruiker) && Objects.equals(tijdstip, other.tijdstip);
	}

	
	
	

}
