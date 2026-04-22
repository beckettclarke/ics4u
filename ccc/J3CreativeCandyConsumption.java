import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  public static void main(String args[]) throws IOException {  
    String nc = br.readLine();
    String mc = br.readLine();
    int nt = 0;
    int mt = 0;
    while (!(nc == "" || mc == "")){
    // System.out.println(nc.charAt(0));
      char n = nc.charAt(0);
      char m = mc.charAt(0);
      if (n == m){
        nt++;
        mt++;
        nc = nc.substring(1);
        mc = mc.substring(1);
      } else if ((n == 'R' && m == 'G') || (n == 'G' && m == 'B') || (n == 'B' && m == 'R')){
        nt++;
        mc = mc.substring(1);
      } else {
        mt++;
        nc = nc.substring(1);
      }

    }
    System.out.println(nt + nc.length());
    System.out.println(mt + mc.length());
  }
}
