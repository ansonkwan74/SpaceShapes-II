package spaceshapes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract superclass to represent the general concept of a Shape. This class
 * defines state common to all special kinds of Shape instances and implements
 * a common movement algorithm. Shape subclasses must override method paint()
 * to handle shape-specific painting.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */

/**
 * The Shape class implements the Template Method Design Pattern. It contains
 * an optional hook - the move() method (which is only overridden in the CarrierShape class),
 * a slot - the paint() method (which is an abstract class and must be overridden
 * 		 	and implemented in each subclass), 
 * and a method declared final - the paintText() method which should have the same
 * functionality and body for all Shape instances. It is a part of the template that
 * should not be changed by its subclasses as the painting method of all Shape instances
 * should be identical.
 */
public abstract class Shape {
	// === Constants for default values. ===
	protected static final int DEFAULT_X_POS = 0;
	
	protected static final int DEFAULT_Y_POS = 0;
	
	protected static final int DEFAULT_DELTA_X = 5;
	
	protected static final int DEFAULT_DELTA_Y = 5;
	
	protected static final int DEFAULT_HEIGHT = 35;

	protected static final int DEFAULT_WIDTH = 25;
	// ===

	// === Instance variables, accessible by subclasses.
	protected int _x;

	protected int _y;

	protected int _deltaX;

	protected int _deltaY;

	protected int _width;

	protected int _height;
	
	protected String _text;
	
	protected CarrierShape _parent;
	
	protected Boolean _shouldFill;
	
	//private List<Shape> _ancestory = new ArrayList<Shape>();
	// ===

	/**
	 * Creates a Shape object with default values for instance variables.
	 */
	public Shape() {
		this(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * Creates a Shape object with a specified x and y position.
	 */
	public Shape(int x, int y) {
		this(x, y, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	/**
	 * Creates a Shape instance with specified x, y, deltaX and deltaY values.
	 * The Shape object is created with a default width and height.
	 */
	public Shape(int x, int y, int deltaX, int deltaY) {
		this(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}

	/**
	 * Creates a Shape instance with specified x, y, deltaX, deltaY, width and
	 * height values.
	 */
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height) {
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
		_shouldFill = false;
	}
	
	/** 
	 * Creates a Shape instance with specified x, y, deltaX, deltaY, width, height
	 * and text values. 
	*/
	public Shape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		_x = x;
		_y = y;
		_deltaX = deltaX;
		_deltaY = deltaY;
		_width = width;
		_height = height;
		_shouldFill = false;
		_text = text;
	}
	
	/**
	 * Moves this Shape object within the specified bounds. On hitting a 
	 * boundary the Shape instance bounces off and back into the two- 
	 * dimensional world. 
	 * @param width - width of two-dimensional world.
	 * @param height - height of two-dimensional world.
	 */
	public void move(int width, int height) {
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;

		if (nextX <= 0) {
			nextX = 0;
			_deltaX = -_deltaX;
			_shouldFill = true;
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;
			_shouldFill = true;
		}

		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;
			_shouldFill = false;
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;
			_shouldFill = false;
		}

		_x = nextX;
		_y = nextY;
	}

	/**
	 * Method to be implemented by concrete subclasses to handle subclass
	 * specific painting.
	 * @param painter the Painter object used for drawing.
	 */
	public final void paint(Painter painter) {
		this.doPaint(painter);
		paintText(painter);
	}
	
	protected abstract void doPaint(Painter painter);

	/**
	 * Returns this Shape object's x position.
	 */
	public int x() {
		return _x;
	}
	
	/**
	 * Returns this Shape object's y position.
	 */
	public int y() {
		return _y;
	}
	
	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaX() {
		return _deltaX;
	}
	
	/**
	 * Returns this Shape object's speed and direction.
	 */
	public int deltaY() {
		return _deltaY;
	}
	
	/**
	 * Returns this Shape's width.
	 */
	public int width() {
		return _width;
	}
	
	/**
	 * Returns this Shape's height.
	 */
	public int height() {
		return _height;
	}
	
	/**
	 * Returns a String whose value is the fully qualified name of this class 
	 * of object. E.g., when called on a RectangleShape instance, this method 
	 * will return "spaceshapes.RectangleShape".
	 */
	public String toString() {
		return getClass().getName();
	}
	
	public String text() {
		return _text;
	}
	
	/**
	 * A Template Method. 
	 * This method is final because subclasses should not make arbitrary 
	 * changes to how text is displayed. Refer to comment describing the 
	 * use of Template Method Design Pattern at the top of this class.
	 * 
	 * Paints the text that the Shape should display
	 * @param painter the Painter object used for drawing.
	 */
	public final void paintText(Painter painter) {
		if (_text != null) {
			painter.drawCentredText(_text, _x, _y, _width, _height);
		}
	}
	
	/** 
	 * Returns the parent CarrierShape that contains this Shape 
	 */
	public CarrierShape parent() {
		return _parent;
	}
	
	/**
	 * Returns an ordered list of Shape objects. The first item within
	 * the list is the root of CarrierShape of the containment hierarchy.
	 * The last item is the callee object.
	 */	
	public List<Shape> path(){
		List<Shape> ancestory = new ArrayList<Shape>();
		Shape currentShape = this;
		while (currentShape._parent != null) {
			ancestory.add(currentShape);
			currentShape = currentShape._parent;
		}
		ancestory.add(currentShape);
		Collections.reverse(ancestory);
		return ancestory;
	}	
}
