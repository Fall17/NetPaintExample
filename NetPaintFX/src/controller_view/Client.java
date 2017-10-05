// Andrew Marrufo

package controller_view;

import java.awt.Color;
import java.awt.Point;
import java.util.Vector;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.ColorTypeConverter;
import model.Line;
import model.Oval;
import model.PaintObject;
import model.Picture;
import model.Rectangle;

/**
 * A GUI for Netpaint that has all paint objects drawn upon a Canvas. This file
 * also represents the controller as it controls how paint objects are drawn and
 * sends new paint objects to the server. All Client objects also listen to the
 * server to read the Vector of PaintObjects and repaint every time any client
 * adds a new one.
 * 
 * 
 */
public class Client extends Application {

	private Vector<PaintObject> allPaintObjects; // Vector to store PaintObjects
	private ColorPicker colorPicker; // ColorPicker for GUI
	private char currObjType; // keeps track of current object type for drawing
	private Color currColor; // keeps track of current color for drawing
	private boolean isClickedOnce; // keeps track of if anchor is currently in place (for EventHandlers)
	private Point start; // anchor point (first click)
	private Point end; // end point (second click)
	private Point currMousePos; // keeps track of current mouse position (for moveHandler, ghost images)
	private Canvas canvas; // canvas to draw everything on

	// main
	public static void main(String[] args) {
		launch(args);
	}

	// initializes GUI
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane all = new BorderPane();

		// create canvas for drawing
		BorderPane canvasPane = new BorderPane();
		canvas = new Canvas(800, 600);
		canvasPane.setCenter(canvas);
		all.setTop(canvasPane);

		// create GridPane for buttons and ColorPicker
		GridPane buttons = new GridPane();
		buttons.setHgap(10);
		buttons.setVgap(10);

		// create vector for storing paintObjects
		// also creates background (white rectangle that covers entire canvas)
		allPaintObjects = new Vector<>();
		allPaintObjects.add(new Rectangle(Color.WHITE, new Point(0,0), new Point(800, 600)));
		drawAllPaintObjects();
		currObjType = 'l';

		Scene scene = new Scene(all, 800, 650);

		// Construct 4 RadioButton objects
		RadioButton one = new RadioButton("Line");
		RadioButton two = new RadioButton("Rectangle");
		RadioButton three = new RadioButton("Oval");
		RadioButton four = new RadioButton("Picture");

		buttons.add(one, 8, 2);
		buttons.add(two, 16, 2);
		buttons.add(three, 24, 2);
		buttons.add(four, 32, 2);

		all.setCenter(buttons);

		// Construct a ToggleGroup so only one can be selected
		// and add the radio buttons to it
		ToggleGroup radioGroup = new ToggleGroup();
		one.setToggleGroup(radioGroup);
		two.setToggleGroup(radioGroup);
		three.setToggleGroup(radioGroup);
		four.setToggleGroup(radioGroup);

		// Add the ColorPicker that will be needed when the color is changed
		colorPicker = new ColorPicker(ColorTypeConverter.Awt2Fx(Color.BLACK));
		colorPicker.setOnAction(new ColorChanger());
		buttons.add(colorPicker, 40, 2);
		currColor = Color.BLACK;

		// Execute this code when the line RadioButon is clicked
		one.setOnAction(event -> {
			currObjType = 'l';
		});

		// Execute this code when the rectangle RadioButon is clicked
		two.setOnAction(event -> {
			currObjType = 'r';
		});

		// Execute this code when the oval RadioButon is clicked
		three.setOnAction(event -> {
			currObjType = 'o';
		});

		// Execute this code when the picture RadioButon is clicked
		four.setOnAction(event -> {
			currObjType = 'p';
		});

		// create clickHandler for anchoring and drawing shapes
		EventHandler<MouseEvent> handleClick = new clickHandler();
		canvas.setOnMouseClicked(handleClick);

		// create moveHandler for creating "ghost" images
		EventHandler<MouseEvent> handleMove = new moveHandler();
		canvas.setOnMouseMoved(handleMove);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// draws all paint objects stored so far.
	// clears canvas first, as to not make ghost images permanent, then
	// redraws all objects
	private void drawAllPaintObjects() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getWidth());
	    for (PaintObject po : allPaintObjects)
	      po.draw(gc);
	}

	// an event handler that gets the ColorPicker's current color
	private class ColorChanger implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent event) {
			currColor = ColorTypeConverter.Fx2Awt(colorPicker.getValue());
		}
	}

	// handles clicking (for anchoring and drawing)
	private class clickHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			// checks if there is no anchor
			if (!isClickedOnce) {
				start = new Point((int) event.getSceneX(), (int) event.getSceneY());
				// dummy PaintObject to be removed if the mouse cursor is not moved (such as to not remove
				// the last drawn object)
				allPaintObjects.add(new Line(currColor, new Point(0, 0), new Point(0, 0)));
				isClickedOnce = true;
			} 
			// if anchor is already in place
			else {
				end = new Point((int) event.getSceneX(), (int) event.getSceneY());
				// removes either last ghost image or dummy PaintObject
				allPaintObjects.remove(allPaintObjects.size()-1);
				// checks current object type
				if (currObjType == 'l') {
					allPaintObjects.add(new Line(currColor, start, end));
				} else if (currObjType == 'r') {
					allPaintObjects.add(new Rectangle(currColor, start, end));
				} else if (currObjType == 'o') {
					allPaintObjects.add(new Oval(currColor, start, end));
				} else {
					allPaintObjects.add(new Picture(start, end, "doge.jpeg"));
				}
				drawAllPaintObjects();
				isClickedOnce = false;
			}
		}
	}

	// handles mouse moving (for ghost images)
	private class moveHandler implements EventHandler<MouseEvent> {
		@Override
		public void handle(MouseEvent event) {
			// checks if there is no anchor
			if (!isClickedOnce) {
			} 
			// if anchor is already in place
			else {
				currMousePos = new Point((int) event.getSceneX(), (int) event.getSceneY());
				// removes last ghost image
				allPaintObjects.remove(allPaintObjects.size()-1);
				// checks current object type
				if (currObjType == 'l') {
					allPaintObjects.add(new Line(currColor, start, currMousePos));
				} else if (currObjType == 'r') {
					allPaintObjects.add(new Rectangle(currColor, start, currMousePos));
				} else if (currObjType == 'o') {
					allPaintObjects.add(new Oval(currColor, start, currMousePos));
				} else {
					allPaintObjects.add(new Picture(start, currMousePos, "doge.jpeg"));
				}
				drawAllPaintObjects();
			}
		}
	}
}