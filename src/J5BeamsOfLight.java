import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  public static void main(String args[]) throws IOException {  
    Scanner scan = new Scanner(System.in);
    String parking = "";
    int pc = Integer.parseInt(br.readLine());
    int lc = Integer.parseInt(br.readLine());
    int qc = Integer.parseInt(br.readLine());

    parking = rc("0", pc);

    
    for (int i = 0; i<lc; i++){
      // String
      int spot = Integer.parseInt(br.readLine()) - 1;
      int spread = Integer.parseInt(br.readLine());
    if (pc / 2 <= spread){
      parking = rc("1", pc);
    } else if (spot < (spread + 1)){
        rog("greater");
        // for (int k = 0; k < spot; k++){
          parking = rc("1", spot) + "1" + rc("1", spread) + parking.substring(spot + spread + 1);
        // }
      } else if ((spread + 1) > pc - spot){
        rog("less");
          parking = parking.substring(0, spot - spread) + rc("1", spread + 1 + pc - (spot + 1));
      } else {
        parking = parking.substring(0, spot - spread) + rc("1",  (spread * 2) + 1) + parking.substring(spot + spread + 1);
      }
      rog(parking);
    }
    rog("final");
    rog(parking);
    for (int i = 0; i < qc; i++){
      if (parking.charAt(Integer.parseInt(br.readLine()) - 1) == '1'){
        System.out.println("Y");
      } else {
        System.out.println("N");
      }
    }
  }
  public static String rc(String r, int k){
    String o = "";
    for (int i = 0; i<k; i++){
      o+=r;
    }
    return o;
  }
  public static void rog(String l){
    if (false){
      System.out.println(l);
    }
  }
}
