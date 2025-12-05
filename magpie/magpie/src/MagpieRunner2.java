/**
 * MagpieRunner2.java
 * A program to carry on conversations with a human user.
 * Create a Magpie2, give it user input, and print its replies.
 */

import java.util.Scanner;      // class necessary to read input from the keyboard
public class MagpieRunner2
{   
   public static void main(String[] args)
   {                                   // This is the only change to the code
      Magpie2 maggie = new Magpie2();  // creates a Magpie2 object named maggie		
      String greeting = maggie.getGreeting();  // calls the getGreeting method 
                                               //     on the maggie object
      System.out.println(greeting);

      Scanner in = new Scanner (System.in);   // creates a Scanner object
                                              //   to read from the keyboard
      
      String statement = in.nextLine();       // reads a line of text 
                                              //   from the keyboard
		
      while (!statement.equals("Bye"))       // loop stopping condition
      {                  // calls the getResponse method on the maggie object
         String response = maggie.getResponse(statement);                    
         System.out.println(response);         
         statement = in.nextLine();        
      }
      System.out.println("Thank you for the conversation. Have a nice day.");
      in.close();

   }
}