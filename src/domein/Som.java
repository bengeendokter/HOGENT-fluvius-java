package domein;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("SOM")
public class Som extends Bewerking{
	
	public Som() {
		
	}
	

	@Override
	public double calculate(List<Double> data) {
		return data.stream().mapToDouble(Double::doubleValue).sum();
	}
	
	@Override
	public String toString()
	{
		return this.getClass().getSimpleName();
	}
}