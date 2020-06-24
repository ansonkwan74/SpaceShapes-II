package spaceshapes;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

/**
 * Class to test class DynamicShape according to its specification.
 * Important:
 * 1.	When the DynamicShape should not be filled, it is logged in the format of
 * 		(rectangle x, y, width, height).
 * 2.	When the DynamicShape should be filled, it is logged in the format of
 * 		(Dyanmic shape filled x, y, width, height).
 * 3.	Before each time a DynamicShape is filled, a colour change (to the
 * 		colour of the DynamicShape filling) is logged. This is in the format of
 * 		(colour now set to java.awt.Color[r,g,b]).
 * 4.	After each time a DynamicShape is filled, a colour change (back to the
 * 		original colour set in GraphicsPainter) is logged. This is always logged as
 * 		(colour has been reset).
 * 5. 	The getColor() and setColor() methods are not invoked when the DynamicShape
 * 		should not be filled.
 */
public class TestDynamicShape {
	// Fixture object that is used by the tests.
	private MockPainter _painter;
	
	/**
	 * This method is called automatically by the JUnit test-runner immediately
	 * before each @Test method is executed. setUp() recreates the fixture so 
	 * that there no side effects from running individual tests.
	 */
	@Before
	public void setUp() {
		_painter = new MockPainter();
	}
	
