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

public class BubblePanel extends JPanel {

	private ArrayList<Bubble> bubbleList;
	private int size = 30;
	private Timer timer;
	// a constant is a "final int" in java
	private final int DELAY = 33; // 33 ms of delay for 30 fps

	public BubblePanel() {
		// to conclude the constructor you need the parenthesis ();
		bubbleList = new ArrayList<Bubble>();
		addMouseListener(new BubbleListener());
		addMouseMotionListener(new BubbleListener());
		addMouseWheelListener(new BubbleListener());
		
		timer = new Timer(DELAY, new BubbleListener());
		
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(600,400));
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
			// We want to add to the bubbleList my mouse location
			bubbleList.add(new Bubble(e.getX(),e.getY(),size));
			repaint();
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
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
			color = new Color(	(float)Math.random(),
								(float)Math.random(),
								(float)Math.random());
			xspeed = (int) (Math.random() * MAX_SPEED * 2  - MAX_SPEED);
			yspeed = (int) (Math.random() * MAX_SPEED * 2  - MAX_SPEED);
			
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