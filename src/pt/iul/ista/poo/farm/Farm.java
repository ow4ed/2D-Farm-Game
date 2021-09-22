package pt.iul.ista.poo.farm;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import pt.iul.ista.poo.farm.objects.Animal;
import pt.iul.ista.poo.farm.objects.Farmer;
import pt.iul.ista.poo.farm.objects.Interactable;
import pt.iul.ista.poo.farm.objects.InteractableComparator;
import pt.iul.ista.poo.farm.objects.Land;
import pt.iul.ista.poo.farm.objects.Rock;
import pt.iul.ista.poo.farm.objects.Updatable;
import pt.iul.ista.poo.farm.saveLoad.Load;
import pt.iul.ista.poo.farm.saveLoad.Save;
import pt.iul.ista.poo.farm.saveLoad.SaveLoadBin;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Farm implements Observer,Serializable {

	private static final String SAVE_FNAME = "config/savedGame";
	private static final String FARM_DIMENSIONS ="config/DimensoesQuinta";

	private static final int MIN_X = 5;
	private static final int MIN_Y = 5;
	private static Farm INSTANCE;

	private int max_x;
	private int max_y;
	private boolean space = false;
	private int points=0;
	private List<Interactable> landVegetablesAnimals = new ArrayList<Interactable>();
	private Farmer farmer = new Farmer(new Point2D(0,0));
	
	public List<Interactable> getInteractables() {
		return landVegetablesAnimals;
	}
	public void setInteractables(List<Interactable> list){
		landVegetablesAnimals=list;
	}
	
	public Farmer getFarmer() {
		return farmer;
	}
	public void setFarmer(Farmer f){
		this.farmer=f;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setPoints(int n) {
		points = n;
	}
	
	private Farm(int max_x, int max_y) {
		if (max_x < MIN_X || max_y < MIN_Y)
			throw new IllegalArgumentException();

		this.max_x = max_x;
		this.max_y = max_y;

		INSTANCE = this;

		ImageMatrixGUI.setSize(max_x, max_y);

		loadScenario();
	}

	private void registerAll() {
		ImageMatrixGUI.getInstance().addImage(farmer);
		Land.spawnAllland();
        Animal.spawn();
        ImageMatrixGUI.getInstance().setStatusMessage("Points: " +points);
        ImageMatrixGUI.getInstance().update();
    
	}

	private void loadScenario()  {
		Load.carregarDimensoes(FARM_DIMENSIONS);
		registerAll();
	}
	

	@Override
	public void update(Observable gui, Object a) {
		System.out.println("Update sent " + a);
		if (a instanceof Integer) {
			int key = (Integer) a;
			if(key == KeyEvent.VK_S) {
//				Save.gravar(SAVE_FNAME);
				SaveLoadBin.escreverObj("config/SaveBin");
				System.out.println("Jogo salvo");
			}
	        if(key==KeyEvent.VK_L) {
	        	try {
//	        		Load.carregarJogo(SAVE_FNAME);
	        		SaveLoadBin.lerObj("config/SaveBin");
	        	} catch (FileNotFoundException e) {
	        		System.out.println("Ficheiro do jogo salvo n�o encontrado");
	        	}
	        }
			if (Direction.isDirection(key) && !space && farmer.validPosition(farmer.getPosition().plus(Direction.directionFor(key).asVector()))) {
				System.out.println("Update is a Direction " + Direction.directionFor(key));
				farmer.setPosition(farmer.getPosition().plus(Direction.directionFor(key).asVector()));
				updatablesUpdate();
			}
			if (key == KeyEvent.VK_SPACE) {
				space = true;	
			}
			if (Direction.isDirection(key) && space && ImageMatrixGUI.getInstance().isWithinBounds(farmer.getPosition().plus(Direction.directionFor(key).asVector()))) {
				firstObjectToInteract(farmer.getPosition().plus(Direction.directionFor(key).asVector())).interact();
				space = false;
            }
		}

		ImageMatrixGUI.getInstance().setStatusMessage("Points: " + points);
		ImageMatrixGUI.getInstance().update();
	}
	
	private Interactable firstObjectToInteract(Point2D position){
		Comparator<Interactable> comparator = new InteractableComparator();
		PriorityQueue<Interactable> queue = new PriorityQueue<Interactable>(3, comparator);
		for(Interactable i : Farm.getInstance().getInteractables()) {
			if(i.getPosition().equals(position)) {
				queue.add(i);
			}	
		}
		return queue.peek();
	}

	public void updatablesUpdate() {
        List<Interactable> updatables = (landVegetablesAnimals.stream().filter(e -> e instanceof Updatable).collect(Collectors.toList()));      
        for (Interactable u : updatables) {
        	((Updatable) u).grow();
        	((Updatable) u).update();
        }
    }
	
	public boolean freePosition(Point2D position) {
		if(farmer.getPosition().equals(position))
			return false;
		for(Interactable i: landVegetablesAnimals) {
			if((i.getLayer() >=2 && i.getPosition().equals(position))) {
				return false;                   
			}
		}
		return true;
	}
	
	
	// N�o precisa de alterar nada a partir daqui
	private void play() {
		ImageMatrixGUI.getInstance().addObserver(this);
		ImageMatrixGUI.getInstance().go();
	}

	public static Farm getInstance() {
		assert (INSTANCE != null);
		return INSTANCE;
	}

	public static void main(String[] args) {
		//As dimensoes da quinta sao configuradas no ficheiro DimensoesQuinta na pasta config
		Farm f = new Farm(5, 5);
		f.play();
	}

}
