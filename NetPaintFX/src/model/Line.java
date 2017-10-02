package model;

import javafx.scene.paint.Color;
import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;

public class Line extends PaintObject {

	public Line(Color color, Point point1, Point point2) {
		super(color, point1, point2);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setStroke(color);
		gc.strokeLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
	}
}
