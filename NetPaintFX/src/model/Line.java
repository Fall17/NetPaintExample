// Andrew Marrufo

package model;

import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;

// Allows for the creation of lines using specific colors on a canvas.
public class Line extends PaintObject implements Serializable {

	// constructor
	public Line(Color color, Point point1, Point point2) {
		super(color, point1, point2);
	}

	// draws line on canvas
	@Override
	public void draw(GraphicsContext gc) {
		gc.setStroke(ColorTypeConverter.Awt2Fx(color));
		gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
	}
}
