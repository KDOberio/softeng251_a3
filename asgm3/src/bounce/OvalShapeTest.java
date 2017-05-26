package bounce;

import static org.junit.Assert.*;

import org.junit.Test;

public class OvalShapeTest {
	private MockPainter _painter = new MockPainter();
//	private final static int FRAME_WIDTH = 100;
//	private final static int FRAME_HEIGHT = 100;
	private final static int TEST_DELTA_X = 1;
	private final static int TEST_DELTA_Y = 1;
	
	/**
	 * Tests if the output is correct for a given constructed oval. 
	 * @param x x-position of oval.
	 * @param y y-position of oval.
	 * @param width width of oval.
	 * @param height height of oval.
	 */
	private void testMove(int x, int y, int width, int height) {
		OvalShape oval = new OvalShape(x, y, TEST_DELTA_X, TEST_DELTA_Y, width, height);
		oval.draw(_painter);

		System.out.println(_painter);
		assertEquals(_painter.toString(), "(oval, x =  " + x + ", y = " + y + ", width = " + width + ", height = " + height + ")");
	}
	
	/**
	 * Tests an arbitrary shape.
	 */
	@Test
	public void test() {
		int x = 45;
		int y = 12;
		int width = 20;
		int height = 35;
		
		testMove(x, y, width, height);
	}
	
	/**
	 * Tests the default width and height shape.
	 */
	@Test
	public void defaultTest() {
		int x = 45;
		int y = 12;

		OvalShape oval = new OvalShape(x, y, TEST_DELTA_X, TEST_DELTA_Y);
		oval.draw(_painter);
		assertEquals(_painter.toString(), "(oval, x =  " + x + ", y = " + y + ", width = " + 25 + ", height = " + 35 + ")");

	}

}
