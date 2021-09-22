package pt.iul.ista.poo.farm.objects;

import java.io.Serializable;

import pt.iul.ista.poo.farm.Farm;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Egg extends FarmObject implements Updatable, Interactable,Serializable {

	private static final int BIRTH= 20;
	private static final int PRIORITY = 2 ;
	private static final int POINTS = 1;
	private int stage;
	
	public Egg(Point2D position) {
		super(position, 2);
		stage=0;
	}
	
	public static void newEgg(Point2D point, int stage) {
		Egg egg = new Egg(point);
		egg.setStage(stage);
		Farm.getInstance().getInteractables().add(egg);
		ImageMatrixGUI.getInstance().addImage(egg);
	}

	@Override
	public void interact() {
		Farm.getInstance().getInteractables().remove(this);
		ImageMatrixGUI.getInstance().removeImage(this);
		Farm.getInstance().setPoints(Farm.getInstance().getPoints() + POINTS);
	}

	@Override
	public void update() {
		if (stage == BIRTH) {
			Chicken.newChicken(this.getPosition(), 0);
			Farm.getInstance().getInteractables().remove(this);
			ImageMatrixGUI.getInstance().removeImage(this);
        }
	}

	@Override
	public void grow() {
		stage++;
	}

	@Override
	public int getStage() {
		return stage;
	}

	@Override
	public int getPriority() {
		return PRIORITY;
	}

	public void setStage(int stage) {
		this.stage=stage;
	}
	
}
