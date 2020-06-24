package spaceshapes;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Class to test class HexagonShape according to its specification.
 * Used to test for and identifying bugs in HexagonShape.
 *
 */
public class TestHexagonShape {
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
	 * Test to perform a simple (non-bouncing) movement, and to ensure that a large
	 * (width >= 40) HexagonShape's position after the movement is correct.
	 */
	@Test
	public void testSimpleMove() {
		HexagonShape shape = new HexagonShape(100, 20, 12, 15, 40, 40);
		shape.paint(_painter);
		shape.move(500,500);
		shape.paint(_painter);
		assertEquals("(line 100,40,120,20)(line 120,20,120,20)(line 120,20,140,40)(line 140,40,120,60)(line 120,60,120,60)(line 120,60,100,40)(line 112,55,132,35)(line 132,35,132,35)(line 132,35,152,55)(line 152,55,132,75)(line 132,75,132,75)(line 132,75,112,55)",
				_painter.toString());
	}
	
	/**
	 * Test to perform a simple (non-bouncing) movement, and to ensure that a small
	 * (width < 40) HexagonShape's position after the movement is correct.
	 */
	@Test
	public void testSimpleMoveSmall() {
		HexagonShape shape = new HexagonShape(100, 20, 12, 15, 39, 39);
		shape.paint(_painter);
		shape.move(500,500);
		shape.paint(_painter);
		assertEquals("(line 100,39,119,20)(line 119,20,139,39)(line 139,39,119,59)(line 119,59,100,39)(line 112,54,131,35)(line 131,35,151,54)(line 151,54,131,74)(line 131,74,112,54)",
				_painter.toString());
	}
	
	/**
	 * Tests the bounce movement off the right wall to ensure correct position
	 * and movement for large HexagonShape instances.
	 */
	@Test
	public void testShapeWithoutBounceOffRight() {
		HexagonShape shape = new HexagonShape(100, 20, 12, 15, 40, 40);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		assertEquals("(line 100,40,120,20)(line 120,20,120,20)(line 120,20,140,40)(line 140,40,120,60)(line 120,60,120,60)(line 120,60,100,40)(line 95,55,115,35)(line 115,35,115,35)(line 115,35,135,55)(line 135,55,115,75)(line 115,75,115,75)(line 115,75,95,55)(line 83,70,103,50)(line 103,50,103,50)(line 103,50,123,70)(line 123,70,103,90)(line 103,90,103,90)(line 103,90,83,70)"
				, _painter.toString());
	}
	
	/**
	 * Tests the bounce movement off the right wall to ensure correct position
	 * and movement for small HexagonShape instances.
	 */
	@Test
	public void testShapeWithoutBounceOffRightSmall() {
		HexagonShape shape = new HexagonShape(100, 20, 12, 15, 39, 39);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		shape.move(135, 10000);
		shape.paint(_painter);
		assertEquals("(line 100,39,119,20)(line 119,20,139,39)(line 139,39,119,59)(line 119,59,100,39)(line 96,54,115,35)(line 115,35,135,54)(line 135,54,115,74)(line 115,74,96,54)(line 84,69,103,50)(line 103,50,123,69)(line 123,69,103,89)(line 103,89,84,69)"
				, _painter.toString());
	}
	
	/**
	 * Tests the bounce movement off the left wall to ensure correct position
	 * and movement for large HexagonShape instances.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeft() {
		HexagonShape shape = new HexagonShape(10, 20, -12, 15, 40, 40);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		assertEquals("(line 10,40,30,20)(line 30,20,30,20)(line 30,20,50,40)(line 50,40,30,60)(line 30,60,30,60)(line 30,60,10,40)(line 0,55,20,35)(line 20,35,20,35)(line 20,35,40,55)(line 40,55,20,75)(line 20,75,20,75)(line 20,75,0,55)(line 12,70,32,50)(line 32,50,32,50)(line 32,50,52,70)(line 52,70,32,90)(line 32,90,32,90)(line 32,90,12,70)"
				, _painter.toString());
	}
	
	/**
	 * Tests the bounce movement off the left wall to ensure correct position
	 * and movement for small HexagonShape instances.
	 */
	@Test
	public void testShapeMoveWithBounceOffLeftSmall() {
		HexagonShape shape = new HexagonShape(10, 20, -12, 15, 39, 39);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		shape.move(10000, 10000);
		shape.paint(_painter);
		assertEquals("(line 10,39,29,20)(line 29,20,49,39)(line 49,39,29,59)(line 29,59,10,39)(line 0,54,19,35)(line 19,35,39,54)(line 39,54,19,74)(line 19,74,0,54)(line 12,69,31,50)(line 31,50,51,69)(line 51,69,31,89)(line 31,89,12,69)"
				, _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the bottom left corner and to
	 * ensure that the large HexagonShape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndLeft() {
		HexagonShape shape = new HexagonShape(10, 90, -12, 15, 40, 40);
		shape.paint(_painter);
		shape.move(125, 135);
		shape.paint(_painter);
		shape.move(125, 135);
		shape.paint(_painter);
		assertEquals("(line 10,110,30,90)(line 30,90,30,90)(line 30,90,50,110)(line 50,110,30,130)(line 30,130,30,130)(line 30,130,10,110)(line 0,115,20,95)(line 20,95,20,95)(line 20,95,40,115)(line 40,115,20,135)(line 20,135,20,135)(line 20,135,0,115)(line 12,100,32,80)(line 32,80,32,80)(line 32,80,52,100)(line 52,100,32,120)(line 32,120,32,120)(line 32,120,12,100)"
				, _painter.toString());
	}
	
	/**
	 * Test to perform a bounce movement off the bottom left corner and to
	 * ensure that the small HexagonShape's position after the movement is correct.
	 */
	@Test
	public void testShapeMoveWithBounceOffBottomAndLeftSmall() {
		HexagonShape shape = new HexagonShape(10, 90, -12, 15, 39, 39);
		shape.paint(_painter);
		shape.move(125, 135);
		shape.paint(_painter);
		shape.move(125, 135);
		shape.paint(_painter);
		assertEquals("(line 10,109,29,90)(line 29,90,49,109)(line 49,109,29,129)(line 29,129,10,109)(line 0,115,19,96)(line 19,96,39,115)(line 39,115,19,135)(line 19,135,0,115)(line 12,100,31,81)(line 31,81,51,100)(line 51,100,31,120)(line 31,120,12,100)"
				, _painter.toString());
	}
	
}
