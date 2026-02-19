import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  public static void main(String args[]) throws IOException {  
    Scanner scan = new Scanner(System.in);
    int m = scan.nextInt();
    int x = 0;
    int y = 0;
    ArrayList<Integer> sx = new ArrayList<Integer>();
    ArrayList<Integer> sy = new ArrayList<Integer>();
    ArrayList<Integer> ex = new ArrayList<Integer>();
    ArrayList<Integer> ey = new ArrayList<Integer>();
    int slimy = 0;
    for (int i = 0; i<m; i++){
      String d = br.readLine();
      int cx = 0, cy = 0;
      sx.add(x);
      sy.add(y);
      if (d == "N"){
        cy=1;
      } else if (d == "S") {
        cy=-1;
      } else if (d == "E") {
        cx=1;
      } else {
        cx=-1;
      }
        // System.out.println(d);
      int a = scan.nextInt();
        // System.out.println(a);
      for (int k = 0; k<a; k++){
        x+=cx;
        y+=cy;
        // System.out.println(sx.size());
        for (int r = 0; r<(sx.size() - 1); r++){
          System.out.println("checking x");
            System.out.println(x + " between " + sx.get(r) + " + " + ex.get(r));
          if (sx.get(r) < ex.get(r)){
            if (x < sx.get(r) && x > ex.get(r)){
              slimy++;
            }
          } else if (x > sx.get(r) && x < ex.get(r)){
              slimy++;
          }
        }
        for (int r = 0; r<(sy.size() - 1); r++){
          System.out.println("checking y");
            System.out.println(y + " between " + sy.get(r) + " + " + ex.get(r));
          if (sy.get(r) < ey.get(r)){
            if (y < sy.get(r) && y > ey.get(r)){
              slimy++;
            }
          } else if (y > sy.get(r) && y < ey.get(r)){
              slimy++;
          }
        }
      }
      ex.add(x);
      ey.add(y);
    }
    System.out.println(slimy);
  }
}
