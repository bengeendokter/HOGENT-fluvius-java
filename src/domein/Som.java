package domein;

import java.util.List;
import java.util.stream.Collectors;

public class Som implements Bewerking{

	@Override
	public double calculate(List<Double> data) {
		return data.stream().mapToDouble(Double::doubleValue).sum();
	}

}