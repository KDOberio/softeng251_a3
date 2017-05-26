package bounce;

import java.awt.Color;

/** 
 * Interface to represent a type that offers primitive drawing methods.
 * 
 * @author Ian Warren
 * 
 */
public interface Painter {
	/**
	 * Draws a rectangle. Parameters x and y specify the top left corner of the
	 * rectangle. Parameters width and height specify its width and height.
	 */
	public void drawRect(int x, int y, int width, int height);
	
	/**
	 * Draws an oval. Parameters x and y specify the top left corner of the
	 * oval. Parameters width and height specify its width and height.
	 */
	public void drawOval(int x, int y, int width, int height);
	
	/**
	 * Draws a line. Parameters x1 and y1 specify the starting point of the 
	 * line, parameters x2 and y2 the ending point.
	 */
	public void drawLine(int x1, int y1, int x2, int y2);
	
	/**
	 * Draws a filled rectangle. Parameters x and y specify the top left corner of the
	 * rectangle. Parameters width and height specify its width and height.
	 */
	public void fillRect(int x, int y, int width, int height);
	
	/**
	 * Gets the colour from java.awt.Color.
	 * @see java.awt.Color.getColor()
	 */
	public void getColor();
	
	/**
	 * Sets the colour for the shape being drawn.
	 * @param c The colour desired for the shape.
	 * @see java.awt.Color.setColor()
	 */
	public void setColor(Color c);
	
	/**
	 * Draws a filled oval. Parameters x and y specify the top left corner of the
	 * rectangle. Parameters width and height specify its width and height.
	 */
	public void fillOval(int x, int y, int width, int height);
	
	public void setDefaultColor();
	
	public void translate(int x, int y);
	
	public void drawCentredText(String string, Shape shape);
}
