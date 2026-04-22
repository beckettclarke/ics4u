
import java.util.Scanner;

public class Main {
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);
    int b = scan.nextInt(), t = scan.nextInt(), p = scan.nextInt();
    if (t - p >= b){
      System.out.println("Y " + (t - p - b));
    } else {
      System.out.println("N");
    }
  }
}