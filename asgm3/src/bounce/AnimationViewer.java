package bounce;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;



/**
 * Simple GUI program to show an animation of shapes. Class AnimationViewer is
 * a special kind of GUI component (JPanel), and as such an instance of 
 * AnimationViewer can be added to a JFrame object. A JFrame object is a 
 * window that can be closed, minimised, and maximised. The state of an
 * AnimationViewer object comprises a list of Shapes and a Timer object. An
 * AnimationViewer instance subscribes to events that are published by a Timer.
 * In response to receiving an event from the Timer, the AnimationViewer iterates 
 * through a list of Shapes requesting that each Shape paints and moves itself.
 * 
 * @author Ian Warren
 * 
 */
@SuppressWarnings("serial")
public class AnimationViewer extends JPanel implements ActionListener {
	// Frequency in milliseconds for the Timer to generate events.
	private static final int DELAY = 20;

	// Collection of Shapes to animate.
	private List<Shape> _shapes;

	private Timer _timer = new Timer(DELAY, this);

	/**
	 * Creates an AnimationViewer instance with a list of Shape objects and 
	 * starts the animation.
	 */
	public AnimationViewer() {
		_shapes = new ArrayList<Shape>();
	
		// Populate the list of Shapes.
		// HERE IS WHERE YOU ADD CHEMISTRY - the one with the shapes and stuff
		NestingShape nest1 = new NestingShape(20, 20, 1, 1, 200, 200);
		NestingShape nest2 = new NestingShape(90, 75, 1, -2, 100, 100);
		NestingShape nest3 = new NestingShape(2, 9, 1, 0, 50, 75);
		NestingShape nest4 = new NestingShape(10, 10, 0, 1, 10, 10);
		nest4.add(new GemShape(2, 2, 0, 1, 5, 5));
		DynamicRectangleShape rect = new DynamicRectangleShape(10, 10, 1, -1, 10, 20, Color.CYAN.darker());
		nest3.add(rect);
		nest1.add(nest2);
		nest2.add(nest3);
		nest3.add(nest4);
		
		OvalShape oval = new OvalShape(0, 0, 1, 1, 25, 10);
		FallingBallShape ball = new FallingBallShape();
		nest2.add(oval);
		nest1.add(ball);
		
		
		_shapes.add(nest1);
		_shapes.add(new RectangleShape(0, 0, 2, 3));
		_shapes.add(new RectangleShape(10, 10, 5, 7));
		
		_shapes.add(new OvalShape());
		OvalShape ovalText = new OvalShape(2, 2, 10, 9, 9, 9);
		ovalText.setText("Bu tushunarli kim ?");
		_shapes.add(ovalText);
		
		GemShape gemPalindrome = new GemShape(100, 100, 4, -3, 51, 39);
		gemPalindrome.setText("meg gem");
		_shapes.add(gemPalindrome);
		_shapes.add(new GemShape(50, 50, 1, 1, 38, 38));
		_shapes.add(new GemShape(25, 10, 5, 3, 40, 70));
		_shapes.add(new GemShape(30, 10, 5, 3, 60, 70));
		
		_shapes.add(new DynamicRectangleShape());
		_shapes.add(new DynamicRectangleShape(50, 50, -5, 15, 50, 50));
		DynamicRectangleShape dynamicPalindrome = new DynamicRectangleShape(100, 50, 10, -5, 40, 30, Color.BLUE);
		dynamicPalindrome.setText("Screw the rules");
		_shapes.add(dynamicPalindrome);
		_shapes.add(new DynamicRectangleShape(100, 50, 0, -5, 40, 30));
		_shapes.add(new DynamicRectangleShape(100, 100, 1, 5, 50, 50, Color.RED));
		
		_shapes.add(new FallingBallShape());
		_shapes.add(new FallingBallShape(50, 40, 5, -7, 50));
		_shapes.add(new FallingBallShape(67, 44, -5, 10, 35, Color.GREEN.darker()));
		_shapes.add(new FallingBallShape(0, 0, 15, 11, 70, Color.BLUE.darker()));
		_shapes.add(new FallingBallShape(0, 50, 5, -20, 55, Color.RED.darker()));
		FallingBallShape ballRed = new FallingBallShape(500 - 0, 500 - 50, -5, 20, 55, Color.RED);
		ballRed.setText("palindrome emordnilap");
		_shapes.add(ballRed);
		
		RectangleShape textShape = new RectangleShape(200, 300, -3, 4, 69, 74);
		textShape.setText("sore was I ere I saw eros");
		_shapes.add(textShape);
		nest2.setText("noel sees leon");
		oval.setText("racecar");
		nest1.setText("a man a plan a canal panama");
		
		DynamicRectangleShape framePretender = new DynamicRectangleShape(0, 0, -3, 5, 300, 300, Color.GRAY.darker());
		framePretender.setText("Tyrannosaurus Rect");
		_shapes.add(framePretender);
		
		// Start the animation.
		_timer.start();
	}

	/**
	 * Called by the Swing framework whenever this AnimationViewer object
	 * should be repainted. This can happen, for example, after an explicit 
	 * repaint() call or after the window that contains this AnimationViewer 
	 * object has been opened, exposed or moved.
	 * 
	 */
	public void paintComponent(Graphics g) {
		// Call inherited implementation to handle background painting.
		super.paintComponent(g);
		
		// Calculate bounds of animation screen area.
		int width = getSize().width;
		int height = getSize().height;
		
		// Create a GraphicsPainter that Shape objects will use for drawing.
		// The GraphicsPainter delegates painting to a basic Graphics object.
		Painter painter = new GraphicsPainter(g);
		
		// Progress the animation.
		for(Shape s : _shapes) {
			s.paint(painter);
			s.move(width, height);
		}
	}

	/**
	 * Notifies this AnimationViewer object of an ActionEvent. ActionEvents are
	 * received by the Timer.
	 */
	public void actionPerformed(ActionEvent e) {
		// Request that the AnimationViewer repaints itself. The call to 
		// repaint() will cause the AnimationViewer's paintComponent() method 
		// to be called.
		repaint();
	}
	
	
	/**
	 * Main program method to create an AnimationViewer object and display this
	 * within a JFrame window.
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Animation viewer");
				frame.add(new AnimationViewer());
		
				// Set window properties.
				frame.setSize(500, 500);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}
}
