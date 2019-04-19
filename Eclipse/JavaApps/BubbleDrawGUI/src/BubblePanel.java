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
import javax.swing.JCheckBox;

public class BubblePanel extends JPanel {

	private ArrayList<Bubble> bubbleList;
	private int size = 30;
	private Timer timer;
	// a constant is a "final int" in java
	private final int DELAY = 33; // 33 ms of delay for 30 fps
	private JTextField txtSize;
	private JTextField txtSpeed;
	private JCheckBox chkGroup;
	private JCheckBox chkPause;

	public BubblePanel() {
		// to conclude the constructor you need the parenthesis ();
		bubbleList = new ArrayList<Bubble>();
		addMouseListener(new BubbleListener());
		addMouseMotionListener(new BubbleListener());
		addMouseWheelListener(new BubbleListener());
		
		timer = new Timer(DELAY, new BubbleListener());
		
		setBackground(new Color(204, 255, 255));
		setPreferredSize(new Dimension(720,400));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		add(panel);
		
		JLabel lblDotSize = new JLabel("Dot Size: ");
		lblDotSize.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDotSize);
		
		txtSize = new JTextField();
		txtSize.setHorizontalAlignment(SwingConstants.CENTER);
		txtSize.setText("30");
		panel.add(txtSize);
		txtSize.setColumns(3);
		
		JLabel lblAnimationSpeedfps = new JLabel("Animation Speed (fps): ");
		panel.add(lblAnimationSpeedfps);
		
		txtSpeed = new JTextField();
		txtSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		txtSpeed.setText("30");
		panel.add(txtSpeed);
		txtSpeed.setColumns(2);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the bubble size
				int newSize = Integer.parseInt(txtSize.getText());
				// makes sure the size does not go under 1
				if (newSize < 1) {
					txtSize.setText("" + 0);
					newSize = 0;
				}
				//get animation speed
				int newSpeed = Integer.parseInt(txtSpeed.getText());
				// makes sure the speed does not go under 1
				if (newSpeed < 1) {
					txtSpeed.setText("" + 0);
					newSpeed = 0;
				}
				// set bubble size
				size = newSize;
				// set bubble speed
				timer.setDelay(1000/newSpeed);
			}
		});
		panel.add(btnUpdate);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bubbleList = new ArrayList<Bubble>();
				repaint();
			}
		});
		
		chkGroup = new JCheckBox("Group Bubbles");
		chkGroup.setBackground(Color.CYAN);
		panel.add(chkGroup);
		
		chkPause = new JCheckBox("Pause");
		chkPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// action performed is when I "click" the checkbox
				if(chkPause.isSelected()) {
					timer.stop();
				}
				else {
					timer.start();
				}
					
			}
		});
		chkPause.setBackground(Color.CYAN);
		panel.add(chkPause);
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
		// page.setColor(Color.GREEN);
		// page.drawString("Count: " + bubbleList.size(), 5, 15);
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
			//timer.stop();
			// We want to add to the bubbleList my mouse location
			bubbleList.add(new Bubble(e.getX(),e.getY(),size));
			repaint();
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// Starts the animation when the mouse is released
			// timer.start();
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
			
			// check to see if the Group Bubbles checkbox is checked
			
			if (chkGroup.isSelected()) {
				// set the xspeed and yspeed of this bubble to the previous speed
				// "(bubbleList.size() - 1)" is the current bubble and "(bubbleList.size() - 2)" is the previous one
				bubbleList.get(bubbleList.size() - 1).xspeed = bubbleList.get(bubbleList.size() - 2).xspeed;				
				bubbleList.get(bubbleList.size() - 1).yspeed = bubbleList.get(bubbleList.size() - 2).yspeed;
			}
				
			repaint();
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			// Change the bubble size whenever the mouse wheel is moved
			// Also forbid the size to be less than 1
			if (size >= 0) {
				size -= e.getWheelRotation();
			}
			else {
				size = 0;
			}
			
			
			txtSize.setText("" + size);
			
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
			//			page.fillOval(bubble.x - bubble.size/2, bubble.y - bubble.size/2, bubble.size, bubble.size);
			if (x <= size / 2 || x + size / 2 >= getWidth()) {
				xspeed = -1 * xspeed;
			}
			if (y <= size /2  || y + size / 2 >= getHeight()) {
				yspeed = -yspeed;
			}
		}
		
	}

}