// @problem  |  CCC 2010 J5 - Knight Hop
// @author   |  Beckett Clarke
// @version  |  3
// @date     |  2025/11/24

import java.util.ArrayList;
import java.util.Scanner;

public class CCCKnightHop {
  public static ArrayList<Integer> queuex = new ArrayList<>();
  public static ArrayList<Integer> queuey = new ArrayList<>();
  public static ArrayList<Integer> moves = new ArrayList<>();
  public static Scanner scan = new Scanner(System.in);
  public static int startx = scan.nextInt(), starty = scan.nextInt(), endx = scan.nextInt(), endy = scan.nextInt();
  public static void main(String args[]){
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
