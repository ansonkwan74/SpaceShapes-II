package spaceshapes;

import java.awt.Color;
import java.awt.Image;

/**
 * Implementation of the Painter interface that does not actually do any
 * painting. A MockPainter implementation responds to Painter requests by
 * logging simply logging them. The contents of a MockPainter object's
 * log can be retrieved by a call to toString() on the MockPainter.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class MockPainter implements Painter {
	// Internal log.
	private StringBuffer _log = new StringBuffer();
	private Color _default = null;
	
	/**
	 * Returns the contents of this MockPainter's log.
	 */
	public String toString() {
		return _log.toString();
	}

	/**
	 * Logs the drawRect call.
	 */
	public void drawRect(int x, int y, int width, int height) {
		_log.append("(rectangle " + x + "," + y + "," + width + "," + height + ")");
	}

	/**
	 * Logs the drawOval call.
	 */
	public void drawOval(int x, int y, int width, int height) {
		_log.append("(oval " + x + "," + y + "," + width + "," + height + ")");
	}

	/**
	 * Logs the drawLine call.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_log.append("(line " + x1 + "," + y1 + "," + x2 + "," + y2 + ")");
	}

	/**
	 * Logs the fillRect call.
	 */
	public void fillRect(int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		_log.append("(Dynamic shape filled "+ x + "," + y + "," + width + "," + height + ")");
	}

	/**
	 * Returns default signifying that the colour has been reverted
	 * after a shape has been painted accordingly.
	 */
	public Color getColor() {
		return _default;
	}

	/**
	 * Logs the setColor call.
	 */
	public void setColor(Color colour) {
		if (colour == null) {
			_log.append("(colour has been reset)");
		}
		else {
			_log.append("(colour now set to " + colour + ")");
		}
	}

	/**
	 * Empty method
	 */
	public void translate(int x, int y) {
	}

	/**
	 * Logs the drawCentredText call.
	 */
	public void drawCentredText(String text, int x, int y, int shapeWidth, int shapeHeight) {
		_log.append("(Text printed: " + text + ")");
	}

	@Override
	public void drawImage(Image _picture, int _x, int _y, int _width, int _height) {
		// TODO Auto-generated method stub
		
	}
}