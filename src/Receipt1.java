/**
 * Receipt1.java  
 *
 * @author – Your name
 * @author – Class period
 */ 
public class Receipt1
{
   public static void main(String [] args)
   {
      String name = "Crescent";
      double drink = 1.50;
      double candy = 1.25;
      double hotDog = 2.75;
      double hamburger = 3.50;
      int qdrink = (int)(Math.random() * 2 + 1);
      int qcandy = (int)(Math.random() * 2 + 1);
      int qhotDog = (int)(Math.random() * 2 + 1);
      int qhamburger = (int)(Math.random() * 2 + 1);
      int orderNumber = (int)(Math.random() * 99 + 1);
      double tdrink = qdrink * drink;
      double tcandy = qcandy * candy;
      double thotDog = qhotDog * hotDog;
      double thamburger = qhamburger * hamburger;
      double total = tdrink + tcandy + thotDog + thamburger;
      System.out.println("**************************************");
      System.out.println("*                                    *");
      System.out.println("*         " + name + " Snack Bar         *");
      System.out.println("*                                    *");
      System.out.println("*     Drink ..........$" + drink + "           *");                      
      System.out.println("*     Candy ..........$" + candy + "          *");     
      System.out.println("*     Hot Dog ........$" + hotDog + "          *");     
      System.out.println("*     Hamburger ......$" + hamburger + "           *");     
      System.out.println("*                                    *");    
      System.out.println("**************************************");   
      System.out.println("*  Order Number: " + orderNumber + "                  ");    
      System.out.println("                                    ");   
      System.out.println("  QTY      ITEM      TOTAL          ");    
      System.out.println("  " + qdrink + "        Drink     $" + tdrink + "          ");
      System.out.println("  " + qcandy + "        Candy     $" + tcandy + "          ");
      System.out.println("  " + qhotDog + "        Hot Dog   $" + thotDog + "          ");
      System.out.println("  " + qhamburger + "        Hamburger $" + thamburger + "          ");
      System.out.println("**************************************");
      System.out.println("   Subtotal: $" + total + "              ");
      System.out.println("   Tax: $" + Math.round(total * 0.13 * 100) / 100.0 + "              ");
      System.out.println("   Total: $" + Math.round(total * 1.13 * 100) / 100.0 + "              ");
   }
}