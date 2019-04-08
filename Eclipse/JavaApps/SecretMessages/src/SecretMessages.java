import java.util.Scanner;

public class SecretMessages {

	public static void main(String[] args) {
		// Ask user for a message to encode or decode
		System.out.println("Enter a message to encode or decode: ");
		Scanner scan = new Scanner(System.in);
		// nextLine() lets us enter a long message until button "Enter" is pressed
		String message = scan.nextLine();
		
		String out = "";
		
		for (int x=message.length()-1; x >= 0; x--)
		{
			out += message.charAt(x);
		}
		System.out.println();
		System.out.println(out);
		
		scan.close();
	}

}
