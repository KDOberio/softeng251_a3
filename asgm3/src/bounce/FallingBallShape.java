package bounce;
import java.awt.Color;
/**
 * @author kobe456
 *
 */
public class FallingBallShape extends Shape {
	private Color _color;
	private int _maxDeltaY;
	
	public FallingBallShape() {
		super(DEFAULT_X_POS, DEFAULT_Y_POS, DEFAULT_DELTA_X, DEFAULT_DELTA_Y, DEFAULT_WIDTH, DEFAULT_WIDTH);
		_maxDeltaY = _deltaY;
		_deltaY = 0;
	}
	
	public FallingBallShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY, DEFAULT_WIDTH, DEFAULT_WIDTH);
		_maxDeltaY = _deltaY;
		_deltaY = 0;
	}
	
	public FallingBallShape(int x, int y, int deltaX, int deltaY, int radius) {
		super(x, y, deltaX, deltaY, radius, radius);
		_maxDeltaY = _deltaY;
		_deltaY = 0;
	}
	
	public FallingBallShape(int x, int y, int deltaX, int deltaY, int radius, Color color) {
		super(x, y, deltaX, deltaY, radius, radius);
		_color = color;
		_maxDeltaY = _deltaY;
		_deltaY = 0;
	}

	/**
	 * Paints the ball. When the shape bounces vertically, the shape is filled.
	 * If it hits the left or right wall, it becomes transparent.
	 * @see bounce.Shape.paint() 
	 */
	@Override
	protected void draw(Painter painter) {
		painter.setColor(_color);
		painter.drawOval(_x, _y, _width, _height);
		if (_latestWallHit == LatestWallHit.TOP || _latestWallHit == LatestWallHit.BOTTOM) {
			painter.fillOval(_x, _y, _width, _height);
		}
		
		painter.setDefaultColor();
		
	}
	
	/**
	 * Bouncing balls always accelerate in the original direction of delta y. Upon touching a vertical
	 * wall, their velocity becomes the original delta y in the opposite direction.
	 * @see Shape.move()
	 */
	public void move(int width, int height) {
		// For every move tick, deltaY increases by 1 in its original direction.
		// i.e. its velocity is pulled back down into the ground.
		if (Math.abs(_deltaY) >= 0) {
			_deltaY += _maxDeltaY / Math.abs(_maxDeltaY);
		}
		int nextX = _x + _deltaX;
		int nextY = _y + _deltaY;
		
		
		if (nextX <= 0) {
			nextX = 0;
			_deltaX = -_deltaX;
			_latestWallHit = LatestWallHit.LEFT;
//			_deltaY -= _maxDeltaY;
		} else if (nextX + _width >= width) {
			nextX = width - _width;
			_deltaX = -_deltaX;
			_latestWallHit = LatestWallHit.RIGHT;
//			_deltaY -= _maxDeltaY;

		}
		
		if (nextY <= 0) {
			nextY = 0;
			_deltaY = -_deltaY;
			_latestWallHit = LatestWallHit.TOP;
		} else if (nextY + _height >= height) {
			nextY = height - _height;
			_deltaY = -_deltaY;
			_latestWallHit = LatestWallHit.BOTTOM;
		}
		_x = nextX;
		_y = nextY;
	}

}
