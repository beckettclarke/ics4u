
import java.util.Scanner;

public class Main {
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);
    int largest = -100, smallest = 100, sum = 0;
    for (int i = 0; i<5; i++){
      int c = scan.nextInt();
      sum+=c;
      if (largest < c){
        largest = c;
      }
      if (smallest > c){
        smallest = c;
      }
    }
    sum-= (largest + smallest);
    sum*= scan.nextInt();
    System.out.println(sum);
  }
}