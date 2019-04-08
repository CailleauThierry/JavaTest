import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SecretMessagesGUI extends JPanel {
	private JTextField txtKey;
	private JTextArea txtOut; // to declare it at the class level so it can be seen by the entire class (scope)
	private JTextArea txtIn; // private means no other apllication can read it
	private JSlider slider;
	
	public String encode(String message, int k) {
		String out="";
		char key = (char)k;
		
		for (int x=0 ; x < message.length(); x++)
		{
			char in = message.charAt(x);
					
			if (in >= 'A' && in <= 'Z') {
				in += key;
				if (in > 'Z')
				{
					//if the character went past Z we wrap it around back to the beginning of the alphabet i.e. back 26 characters
					in -= 26;
				}
				if (in < 'A') {
					//if the character went less than A, we bring it back forward 26 characters
					in += 26;
				}
			}
		   if (in >= 'a' && in <= 'z') {
				in += key;
				if (in > 'z')
				{
					//if the character went past z we wrap it around back to the beginning of the alphabet i.e. back 26 characters
					in -= 26;
				}
				if (in < 'a') {
					//if the character went less than a, we bring it back forward 26 characters
					in += 26;
				}
		   }
		   if (in >= '0' && in <= '9') {
				in += key;
				if (in > '9')
				{
					//if the character went past 9 we wrap it around back to the beginning of the number range i.e. back 10 characters
						in -= 10;
				}
				if (in < '0') {
					//if the character went less than 0, we bring it back forward 10 characters
						in += 10;
				}
			}
			
			out += in;	
			
		}
		return out;
	}
	
	public SecretMessagesGUI() {
		setLayout(null);
		
		txtIn = new JTextArea();
		txtIn.setBounds(10, 11, 430, 110);
		add(txtIn);
		
		JLabel lblKey = new JLabel("Key: ");
		lblKey.setBounds(183, 142, 32, 14);
		add(lblKey);
		
		txtKey = new JTextField();
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("0");
		txtKey.setBounds(215, 139, 38, 20);
		add(txtKey);
		txtKey.setColumns(10);
		
		JButton btnNewButton = new JButton("ENCODE / DECODE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get the message from txtIn
				String message = txtIn.getText();
				
				// get the key amount from txtKey
				int key = Integer.parseInt(txtKey.getText());
				
				// encode that message with that key
				String output = encode(message, key);
				
				// show the message in txtOut
				txtOut.setText(output);
				
				
			}
		});
		btnNewButton.setBounds(255, 138, 171, 23);
		add(btnNewButton);
		
		txtOut = new JTextArea();
		txtOut.setBounds(10, 179, 430, 110);
		add(txtOut);
		setPreferredSize(new Dimension(450,320));
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// anonymous inner class, adding aan emplty string to it ot's like casting to a String
				txtKey.setText("" + slider.getValue());
			}
		});
		slider.setPaintLabels(true);
		slider.setMajorTickSpacing(13);
		slider.setSnapToTicks(true);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setValue(0);
		slider.setMinimum(-13);
		slider.setMaximum(13);
		slider.setBounds(20, 132, 148, 42);
		add(slider);
	}

	public static void main(String[] args) {
		// To make JPanel show we need to make a windows Frame, with a JFRame
		JFrame frame = new JFrame ("Thierry's Secret Message App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // let's us close the application when we exit the windows
		
		// add the encoder Panel to the Frame
		frame.getContentPane().add(new SecretMessagesGUI());
		
		// prepare and show th eframe
		frame.pack();
		frame.setVisible(true);
	
		
	}
}
