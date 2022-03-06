package domein;

import java.util.List;

public interface Doelstelling {

	public String getNaam();
	public String getIcon();
	public double getDoelwaarde();
	public String getType();
	public List<Rol> getRollen();
	public List<MVODatasource> getDatasources();
}
