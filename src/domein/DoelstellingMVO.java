package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("DMVO")
public class DoelstellingMVO extends MVODoelstellingComponent implements Doelstelling, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Attributen uit associaties
	private List<MVODoelstellingComponent> mvoDoelstellingComponents = new ArrayList<>();

	// Constructor
	public DoelstellingMVO(DTOMVODoelstelling d) {
		super(d);
	}
	
	protected DoelstellingMVO() {
		
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
