import java.util.Scanner;

public class SecretMessages02 {

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
			char in = message.charAt(x);
					
			if (in >= 'A' && in <= 'Z') {
				in += key;
				if (in > 'Z')
				{
					//if the character went past Z we wrap it around back to the beginning of the alphabet i.e. back 26 characters
					in -= 26;
				}
			}
			else if (in >= 'a' && in <= 'z') {
				in += key;
				if (in > 'z')
				{
					//if the character went past z we wrap it around back to the beginning of the alphabet i.e. back 26 characters
					in -= 26;
				}
			}
			
			out += in;
			
			
		}
		System.out.println();
		System.out.println(out);
		
		scan.close();
	}

}
