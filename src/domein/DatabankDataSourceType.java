package domein;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("D")
public class DatabankDataSourceType extends TypeDatasource implements Serializable {
	private static final long serialVersionUID = 1L;
	private String hostname;
	private String username;
	private String password;

	protected DatabankDataSourceType() {

	}

	public DatabankDataSourceType(String hostname, String username, String password) {
		setHostname(hostname);
		setUsername(username);
		setPassword(password);
	}

	public String getLink() {
		return "geen link";
	}

	public String getHostname() {
		return hostname;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setHostname(String hostname) {
		if (hostname == null || hostname.isBlank() || hostname.isEmpty()) {
			throw new IllegalArgumentException("De hostnaam moet ingevuld zijn");
		}
		this.hostname = hostname;
	}

	public void setUsername(String username) {
		if (username == null || username.isBlank() || username.isEmpty()) {
			throw new IllegalArgumentException("De gebruikersnaam moet ingevuld zijn");
		}
		this.username = username;
	}

	public void setPassword(String password) {
		if (password == null || password.isBlank() || password.isEmpty()) {
			throw new IllegalArgumentException("Het wachtwoord moet ingevuld zijn");
		}
		this.password = password;
	}

	// List<Double>
	public List<Double> getData(int kolom) {
		// return leesAf();
		List<List<Double>> li = new ArrayList<>();
		li.add(Arrays.asList(9.9, 5.6, 7.8));
		li.add(Arrays.asList(5.6, 7.8, 8.6));
		li.add(Arrays.asList(3.4, 3.3, 7.8));

		return li.stream().map(e -> e.get(kolom - 1)).collect(Collectors.toList());
	}

	public List<Double> leesAf() {

		List<Double> eenKolom = new ArrayList<>();

		eenKolom.add(26.5);
		eenKolom.add(13.7);
		eenKolom.add(5.0);
		return eenKolom;
	}

	@Override
	public String toString() {
		return "databank";
	}

	@Override
	public int geefKolomLengte() throws IOException {
		return 3; // default returnwaarde voor databanktypes
	}
}
