import java.util.Scanner;

public class SecretMessages03 {

	public static void main(String[] args) {
		// Ask user for a message to encode or decode
		System.out.println("Enter a message to encode or decode: ");
		Scanner scan = new Scanner(System.in);
		// nextLine() lets us enter a long message until button "Enter" is pressed
		String message = scan.nextLine();
		
		String out = "";
		
		System.out.print("Enter a secret key (-26 to 26): ");
		// Using parseInt to bypass the end of line character that would throw us off... ...NextLine read the whole next line
		int intKey = Integer.parseInt(scan.nextLine());
		
		char key = (char)intKey;
		
		while (message.length() > 0) {
		
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
		System.out.println();
		System.out.println(out);
		
		System.out.println("Please enter another secret message with the same key of \"" + (int)key + "\" or enter blank to exit");
		out = "";
		message = scan.nextLine();
		System.out.println("message.length() is: " + message.length());
	}
		
		scan.close();
	}
}

