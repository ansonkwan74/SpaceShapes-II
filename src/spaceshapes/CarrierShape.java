package spaceshapes;

import java.util.ArrayList;
import java.util.List;
/**
 * The CarrierShape class is a subclass of Shape.
 * 
 * A CarrierShape instance can contain zero or more Shape instances,
 * including other CarrierShape instances. Hence, it can have arbitrary
 * depth. The CarrierShape is the bounding box for its children.
 *
 * The CarrierShape class implements the General Hierarchy Design Pattern. 
 * In this general hierarchy, all elements or nodes are Shape instances. 
 * There are two types of nodes: leaf and composite nodes.
 * Leaf nodes are non-CarrierShape instances. They do not contain other Shapes.
 * Composite nodes are CarrierShape instances, as they can contain other Shape
 * instances including other CarrierShapes. Each CarrierShape instance controls 
 * the movement themselves, and the movement of all Shape instances contained
 * by the CarrierShape.
 * 
 */
public class CarrierShape extends Shape {	
	/**
	 * List of shapes representing all Shape instances contained by this CarrierShape instance.
	 */
	List<Shape> _containedShapes = new ArrayList<Shape>();
	
	/**
	 * Default constructor that creates a CarrierShape instance whose instance
	 * variables are set to default values.
	 */
	public CarrierShape() {		
		super();
	}
	
	/**
	 * Creates a CarrierShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 */
	public CarrierShape(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Creates a CarrierShape instance with specified values for instance 
	 * variables.
	 * @param x x position.
	 * @param y y position.
	 * @param deltaX speed and direction for horizontal axis.
	 * @param deltaY speed and direction for vertical axis.
	 */
	public CarrierShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}
	
	/**
	 * Creates a CarrierShape instance with specified values for instance 
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
	public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}
	
	/**
	 * Creates a CarrierShape instance with specified values for instance 
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
	public CarrierShape(int x, int y, int deltaX, int deltaY, int width, int height, String text) {
		super(x, y, deltaX, deltaY, width, height, text);
	}
	
	/**
	 * Moves this CarrierShape object within the specified bounds. On hitting a 
	 * boundary the CarrierShape instance bounces off and back into the two- 
	 * dimensional world. 
	 * 
	 * Is also responsible for invoking the move method on all Shape instances
	 * contained inside this CarrierShape instance.
	 * 
	 * @param width - width of two-dimensional world.
	 * @param height - height of two-dimensional world.
	 */
	@Override
	public void move(int width, int height) {
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;

		if (nextX <= 0) {
			nextX = 0;
			_deltaX = -_deltaX;
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;
		}

		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;
		}

		_x = nextX;
		_y = nextY;
		
		for (Shape s: _containedShapes) {
			s.move(_width, _height);
		}
	}
	
	/**
	 * Paints this CarrierShape object using the supplied Painter object.
	 * 
	 * Is also responsible for painting all Shape instances contained by
	 * this CarrierShape instance.
	 */
	@Override
	public void doPaint(Painter painter) {
		painter.drawRect(_x,_y,_width,_height);
		painter.translate(_x, _y);
		for (Shape s: _containedShapes) {		
			s.paint(painter);
		}
		painter.translate(-_x, -_y);
	}
	
	/*
	 * Attempts to add a Shape to a CarrierShape object. If successful, a
	 * two-way link is established between the CarrierShape and the newly
	 * added Shape. 
	 * @param shape the shape to be added.
	 * @throws IllegalArgumentException if an attempt is made to add a Shape
	 * to a CarrierShape instance when it is invalid - i.e. shape is already a
	 * child within this CarrierShape instance, or is a child of another CarrierShape
	 * instance, or its size exceeds the bounds of this CarrierShape object.
	 */
	void add(Shape shape) throws IllegalArgumentException {		
		if (shape._width + shape._x> this._width) {
			throw new IllegalArgumentException();
		}
		else if (shape._height + shape._y > this._height) {
			throw new IllegalArgumentException();
		}
		else if (shape._parent != null) {
			throw new IllegalArgumentException();
		}
		else if (shape._parent == this) {
			throw new IllegalArgumentException();
		}
		else {
			shape._parent = this;
			_containedShapes.add(shape);
		}		
	}
	
	/**
	 * Removes a particular Shape from a CarrierShape instance. The two-way
	 * link between the CarrierShape and the former child is destroyed. Has
	 * no effect if shape is not a child of CarrierShape.
	 * @param shape shape to be removed.
	 */
	void remove(Shape shape) {
		for (int i = 0; i < _containedShapes.size(); i++) {
			if (_containedShapes.get(i) == shape) {
				shape._parent = null;
				_containedShapes.remove(i);
			}
		}
	}
	
	/**
	 * Returns the Shape at a specified position within CarrierShape. If
	 * the position specified is less than 0 or greater than the number 
	 * of children stored in the CarrierShape - 1 then an IndexOutOfBoundsException
	 * is thrown.
	 * @param index the specified index position.
	 */
	public Shape shapeAt(int index) throws IndexOutOfBoundsException {
		if (index > shapeCount()) {
			throw new IndexOutOfBoundsException();
		}
		else if (index < 0) {
			throw new IndexOutOfBoundsException();
		}
		else {
			return _containedShapes.get(index);
		}
	}
	
	/**
	 * Returns the number of Shape instances contained by CarrierShape.
	 */
	public int shapeCount() {
		return _containedShapes.size();
	}
	
	/**
	 * Finds the position of a child within CarrierShape. Returns -1 if
	 * shape is not a child of CarrierShape.
	 * @param shape shape to find.
	 */
	public int indexOf(Shape shape) {
		for (int i = 0; i < _containedShapes.size(); i++) {
			if (_containedShapes.get(i) == shape) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Returns true if shape is a child of CarrierShape.
	 * @param shape shape in question
	 */
	public boolean contains(Shape shape) {
		if (indexOf(shape) == -1) {
			return false;
		}
		else {
			return true;
		}
	}	
}
