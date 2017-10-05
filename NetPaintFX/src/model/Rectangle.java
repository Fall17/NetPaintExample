// Andrew Marrufo

package model;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;

// Allows for the creation of rectangles using specific colors on a canvas.
public class Rectangle extends PaintObject implements Serializable {

	// constructor
	public Rectangle(Color color, Point point1, Point point2) {
		super(color, point1, point2);
	}

	// draws rectangle on canvas
	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(ColorTypeConverter.Awt2Fx(color));
		// the purpose of these variables is to allow for the anchor point of a PaintObject
		// to not have to be in the top right-hand corner of the image (so that the user can draw
		// the object however they'd like)
		double highX;
		double lowX;
		double highY;
		double lowY;
		if(point1.getX() > point2.getX()) {
			highX = point1.getX();
			lowX = point2.getX();
		}
		else {
			highX = point2.getX();
			lowX = point1.getX();
		}
		if(point1.getY() > point2.getY()) {
			highY = point1.getY();
			lowY = point2.getY();
		}
		else {
			highY = point2.getY();
			lowY = point1.getY();
		}
		double width = highX - lowX;
		double height = highY - lowY;
		gc.fillRect(lowX, lowY, width, height);
	}
}
