package domein;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Gebruiker")
@NamedQueries({
		@NamedQuery(name = "gebruiker.findByNaam", query = "select g from domein.Gebruiker g where g.gebruikersnaam = :naam"),
		@NamedQuery(name = "gebruiker.findById", query = "select g from domein.Gebruiker g where g.gebruikerID = :id")})
public class Gebruiker implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gebruikerID;
	
	@Column(unique = true)
	private String gebruikersnaam;
	private String wachtwoord;
	private String rol;
	private String status;
	private String salt = null;
	
	public Gebruiker(String gebruikersnaam, String wachtwoord, String rol, String status)
	{
		this.gebruikersnaam = gebruikersnaam;
		SecureRandom random = new SecureRandom();
		byte[] array = new byte[16];
		random.nextBytes(array);
		
		salt = new String(array, Charset.forName("UTF-8"));
	
		this.wachtwoord = PasswordHasher.hash(wachtwoord,getSalt());
		this.rol = rol;
		this.status = status;
		
	}
	
	protected Gebruiker()
	{
		
	}
	
	public boolean controleerWachtwoord(String wachtwoord)
	{
		return this.wachtwoord.equals(wachtwoord);
	}
	
	public String getGebruikersnaam()
	{
		return gebruikersnaam;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public String getRol()
	{
		return rol;
	}
	
	public byte[] getSalt()
	{
		return salt.getBytes();
	}
	
	@Override
	public int hashCode()
	{
		return Objects.hash(gebruikersnaam);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Gebruiker other = (Gebruiker) obj;
		return Objects.equals(gebruikersnaam, other.gebruikersnaam);
	}
	
	@Override
	public String toString()
	{
		return "Gebruiker [gebruikersnaam=" + gebruikersnaam + ", status=" + status + ", rol=" + rol + "]";
	}
	
}
