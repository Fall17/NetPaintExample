package model;

import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;

public class Picture extends PaintObject {

	public Picture(Point point1, Point point2, String image) {
		super(point1, point2, image);
	}

	@Override
	public void draw(GraphicsContext gc) {
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
		gc.drawImage(image, lowX, lowY, width, height);
	}
}
