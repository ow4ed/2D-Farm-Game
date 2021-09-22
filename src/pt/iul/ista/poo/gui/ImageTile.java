package pt.iul.ista.poo.gui;

import pt.iul.ista.poo.utils.Point2D;

public interface ImageTile {

	/**
	 * The name of the image. Must correspond to the name of an image file in
	 * the "images" folder otherwise i will trigger one of the following
	 * exceptions: FileNotFoundException, IllegalArgumentException
	 * 
	 * @return The name of the image file containing the desired image (without
	 *         extention)
	 * 
	 */
	String getName();

	/**
	 * Getter for the position (in grid coordinates) were the image should be
	 * placed.
	 * 
	 * @return A position.
	 */
	Point2D getPosition();

	/**
	 * Getter for the level of the image, higher levels are displed on top of
	 * lower ones (level 0 will be the first images to be displayed, the higher
	 * the level the later in the rendering the image will be added).
	 * 
	 * @return A position.
	 */
	int getLayer();

}
