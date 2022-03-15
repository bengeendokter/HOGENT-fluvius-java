package domein;

import java.util.List;

public interface Doelstelling {

	public int getDoelstellingID();
	public String getNaam();
	public String getIcon();
	public double getDoelwaarde();
	public Bewerking getFormule();
	public List<Rol> getRollen();
	public SdGoal getSdGoal();
	List<Component> getComponents();

//	public boolean isLeaf();
//	public List<MVODatasource> getDatasources();
}