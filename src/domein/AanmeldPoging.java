package domein;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Aanmeldpoging")
@IdClass(AanmeldPogingId.class)
@NamedQueries({
    @NamedQuery(name = "Aanmeldpoging.findByGebruikersnaam",
                         query = "select top 1 a from Aanmeldpoging a where a.gebruikersnaam = :gebruikersnaam")            
})
public class AanmeldPoging implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@OneToOne
	@JoinColumn(name = "gebruikersnaam")
	private Gebruiker gebruiker;
	
	@Id
	@Temporal(TemporalType.TIMESTAMP)
	private Date tijdstip;
	
	@Column(name = "isSuccesvol")
	private boolean success;

	private String rol;
	private String status;
	
	@Column(name = "aantalFoutievePogingen")
	private int poging;

	public AanmeldPoging(Gebruiker gebruiker, Date tijdstip, boolean success, String rol, String status, int poging) {
		this.gebruiker = gebruiker;
		this.tijdstip = tijdstip;
		this.success = success;
		this.rol = rol;
		this.status = status;
		this.poging = poging;
	}

	protected AanmeldPoging() {
	}
}
