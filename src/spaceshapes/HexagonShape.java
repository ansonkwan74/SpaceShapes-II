package spaceshapes;

/**
 * A subclass of Shape to represent a hexagon shape. This inherits all
 * properties of the Shape superclass, and has its own paint() method
 * to handle hexagon-specific painting.
 * 
 * There are two types of hexagons, large and small, determined by their
 * width. These different types of hexagons are painted differently as
 * described in the paint() method.
 * 
 */
public class HexagonShape extends Shape {
	
	/**
	 * Default constructor that creates a HexagonShape instance whose instance
	 * variables are set to default values.
	 */
	public HexagonShape() {
		super();
	}
	
	/**
	 * Creates a HexagonShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public HexagonShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}
	
	/**
	 * Creates a HexagonShape instance with specified values for instance 
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
	public HexagonShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}
	
	/**
	 * Creates a HexagonShape instance with specified values for instance 
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
	public HexagonShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}
	
	/*
	 * Paints the HexagonShape instance with the supplied painter object.
	 */
	@Override
	public void doPaint(Painter painter) {
		// 6 edges if the Hexagon's width is greater than or equal to 40 pixels.
		if (_width >= 40) {
			painter.drawLine(_x, _y+_height/2, _x+20, _y);
			painter.drawLine(_x+20, _y, _x+_width-20, _y);
			painter.drawLine(_x+_width-20, _y, _x+_width, _y+_height/2);
			painter.drawLine(_x+_width, _y+_height/2, _x+_width-20, _y+_height);
			painter.drawLine(_x+_width-20, _y+_height, _x+20, _y+_height);
			painter.drawLine(_x+20, _y+_height, _x, _y+_height/2);
		}
		// 4 edges if the Hexagon's width is less than 40.
		else {
			painter.drawLine(_x, _y+_height/2, _x+_width/2, _y);
			painter.drawLine(_x+_width/2, _y, _x+_width, _y+_height/2);
			painter.drawLine(_x+_width, _y+_height/2, _x+_width/2, _y+_height);
			painter.drawLine(_x+_width/2, _y+_height, _x, _y+_height/2);
		}
	}

}
