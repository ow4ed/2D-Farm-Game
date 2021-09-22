package pt.iul.ista.poo.farm.objects;

import java.io.Serializable;

import pt.iul.ista.poo.farm.Farm;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Land extends FarmObject implements Interactable,Serializable {
	private static final int PROB_ROCK = 1;// entre 0 e 10
	private static final int PRIORITY =4;

	private String name;

	public Land(Point2D p) {
		super(p, 0);
		name = "land";
	}
	
	public static void newLand(Point2D point, String name) {
		Land land = new Land(point);
		if(!name.equals("land"))
			land.interact();
		Farm.getInstance().getInteractables().add(land);
		ImageMatrixGUI.getInstance().addImage(land);
	}

	public static void spawnAllland() {
		for (int i = 0; i < ImageMatrixGUI.getInstance().getGridDimension().getWidth(); i++) {
			for (int j = 0; j < ImageMatrixGUI.getInstance().getGridDimension().getHeight(); j++) {
				spawn(new Point2D(i, j));
			}
		}
	}

	private static void spawn(Point2D position) {
		int n = (int) (Math.random() * 10);
		if (n < PROB_ROCK) 
			Rock.newRock(position);
		else
			Land.newLand(position, "land");
	}
	
	private void reset() {
		name = "land";
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void interact() {
		if (name.equals("land")) {
			name="plowed";
		}
		else {
			Vegetable.spawn(super.getPosition());
		}
	}

	public static void clean(Point2D position){
		for(Interactable i: Farm.getInstance().getInteractables()){
			if(i.getPosition().equals(position) && i.getName().equals("plowed")){
				((Land)i).reset();
			}
		}
	}
	@Override
	public int getPriority() {
		return PRIORITY;
	}
	
}
