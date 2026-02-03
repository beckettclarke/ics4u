import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class picturemagick {
  static int[][] red, green, blue, ored, ogreen, oblue;
  static int width, height;
  
  public static void main(String[] args) throws IOException{
    Scanner scan = new Scanner(System.in);
    // Gets the image path from inputs
    System.out.print("Enter image file path: ");
    String path = scan.nextLine().trim();
    // Remove the quotes that terminal adds when you drag the file into the window instead of typing manually
    if (path.charAt(0) == '\'' && path.charAt(path.length() - 1) == '\'') {
      path = path.substring(1, path.length() - 1);
    }
    BufferedImage img = null;
    try {
      img = ImageIO.read(new File(path));
    } catch (IOException e) {
      System.out.println(e);
      return;
    }
    width = img.getWidth();
    height = img.getHeight();

    // 2D arrays to hold the red green and blue values of each pixel
    // Need to be initialized here since we have the width/height now
    red = new int[height][width];
    green = new int[height][width];
    blue = new int[height][width];
    // These will store the values that have been processed later on
    ored = new int[height][width];
    ogreen = new int[height][width];
    oblue = new int[height][width]; 

    // Loops over each pixel, adds red green and blue values to array 
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = img.getRGB(x, y);
        red[y][x] = (pixel >> 16) & 0xFF;
        green[y][x] = (pixel >> 8) & 0xFF;
        blue[y][x] = pixel & 0xFF;
      }
    }

    // Main menu screen
    int choice = -1;
    while (choice != 0) {
      System.out.println("--- Select action ---");
      System.out.println("1 | Gaussian Blur");
      System.out.println("2 | Box Blur");
      System.out.println("3 | Overexpose");
      System.out.println("0 | Quit");
      System.out.print("Action to apply: ");
      choice = scan.nextInt();
      switch (choice) {
        case 1:
          System.out.print("Enter blur radius: ");
          int gbr = scan.nextInt();
          GaussianBlur(gbr);
          pushNewImg();
          saveimage(path.substring(0, path.indexOf("."))+ "-modified.png", ored, ogreen, oblue);
          break;
        case 2:
          System.out.print("Enter blur radius: ");
          int bbr = scan.nextInt();
          BoxBlur(bbr);
          pushNewImg();
          saveimage(path.substring(0, path.indexOf("."))+ "-modified.png", ored, ogreen, oblue);
          break;
        case 3:
          System.out.print("Enter overexpose amount: ");
          int over = scan.nextInt();
          Overexpose(over);
          pushNewImg();
          saveimage(path.substring(0, path.indexOf("."))+ "-modified.png", ored, ogreen, oblue);
          break;
        case 0:
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Invalid option.");
          break;
      }
    }
  }

  // =================================================================================================================================== //

  public static void pushNewImg() {
    // Replaces the image once its modified so it can be modified further
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        red[y][x] = ored[y][x];
        green[y][x] = ogreen[y][x];
        blue[y][x] = oblue[y][x];
      }
    }
  }

  // =================================================================================================================================== //

  public static void GaussianBlur(int radius){
    double[][] kernel = GaussianKernel(radius);
    System.out.println("Generated Kernel");
    // For each pixel in each row
    System.out.println("--------------------------------");
    for (int i = 0; i < width; i++){
      System.out.print("\rProcessing row [" + (i+1) + "/" + width + "]");
      for (int k = 0; k < height; k++){
        double rtotal = 0, gtotal = 0, btotal = 0;
        // For each neighbouring pixel within the radius
        for (int j = -radius; j <= radius; j++){
          for (int l = -radius; l <= radius; l++){
            int lookingatx = i + j;
            int lookingaty = k + l;
            // Makes sure its within the image edges (Not checking a value that doesnt exist)
            if (lookingatx >= 0 && lookingatx < width && lookingaty >= 0 && lookingaty < height){
              double weight = kernel[j + radius][l + radius];
              rtotal += red[lookingaty][lookingatx] * weight;
              gtotal += green[lookingaty][lookingatx] * weight;
              btotal += blue[lookingaty][lookingatx] * weight;
            }
          }
        }
        // Set the new pixel values but makes sure they arent too high or low (Must between 0-255)
        ored[k][i] = (int) Math.min(Math.max(Math.round(rtotal), 0), 255);
        ogreen[k][i] = (int) Math.min(Math.max(Math.round(gtotal), 0), 255);
        oblue[k][i] = (int) Math.min(Math.max(Math.round(btotal), 0), 255);
        
      }
    }
    System.out.println();
    System.out.println("--------------------------------");
  }

  // =================================================================================================================================== //
  
  public static double[][] GaussianKernel(int radius){
    int size = radius * 2 + 1; // Middle pixel + the radius on each side
    double[][] kernel = new double[size][size];
    double sigma = radius / 3.0; // Apparently this should be 2.0 or 3.0 for nice blur
    double sum = 0.0;
    
    // Calculate Gaussian values
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        // Distance from center
        int dx = x - radius;
        int dy = y - radius;
        double base = 1.0 / (2.0 * Math.PI * Math.pow(sigma, 2)); // Bottom part
        double exponent = -(dx*dx + dy*dy) / (2.0 * Math.pow(sigma, 2)); // Fixed: added parentheses
        kernel[x][y] = base * Math.exp(exponent);
        sum += kernel[x][y];
      }
    }
    
    // Normalize the kernel so all values sum to 1
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        kernel[x][y] /= sum;
      }
    }
    
    return kernel;
  }

  // =================================================================================================================================== //

  public static void Overexpose(int radius){
    double[][] kernel = OverexposeKernel(radius);
    // For each pixel in each row
    for (int i = 0; i < width; i++){
      for (int k = 0; k < height; k++){
        int rtotal = 0, gtotal = 0, btotal = 0;
        // For each neighbouring pixel within the radius
        for (int j = -radius; j <= radius; j++){
          for (int l = -radius; l <= radius; l++){
            int lookingatx = i + j;
            int lookingaty = k + l;
            // Makes sure its within the image edges (Not checking a value that doesnt exist)
            if (lookingatx >= 0 && lookingatx < width && lookingaty >= 0 && lookingaty < height){
              double weight = kernel[j + radius][l + radius];
              rtotal += red[lookingaty][lookingatx] * weight;
              gtotal += green[lookingaty][lookingatx] * weight;
              btotal += blue[lookingaty][lookingatx] * weight;
            }
          }
        }
        // Set the new pixel values but makes sure they arent too high or low (Must between 0-255)
        ored[k][i] = (int) Math.min(Math.max(rtotal, 0), 255);
        ogreen[k][i] = (int) Math.min(Math.max(gtotal, 0), 255);
        oblue[k][i] = (int) Math.min(Math.max(btotal, 0), 255);
        
      }
    }
  }

  // =================================================================================================================================== //
  
  public static double[][] OverexposeKernel(int radius){
    int size = radius * 2 + 1; // Middle pixel + the radius on each side
    double[][] kernel = new double[size][size];
    double sigma = radius / 3.0; // Apparently this should be 2.0 or 3.0 for nice blur
    for (int x = 0; x < size; x++) {
      for (int y = 0; y < size; y++) {
        double base = 1 / 2.0 * Math.PI * Math.pow(sigma, 2); // Bottom part
        double exponent = -(x*x + y*y) / 2*Math.pow(sigma, 2); // Exponent that goes to eulers number
        kernel[x][y] = base * Math.exp(exponent);
      }
    }
    return kernel;
  }


  // =================================================================================================================================== //

  public static void BoxBlur(int radius){
    // For each pixel in each row
    for (int i = 0; i < width; i++){
      for (int k = 0; k < height; k++){
        // Variables to store the total of each value
        int rtotal = 0, gtotal = 0, btotal = 0;
        int count = 0; // Stores how many values have been added 
        // For each neighbouyring pixel based on radius
        for (int j = -radius; j <= radius; j++){
          int lookingat = i + j;
          if (lookingat >= 0 && lookingat < width){
            rtotal += red[k][lookingat];
            gtotal += green[k][lookingat];
            btotal += blue[k][lookingat];
            count++;
          }
        }

        // calculates the average of that pixel;
        ored[k][i] = rtotal / count;
        ogreen[k][i] = gtotal / count;
        oblue[k][i] = btotal / count;
      }
    }
    // For each pixel in each column
    for (int i = 0; i < height; i++){
      for (int k = 0; k < width; k++){
        // Variables to store the total of each value
        int rtotal = 0, gtotal = 0, btotal = 0;
        int count = 0; // Stores how many values have been added
        // For each neighbouyring pixel based on radius
        for (int j = -radius; j <= radius; j++){
          int lookingat = i + j;
          if (lookingat >= 0 && lookingat < height){
            rtotal += ored[lookingat][k];
            gtotal += ogreen[lookingat][k];
            btotal += oblue[lookingat][k];
            count++;
          }
        }
        // calculates the average of that pixel;
        ored[i][k] = rtotal / count;
        ogreen[i][k] = gtotal / count;
        oblue[i][k] = btotal / count;
      }
    }
    // System.out.println("Red values: " + Arrays.deepToString(ored));
    // System.out.println("Green values: " + Arrays.deepToString(ogreen));
    // System.out.println("Blue values: " + Arrays.deepToString(oblue));
  }


  // =================================================================================================================================== //

  public static void saveimage(String output, int[][] red, int[][] green, int[][] blue) throws IOException {
    // Creates empty image for everything to go into
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    // For every pixel in the image
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        // Put the red green and blue values into one pixel memory adress integer and set the corresponding pixel
        int rgb = (red[y][x] << 16) | (green[y][x] << 8) | blue[y][x];
        img.setRGB(x, y, rgb);
      }
    }
    // Saves the file to the same directory as originally inputted image
    ImageIO.write(img, "png", new File(output));
    System.out.println("Image exported to " + output);

  }
}
