package pt.iul.ista.poo.farm.objects;

import java.io.Serializable;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public abstract class FarmObject implements ImageTile,Serializable {

	private Point2D position;
	private int layer;

	public FarmObject(Point2D position, int layer) {
		this.position = position;
		this.layer = layer;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	@Override
	public String getName() {
		return getClass().getSimpleName().toLowerCase();
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		return layer;
	}
}
