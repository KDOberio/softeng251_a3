package bounce;

import org.junit.*;

import static org.junit.Assert.*;


public class GemShapeTest {
	private Painter _painter = new MockPainter();
	private final static int NUMBER_OF_SIDES = 6;
	
	private String formatOutput(int x1, int y1, int x2, int y2) {
		String formattedOutput = "(line, x1 = " + x1 + ", y1 = " + y1 + ", x2 = " + x2 + ", y2 = " + y2 + ")";
		
		return formattedOutput;
	}
	
	private void checkIfCorrect(String[] correctOutput) {
		String assertValue = "";
		for (String i: correctOutput) {
			assertValue += i;
		}
		
		assertEquals(_painter.toString(), assertValue);
	}
	
	@Test
	public void TestDefault() {
		GemShape gemmy = new GemShape();
		gemmy.draw(_painter);
		
		String[] correctOutput = new String[NUMBER_OF_SIDES];
		
		correctOutput[0] = formatOutput(12, 0, 12, 0);
		correctOutput[1] = formatOutput(12, 0, 25, 17);
		correctOutput[2] = formatOutput(25, 17, 12, 35);
		correctOutput[3] = formatOutput(12, 35, 12,35);
		correctOutput[4] = formatOutput(12, 35, 0, 17);
		correctOutput[5] = formatOutput(0, 17, 12, 0);
		
		checkIfCorrect(correctOutput);
		
	}
	
	// Should produce a (very) small gem.
	@Test
	public void testWidth1() {
		GemShape ourSmallGem = new GemShape(10, 10, 5, 5, 1, 5);
		ourSmallGem.draw(_painter);
		
		String[] correctOutput = new String[NUMBER_OF_SIDES];
		
		correctOutput[0] = formatOutput(10, 10, 10, 10);
		correctOutput[1] = formatOutput(10, 10, 11, 12);
		correctOutput[2] = formatOutput(11, 12, 10, 15);
		correctOutput[3] = formatOutput(10, 15, 10, 15);
		correctOutput[4] = formatOutput(10, 15, 10, 12);
		correctOutput[5] = formatOutput(10, 12, 10, 10);
		
		checkIfCorrect(correctOutput);

	}
	// Edge case 39 should produce a small gem.
	@Test
	public void testWidth39() {
		GemShape ourSmallGem = new GemShape(10, 10, 5, 5, 39, 5);
		ourSmallGem.draw(_painter);
		
		String[] correctOutput = new String[NUMBER_OF_SIDES];
		
		correctOutput[0] = formatOutput(29, 10, 29, 10);
		correctOutput[1] = formatOutput(29, 10, 49, 12);
		correctOutput[2] = formatOutput(49, 12, 29, 15);
		correctOutput[3] = formatOutput(29, 15, 29, 15);
		correctOutput[4] = formatOutput(29, 15, 10, 12);
		correctOutput[5] = formatOutput(10, 12, 29, 10);

		checkIfCorrect(correctOutput);
		
	}

	// Edge case 40 should produce a regular gem.
	@Test
	public void testWidth40() {
		GemShape ourSmallGem = new GemShape(10, 10, 5, 5, 40, 5);
		ourSmallGem.draw(_painter);
		
		String[] correctOutput = new String[NUMBER_OF_SIDES];
		
		correctOutput[0] = formatOutput(30, 10, 30, 10);
		correctOutput[1] = formatOutput(30, 10, 50, 12);
		correctOutput[2] = formatOutput(50, 12, 30, 15);
		correctOutput[3] = formatOutput(30, 15, 30, 15);
		correctOutput[4] = formatOutput(30, 15, 10, 12);
		correctOutput[5] = formatOutput(10, 12, 30, 10);
		
		checkIfCorrect(correctOutput);
	}
	
	// Edge case 41 should produce a regular gem.
	@Test
	public void testWidth41() {
		GemShape ourRegularGem = new GemShape(10, 10, 5, 5, 41, 5);
		ourRegularGem.draw(_painter);
		
		String[] correctOutput = new String[NUMBER_OF_SIDES];
		
		correctOutput[0] = formatOutput(30, 10, 31, 10);
		correctOutput[1] = formatOutput(31, 10, 51, 12);
		correctOutput[2] = formatOutput(51, 12, 31, 15);
		correctOutput[3] = formatOutput(31, 15, 30, 15);
		correctOutput[4] = formatOutput(30, 15, 10, 12);
		correctOutput[5] = formatOutput(10, 12, 30, 10);
		
		checkIfCorrect(correctOutput);
	}
	
	// Case 100 should produce a (big) regular gem.
	@Test
	public void testWidth100() {
		GemShape ourRegularGem = new GemShape(10, 10, 5, 5, 100, 5);
		ourRegularGem.draw(_painter);
		
		String[] correctOutput = new String[NUMBER_OF_SIDES];
		
		correctOutput[0] = formatOutput(30, 10, 90, 10);
		correctOutput[1] = formatOutput(90, 10, 110, 12);
		correctOutput[2] = formatOutput(110, 12, 90, 15);
		correctOutput[3] = formatOutput(90, 15, 30, 15);
		correctOutput[4] = formatOutput(30, 15, 10, 12);
		correctOutput[5] = formatOutput(10, 12, 30, 10);
		
		checkIfCorrect(correctOutput);
	}


}
