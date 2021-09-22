package pt.iul.ista.poo.farm.saveLoad;

import pt.iul.ista.poo.farm.objects.Cabbage;
import pt.iul.ista.poo.farm.objects.Chicken;
import pt.iul.ista.poo.farm.objects.Egg;
import pt.iul.ista.poo.farm.objects.Land;
import pt.iul.ista.poo.farm.objects.Rock;
import pt.iul.ista.poo.farm.objects.Sheep;
import pt.iul.ista.poo.farm.objects.Tomato;
import pt.iul.ista.poo.utils.Point2D;

public class ObjectsFactory {
	
	public static void criar(String name,Point2D point) {
		if (name.equals("land") || name.equals("plowed"))
			Land.newLand(point, name);
		if (name.equals("rock"))
			Rock.newRock(point);
	}

	public static void criar(String name, Point2D point, Integer stage) {
		if (name.equals("chicken"))
			Chicken.newChicken(point, stage);
		if (name.equals("egg"))
			Egg.newEgg(point, stage);
		if (name.equals("sheep") || name.equals("famished_sheep"))
			Sheep.newSheep(point, stage);
		if (name.equals("small_cabbage") || name.equals("cabbage") || name.equals("bad_cabbage"))
			Cabbage.newCabbage(point, stage);
	}

	public static void criar(String name, Point2D point, Integer stage, String trueOrFalse) {
		Tomato.newTomato(point, stage, trueOrFalse);
	}

}
