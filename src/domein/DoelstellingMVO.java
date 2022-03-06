package domein;

import java.util.ArrayList;
import java.util.List;


public class DoelstellingMVO extends MVODoelstellingComponent implements Doelstelling{
	
	// Attributen uit associaties
	private List<MVODoelstellingComponent> mvoDoelstellingComponents = new ArrayList<>();

	// Constructor
	public DoelstellingMVO(DTOMVODoelstelling d) {
		super(d);
	}
	
	// Typische component methodes
	public void add(MVODoelstellingComponent mvocomponent) {
		mvoDoelstellingComponents.add(mvocomponent);
    }

    public void remove(MVODoelstellingComponent mvocomponent) {
    	mvoDoelstellingComponents.remove(mvocomponent);
    }

    public MVODoelstellingComponent getChild(int i) {
        return mvoDoelstellingComponents.get(i);
    }
    
    
}
