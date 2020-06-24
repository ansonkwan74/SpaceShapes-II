package spaceshapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Paramvir Singh (Original Author - Ian Warren)
 * 
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Graphics _g;
	
	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
		_g.setColor(new Color(212, 212, 212));
	}

	/**
	 * @see spaceshapes.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}

	/**
	 * @see spaceshapes.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * @see spaceshapes.Painter.drawLine.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}

	/**
	 * @see spaceshapes.Painter.fillRect.
	 */
	public void fillRect(int x, int y, int width, int height) {
		_g.fillRect(x, y, width, height);		
	}
	
	/**
	 * @see spaceshapes.Painter.getColor.
	 */
	public Color getColor() {
		return _g.getColor();
	}
	
	/**
	 * @see spaceshapes.Painter.setColor.
	 */
	public void setColor(Color colour) {
		_g.setColor(colour);
	}
	
	/**
	 * @see spaceshapes.Painter.translate.
	 */
	public void translate(int x, int y) {
		_g.translate(x, y);
	}
	
	/**
	 * @see spaceshapes.Painter.drawCentredText.
	 */
	public void drawCentredText(String text, int x, int y, int shapeWidth, int shapeHeight) {
		int textWidth = _g.getFontMetrics().stringWidth(text);
		int startX;
		int startY;
		int adjustmentY = 0;
		//Adjustments in the x direction to centre the text
		if (shapeWidth > textWidth) {
			startX = x + ((shapeWidth - textWidth) / 2);
		}
		else {
			startX = x - ((textWidth - shapeWidth) / 2);
		}
		//System.out.println("Ascent = " + _g.getFontMetrics().getMaxAscent());
		//System.out.println("Descent = " + _g.getFontMetrics().getMaxDescent());
		//Adjustments in the y direction to centre the text
		if (_g.getFontMetrics().getAscent() > _g.getFontMetrics().getDescent()) {
			adjustmentY = (_g.getFontMetrics().getAscent() - _g.getFontMetrics().getDescent()) /2;
			startY = y + shapeHeight/2 + adjustmentY;
		}
		else if (_g.getFontMetrics().getAscent() < _g.getFontMetrics().getDescent()) {
			adjustmentY = (_g.getFontMetrics().getDescent() - _g.getFontMetrics().getAscent()) /2;
			startY = y + shapeHeight/2 - adjustmentY;
		}
		else {
			startY = y;
		}
		//Draws the text
		_g.drawString(text, startX, startY);
	}

	@Override
	public void drawImage(Image _picture, int _x, int _y, int _width, int _height) {
		_g.drawImage(_picture, _x, _y, _width, _height, null);		
	}
}
