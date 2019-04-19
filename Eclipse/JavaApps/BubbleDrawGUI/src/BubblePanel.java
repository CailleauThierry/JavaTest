import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class BubblePanel extends JPanel {

	private ArrayList<Bubble> bubbleList;
	private int size = 30;
	private Timer timer;
	// a constant is a "final int" in java
	private final int DELAY = 33; // 33 ms of delay for 30 fps
	private JTextField textField;
	private JTextField textField_1;

	public BubblePanel() {
		// to conclude the constructor you need the parenthesis ();
		bubbleList = new ArrayList<Bubble>();
		addMouseListener(new BubbleListener());
		addMouseMotionListener(new BubbleListener());
		addMouseWheelListener(new BubbleListener());
		
		timer = new Timer(DELAY, new BubbleListener());
		
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(600,400));
		
		JPanel panel = new JPanel();
		add(panel);
		
		JLabel lblDotSize = new JLabel("Dot Size: ");
		lblDotSize.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDotSize);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("30");
		panel.add(textField);
		textField.setColumns(3);
		
		JLabel lblAnimationSpeedfps = new JLabel("Animation Speed (fps): ");
		panel.add(lblAnimationSpeedfps);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setText("30");
		panel.add(textField_1);
		textField_1.setColumns(2);
		
		JButton btnUpdate = new JButton("Update");
		panel.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		panel.add(btnClear);
		// usually start the timer last
		timer.start(); // will fire the action listener
	}
	public void paintComponent(Graphics page){
		super.paintComponent(page);
		// Draw all Bubbles from bubbleList
		for (Bubble bubble:bubbleList) {
			page.setColor(bubble.color);
			page.fillOval(bubble.x - bubble.size/2, bubble.y - bubble.size/2, bubble.size, bubble.size);
		}
		
		// write the number of bubbles on screen
		page.setColor(Color.GREEN);
		page.drawString("Count: " + bubbleList.size(), 5, 15);
	}
		
	private class BubbleListener implements MouseListener,
											MouseMotionListener,
											MouseWheelListener,
											ActionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// Stops the animation when the mouse is pressed
			timer.stop();
			// We want to add to the bubbleList my mouse location
			bubbleList.add(new Bubble(e.getX(),e.getY(),size));
			repaint();
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// Starts the animation when the mouse is released
			timer.start();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// We want to add to the bubbleList my mouse location
			bubbleList.add(new Bubble(e.getX(),e.getY(),size));
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			// Change the bubble size whenever the mouse wheel is moved
			size -= e.getWheelRotation();
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// update the location of each bubble for the animation
			for (Bubble bubble:bubbleList)
				bubble.update();
			
			// repaint the screen
			repaint();
			
		}
		
	}
	
	private class Bubble{
		/** A Bubble needs and x, y location and a size */
		public int x;
		public int y;
		public int size;
		public Color color;
		public int xspeed;
		public int yspeed;
		private final int MAX_SPEED = 5;
		
		public Bubble(int newX, int newY, int newSize){
			x = newX;
			y = newY;
			size = newSize;
			// Math.random()*0.8) to make all the bubble visibly transparent
			color = new Color(	(float)Math.random(),
								(float)Math.random(),
								(float)Math.random(),
								(float)(Math.random()*0.8));
			xspeed = NonZeroRandomSpeed();
			yspeed = NonZeroRandomSpeed();
			
		}
		
		public int NonZeroRandomSpeed() {
			// Takes final int MAX_SPEED but makes sure to return a non-zero random number
			int returnedSpeed = 0;
			while (returnedSpeed == 0) {
				returnedSpeed = (int) (Math.random() * MAX_SPEED * 2  - MAX_SPEED);
			}
			return returnedSpeed;
		}
		
		public void update() {
			x += xspeed; 
			y += yspeed; 
			
			// collision detection part of the update, with the edges of the panel
			if (x < 0 || x > getWidth()) {
				xspeed = -1 * xspeed;
			}
			if (y < 0 || y > getHeight()) {
				yspeed = -yspeed;
			}
		}
		
	}

}