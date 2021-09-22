package pt.iul.ista.poo.farm.saveLoad;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import pt.iul.ista.poo.farm.Farm;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.utils.Point2D;

public class Load {
	public static void carregarJogo(String filename) throws FileNotFoundException {
		try {
			Scanner scanner = new Scanner(new File(filename));

			String line = scanner.nextLine();

			String dimensions[] = line.split(";");

			int saveWidth = Integer.valueOf(dimensions[0]);
			int saveHeight = Integer.valueOf(dimensions[1]);

			int gridWidth = (int) ImageMatrixGUI.getInstance().getGridDimension().getWidth();
			int gridHeight = (int) ImageMatrixGUI.getInstance().getGridDimension().getHeight();

			if (saveWidth != gridWidth || saveHeight != gridHeight) {
				scanner.close();
				System.out.println("Tamanhos não compatíveis");
				throw new IllegalStateException();
			} else {
				Farm.getInstance().getInteractables().clear();
				ImageMatrixGUI.getInstance().clearImages();
				line = scanner.nextLine();
				Farm.getInstance().setPoints(Integer.valueOf(line));
				line = scanner.nextLine();
				Farm.getInstance().getFarmer().setPosition(
						new Point2D(Integer.valueOf(line.substring(1, 2)), Integer.valueOf(line.substring(4, 5))));
				ImageMatrixGUI.getInstance().addImage(Farm.getInstance().getFarmer());
				
				while (scanner.hasNextLine()) {
					line = scanner.nextLine();
					String info[] = line.split(";");
					if (info.length == 2)
						ObjectsFactory.criar(info[0], new Point2D(Integer.valueOf(info[1].substring(1, 2)),
								Integer.valueOf(info[1].substring(4, 5))));
					if (info.length == 3)
						ObjectsFactory.criar(info[0], new Point2D(Integer.valueOf(info[1].substring(1, 2)),
								Integer.valueOf(info[1].substring(4, 5))), Integer.valueOf(info[2]));
					if (info.length == 4)
						ObjectsFactory.criar(info[0], new Point2D(Integer.valueOf(info[1].substring(1, 2)),
								Integer.valueOf(info[1].substring(4, 5))), Integer.valueOf(info[2]), info[3]);
				}
			}
			scanner.close();
			System.out.println("Jogo carregado com sucesso!");
		} catch (IllegalStateException e) {
			System.out.println("Jogo não carregado!");
		} catch (FileNotFoundException f){
			System.out.println("Ficheiro de jogo "+ filename +" não encontrado!");
		}
	}

	public static void carregarDimensoes(String filename) {
		Scanner scanner;
		try {
			scanner = new Scanner(new File(filename));
			String line = scanner.nextLine();
			String dimensions[] = line.split(" ");
			if(Integer.valueOf(dimensions[0])<5 || Integer.valueOf(dimensions[1])<5){
				throw new IllegalArgumentException();
			}	else{
			ImageMatrixGUI.setSize(Integer.valueOf(dimensions[0]),Integer.valueOf(dimensions[1]));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Ficheiro com as dimensoes da quinta nao encontrado ("+ filename +")" + ".A quinta vai ser iniciada com a dimensao 5x5");
		} catch(IllegalArgumentException e){
			System.out.println("As dimensoes minimas sao 5x5");
		}
	}
}
