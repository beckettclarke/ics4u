/**
 * Receipt1.java  
 *
 * @author – Your name
 * @author – Class period
 */ 
import java.util.Scanner;
public class Receipt4
{
   public static void main(String [] args)
   {
      Scanner scan = new Scanner(System.in);
      double drink = 4;
      double candy = 3;
      double hotDog = 1;
      double twizzlers = 5;
      double cookies = 3.50;
      System.out.print("Enter quantity of soda: ");
      int qdrink = scan.nextInt();
      System.out.print("Enter quantity of maynards: ");
      int qcandy = scan.nextInt();
      System.out.print("Enter quantity of rice crackers: ");
      int qhotDog = scan.nextInt();
      System.out.print("Enter quantity of twizzlers: ");
      int qtwizzlers = scan.nextInt();
      System.out.print("Enter quantity of cookies: ");
      int qcookies = scan.nextInt();
      int orderNumber = (int)(Math.random() * 99 + 1);
      double tdrink = qdrink * drink;
      double tcandy = qcandy * candy;
      double thotDog = qhotDog * hotDog;
      double thamburger = qtwizzlers * twizzlers;
      double tcookies = qcookies * cookies;
      double total = tdrink + tcandy + thotDog + thamburger + tcookies;
      System.out.println("**************************************");
      System.out.println("*                                    *");
      System.out.println("*       Beckett's Snack Locker       *");
      System.out.println("*                                    *");
      System.out.println("*     Soda ..........$" + drink + "            *");                      
      System.out.println("*     Maynards ..........$" + candy + "        *");     
      System.out.println("*     Rice Crackers ........$" + hotDog + "     *");     
      System.out.println("*     Twizzlers ......$" + twizzlers + "           *");     
      System.out.println("*     Cookies ........$" + cookies + "           *");     
      System.out.println("*                                    *");    
      System.out.println("**************************************");   
      System.out.println("*  Order Number: " + orderNumber + "                  ");    
      System.out.println("                                    ");   
      System.out.println("  QTY      ITEM      TOTAL          ");    
      System.out.println("  " + qdrink + "        Drink     $" + tdrink + "          ");
      System.out.println("  " + qcandy + "        Candy     $" + tcandy + "          ");
      System.out.println("  " + qhotDog + "        Hot Dog   $" + thotDog + "          ");
      System.out.println("  " + qtwizzlers + "        Twizzlers $" + thamburger + "          ");
      System.out.println("  " + qcookies + "        Cookies   $" + tcookies + "          ");
      System.out.println("**************************************");
      System.out.println("   Subtotal: $" + total + "              ");
      System.out.println("   Tax: $" + Math.round(total * 0.13 * 100) / 100.0 + "              ");
      System.out.println("   Total: $" + Math.round(total * 1.13 * 100) / 100.0 + "              ");
   }
}
