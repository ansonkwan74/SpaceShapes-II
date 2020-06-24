package spaceshapes;

import java.awt.Color;
/**
 * The DynamicShape class represents a shape that changes colour upon bouncing off walls.
 * DynamicShape instances are filled with colour when bouncing off the left or right
 * boundaries, and reverts to its original state (unfilled with outline) when bouncing off
 * the top or bottom boundaries.
 * 
 * Assumptions:
 * 1.	When the DynamicShape is not filled, the outline should have the same colour
 * 		as other unfilled Shape instances (i.e. the original colour set in GraphicsPainter).
 * 2.	When DynamicShape is filled, it has no distinguishable outline / its outline 
 * 		is the same colour as the fill colour.
 * 3.	When text should be displayed, the text should be the colour set in GraphicsPainter
 * 		and does not change colour when the DynamicShape hits a wall.
 * 4.	The DynamicShape becomes unfilled when bouncing off a corner.
 */
public class DynamicShape extends Shape {
	// === Field that describes the colour set for this DynamicShape
	private Color _c = Color.white;
	
	/**
	 * Default constructor that creates a RectangleShape instance whose instance
	 * variables are set to default values.
	 */
	public DynamicShape() {
		super();
	}	
	
	/**
	 * Creates a DynamicShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}
	
	/**
	 * Creates a Dynamic instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param text text to display.
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}
	
	/**
	 * Creates a Dynamic instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param colour colour of shape upon collision against left or right wall.
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height, Color colour) {
		super(x, y, deltaX, deltaY, width, height);
		_c = colour;
	}
	
	/**
	 * Creates a Dynamic instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed (pixels per move call) and direction for horizontal 
	 *        axis.
	 * @param deltaY speed (pixels per move call) and direction for vertical 
	 *        axis.
	 * @param width width in pixels.
	 * @param height height in pixels.
	 * @param colour colour of shape upon collision against left or right wall.
	 * @param text text to display.
	 */
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height, Color colour, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
		_c = colour;
	}
	
	public DynamicShape(int x, int y, int deltaX, int deltaY, int width, int height, String text, Color colour) {
		super(x, y, deltaX, deltaY, width, height, text);
		_c = colour;
	}
	
	/*
	 * Paints the DynamicShape based on whether or not it should be filled.
	 * @param painter Painter object used to paint the shape. 
	 */
	@Override
	public void doPaint(Painter painter) {	
		if (_shouldFill) {
			Color tempColour;
			tempColour = painter.getColor();
			painter.setColor(_c);
			painter.fillRect(_x, _y, _width, _height);
			painter.setColor(tempColour);
		}
		else {
			painter.drawRect(_x, _y, _width, _height);
		}
	}
}
