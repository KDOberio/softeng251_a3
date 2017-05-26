package bounce;

import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Font;

/**
 * Implementation of the Painter interface that delegates drawing to a
 * java.awt.Graphics object.
 * 
 * @author Ian Warren
 * 
 */
public class GraphicsPainter implements Painter {
	// Delegate object.
	private Graphics _g;
	private final static Color DEFAULT_COLOR = Color.BLACK;

	/**
	 * Creates a GraphicsPainter object and sets its Graphics delegate.
	 */
	public GraphicsPainter(Graphics g) {
		this._g = g;
	}

	/**
	 * @see bounce.Painter.drawRect
	 */
	public void drawRect(int x, int y, int width, int height) {
		_g.drawRect(x, y, width, height);
	}

	/**
	 * @see bounce.Painter.drawOval
	 */
	public void drawOval(int x, int y, int width, int height) {
		_g.drawOval(x, y, width, height);
	}

	/**
	 * @see bounce.Painter.drawLine
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		_g.drawLine(x1, y1, x2, y2);
	}
	
	/**
	 * @see bounce.Painter.fillRect
	 */
	public void fillRect(int x, int y, int width, int height) {
		_g.fillRect(x, y, width, height);
		
	}
	
	/**
	 * @see bounce.Painter.fillOval
	 */
	public void fillOval(int x, int y, int width, int height) {
		_g.fillOval(x, y, width, height);
	}
	
	/**
	 * 
	 */
	public void setColor(Color c) {
		_g.setColor(c);
	}
	
	/**
	 * 
	 */
	public void getColor() {
		_g.getColor();
	}
	
	public void setDefaultColor() {
		_g.setColor(DEFAULT_COLOR);
	}

	@Override
	public void translate(int x, int y) {
		_g.translate(x, y);
		
	}
	
	public void drawCentredText(String text, Shape shape) {
		FontMetrics fontMetrics = _g.getFontMetrics();
		int ascent = fontMetrics.getAscent();
		int descent = fontMetrics.getDescent();
				
		int x = shape.x() + (shape.width() / 2) - fontMetrics.stringWidth(text) / 2;
		int y = shape.y() + (shape.height() / 2);
		
		if (ascent > descent) {
			y += (ascent - descent) / 2;
		} else if (ascent < descent) {
			y -= (ascent - descent) / 2;
		}
	    _g.drawString(text, x, y);
	}
	
}
