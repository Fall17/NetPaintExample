package model;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.awt.Point;

/**
 * PaintObject
 * 
 * PaintObject is the superclass of paint objects Oval, Rectangle, Line, and
 * Picture that can be drawn using a Color, two Points, and some Canvas methods.   
 * 
 * @author YOUR NAME
 *
 */
public abstract class PaintObject {
	
	Point point1;
	Point point2;
	Color color;
	Image image;
	
	public PaintObject(Point point1, Point point2, String image) {
		this.point1 = point1;
		this.point2 = point2;
		this.image = new Image("file:NetPaintFX/images/" + image);
	}
	
	public PaintObject(javafx.scene.paint.Color color, Point point1, Point point2) {
		this.point1 = point1;
		this.point2 = point2;
		this.color = color;
	}

	public abstract void draw(GraphicsContext gc);
}