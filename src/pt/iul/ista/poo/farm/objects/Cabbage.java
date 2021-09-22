package pt.iul.ista.poo.farm.objects;

import java.io.Serializable;

import pt.iul.ista.poo.farm.Farm;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;
/** Representa uma couve numa grelha2D 
 * 
 * @author Nichal e Stefan
 *
 */
public class Cabbage extends Vegetable implements Serializable {
	/**
	 * Número de ciclos que a couve precisa para amadurecer
	 */
	private static final int AMADURECER = 10;
	/**
	 * Número de ciclos que a couve precisa para apodrecer
	 */
	private static final int APODRECER =30;
	/**
	 * Número de pontos ao colher a couve madura
	 */
	private static final int PONTOS=2;
	/**
	 * Estado da couve (pequena, madura, podre)
	 */
	private String name;
	/** Cria uma nova couve com base num Point2D
	 * 
	 * 
	 * @param p Point2D 
	 */
    public Cabbage(Point2D p) {
        super(p);
        name = Vegetables.small_cabbage.toString();
    }
    /**Permite criar uma couve e ao mesmo tempo definir um ciclo para a mesma.
	 * Utilizado para o carregamento de um ficheiro gravado.
	 * 
	 * @param point posicao
	 * @param stage	ciclo
	 */
	public static void newCabbage(Point2D point, int stage) {
		Cabbage cabbage = new Cabbage(point);
		cabbage.setStage(stage);
		cabbage.update();
		Farm.getInstance().getInteractables().add(cabbage);
		ImageMatrixGUI.getInstance().addImage(cabbage);
	}
    /**Devolve o estado da couve
     * 
     * @return name estado
     */
    @Override
    public String getName() {
    	return name;
    }
    /**
     * Efetua a interacao com a couve
     */
    @Override
    public void interact() {
    	if(Vegetables.isDone(this.name)) {
    		if (name.equals("cabbage")) {
    			Farm.getInstance().setPoints(Farm.getInstance().getPoints() + PONTOS);
    		}	
    		super.colher();
    	}
    	super.setStage(super.getStage()+1);
    }
    /**
     * Atualiza o ciclo da couve após cada ciclo de jogo, para poder amadurecer e apodrecer
     */
    @Override
    public void update() {
    	if(super.getStage() >=APODRECER-1 && this.name.equals("small_cabbage")){// para quando for carregado o jogo
    		this.name="bad_cabbage";
    	}
    	if((super.getStage() >= AMADURECER-1  && this.name.equals(Vegetables.small_cabbage.toString())) || (super.getStage() >= APODRECER-1 && this.name.equals(Vegetables.cabbage.toString()))) {
    		this.name = Vegetables.nextVegetal(this.name);
		}
    }
    
    @Override
    public String toString() {
    	return this.name+super.getStage();
    }
    /**Permite atualizar o estado da couve
     * 
     * @param name estado
     */
	public void setName(String name) {
		this.name=name;
	}
     
}
