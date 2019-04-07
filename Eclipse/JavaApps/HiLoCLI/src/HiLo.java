import java.util.Scanner;

public class HiLo {

	public static void main(String[] args) {
		// Get ready for the player to use the keyboard
		Scanner scan = new Scanner(System.in);
		
		// Create a ew random number from 1 to 100
		int theNumber = (int)(Math.random()*100 +1);
		
		int guess = 0;
		
		while( guess != theNumber ) {
			System.out.println("Guess a number between 1 and 100: ");
			
			// Get the user's guess
			guess = scan.nextInt();
			
			if ( guess < theNumber)
				System.out.println(guess + " is too low, try again.");
			else if (guess > theNumber)
				System.out.println(guess + " is too high, try again.");
			else
				System.out.println(guess + " is correct! You win!");
		}

	}

}
