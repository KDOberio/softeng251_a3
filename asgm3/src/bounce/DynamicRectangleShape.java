package bounce;
import java.awt.Color;

public class DynamicRectangleShape extends RectangleShape {
	/**
	 * @see bounce.RectangeShape#RectangleShape()
	 */
	public DynamicRectangleShape() {
		super();
	}
	
	/**
	 * @see bounce.RectangleShape#RectangleShape(int x, int y, int deltaX, int deltaY)
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY) {
		super(x,y,deltaX,deltaY);
		_color = Color.BLACK;
	}
	
	/**
	 * @see bounce.RectangleShape#RectangleShape(int x, int y, int deltaX, int deltaY, int width, int height)
	 */
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x,y,deltaX,deltaY,width,height);
		_color = Color.BLACK;
	}
	
	public DynamicRectangleShape(int x, int y, int deltaX, int deltaY, int width, int height, Color color) {
		super(x,y,deltaX,deltaY,width,height);
		_color = color;
	}
	
	/**
	 * Paints the supplied object. 
	 * Upon bouncing on the left or right wall, this object will get filled with the appropriate colour
	 * (black if no colour is given). In corner collisions, the rectangle is still filled.
	 * @see bounce.RectangleShape.paint()
	 */
	@Override
	protected void draw(Painter painter) {
		painter.setColor(_color);
		if ((_latestWallHit == LatestWallHit.LEFT) |(_latestWallHit == LatestWallHit.RIGHT)) {
			painter.drawRect(_x, _y, _width, _height);
			painter.fillRect(_x, _y, _width, _height);
		} else {
			super.draw(painter);
		}
		
		painter.setDefaultColor();
	}
	
	/**
	 * Overrides the move() method in the Shape class. Ensures that
	 * when a corner bounce is detected, vertical walls are prioritised
	 * over horizontal walls.
	 */
	public void move(int width, int height) {
		super.move(width, height);
		
		// The conditionals represent what happens in shape after
		// a bounce is detected.
		if (_x == 0) {
			_latestWallHit = LatestWallHit.LEFT;
		} else if (_x == width - _width) {
			_latestWallHit = LatestWallHit.RIGHT;
		}
	}



}
