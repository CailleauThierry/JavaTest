import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class GuessingGame01 extends JFrame {
	private JTextField txtGuess; // Text field for the User's guess
	private JLabel lblOutput; // this will make the output label accessible at the class level
	private int theNumber;    // the number we are trying to guess
	private int numberOfTry = 0;
	private int totalTries = 7;
	private int userChoice;
	
	public void checkGuess() { // method/function to check too high or too low
		// get the user's guess
		String guessText = txtGuess.getText();
		String message = "";


		
		try {
			// check the guess for too high / too low
			int guess = Integer.parseInt(guessText);
	
			// too high
			if (guess > theNumber)
			{
				numberOfTry = numberOfTry + 1;
				message = guess + " was too high. ";
				message += "Only " + (totalTries - numberOfTry) + " tries left";
				lblOutput.setText(message);
			}
			// too low
			else if (guess < theNumber)
			{
				numberOfTry = numberOfTry + 1;
				message = guess + " was too low. ";
				message += "Only " + (totalTries - numberOfTry) + " tries left";
				lblOutput.setText(message);
			}
			// you must be right
			else // guessed correctly
			{
				numberOfTry = numberOfTry + 1;
				message = guess + 
						" was right! You guess the right number in " 
						+ numberOfTry 
						+ " attempt(s)!" ;
				lblOutput.setText(message);
				newGame();
			}
		}
		// catch all exception e, e lets you do something with it later
		catch(Exception e){
			lblOutput.setText("Enter a whole number between 1 and 100");
		}
		finally {
			if (numberOfTry == totalTries){
				lblOutput.setText("Failed to guess with " + totalTries + " attempts.");
				message = "Sorry, you ran out of tries. The correct number was: " + theNumber + ". Want to guess a new number?";
				userChoice = (int)javax.swing.JOptionPane.showConfirmDialog(null, message);
// 				javax.swing.JOptionPane.showConfirmDialog return value is 0 for "Yes", 1 for "No" an 2 for "Cancel"
				if (userChoice == 1) {
					System.exit(0);
				}
				else{
					newGame();
				}

				
			}
		
			//finally always happens no matter what
			txtGuess.requestFocus();
			txtGuess.selectAll();
			
		}	

		
	}
	
	public void newGame() { // create a new random number between 1 and 100
		theNumber = (int)(Math.random() * 100 + 1);
		// can use 50 as a 1st try guessed right value 
		//		theNumber = 50;
		totalTries = 7;
		numberOfTry = 0;
	}
	
	public GuessingGame01() {
		setTitle("Thierry Cailleau's Guessing Game");
		getContentPane().setBackground(new Color(51, 102, 255));
		setBackground(new Color(51, 102, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JLabel lblMrCailleauHilo = new JLabel("Mr. Cailleau's Hi-Lo Guessing Game");
		lblMrCailleauHilo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMrCailleauHilo.setBounds(0, 30, 434, 24);
		lblMrCailleauHilo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblMrCailleauHilo);
		
		JButton btnGuessNow = new JButton("Guess Now!");
		btnGuessNow.setBackground(new Color(51, 204, 255));
		btnGuessNow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkGuess();
			}
		});
		btnGuessNow.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnGuessNow.setBounds(163, 144, 108, 23);
		getContentPane().add(btnGuessNow);
		
		lblOutput = new JLabel("Enter a number and click \"Guess Now!\" You have " + totalTries + " tries!");
		lblOutput.setForeground(new Color(0, 51, 51));
		lblOutput.setBackground(new Color(51, 204, 153));
		lblOutput.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setBounds(10, 197, 414, 30);
		getContentPane().add(lblOutput);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(new Color(51, 204, 153));
		panel.setBounds(38, 84, 358, 30);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblGuessANumber = new JLabel("Guess a number between 1 and 100:");
		lblGuessANumber.setBackground(new Color(0, 204, 153));
		lblGuessANumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGuessANumber.setBounds(0, 8, 240, 15);
		panel.add(lblGuessANumber);
		lblGuessANumber.setHorizontalAlignment(SwingConstants.RIGHT);
		
		txtGuess = new JTextField();
		txtGuess.setBackground(new Color(204, 255, 255));
		txtGuess.setHorizontalAlignment(SwingConstants.CENTER);
		txtGuess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkGuess();
			}
		});
		txtGuess.setBounds(267, 6, 81, 20);
		panel.add(txtGuess);
		txtGuess.setColumns(10);
	}

	public static void main(String[] args) {
		GuessingGame01 theGame = new GuessingGame01();
		theGame.newGame();
		theGame.setSize(new Dimension(430, 330));
		theGame.setVisible(true);
	}
}
