package domein;

import java.io.IOException;
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
	public double getBerekendewaarde() throws IOException;

//	public boolean isLeaf();
//	public List<MVODatasource> getDatasources();
}