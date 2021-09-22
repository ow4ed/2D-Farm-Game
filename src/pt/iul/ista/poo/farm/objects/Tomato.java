package pt.iul.ista.poo.farm.objects;

import java.io.Serializable;

import pt.iul.ista.poo.farm.Farm;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Tomato extends Vegetable implements Serializable{
	private static final int AMADURECER=15;
	private static final int APODRECER =25;
	private static final int PONTOS=3;
	private String name;
	private boolean tookCare;
	
    public Tomato(Point2D p) {
        super(p);
        name = Vegetables.small_tomato.toString();
        tookCare = false;
    }    
    
    @Override
    public String getName() {
    	return name;
    }
    
    @Override
    public void interact() {
    	if(Vegetables.isDone(this.name)) {
    		if (name.equals("tomato")) {
    			Farm.getInstance().setPoints(Farm.getInstance().getPoints() + PONTOS);
    		}
    		super.colher();
    	}
    	tookCare = true;
    }
    
    @Override
    public void update() {
    	if(tookCare == true && super.getStage()>=AMADURECER-1)
    		this.name = Vegetables.tomato.toString();
    	if(super.getStage() >= APODRECER-1)
    		this.name = Vegetables.bad_tomato.toString();
    }
    
    @Override
    public String toString() {
    	return this.name+super.getStage();
    }

	public void setName(String name) {
		this.name=name;
	}
	public String getTookCare() {
		if(tookCare == true){
			return "true";
		}
		else
			return "false";
	}
	public void setTookCare(String tookCare) {
		if(tookCare.equals("true")) {
			this.tookCare = true;
		}
	}

	public static void newTomato(Point2D point, int stage, String trueOrFalse) {
		Tomato tomato = new Tomato(point);
		tomato.setStage(stage);
		tomato.setTookCare(trueOrFalse);
		tomato.update();
		Farm.getInstance().getInteractables().add(tomato);
		ImageMatrixGUI.getInstance().addImage(tomato);
	}

}