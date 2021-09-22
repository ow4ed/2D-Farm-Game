package pt.iul.ista.poo.farm.objects;

import java.io.Serializable;
import java.util.Random;

import pt.iul.ista.poo.farm.Farm;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Vegetable extends FarmObject implements Interactable, Updatable, Serializable{
	
	private static final int PRIORITY = 3;
	private int stage;

	public Vegetable(Point2D p) {
		super(p, 1);
		stage = -1;
	}

	public static void spawn(Point2D p) { 
		Random r = new Random();
		if (r.nextInt(2) == 0) 
			Tomato.newTomato(p, -1, "false");
		else 
			Cabbage.newCabbage(p, -1);
	}
	
	public void colher() { 
		Farm.getInstance().getInteractables().remove(this);
		ImageMatrixGUI.getInstance().removeImage(this);
		Land.clean(super.getPosition());
	}
	
	public static void remove(Point2D position){
		for(Interactable i: Farm.getInstance().getInteractables()){
			if(i.getPosition().equals(position) && Vegetables.isAnVegetal(i.getName())){
				Farm.getInstance().getInteractables().remove(i);
				ImageMatrixGUI.getInstance().removeImage(i);
				Land.clean(position);
				return;
			}
		}
	}
	
	public void setStage(int stage) {
		this.stage = stage;
	}

	@Override
	public int getStage() {
		return stage;
	}

	@Override
	public void grow() {
		this.stage++;
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}

}
