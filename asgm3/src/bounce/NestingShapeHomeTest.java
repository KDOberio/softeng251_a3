package bounce;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class NestingShapeTest {
	private HashMap<String, Shape> _shapes = new HashMap<String, Shape>();
	
	@Before
	public void setUp() {
		NestingShape nest1 = new NestingShape(20, 20, 1, 1, 200, 200);
		NestingShape nest2 = new NestingShape(50, 50, 5, -7, 100, 100);
		NestingShape nest3 = new NestingShape(2, 9, 3, 8, 50, 75);
		DynamicRectangleShape rect = new DynamicRectangleShape(10, 10, 1, -1, 10, 20, Color.CYAN.darker());

		nest3.add(rect);
		nest1.add(nest2);
		nest2.add(nest3);
		
		OvalShape oval = new OvalShape(0, 0, 1, 1, 25, 10);
		FallingBallShape ball = new FallingBallShape();
		nest2.add(oval);
		nest1.add(ball);
		
		_shapes.put("n1", nest1);
		_shapes.put("n2", nest2);
		_shapes.put("n3", nest3);
		
		_shapes.put("rect", rect);
		_shapes.put("oval", oval);
		_shapes.put("ball", ball);
	}

	@Test
	public void testParent() {
		assertSame(_shapes.get("oval").parent(), _shapes.get("n2"));
		assertSame(_shapes.get("n1").parent(), null);
		assertSame(_shapes.get("n2").parent(), _shapes.get("n1"));
		assertSame(_shapes.get("rect").parent(), _shapes.get("n3"));
		assertSame(_shapes.get("n3").parent(), _shapes.get("n2"));
		assertSame(_shapes.get("ball").parent(), _shapes.get("n1"));
	}
	 
	@Test
	public void testPath() {
		List<Shape> n1Path = Arrays.asList(_shapes.get("n1"));
		List<Shape> n2Path = Arrays.asList(_shapes.get("n1"), _shapes.get("n2"));
		List<Shape> n3Path = Arrays.asList(_shapes.get("n1"), _shapes.get("n2"), _shapes.get("n3"));
		List<Shape> rectPath = Arrays.asList(_shapes.get("n1"), _shapes.get("n2"), _shapes.get("n3"), _shapes.get("rect"));
		List<Shape> ovalPath = Arrays.asList(_shapes.get("n1"), _shapes.get("n2"), _shapes.get("oval"));
		List<Shape> ballPath = Arrays.asList(_shapes.get("n1"), _shapes.get("ball"));
		
		assertEquals(new ArrayList<Shape>(_shapes.get("n1").path()), n1Path);
		assertEquals(new ArrayList<Shape>(_shapes.get("n2").path()), n2Path);
		assertEquals(new ArrayList<Shape>(_shapes.get("n3").path()), n3Path);
		assertEquals(new ArrayList<Shape>(_shapes.get("rect").path()), rectPath);
		assertEquals(new ArrayList<Shape>(_shapes.get("oval").path()), ovalPath);
		assertEquals(new ArrayList<Shape>(_shapes.get("ball").path()), ballPath);
		
	}
	
	@Test
	public void testTooWide() {
		try {
			NestingShape nest = new NestingShape(50, 50, 5, -7, 100, 100);
			RectangleShape tooWide = new RectangleShape(90, 90, 1, 0, 11, 9);
			nest.add(tooWide);
			fail();
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void testTooTall() {
		try {
			NestingShape nest = new NestingShape(50, 50, 5, -7, 100, 100);
			RectangleShape tooTall = new RectangleShape(90, 90, 1, 0, 9, 11);
			nest.add(tooTall);
			fail();
		} catch (IllegalArgumentException e) {
			
		}
	}
	
	@Test
	public void CannotChangeParent() {
		try {
			NestingShape nest = (NestingShape) _shapes.get("n2");
			nest.add(_shapes.get("ball"));
			fail();
		} catch (IllegalArgumentException e) {
			
		}
	}


}
