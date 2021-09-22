package pt.iul.ista.poo.farm.objects;

import java.util.Comparator;

public class InteractableComparator implements Comparator<Interactable> {

    @Override
    public int compare(Interactable i1, Interactable i2) {
    	return i1.getPriority() - i2.getPriority();
    }
    
}