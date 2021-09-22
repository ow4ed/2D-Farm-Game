package pt.iul.ista.poo.farm.objects;

import java.io.Serializable;

import pt.iul.ista.poo.farm.Farm;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Chicken extends Animal implements Serializable{

	private static final int PLACE_EGG=10;
	private static final int POINTS=2;
    private boolean moveOrNot;
    private int withoutEgg;

    public Chicken(Point2D position) {
        super(position);
        this.withoutEgg = 0;
        this.moveOrNot = true;
    }
    
    public static void newChicken(Point2D point, int stage) {
		Chicken chicken = new Chicken(point);
		chicken.setStage(stage);
		Farm.getInstance().getInteractables().add(chicken);
		ImageMatrixGUI.getInstance().addImage(chicken);	
	}

    @Override
    public void interact() {
        Farm.getInstance().getInteractables().remove(this);
        ImageMatrixGUI.getInstance().removeImage(this);
        Farm.getInstance().setPoints(Farm.getInstance().getPoints() + POINTS);
    }

    @Override
    public void grow() {
    	withoutEgg++;
    }

    @Override
    public int getStage() {
        return withoutEgg;
    }
    
    public void setStage(int stage) {
		withoutEgg=stage;
	}

    @Override
    public void update(){
        if(withoutEgg>=PLACE_EGG && !hasEgg(super.getPosition()) && randomAvailablePosition()!=null) {
            Egg.newEgg(randomAvailablePosition(),0);
            tryToMove();
            withoutEgg=0;
        }
        else 
            tryToMove();
    }

    public boolean hasEgg(Point2D position) {
        for (Interactable u : Farm.getInstance().getInteractables()) {
            if (u.getLayer() == 2 && u.getName().equals("egg") && u.getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    public void tryToMove() {
        if (this.moveOrNot == true) {
            Point2D nextPosition = super.randomAvailablePosition();
            if(isThereAnTomato(nextPosition)) {	
            	this.moveOrNot = false;
            	Vegetable.remove(nextPosition);
            	return;
            }
            if(nextPosition!=null) {
    			super.setPosition(nextPosition);
    			this.moveOrNot = false;
    		}	
        } 
        else {
            this.moveOrNot = true;
        }
    }
    
    private boolean isThereAnTomato(Point2D position) {
    	if(position == null)
    		return false;
    	for(Interactable i :Farm.getInstance().getInteractables()){
    		if(i.getPosition().equals(position) && Vegetables.isAnTomato(i.getName())) {
    			return true;
    		}
    	}
    	return false;
    }

}

