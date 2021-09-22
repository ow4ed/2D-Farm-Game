package pt.iul.ista.poo.farm.objects;

import pt.iul.ista.poo.farm.Farm;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Farmer extends FarmObject {

	public Farmer(Point2D position) {
		super(position , 3);
	}
	
	public boolean validPosition(Point2D position) {
//		Farm.getInstance().updatablesUpdate();
		if(!ImageMatrixGUI.getInstance().isWithinBounds(position) || !Farm.getInstance().freePosition(position)) {
			Farm.getInstance().updatablesUpdate();
			return false;
		}
		return true;
	}
	
}
