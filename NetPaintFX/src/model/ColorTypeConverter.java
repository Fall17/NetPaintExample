// Andrew Marrufo

package model;

import java.awt.Color;

// allows for the conversion of JavaFX colors to AWT colors and vice versa
public class ColorTypeConverter {
	
  // converts from JavaFX to AWT
  public static Color Fx2Awt(javafx.scene.paint.Color fxColor) {
    int r = (int) (255 * fxColor.getRed());
    int g = (int) (255 * fxColor.getGreen());
    int b = (int) (255 * fxColor.getBlue());
    java.awt.Color awtColor = new java.awt.Color(r, g, b);
    return awtColor;
  }

  // converts from AWT to JavaFX
  public static javafx.scene.paint.Color Awt2Fx(Color awtColor) {
    int r = awtColor.getRed();
    int g = awtColor.getGreen();
    int b = awtColor.getBlue();
    javafx.scene.paint.Color fxColor = javafx.scene.paint.Color.rgb(r, g, b); // , opacity); 
    return fxColor;
  }
}
