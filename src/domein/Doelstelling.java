package domein;

import java.util.List;

public interface Doelstelling {

	public int getDoelstellingID();
	public String getNaam();
	public String getIcon();
	public double getDoelwaarde();
	public String getDoelstellingsType();
	public List<Rol> getRollen();
	public List<MVODatasource> getDatasources();
}
