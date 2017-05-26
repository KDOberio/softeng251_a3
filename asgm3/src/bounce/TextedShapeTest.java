package bounce;

import org.junit.*;
import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TextedShapeTest {
	List<Shape> _shapes;
	int _numSetTextInvocations;
	@Before
	public void setUp() {
		
		_shapes = new ArrayList<Shape>();
		NestingShape nest1 = new NestingShape(20, 20, 1, 1, 200, 200);
		NestingShape nest2 = new NestingShape(90, 75, 1, -2, 100, 100);
		NestingShape nest3 = new NestingShape(2, 9, 1, 0, 50, 75);
		
		OvalShape ovalText = new OvalShape(2, 2, 10, 9, 9, 9);
		GemShape gemPalindrome = new GemShape(100, 100, 4, -3, 51, 39);
		DynamicRectangleShape dyRect = new DynamicRectangleShape(10, 10, 1, -1, 10, 20, Color.CYAN.darker());
		FallingBallShape ball = new FallingBallShape(54, 27, 2, 15, 34);
		RectangleShape rect = new RectangleShape(45, 34, 0, 12, 15, 75);

		nest3.add(dyRect);
		nest1.add(nest2);
		nest2.add(nest3);
		
		_shapes.add(nest1);
		_shapes.add(ovalText);
		_shapes.add(gemPalindrome);
		_shapes.add(ball);
		_shapes.add(rect);
		
		nest1.setText("a man a plan a canal panama");
		dyRect.setText("You are here");
		ovalText.setText("toot");
		gemPalindrome.setText("Je suis Catherine, Reine Mere de France");
		rect.setText("Tyrannosaurus rect");
		ball.setText("bol");
		nest2.setText("running out of ideas");
		
		// Check above to see you set the same number of times.
		_numSetTextInvocations = 7;
	}

	@Test
	public void testEverythingIsPainted() {
		MockPainter painter = new MockPainter();
		
		// Progress the animation.
		for(Shape s: _shapes) {
			s.paint(painter);
		}
		
		if (_numSetTextInvocations != painter.countTextDraws()) {
			System.out.println(_numSetTextInvocations + " " + painter.countTextDraws());
			fail();
		}
	}

}
