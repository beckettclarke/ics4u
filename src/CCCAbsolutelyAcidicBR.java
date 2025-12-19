// @problem  |  CCC 2012 S3 - Absolutely Acidic
// @author   |  Beckett Clarke
// @version  |  3fast
// @date     |  2025-12-14
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
public class CCCAbsolutelyAcidicBR {

  static  ArrayList<Integer> result = new ArrayList<>();
  static  ArrayList<Integer> result2 = new ArrayList<>();
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  public static void main(String[] args) throws IOException{
  
    // Gets all the inputs (# of readings and the readings themselves)
    int readcount = Integer.parseInt(br.readLine());
    ArrayList<Integer> readings = new ArrayList<>();
    ArrayList<Integer> frequencies = new ArrayList<>();
    for (int i = 0; i < readcount; i++){
      int r = Integer.parseInt(br.readLine());
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
    
    // IF THE MOST FREQUENT LIST HAS MULTIPLE VALUES, Only compare those values
    if (result.size() > 1) {
      // Multiple readings have the max frequency - find max difference among them
      for (int i = 0; i < result.size(); i++){ 
        for (int k = 0; k < result.size(); k++){
          int diff = Math.abs(readings.get(result.get(i)) - readings.get(result.get(k))); // Find difference between the two
          if (diff > maxdif){ // Update maxdif if this is the largest difference found
            maxdif = diff;
          }
        }
      }
    } else {
      // IF IT DOESNT, compare most frequent with second most frequent
      // Loops over all possible combos of the two
      for (int k = 0; k < result2.size(); k++){
        int diff = Math.abs(readings.get(result.get(0)) - readings.get(result2.get(k)));
        if (diff > maxdif){ // Update if this is the largest difference found
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