package domein;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Aanmeldpoging")
@NamedQueries({
		@NamedQuery(name = "aanmeldpoging.findByGebruiker", query = "select entity from domein.AanmeldPoging entity where entity.gebruiker = :gebruiker order by entity.tijdstip desc")//         
})
public class AanmeldPoging implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int aanmeldID;
	
	@ManyToOne
	@JoinColumn(name = "gebruikerID")
	private Gebruiker gebruiker;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date tijdstip;
	
	@Column(name = "isSuccesvol")
	private boolean success;
	
	private String rol;
	private String status;
	
	@Column(name = "aantalFoutievePogingen")
	private int poging;
	
	public int getPoging()
	{
		return poging;
	}
	
	public AanmeldPoging(Gebruiker gebruiker, Date tijdstip, boolean success, String rol, String status, int poging)
	{
		this.gebruiker = gebruiker;
		this.tijdstip = tijdstip;
		this.success = success;
		this.rol = rol;
		this.status = status;
		this.poging = poging;
	}
	
	protected AanmeldPoging()
	{
	}
	
	@Override
	public String toString()
	{
		return "AanmeldPoging [gebruiker=" + gebruiker + ", tijdstip=" + tijdstip + ", success=" + success + ", rol="
				+ rol + ", status=" + status + ", poging=" + poging + "]";
	}
}
