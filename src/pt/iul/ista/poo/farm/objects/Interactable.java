package pt.iul.ista.poo.farm.objects;

import pt.iul.ista.poo.gui.ImageTile;

public interface Interactable extends ImageTile{
	
	void interact();
	int getPriority();
	
} 
