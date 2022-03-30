package domein;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "SDG")
@NamedQueries({
		@NamedQuery(name = "sdGoal.findByNaam", query = "select s from domein.SdGoal s where s.naam = :naam order by s.afbeeldingnaam") })
public class SdGoal implements Serializable, ListViewInterface {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idSDG")
	private int sdGoalID;

	private String afbeeldingnaam;
	@Column(length = 4000)
	private String naam;
	private String icon;

	@ManyToOne
	private SdGoal parentSDG;

	@OneToMany(mappedBy = "sdGoal")
	private List<Component> componenten;

	public SdGoal(String naam) {
		this.naam = naam;
	}

	public SdGoal(String afbeeldingnaam, String naam) {
		this.afbeeldingnaam = afbeeldingnaam;
		this.naam = naam;
		this.icon = String.format("file:src/images/%s.jpg", afbeeldingnaam);
	}

	protected SdGoal() {

	}

	public int getId() {
		return this.sdGoalID;
	}

	public void setId(int id) {
		sdGoalID = id;
	}

	public String getAfbeeldingnaam() {
		return afbeeldingnaam;
	}

	public int getAfbeeldingNaamAlsInt() {
		return Integer.parseInt(afbeeldingnaam);
	}

	public void setAfbeeldingnaam(String afbeeldingnaam) {
		this.afbeeldingnaam = afbeeldingnaam;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public SdGoal getParentSDG() {
		return parentSDG;
	}

	public void setParentSDG(SdGoal parentSDG) {
		this.parentSDG = parentSDG;
	}

	public String getNaam() {
		return naam;
	}

	@Override
	public int hashCode() {
		return Objects.hash(naam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SdGoal)) {
			return false;
		}
		SdGoal other = (SdGoal) obj;
		return Objects.equals(naam, other.naam);
	}

	@Override
	public String toString() {
		return naam;
	}
}
