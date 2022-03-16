package domein;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AVG")
public class Average extends Bewerking{
	
	public Average() {
		
	}

	@Override
	public double calculate(List<Double> data) {
		return data.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
	}
	
	@Override
	public String toString()
	{
		return "Gemiddelde";
	}
}