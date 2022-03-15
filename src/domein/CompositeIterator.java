package domein;

import java.util.*;
import java.util.Iterator;

import javafx.collections.ObservableList;

public class CompositeIterator implements Iterator<Component> {
    private Stack<Iterator<Component>> stack = new Stack<>();
    //parameter : de iterator van de rootcompositie waardoor we gaan itereren
    public CompositeIterator(Iterator<Component> iterator) {
        stack.push(iterator);
    }
    
    public Component next() {
        if (hasNext()) { //Is er wel een volgend element?
            //haal lopende iterator van de stack en ga naar volgend element
            Iterator<Component> iterator = stack.peek();
            Component component = iterator.next();
            //Is dit element een Menu, dan moet die compositie in de iteratie worden opgenomen
            //Dus zetten we het op de stack (en zullen we sowieso later verwerken)
            if (component instanceof Composite) {
                stack.push(component.createIterator());
            }
            return component;
        } else {     return null;      }    } // er isgeen volgend element

    @Override
    public boolean hasNext() {
        if (stack.empty()) { //is er een volgend element? Niet als de stack leeg is
            return false;
        } else {
            //Haal iterator van de top van de stack 
            Iterator<Component> iterator = stack.peek();
            if (!iterator.hasNext()) { //en kijk of er een volgend element is
                //zo niet, dan poppen we hem van de stack en roepen hasNext() recursief aan
                stack.pop();
                return hasNext();
            } else { //er is een volgend element
                return true;
            }
        }
    }
}
