package model;

import javafx.scene.paint.Color;
import java.awt.Point;

import javafx.scene.canvas.GraphicsContext;

public class Rectangle extends PaintObject {

	public Rectangle(Color color, Point point1, Point point2) {
		super(color, point1, point2);
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(color);
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
