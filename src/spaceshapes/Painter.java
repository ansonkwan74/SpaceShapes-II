package spaceshapes;

import java.awt.Color;
import java.awt.Image;

/** 
 * Interface to represent a type that offers primitive drawing methods.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public interface Painter {
	/**
	 * Draws a rectangle. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawRect(int x, int y, int width, int height);
	
	/**
	 * Draws an oval. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawOval(int x, int y, int width, int height);
	
	/**
	 * Draws a line. Parameters x1 and y1 specify the starting point of the 
	 * line, parameters x2 and y2 the ending point.
	 */
	public void drawLine(int x1, int y1, int x2, int y2);
	
	/**
	 * Fills a rectangle. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void fillRect(int x, int y, int width, int height);
	
	/**
	 * Gets the colour of a Shape instance.
	 */
	public Color getColor();
	
	/**
	 * Sets the colour of a Shape instance.
	 */
	public void setColor(Color colour);
	
	/**
	 * Translates the co-ordinates so that Shape instances inside CarrierShape
	 * are confined by the boundaries of CarrierShape and move with the CarrierShape.
	 */
	public void translate(int x, int y);
	
	/**
	 * Draws centred text for Shape instances that display text.
	 */
	public void drawCentredText(String text, int x, int y, int shapeWidth, int shapeHeight);

	public void drawImage(Image _picture, int _x, int _y, int _width, int _height);
}
