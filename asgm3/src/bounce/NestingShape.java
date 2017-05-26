package bounce;
import java.util.ArrayList;

public class NestingShape extends RectangleShape {
	private ArrayList<Shape> _childrenShapes = new ArrayList<Shape>();
	
	/**
	 * Creates a NestingShape object with default values for state.
	 */
	public NestingShape() {
		super();
	};
	
	/**
	 * Creates a  NestingShape object with specified location values,
	 * default values for other state items.
	 */
	public NestingShape(int x, int y) {
		super(x, y);
	}
	
	/**
	 * Creates a NestingShape with specified values for location, velocity
	 * and direction. Non-specified state items take on default values.
	 */
	public NestingShape(int x, int y, int deltaX, int deltaY) {
		super(x, y, deltaX, deltaY);
	}

	/**
	 * Creates a NestingShape with specified values for location, velocity, direction, width
	 * and height.
	 */
	public NestingShape(int x, int y, int deltaX, int deltaY, int width, int height) {
		super(x, y, deltaX, deltaY, width, height);
	}
	
	
	/**
	 * Paints a NestingShape object by drawing a rectangle around the edge of its bounding box.
	 * The NestingShape object's children are then painted.
	 */
	protected void draw(Painter painter) {
		super.draw(painter);
		painter.translate(_x, _y);
		
		// Paints each child shape within the Nesting shape and moves it;
		// emulates a frame.
		for(Shape shape : _childrenShapes) {
			shape.paint(painter);
			shape.move(_width, _height);
		}
		
		// To return to origin of previous shape, move the coordinates back by negative.
		painter.translate(-_x, -_y);
	}
	
	/**
	 * Attempts to add a Shape to a NestingShape object. If successful, a
	 * two-way link is established between the NestingShape and the newly
	 * added Shape. Note that this method has package visibility - for reasons
	 * that will become apparent in Bounce III.
	 * @param shape the shape to be added.
	 * @throws IllegalArgumentException if an attempt is made to add a Shape 
	 * to a NestingShape instance where the Shape argument is already a child
	 * within a NestingShape instance. An IllegealArgumentException is also 
	 * thrown when an attempt is made to add a shape that will not fit within 
	 * the bounds of the proposed NestingShape object.
	 */
	void add(Shape shape) throws IllegalArgumentException {
		if (shape.parent() != null) {
			throw new IllegalArgumentException();
		}
		
		int shapesRightEdge = shape.x() + shape.width();
		int shapesBottomEdge = shape.y() + shape.height();
		
		if (shapesRightEdge > _width || shapesBottomEdge > _height) {
			throw new IllegalArgumentException();
		}
		
		_childrenShapes.add(shape);
		shape.isNowChildOf(this);
	}
	
	/**
	 * Removes a particular Shape from a NestingShape instance. Once removed,
	 * the two-way link between the NestingShape and its former child is destroyed.
	 * This method has no effect if the Shape specified to remove is not a child 
	 * of the NestingShape. Note that this method has package visibility - for reasons
	 * that becomes apparent later.
	 * @param shape the shape to be removed.
	 */
	void remove(Shape shape) {
		int indexForRemoval = indexOf(shape);
		if (indexForRemoval >= 0) {
			_childrenShapes.remove(indexForRemoval);
			shape.isNowChildOf(null);
		}
	}
	
	/**
	 * Returns the shape at a specified position within a NestingShape. If the position specified
	 * is less than zero or greater than the number of children stored in the NestingShape less one
	 * this method throws and IndexOutOfBoundsException.
	 * @param index the specified index position.
	 */
	public Shape shapeAt(int index) throws IndexOutOfBoundsException {
		Shape shapeRequested = _childrenShapes.get(index);
		return shapeRequested;
	}
	
	/**
	 * Returns the number of children contained within a NestingShape object.
	 * Note that this method is not recursive - it simply returns the number of 
	 * children at the top level within the callee NestingShape object.
	 */
	public int shapeCount() {
		return _childrenShapes.size();
	}
	
	/**
	 * Returns the index of a specified child within a NestingShape object.
	 * If the Shape specified is not actually a child of the NestingShape
	 * this method returns -1; otherwise the value returned is in the range 
	 * 0 .. shapeCount() - 1;
	 * @param shape the shape whose index position within the NestingShape is requested.
	 */
	public int indexOf(Shape shape) {
		return _childrenShapes.indexOf(shape);
	}
	
	/**
	 * Returns true if the Shape argument is a child of the NestingShape
	 * object on which this method is called, false otherwise.
	 */
	public boolean contains(Shape shape) {
		return _childrenShapes.contains(shape);
	}

}