package spaceshapes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Class to test class HexagonShape according to its specification.
 * Used to test for and identifying bugs in OvalShape.
 *
 */
public class TestOvalShape {
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
	 * Test to perform a simple (non-bouncing) movement, and to ensure that an
	 * OvalShape's position after the movement is correct.
	 */
	@Test
	public void testSimpleMove() {
		OvalShape shape = new OvalShape(100, 20, 12, 15);
		shape.paint(_painter);
		shape.move(500,500);
		shape.paint(_painter);
		assertEquals("(oval 100,20,25,35)(oval 112,35,25,35)", 
				_painter.toString());
	}
	
	/**
	 * Tests the bounce movement off the right wall to ensure correct position
	 * and movement.
	 */
	@Test
	public void testShapeWithoutBounceOffRight() {
		OvalShape shape = new OvalShape(100, 20, 12, 15);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		assertEquals("(oval 100,20,25,35)(oval 110,35,25,35)"
				+ "(oval 98,50,25,35)", _painter.toString());
	}
	
	/**
	 * Tests the bounce movement off the left wall to ensure correct position
	 * and movement.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeft() {
		OvalShape shape = new OvalShape(10, 20, -12, 15);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		assertEquals("(oval 10,20,25,35)(oval 0,35,25,35)"
				+ "(oval 12,50,25,35)", _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the bottom left corner and to
	 * ensure that the OvalShape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndLeft() {
		OvalShape shape = new OvalShape(10, 90, -12, 15);
		shape.paint(_painter);
		shape.move(125, 135);
		shape.paint(_painter);
		shape.move(125, 135);
		shape.paint(_painter);
		assertEquals("(oval 10,90,25,35)(oval 0,100,25,35)"
				+ "(oval 12,85,25,35)", _painter.toString());
	}
}

