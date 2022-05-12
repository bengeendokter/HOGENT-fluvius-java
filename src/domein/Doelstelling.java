package domein;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Doelstelling extends ListViewInterface {

	public int getDoelstellingID();
	public String getNaam();
	public String getIcon();
	public double getDoelwaarde();
	public Bewerking getFormule();
	public List<Rol> getRollen();
	public boolean isMax();
	public SdGoal getSdGoal();
	List<Component> getComponents();
	public Map<String, Double> getBerekendewaarde() throws IOException;
	public Composite getParentComponent();
	public MVODatasource getDatasource();
	public String getEenheid();
	public int getJaar();
	public void remove(Component mvocomponent);
}