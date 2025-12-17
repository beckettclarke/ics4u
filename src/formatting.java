// @problem  |  CCC 2012 S3 - Absolutely Acidic
// @author   |  Beckett Clarke
// @version  |  3
// @date     |  2025-12-14
import java.util.ArrayList;
import java.util.Scanner;
public class CCCAbsolutelyAcidic {

  static  ArrayList<Integer> result = new ArrayList<>();
  static  ArrayList<Integer> result2 = new ArrayList<>();
  public static void main(String[] args){
    Scanner scan = new Scanner(System.in);
    // Gets all the inputs (# of readings and the readings themselves)
    int readcount = scan.nextInt();
    ArrayList<Integer> readings = new ArrayList<>();
    ArrayList<Integer> frequencies = new ArrayList<>();
    for (int i = 0; i < readcount; i++){
      int r = scan.nextInt();
      int ri = readings.indexOf(r);
      // Stores the readings by increasing the frequency if it already exists rather than making a new duplicate entry to make things easier later
      if (ri != -1){
        frequencies.set(ri,frequencies.get(ri)+1);
      } else {
        readings.add(r);
        frequencies.add(1);
      }
    }
    // DONE GETTING INPUT, START PROCESSING

    // Get the max and second max values
    findminmax(frequencies);
    int maxdif = 0;
    // Go through every possible combination of maxes and second maxes, see which has the biggest difference between the two
    for (int i = 0; i < result.size(); i++){
      for (int k = 0; k < result2.size(); k++){
        int diff = Math.abs(readings.get(result.get(i)) - readings.get(result2.get(k)));
        if (diff > maxdif){
          maxdif = diff;
        }
      }
    }
    System.out.println(maxdif);
  }

  // This basically finds all candidates that are at the max value and second max value
  public static void findminmax(ArrayList<Integer> search){
    int minmax = -1; // Current maximum frequency
    int minmax2 = 0; // Current second maximum frequency
    for (int i = 0; i < search.size(); i++){ // For every item in the frequency list
      int cur = search.get(i);
      if (cur > minmax){ // If the current frequency is a max
        result2.clear(); // Move old max to second max
        result2.addAll(result);
        minmax2 = minmax;
        result.clear(); // Remove all old items since they arent useful anymore (New max found)
        result.add(i);
        minmax = cur;
      } else if (minmax == cur) {
        result.add(i); // Add the current one as candidate since it is the same frequency as the max
        if (result2.isEmpty()) {
          result2.add(i); // If second max not set yet, also add to result2
          minmax2 = minmax; // Set minmax2 to match minmax for next iterations
        } else if (minmax2 == cur) {
          result2.add(i); // Add to result2 if it matches the second max frequency
        }
      } else if (cur > minmax2) {
        result2.clear(); // New second max found
        result2.add(i);
        minmax2 = cur;
      } else if (minmax2 == cur) {
        result2.add(i); // Add the current one as candidate since it is the same frequency as the second max
      }
    }
  }
}