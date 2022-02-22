package domein;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Gebruiker")
public class Gebruiker implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String gebruikersnaam;
	private String wachtwoord;
	private String rol;
	private String status;

	
	
	public boolean controleerWachtwoord(String wachtwoord) {
		return this.wachtwoord.equals(wachtwoord);
	}
	
	
	public String getWachtwoord() {
		return wachtwoord;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getRol() {
		return rol;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(gebruikersnaam);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gebruiker other = (Gebruiker) obj;
		return Objects.equals(gebruikersnaam, other.gebruikersnaam);
	}
	
	@Override
	public String toString() {
		return "Gebruiker [gebruikersnaam=" + gebruikersnaam + ", status=" + status
				+ ", rol=" + rol + "]";
	}

}
