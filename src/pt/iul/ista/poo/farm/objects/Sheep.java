package pt.iul.ista.poo.farm.objects;

import java.io.Serializable;

import pt.iul.ista.poo.farm.Farm;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Sheep extends Animal implements Serializable{
	
	private static final int PROCURAR_COMIDA = 10;// apos estes ciclos a ovelha começa a procurar comida
    private static final int FAMINTA = 20;// apos estes ciclos a ovelha para de andar e fica faminta
    private static final int PONTOS_POR_CICLO=1;
    private int naoAlimentacao;
    private String name;

    public Sheep(Point2D position) {
        super(position);
        this.name = "sheep";
        this.naoAlimentacao = 0;
    }
    
    public static void newSheep(Point2D point, Integer stage) {
		Sheep sheep = new Sheep(point);
		sheep.setStage(stage);
		sheep.updateTheName();
		Farm.getInstance().getInteractables().add(sheep);
		ImageMatrixGUI.getInstance().addImage(sheep);
	}

    @Override
    public void interact() {
        this.naoAlimentacao = 0;
        if(this.name!="sheep")
        	this.name="sheep";
    }

    @Override
    public void grow() {
    	naoAlimentacao++;
    }

    @Override
    public int getStage() {
        return naoAlimentacao;
    }

    @Override
    public void update() {
    	if(naoAlimentacao < PROCURAR_COMIDA && naoAlimentacao>0)
    		Farm.getInstance().setPoints(Farm.getInstance().getPoints() + PONTOS_POR_CICLO);
    	
        if (naoAlimentacao > PROCURAR_COMIDA && naoAlimentacao < FAMINTA) 
            tryToMoveAndEat();
        
        updateTheName();
    }
    private void updateTheName(){
    	if (naoAlimentacao > FAMINTA && name.equals("sheep"))
            name = "famished_sheep";
    }
    
    private void tryToMoveAndEat() {
    	Point2D nextPosition = super.randomAvailablePosition();
    	if(isThereAnVegetal(nextPosition)) {
    		naoAlimentacao = 0;
    		Vegetable.remove(nextPosition);
        	return;
    	}
    	 if(nextPosition!=null) {
 			super.setPosition(nextPosition);
 		}	

    }
    
    private boolean isThereAnVegetal(Point2D position) {
    	if(position == null)
    		return false;
    	for(Interactable i :Farm.getInstance().getInteractables()){
    		if(i.getPosition().equals(position) && Vegetables.isAnVegetal(i.getName())) {
    			return true;
    		}
    	}
    	return false;
    }

    @Override
    public String getName() {
        return name;
    }
    public void setStage(int i) {
    	naoAlimentacao=i;
    }

	public void setName(String name) {
		this.name=name;	
	}

	

}