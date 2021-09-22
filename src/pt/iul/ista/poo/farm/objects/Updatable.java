package pt.iul.ista.poo.farm.objects;

public interface Updatable extends Interactable{
	
	void update();
	void grow();
	int  getStage();
	
}
