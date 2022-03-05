package domein;
import java.util.List;

public interface Categorie {
	public int getCategorieID();
	public String getNaam();
	public String getIcon();
	public List<SdGoal> getSdGoals();
}
