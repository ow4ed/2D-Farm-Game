package pt.iul.ista.poo.farm.saveLoad;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import pt.iul.ista.poo.farm.Farm;
import pt.iul.ista.poo.farm.objects.Farmer;
import pt.iul.ista.poo.farm.objects.Interactable;
import pt.iul.ista.poo.gui.ImageMatrixGUI;

public class SaveLoadBin {
	public static void escreverObj(String filename) {

		try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {
			output.writeObject(ImageMatrixGUI.getInstance().getGridDimension());
			output.writeObject(Farm.getInstance().getPoints());
			output.writeObject(Farm.getInstance().getInteractables());
			output.writeObject(Farm.getInstance().getFarmer());

		} catch (IOException e) {
			System.out.println("Problemas com a escrita do ficheiro.");
		}
	}

	public static void lerObj(String nomeFicheiro) throws FileNotFoundException {

		try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(nomeFicheiro))) {
			if (ImageMatrixGUI.getInstance().getGridDimension().toString().compareTo(input.readObject().toString()) != 0)
				throw new IllegalStateException();
			else {
				Farm.getInstance().setPoints(Integer.valueOf(input.readObject().toString()));
				@SuppressWarnings("unchecked")
				List<Interactable> interactablesgravados = (List<Interactable>) input.readObject();
				Farm.getInstance().getInteractables().clear();
				ImageMatrixGUI.getInstance().clearImages();
				Farm.getInstance().setInteractables(interactablesgravados);
				Farmer savedFarmer= (Farmer)input.readObject();
				Farm.getInstance().setFarmer(savedFarmer);
				ImageMatrixGUI.getInstance().addImage(savedFarmer);
				for (Interactable i : Farm.getInstance().getInteractables())
					ImageMatrixGUI.getInstance().addImage(i);
			}

		} catch (IOException e) {
			System.out.println("Problemas com a leitura do ficheiro.");

		} catch (ClassNotFoundException e) {
			System.out.println("Problemas com o formato dos dados - classe de objectos não encontrada.");
		} catch (IllegalStateException e) {
			System.out.println("Tamanhos incompatíveis");
		}

	}

}
