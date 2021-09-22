package pt.iul.ista.poo.farm.objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import pt.iul.ista.poo.farm.Farm;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public abstract class Animal extends FarmObject implements Interactable, Updatable,Serializable{
	
	private static final int NUMERO_ANIMAIS_DE_CADA_ESPECIE=2;
	private static final int PRIORITY = 1;
	
	public Animal(Point2D position) {
		super(position, 3);
	}
	
	@Override
	public int getPriority() {
		return PRIORITY;
	}
	
	public static void spawn() {
        for(int i =0; i!=NUMERO_ANIMAIS_DE_CADA_ESPECIE; i++) {
        	Sheep.newSheep(randomPosition(), 0);
        	Chicken.newChicken(randomPosition(), 0);
        } 
    }
	
	public static Point2D randomPosition() {
		Point2D position = null;
        do{
        	int c = randomWithRange(0, (int) ImageMatrixGUI.getInstance().getGridDimension().getWidth()-1);
        	int d = randomWithRange(0, (int) ImageMatrixGUI.getInstance().getGridDimension().getHeight()-1);
        	position = new Point2D(c, d);
        }while(!Farm.getInstance().freePosition(position));
        return position;
    }
	
	private static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }	

	public Point2D randomAvailablePosition() {
		List<Point2D> availablePositions = new ArrayList<Point2D>();
		for(Point2D position : Direction.getNeighbourhoodPoints(super.getPosition())) {
			if(Farm.getInstance().freePosition(position) && ImageMatrixGUI.getInstance().isWithinBounds(position)) {
				availablePositions.add(position);
			}
		}
		if(!availablePositions.isEmpty())
			return availablePositions.get((int)(Math.random()*availablePositions.size()));
		return null;
	}

}