	/**
	 * Test to perform a bounce movement off the left-most boundary and to
	 * ensure that the DynamicShape's position and colour after the movement 
	 * is correct.
	 * 
	 * The DynamicShape is initially unfilled, then becomes filled after
	 * bouncing off the left wall.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeft() {
		DynamicShape shape = new DynamicShape(10,10,-12,0,10,10, Color.blue);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 10,10,10,10)(colour now set to java.awt.Color[r=0,g=0,b=255])(Dynamic shape filled 0,10,10,10)(colour has been reset)(colour now set to java.awt.Color[r=0,g=0,b=255])(Dynamic shape filled 12,10,10,10)(colour has been reset)", 
				_painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the top-most boundary and to
	 * ensure that the DynamicShape's position and colour after the movement 
	 * is correct.
	 * 
	 * The DynamicShape is initially unfilled, then remains unfilled after
	 * bouncing off the top wall.
	 */
	@Test
	public void testShapeMoveWithBounceOffTop() {
		DynamicShape shape = new DynamicShape(10,10,0,-12,10,10, Color.blue);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 10,10,10,10)(rectangle 10,0,10,10)(rectangle 10,12,10,10)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the right-most boundary and to
	 * ensure that the DynamicShape's position and colour after the movement 
	 * is correct.
	 * 
	 * The DynamicShape is initially unfilled, the becomes filled after bouncing
	 * off the right wall.
	 */
	@Test
	public void testShapeMoveWithBounceOffRight() {
		DynamicShape shape = new DynamicShape(80,10,12,0,10,10, Color.blue);
		shape.paint(_painter);
		shape.move(100,100);
		shape.paint(_painter);
		shape.move(100,100);
		shape.paint(_painter);
		assertEquals("(rectangle 80,10,10,10)(colour now set to java.awt.Color[r=0,g=0,b=255])(Dynamic shape filled 90,10,10,10)(colour has been reset)(colour now set to java.awt.Color[r=0,g=0,b=255])(Dynamic shape filled 78,10,10,10)(colour has been reset)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the bottom-most boundary and to
	 * ensure that the DynamicShape's position and colour after the movement 
	 * is correct.
	 * 
	 * The DynamicShape is initially unfilled, then remains unfilled after
	 * bouncing off the bottom wall.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottom() {
		DynamicShape shape = new DynamicShape(10,80,0,12,10,10, Color.blue);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 10,80,10,10)(rectangle 10,90,10,10)(rectangle 10,78,10,10)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the top-left corner and to
	 * ensure that the DynamicShape's position and colour after the movement 
	 * is correct. It is assumed that the colour of DynamicShape does 
	 * not change after bouncing off a corner.
	 * 
	 * The Dynamic shape is initially unfilled, then remains unfilled after
	 * bouncing off the top left corner (two walls at the same time).
	 */
	@Test
	public void testShapeMoveWithBounceOffTopLeftCorner() {
		DynamicShape shape = new DynamicShape(10,10,-12,-12,10,10, Color.blue);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 10,10,10,10)(rectangle 0,0,10,10)(rectangle 12,12,10,10)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the top-right corner and to
	 * ensure that the DynamicShape's position and colour after the movement 
	 * is correct. It is assumed that the colour of DynamicShape does 
	 * not change after bouncing off a corner.
	 * 
	 * The Dynamic shape is initially unfilled, then remains unfilled after
	 * bouncing off the top right corner (two walls at the same time).
	 */
	@Test
	public void testShapeMoveWithBounceOffTopRightCorner() {
		DynamicShape shape = new DynamicShape(80,10,12,-12,10,10, Color.blue);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 80,10,10,10)(rectangle 90,0,10,10)(rectangle 78,12,10,10)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the bottom-left corner and to
	 * ensure that the DynamicShape's position and colour after the movement 
	 * is correct. It is assumed that the colour of DynamicShape does 
	 * not change after bouncing off a corner.
	 * 
	 * The Dynamic shape is initially unfilled, then remains unfilled after
	 * bouncing off the bottom left corner (two walls at the same time).
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomLeftCorner() {
		DynamicShape shape = new DynamicShape(10,80,-12,12,10,10, Color.blue);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 10,80,10,10)(rectangle 0,90,10,10)(rectangle 12,78,10,10)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the bottom-right corner and to
	 * ensure that the DynamicShape's position and colour after the movement 
	 * is correct. It is assumed that the colour of DynamicShape does 
	 * not change after bouncing off a corner.
	 * 
	 * The Dynamic shape is initially unfilled, then remains unfilled after
	 * bouncing off the bottom right corner (two walls at the same time).
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomRightCorner() {
		DynamicShape shape = new DynamicShape(80,80,12,12,10,10, Color.blue);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 80,80,10,10)(rectangle 90,90,10,10)(rectangle 78,78,10,10)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the left boundary then top boundary
	 * to ensure that the DynamicShape's position and colour changes accordingly.
	 * 
	 * The DynamicShape is originally unfilled, then becomes filled after bouncing
	 * off the left wall, then becomes unfilled after bouncing off the top wall.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeftThenTop() {
		DynamicShape shape = new DynamicShape(10,10,-12,-8,10,10, Color.blue);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 10,10,10,10)(colour now set to java.awt.Color[r=0,g=0,b=255])(Dynamic shape filled 0,2,10,10)(colour has been reset)(rectangle 12,0,10,10)(rectangle 24,8,10,10)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the right boundary then bottom boundary
	 * to ensure that the DynamicShape's position and colour changes accordingly.
	 * 
	 * The DynamicShape is originally unfilled, then becomes filled after bouncing
	 * off the right wall, then becomes unfilled after bouncing off the bottom wall.
	 */
	@Test
	public void testShapeMoveWithBounceOffRightThenBottom() {
		DynamicShape shape = new DynamicShape(80,80,12,8,10,10, Color.blue);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 80,80,10,10)(colour now set to java.awt.Color[r=0,g=0,b=255])(Dynamic shape filled 90,88,10,10)(colour has been reset)(rectangle 78,90,10,10)(rectangle 66,82,10,10)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the right boundary then bottom boundary
	 * to ensure that the DynamicShape's position and colour changes accordingly.
	 * 
	 * The DynamicShape is originally unfilled, then becomes filled after bouncing
	 * off the left wall, then becomes unfilled after bouncing off the bottom wall.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeftThenBottom() {
		DynamicShape shape = new DynamicShape(10,80,-12,8,10,10, Color.blue);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 10,80,10,10)(colour now set to java.awt.Color[r=0,g=0,b=255])(Dynamic shape filled 0,88,10,10)(colour has been reset)(rectangle 12,90,10,10)(rectangle 24,82,10,10)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the right boundary then bottom boundary
	 * to ensure that the DynamicShape's position and colour changes accordingly.
	 * 
	 * The DynamicShape is originally unfilled, then becomes filled after bouncing
	 * off the right wall, then becomes unfilled after bouncing off the top wall.
	 */
	@Test
	public void testShapeMoveWithBounceOffRightThenTop() {
		DynamicShape shape = new DynamicShape(80,10,12,-8,10,10, Color.blue);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		shape.move(100, 100);
		shape.paint(_painter);
		assertEquals("(rectangle 80,10,10,10)(colour now set to java.awt.Color[r=0,g=0,b=255])(Dynamic shape filled 90,2,10,10)(colour has been reset)(rectangle 78,0,10,10)(rectangle 66,8,10,10)", _painter.toString());
	}
	
}
