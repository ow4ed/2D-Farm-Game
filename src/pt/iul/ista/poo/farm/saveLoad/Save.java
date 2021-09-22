package pt.iul.ista.poo.farm.saveLoad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import pt.iul.ista.poo.farm.Farm;
import pt.iul.ista.poo.farm.objects.Interactable;
import pt.iul.ista.poo.farm.objects.Tomato;
import pt.iul.ista.poo.farm.objects.Updatable;
import pt.iul.ista.poo.gui.ImageMatrixGUI;

public class Save {
	public static void gravar(String filename) {

		try {
			PrintWriter pw = new PrintWriter(new File(filename));
			pw.println((int) ImageMatrixGUI.getInstance().getGridDimension().getWidth() + ";"+ (int) ImageMatrixGUI.getInstance().getGridDimension().getHeight());
			pw.println(Farm.getInstance().getPoints());
			pw.println(Farm.getInstance().getFarmer().getPosition());

			for (Interactable a : Farm.getInstance().getInteractables()) {
				if(a instanceof Updatable) {
					if(a instanceof Tomato) 
						pw.println(a.getName()+";"+a.getPosition()+";"+((Updatable)a).getStage()+";"+((Tomato)a).getTookCare());
					else
						pw.println(a.getName() + ";" + a.getPosition() + ";" + ((Updatable)a).getStage());
				}
				else {// se for land
					pw.println(a.getName() + ";" + a.getPosition());
				}
			}
			pw.close();

		} catch (FileNotFoundException e) {
			System.out.println("Erro na abertura do ficheiro para escrita");
		}
	}

}
