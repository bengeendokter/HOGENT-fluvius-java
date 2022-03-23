package domein;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("SOM")
public class Som extends Bewerking{
	
	public Som() {
		
	}
	

	@Override
	public Map<String, Double> calculate(Map<String, Double> data) {
		Map<String, Double> map = new HashMap<>();
		map.put(toString(), data.values().stream().mapToDouble(Double::doubleValue).sum());
		return map;
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName();
	}
}