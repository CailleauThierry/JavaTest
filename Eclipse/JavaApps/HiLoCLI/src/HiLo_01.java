import java.util.Scanner;

public class HiLo_01 {

	public static void main(String[] args) {
		// Get ready for the player to use the keyboard
		Scanner scan = new Scanner(System.in);
		
		// Play again string
		String playAgain = "";
		
		// game loop
		do {
			// Create a new random number from 1 to 100
			int theNumber = (int)(Math.random()*100 +1);
			
			int guess = 0;
			
			while( guess != theNumber ) 
			{
				System.out.println("Guess a number between 1 and 100: ");
				
				// Get the user's guess
				guess = scan.nextInt();
				
				if ( guess < theNumber)
				{
					System.out.println(guess + " is too low, try again.");
					// More code here if needed
				}
				else if (guess > theNumber)
					System.out.println(guess + " is too high, try again.");
				else
					System.out.println(guess + " is correct! You win!");
			} //end of while loop
			
			// Ask user to play again by typing 'y'
			System.out.println("Would you like to play again (y/n)?: ");
			playAgain = scan.next();
			
		} while (playAgain.equalsIgnoreCase("y"));
			// thank the user for playing
			System.out.println("Thank you for playing, goodbye!");
	}

}
