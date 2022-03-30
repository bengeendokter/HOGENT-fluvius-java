package domein;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Categorie")
@NamedQueries({
		@NamedQuery(name = "categorie.findByNaam", query = "select c from domein.SDGCategorie c where c.naam = :naam") })
public class SDGCategorie implements Serializable, Categorie {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int categorieID;

	@Column(unique = true)
	private String naam;

	private String icon;

	@OneToMany
	@JoinColumn(name = "CATID", nullable = true, foreignKey = @ForeignKey(name = "FK_SDG_ID", foreignKeyDefinition = "FOREIGN KEY (CATID) REFERENCES SDGOAL(idSDG) ON UPDATE CASCADE ON DELETE CASCADE"))
	private List<SdGoal> sdGoals;

	protected SDGCategorie() {

	}

	public SDGCategorie(DTOCategorie dca) {
		setNaam(dca.naam);
		setIcon(dca.icon);
		wijzigSdGoals(dca.sdgoals);
	}

	public void setCategorieID(int mock) {
		this.categorieID = mock;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getCategorieID() {
		return categorieID;
	}

	public final void setNaam(String naam) {
		if (naam == null || naam.isBlank()) {
			throw new IllegalArgumentException("De naam van de Categorie mag niet leeg zijn");
		}

		this.naam = naam;
	}

	public String getNaam() {
		return naam;
	}

	public String getIcon() {
		return icon;
	}

	public List<SdGoal> getSdGoals() {
		return Collections.unmodifiableList(sdGoals);
	}

	public void wijzigSdGoals(List<SdGoal> sdGoals) {
		if (sdGoals.isEmpty() || sdGoals == null) {
			throw new IllegalArgumentException("Een Categorie moet minstens 1 SdGoal hebben");
		}
		System.out.println(sdGoals);

		this.sdGoals = sdGoals;
		System.out.println(sdGoals);

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
		if (!(obj instanceof SDGCategorie)) {
			return false;
		}
		SDGCategorie other = (SDGCategorie) obj;
		return Objects.equals(naam, other.naam);
	}

	@Override
	public String toString() {
		return naam;
	}
}