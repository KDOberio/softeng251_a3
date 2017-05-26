package bounce;

import java.awt.Color;

/**
 * Implementation of the Painter interface that does not actually do any
 * painting. A MockPainter implementation responds to Painter requests by
 * logging simply logging them. The contents of a MockPainter object's
 * log can be retrieved by a call to toString() on the MockPainter.
 * 
 * @author Ian Warren
 * 
 */
public class MockPainter implements Painter {
	// Internal log.
	private StringBuffer _log = new StringBuffer();
	private Color _color;
	private int _drewTextCount = 0;
	/**
	 * Returns the contents of this MockPainter's log.
	 */
	public String toString() {
		return _log.toString();
	}

	/**
	 * Logs the drawRect call.
	 */
	public void drawRect(int x, int y, int width, int height) {
		// _log.append("(rectangle, x = " + x + ", y = " + y + ", width = " + width + ", height = " + height + ")");
		_log.append("(rectangle " + x + "," + y + "," + width + "," + height + ")");

	}
	
	/**
	 * Logs the drawOval call.
	 */
	public void drawOval(int x, int y, int width, int height) {
		_log.append("(oval, x =  " + x + ", y = " + y + ", width = " + width + ", height = " + height + ")");
	}

	/**
	 * Logs the drawLine call.
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_log.append("(line, x1 = " + x1 + ", y1 = " + y1 + ", x2 = " + x2 + ", y2 = " + y2 + ")");
	}

	@Override
	public void fillRect(int x, int y, int width, int height) {
		_log.append("(filled rectangle colour = " + _color +")");
		
	}

	@Override
	public void getColor() {
		_log.append(_color);
	}

	@Override
	public void setColor(Color c) {
		_color = c;
		
	}
	
	public void setDefaultColor() {
		
	}
	
	public void fillOval(int x, int y, int width, int height) {
		_log.append("(filled oval colour = " + _color + ")");
	}

	public void translate(int x, int y) {
		//_log.append("New coordinates defined: (" + x + ", "+ y + ")");
	}

	@Override
	public void drawCentredText(String string, Shape shape) {
		_log.append("Drew string: " + string + " at (" + shape + ")");
		_drewTextCount++;
		
	}
	
	public int countTextDraws() {
		return _drewTextCount;
	}
}