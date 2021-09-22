package pt.iul.ista.poo.farm.objects;

import java.io.Serializable;

import pt.iul.ista.poo.farm.Farm;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Rock extends FarmObject implements Interactable,Serializable {
	private static final int PRIORITY =4;

	public Rock(Point2D position) {
		super(position,3);
	}

	@Override
	public void interact() {
		// remove rock object from game
		for(Interactable i: Farm.getInstance().getInteractables()){
			if(i.getPosition().equals(super.getPosition())){
				Farm.getInstance().getInteractables().remove(i);
				ImageMatrixGUI.getInstance().removeImage(i);
				break;
			}
		}
		
		// create pit
		Pit pit = new Pit(super.getPosition());
		Farm.getInstance().getInteractables().add(pit);
		ImageMatrixGUI.getInstance().addImage(pit);
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}

	public static void newRock(Point2D point) {
		Rock rock = new Rock(point);
		Farm.getInstance().getInteractables().add(rock);
		ImageMatrixGUI.getInstance().addImage(rock);
	}

}
