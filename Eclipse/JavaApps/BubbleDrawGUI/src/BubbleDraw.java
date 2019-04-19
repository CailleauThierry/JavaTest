import javax.swing.JFrame;

public class BubbleDraw extends JFrame {

	public static void main(String[] args) {
		// Setup the frame for our BubbleDraw app
		JFrame frame = new JFrame("Thierry and Aurelie's BubbleDraw GUI App :) ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new BubblePanel());
		frame.pack();
		frame.setVisible(true);
	}

}
