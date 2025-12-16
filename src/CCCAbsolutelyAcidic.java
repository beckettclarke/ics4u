// @problem  |  CCC 2010 J5 - Knight Hop
// @author   |  Beckett Clarke
// @version  |  3
// @date     |  2025/11/24
import java.util.ArrayList;
import java.util.Scanner;
public class CCCAbsolutelyAcidic {
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    int readcount = scan.nextInt();
    ArrayList<Integer> readings = new ArrayList<Integer>();
    ArrayList<Integer> frequencies = new ArrayList<Integer>();
    for (int i = 0; i < readcount; i++){
      int r = scan.nextInt();
      int ri = readings.indexOf(r);
      if (ri != -1){
        frequencies.set(ri,frequencies.get(ri)+1);
      } else {
        readings.add(r);
        frequencies.add(1);
      }
      
    }
    System.out.println(readings.get(1)+" , "+frequencies.get(1));
  }
}
