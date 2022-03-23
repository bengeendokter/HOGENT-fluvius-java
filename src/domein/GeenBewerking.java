package domein;

import java.util.Map;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("GEENBEWERKING")
public class GeenBewerking extends Bewerking{

	private static final long serialVersionUID = 1L;

	public GeenBewerking() {
		
	}
	
	@Override
	public Map<String, Double> calculate(Map<String, Double> data) {
		
		return data;
	}

	@Override
	public String toString() {
		return "Geen Bewerking";
	}

}
