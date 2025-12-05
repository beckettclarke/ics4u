// @problem  |  CCC 2010 J5 - Knight Hop
// @author   |  Beckett Clarke
// @version  |  3-fast
// @date     |  2025/11/24

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CCCKnightHopBR {
  public static ArrayList<Integer> queuex = new ArrayList<>();
  public static ArrayList<Integer> queuey = new ArrayList<>();
  public static ArrayList<Integer> moves = new ArrayList<>();
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
public static int startx, starty, endx, endy;

public static void main(String args[]) throws IOException {
    String[] s = br.readLine().split(" ");
    startx = Integer.parseInt(s[0]);
    starty = Integer.parseInt(s[1]);

    String[] e = br.readLine().split(" ");
    endx = Integer.parseInt(e[0]);
    endy = Integer.parseInt(e[1]);
    // Checks if the start is the same as the destination (0 Moves)
    if (startx == endx && starty == endy){
        System.out.println("0");
        System.exit(0);
    }
    // Adds the starting position to the arrays to be checked first
    queuex.add(startx);
    queuey.add(starty);
    moves.add(0);
    // Setting all varaibles
    for (int i = 0; i < 1000; i++){
      checkPos(queuex.get(0),queuey.get(0));
      queuex.remove(0);
      queuey.remove(0);
      moves.remove(0);
    }
  }

  public static void checkPos(int x, int y){
    // Checks each possible position the knight can move to
    checkPoint(x+1, y+2);
    checkPoint(x+2, y+1);
    checkPoint(x+2, y-1);
    checkPoint(x+1, y-2);
    checkPoint(x-1, y-2);
    checkPoint(x-2, y-1);
    checkPoint(x-2, y+1);
    checkPoint(x-1, y+2);
    
  }
  public static void checkPoint(int x, int y){
    // If the position is inside the chessboard
    if (0 < x && x <= 8 && 0 < y && y <= 8){
      // If this is the solution, output the number of moves
      if (x == endx && y == endy){
        System.out.println(moves.get(0) + 1);
        System.exit(0);
      }
      // Add this position to the queue to check moves that come after it
      queuex.add(x);
      queuey.add(y);
      moves.add(moves.get(0) + 1);
    }
  }
}
