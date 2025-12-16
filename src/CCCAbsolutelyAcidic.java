// @problem  |  CCC 2012 S3 - Absolutely Acidic
// @author   |  Beckett Clarke
// @version  |  1
// @date     |  2025-12-14
import java.util.ArrayList;
import java.util.Scanner;
public class CCCAbsolutelyAcidic {
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    int readcount = scan.nextInt();
    ArrayList<Integer> readings = new ArrayList<>();
    ArrayList<Integer> frequencies = new ArrayList<>();
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
    // DONE GETTING INPUT, START PROCESSING

    // Get the max and min values
    ArrayList<Integer> max = findminmax(frequencies, 1);
    ArrayList<Integer> min = findminmax(frequencies, -1);
    int maxdif = 0;
    // Go through every possible combination of mins and maxes, see which has the biggest difference between the two
    for (int i = 0; i < max.size(); i++){
      for (int k = 0; k < min.size(); k++){
        int diff = Math.abs(readings.get(max.get(i)) - readings.get(min.get(k)));
        if (diff > maxdif){
          maxdif = diff;
        }
      }
    }
    System.out.println(maxdif);
  }

  // This basically finds all candidates that are at the min value or the max value
  public static ArrayList<Integer> findminmax(ArrayList<Integer> search, int mode){
    // searching mode: max = 1, min = -1, saves having to have 2 separate methods to do very simliar things
    ArrayList<Integer> result = new ArrayList<>(); // Arraylist stores all values that occur the min/max
    int minmax = (mode == 1) ? -1 : Integer.MAX_VALUE; // Current maximum or minimum frequency
    for (int i = 0; i < search.size(); i++){ // For every item in the frequency list
      int cur = search.get(i);
      if ((mode == 1 && cur > minmax) || (mode == -1 && cur < minmax)){ // If the current frequency is a min/max based on mode
        result.clear(); // Remove all old items since they arent useful anymore (New min/max found)
        result.add(i);
        minmax = cur;
      } else if (minmax == cur) {
        result.add(i); // Add the current one as candidate since it is the same frequency as the minmax
      }
    }
    return result;
  }
}
