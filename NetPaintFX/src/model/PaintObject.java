package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

/**
 * PaintObject
 * 
 * PaintObject is the superclass of paint objects Oval, Rectangle, Line, and
 * Picture that can be drawn using a Color, two Points, and some Canvas methods.   
 * 
 * @author YOUR NAME
 *
 */
// Allows for the creation of PaintObjects on a canvas (PaintObjects include lines, ovals, rectangles, and images)
public abstract class PaintObject implements Serializable {
	
	Point point1;
	Point point2;
	Color color;
	Image image;
	
	// constructor for pictures
	public PaintObject(Point point1, Point point2, String image) {
		this.point1 = point1;
		this.point2 = point2;
		// picture you want to use must be stores in the images folder
		this.image = new Image("file:NetPaintFX/images/" + image);
	}
	
	// constructor for non-pictures
	public PaintObject(Color color, Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
		this.color = color;
	}

	public abstract void draw(GraphicsContext gc);
}