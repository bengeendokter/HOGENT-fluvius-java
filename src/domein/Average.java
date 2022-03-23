package domein;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AVG")
public class Average extends Bewerking{
	
	private static final long serialVersionUID = 1L;

	public Average() {
		
	}

	@Override
	public Map<String, Double> calculate(Map<String, Double> data) {
		Map<String, Double> map = new HashMap<>();
		map.put(toString(), data.values().stream().mapToDouble(Double::doubleValue).average().getAsDouble());
		return map;
	}
	
	@Override
	public String toString()
	{
		return "Gemiddelde";
	}
}