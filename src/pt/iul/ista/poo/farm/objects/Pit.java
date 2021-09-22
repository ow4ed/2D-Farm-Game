package pt.iul.ista.poo.farm.objects;

import java.io.Serializable;

import pt.iul.ista.poo.utils.Point2D;

public class Pit extends FarmObject implements Interactable,Serializable{

	private static final int PRIORITY =4;
	
	public Pit(Point2D position) {
		super(position, 3);
	}

	@Override
	public void interact() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

}
