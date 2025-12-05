// @problem  |  CCC 2004 J4 - Simple Encryption
// @author   |  Beckett Clarke
// @version  |  1
// @date     |  2025/9/18

import java.util.Scanner;
public class SimpleEncryption {
  public static void main(String[] args) {
    // Initial variables
    Scanner scan = new Scanner(System.in);
    String key = scan.nextLine();
    String message = scan.nextLine();
    int currentkey = 0;
    String encrypted = "";
    // Repeat for each letter in the original message
    for (int i = 0; i < message.length(); i++) {
      char letter = message.charAt(i);
      // Only encrypt the letter if its from A-Z (Uppercase only), otherwise don't add to the final string
      if (65 <= letter  && letter <= 90){
        // Encrypts the letter by offsetting it by the current key character
        int newletter = (int) letter + (int) key.charAt(currentkey) - 65;
        // If the new letter goes past Z, move it back to the start of A-Z
        if (newletter > 90){
          newletter -= 26;
        }
        encrypted += (char) newletter;
        // Next key character, if at the end of the key, go back to start
        currentkey++;
        if (currentkey > key.length() - 1){
          currentkey = 0;
        }
      }
    }
    // This is the most complex part of the program, it outputs the final message
    // Took me years to figure out how to do this
    System.out.println(encrypted);
  }
}
