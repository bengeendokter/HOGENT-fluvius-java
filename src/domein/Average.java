package domein;

import java.util.List;

public class Average implements Bewerking{

	@Override
	public double calculate(List<Double> data) {
		return data.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
	}

}