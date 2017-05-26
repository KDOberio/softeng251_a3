package bounce;

import org.junit.*;
import java.awt.Color;

import static org.junit.Assert.*;

public class DynamicRectangleShapeTest {
	private final static int TEST_WIDTH = 10;
	private final static int TEST_HEIGHT = 10;
	private final static int FRAME_WIDTH = 100;
	private final static int FRAME_HEIGHT = 100;
	private final static String DEFAULT_COLOR = "java.awt.Color[r=0,g=0,b=0]";
	private Painter _painter = new MockPainter();
	
	/**
	 * Moves an object in a particular direction and checks if that move is what is expected.
	 * @param x Initial x-position of upper-left corner of shape.
	 * @param y Initial y-position of upper-left corner of shape.
	 * @param deltaX Initial velocity of shape in x-direction.
	 * @param deltaY Initial velocity of shape in y-direction.
	 * @param correctX Expected value of x after one move.
	 * @param correctY Expected value of y after one move.
	 * @param isFilled whether the rectangle is supposed to be filled after the move.
	 */
	private void testMove(int x, int y, int deltaX, int deltaY, int correctX, int correctY, boolean isFilled) {
		DynamicRectangleShape rectangle = new DynamicRectangleShape(x, y, deltaX, deltaY, TEST_WIDTH, TEST_HEIGHT);
		rectangle.move(FRAME_WIDTH, FRAME_HEIGHT);
		rectangle.draw(_painter);
		
		if (isFilled) {
			assertEquals(_painter.toString(), "(rectangle, x = " + correctX + ", y = " + correctY + ", width = " + TEST_WIDTH + ", height = "+ TEST_HEIGHT +")"
				+ "(filled rectangle colour = " + DEFAULT_COLOR + ")");
		} else {
			assertEquals(_painter.toString(), "(rectangle, x = " + correctX + ", y = " + correctY + ", width = " + TEST_WIDTH + ", height = "+ TEST_HEIGHT +")");
		}
	}
	
	/**
	 * @see bounce.DynamicRectangleShapeTest#testMove(int x, int y, int deltaX, int deltaY, int correctX, int correctY, boolean isFilled)
	 * @param color The colour of the dynamic rectangle.
	 */
	private void testMove(int x, int y, int deltaX, int deltaY, int correctX, int correctY, Color color, boolean isFilled) {
		DynamicRectangleShape rectangle = new DynamicRectangleShape(x, y, deltaX, deltaY, TEST_WIDTH, TEST_HEIGHT, color);
		rectangle.move(FRAME_WIDTH, FRAME_HEIGHT);
		rectangle.draw(_painter);
		
		if (isFilled) {
			assertEquals(_painter.toString(), "(rectangle, x = " + correctX + ", y = " + correctY + ", width = " + TEST_WIDTH + ", height = "+ TEST_HEIGHT +")"
				+ "(filled rectangle colour = " + color + ")");
		} else {
			assertEquals(_painter.toString(), "(rectangle, x = " + correctX + ", y = " + correctY + ", width = " + TEST_WIDTH + ", height = "+ TEST_HEIGHT +")");
		}
	}
	
	/*
	 * Most of the tests follow a simple pattern of moving to one wall or corner and
	 * checking that their values after that move are as expected. Correct values done
	 * by hand.
	 * 
	 * Two tests per method: one for colourless and one for coloured.
	 */

	@Test
	public void moveToLeftWall() {
		int x = 1;
		int y = 50;
		int deltaX = -1;
		int deltaY = 0;
		Color color = Color.BLUE;
		
		int correctX = 0;
		int correctY = 50;
		boolean isFilled = true;

		testMove(x, y, deltaX, deltaY, correctX, correctY, isFilled);
		_painter = new MockPainter();
		testMove(x, y, deltaX, deltaY, correctX, correctY, color, isFilled);
	}
	
	@Test
	public void moveToRightWall() {
		int x = 89;
		int y = 50;
		int deltaX = 1;
		int deltaY = 0;
		Color color = Color.RED;
		
		int correctX = 90;
		int correctY = 50;
		boolean isFilled = true;
		
		testMove(x, y, deltaX, deltaY, correctX, correctY, isFilled);
		_painter = new MockPainter();
		testMove(x, y, deltaX, deltaY, correctX, correctY, color, isFilled);
	}
	
	@Test
	public void moveToTopWall() {
		int x = 50;
		int y = 1;
		int deltaX = 0;
		int deltaY = -1;
		Color color = Color.RED;
		
		int correctX = 50;
		int correctY =  0;
		boolean isFilled = false;
		
		testMove(x, y, deltaX, deltaY, correctX, correctY, isFilled);
		_painter = new MockPainter();
		testMove(x, y, deltaX, deltaY, correctX, correctY, color, isFilled);
	}
	
	@Test
	public void moveToBottomWall() {
		int x = 50;
		int y = 89;
		int deltaX = 0;
		int deltaY = 1;
		Color color = Color.RED;
		
		int correctX = 50;
		int correctY = 90;
		boolean isFilled = false;
		
		testMove(x, y, deltaX, deltaY, correctX, correctY, isFilled);
		_painter = new MockPainter();
		testMove(x, y, deltaX, deltaY, correctX, correctY, color, isFilled);
	}
	
	@Test 
	public void moveToTopLeftCorner() {
		int x = 1;
		int y = 1;
		int deltaX = -1;
		int deltaY = -1;
		Color color = Color.RED;
		
		int correctX = 0;
		int correctY = 0;
		boolean isFilled = true;
		
		testMove(x, y, deltaX, deltaY, correctX, correctY, isFilled);
		_painter = new MockPainter();
		testMove(x, y, deltaX, deltaY, correctX, correctY, color, isFilled);
	}
	
	@Test 
	public void moveToTopRightCorner() {
		int x = 89;
		int y = 1;
		int deltaX = 1;
		int deltaY = -1;
		Color color = Color.RED;
		
		int correctX = 90;
		int correctY = 0;
		boolean isFilled = true;
		testMove(x, y, deltaX, deltaY, correctX, correctY, isFilled);
		
		_painter = new MockPainter();
		testMove(x, y, deltaX, deltaY, correctX, correctY, color, isFilled);
	}
	
	@Test 
	public void moveToBottomLeftCorner() {
		int x = 1;
		int y = 89;
		int deltaX = -1;
		int deltaY = 1;
		Color color = Color.BLUE;
		
		int correctX = 0;
		int correctY = 90;
		boolean isFilled = true;
		
		testMove(x, y, deltaX, deltaY, correctX, correctY, isFilled);
		_painter = new MockPainter();
		testMove(x, y, deltaX, deltaY, correctX, correctY, color, isFilled);
	}
	
	@Test 
	public void moveToBottomRightCorner() {
		int x = 89;
		int y = 89;
		int deltaX = 1;
		int deltaY = 1;
		Color color = Color.RED;
		
		int correctX = 90;
		int correctY = 90;
		boolean isFilled = true;
		
		testMove(x, y, deltaX, deltaY, correctX, correctY, isFilled);
		_painter = new MockPainter();
		testMove(x, y, deltaX, deltaY, correctX, correctY, color, isFilled);
	}

}
