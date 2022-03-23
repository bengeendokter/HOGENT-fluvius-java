package domein;

import java.util.Iterator;

public class NullIterator implements Iterator<Component> {
	@Override
	public Component next() {
		return null;
	}

	@Override
	public boolean hasNext() {
		return false;
	}
}
