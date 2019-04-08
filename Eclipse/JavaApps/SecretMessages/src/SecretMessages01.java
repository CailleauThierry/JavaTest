import java.util.Scanner;

public class SecretMessages01 {

	public static void main(String[] args) {
		// Ask user for a message to encode or decode
		System.out.println("Enter a message to encode or decode: ");
		Scanner scan = new Scanner(System.in);
		// nextLine() lets us enter a long message until button "Enter" is pressed
		String message = scan.nextLine();
		
		String out = "";
		
		char key = 13;
		
		for (int x=0 ; x < message.length(); x++)
		{
			out += (char)((message.charAt(x)) + key);
		}
		System.out.println();
		System.out.println(out);
		
		scan.close();
		
	}

}
