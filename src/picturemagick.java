import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class picturemagick {
  static int[][] red, green, blue, alpha, ored, ogreen, oblue, oalpha;
  static int width, height;
  
  public static void main(String[] args) throws IOException{
    Scanner scan = new Scanner(System.in);
    // Gets the image path from inputs
    System.out.println("--------------------------------");
    System.out.println("|         PICTUREMAGICK        |");
    System.out.println("|     easy image processing    |");
    System.out.println("--------------------------------");
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
    alpha = new int[height][width];
    // These will store the values that have been processed later on
    ored = new int[height][width];
    ogreen = new int[height][width];
    oblue = new int[height][width];
    oalpha = new int[height][width];

    // Loops over each pixel, adds red green and blue values to array 
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int pixel = img.getRGB(x, y);
        alpha[y][x] = (pixel >> 24) & 0xFF;
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
      System.out.println("4 | Greyscale");
      System.out.println("5 | Corner Round");
      System.out.println("0 | Quit");
      System.out.print("Action to apply: ");
      choice = scan.nextInt();
      switch (choice) {
        case 1:
          int gbr = -1;
          while (gbr < 0 || gbr > 75) {
            System.out.print("Enter blur radius (px, 0-75): ");
            gbr = (int) Math.round(scan.nextDouble());
            if (gbr < 0 || gbr > 75) {
              System.out.println("Invalid radius. Must be between 0 and 75.");
            }
          }
          GaussianBlur(gbr);
          pushNewImg();
          saveimage(path.substring(0, path.indexOf(".")) + "-modified.png", ored, ogreen, oblue, oalpha);
          break;
        case 2:
          int bbr = -1;
          while (bbr < 0 || bbr > 75) {
            System.out.print("Enter blur radius (px, 0-75): ");
            bbr = (int) Math.round(scan.nextDouble());
            if (bbr < 0 || bbr > 75) {
              System.out.println("Invalid radius. Must be between 0 and 75.");
            }
          }
          BoxBlur(bbr);
          pushNewImg();
          saveimage(path.substring(0, path.indexOf(".")) + "-modified.png", ored, ogreen, oblue, oalpha);
          break;
        case 3:
          System.out.print("Enter overexpose amount (0-50): ");
          int over = (int) Math.round(scan.nextDouble());
          Overexpose(over);
          pushNewImg();
          saveimage(path.substring(0, path.indexOf(".")) + "-modified.png", ored, ogreen, oblue, oalpha);
          break;
        case 4:
          Greyscale();
          pushNewImg();
          saveimage(path.substring(0, path.indexOf(".")) + "-modified.png", ored, ogreen, oblue, oalpha);
          break;
        case 5:
          int max = Math.min(width, height) / 2;
          int r = -1;
          while (r < 0 || r > max) {
            System.out.print("Enter corner rounding radius (px, 0-" + max + "): ");
            r = (int) Math.round(scan.nextDouble());
            if (r < 0 || r > max) {
              System.out.println("Invalid radius. Must be between 0 and " + max + ".");
            }
          }
          CornerRound(r);
          pushNewImg();
          saveimage(path.substring(0, path.indexOf(".")) + "-modified.png", ored, ogreen, oblue, oalpha);
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
        alpha[y][x] = oalpha[y][x];
      }
    }
  }

  // =================================================================================================================================== //

  public static void GaussianBlur(int radius){
    long start = System.currentTimeMillis();
    double[][] kernel = GaussianKernel(radius);
    System.out.println("Generated Kernel");
    // For each pixel in each row
    System.out.println("--------------------------------");
    for (int i = 0; i < width; i++){
      System.out.print("\rProcessing row [" + (i+1) + "/" + width + "]");
      for (int k = 0; k < height; k++){
        double rtotal = 0, gtotal = 0, btotal = 0, atotal = 0;
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
              atotal += alpha[lookingaty][lookingatx] * weight;
            }
          }
        }
        // Set the new pixel values but makes sure they arent too high or low (Must between 0-255)
        ored[k][i] = (int) Math.min(Math.max(Math.round(rtotal), 0), 255);
        ogreen[k][i] = (int) Math.min(Math.max(Math.round(gtotal), 0), 255);
        oblue[k][i] = (int) Math.min(Math.max(Math.round(btotal), 0), 255);
        oalpha[k][i] = (int) Math.min(Math.max(Math.round(atotal), 0), 255);
        
      }
    }
    System.out.println();
    System.out.println("--------------------------------");
    System.out.println("Finished, took " + ((System.currentTimeMillis() - start) / 1000.0) +" seconds to process");
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
        double exponent = -(dx*dx + dy*dy) / (2.0 * Math.pow(sigma, 2));
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
    long start = System.currentTimeMillis();
    double[][] kernel = OverexposeKernel(radius);
    // For each pixel in each row
    for (int i = 0; i < width; i++){
      System.out.print("\rProcessing row [" + (i+1) + "/" + width + "]");
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
        ored[k][i] = (int) Math.min(Math.max(rtotal / 20, 0), 255);
        ogreen[k][i] = (int) Math.min(Math.max(gtotal / 20, 0), 255);
        oblue[k][i] = (int) Math.min(Math.max(btotal / 20, 0), 255);
        oalpha[k][i] = alpha[k][i];
        
      }
    }
    System.out.println();
    System.out.println("--------------------------------");
    System.out.println("Finished, took " + ((System.currentTimeMillis() - start) / 1000.0) +" seconds to process");
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
    long start = System.currentTimeMillis();
    // For each pixel in each row
    for (int i = 0; i < width; i++){
      System.out.print("\rProcessing row [" + (i+1) + "/" + width + "]");
      for (int k = 0; k < height; k++){
        // Variables to store the total of each value
        int rtotal = 0, gtotal = 0, btotal = 0, atotal = 0;
        int count = 0; // Stores how many values have been added 
        // For each neighbouyring pixel based on radius
        for (int j = -radius; j <= radius; j++){
          int lookingat = i + j;
          if (lookingat >= 0 && lookingat < width){
            rtotal += red[k][lookingat];
            gtotal += green[k][lookingat];
            btotal += blue[k][lookingat];
            atotal += alpha[k][lookingat];
            count++;
          }
        }

        // calculates the average of that pixel;
        ored[k][i] = rtotal / count;
        ogreen[k][i] = gtotal / count;
        oblue[k][i] = btotal / count;
        oalpha[k][i] = atotal / count;
      }
    }
    // For each pixel in each column
    for (int i = 0; i < height; i++){
      System.out.print("\rProcessing column [" + (i+1) + "/" + height + "]");
      for (int k = 0; k < width; k++){
        // Variables to store the total of each value
        int rtotal = 0, gtotal = 0, btotal = 0, atotal = 0;
        int count = 0; // Stores how many values have been added
        // For each neighbouyring pixel based on radius
        for (int j = -radius; j <= radius; j++){
          int lookingat = i + j;
          if (lookingat >= 0 && lookingat < height){
            rtotal += ored[lookingat][k];
            gtotal += ogreen[lookingat][k];
            btotal += oblue[lookingat][k];
            atotal += oalpha[lookingat][k];
            count++;
          }
        }
        // calculates the average of that pixel;
        ored[i][k] = rtotal / count;
        ogreen[i][k] = gtotal / count;
        oblue[i][k] = btotal / count;
        oalpha[i][k] = atotal / count;
      }
    }
    System.out.println();
    System.out.println("--------------------------------");
    System.out.println("Finished, took " + ((System.currentTimeMillis() - start) / 1000.0) +" seconds to process");
  }


  // ==================================================================================================================================== //

  public static void Greyscale(){
    // loops over each pixel in the image
    for (int y = 0; y < height; y++) {
      System.out.print("\rProcessing row [" + (y+1) + "/" + height + "]");
      for (int x = 0; x < width; x++) {
        // Gets the average of the red green and blue values to get the greyscale value
        int grey = (red[y][x] + green[y][x] + blue[y][x]) / 3;
        ored[y][x] = grey;
        ogreen[y][x] = grey;
        oblue[y][x] = grey;
        oalpha[y][x] = alpha[y][x];
      }
    }
  }

  // =================================================================================================================================== //

  public static void CornerRound(int r){
    // Clone original image
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        ored[y][x] = red[y][x];
        ogreen[y][x] = green[y][x];
        oblue[y][x] = blue[y][x];
        oalpha[y][x] = alpha[y][x];
      }
    }

    // For each corner
    for (int n = 1; n <= r && n <= height && n <= width; n++) {
      // How many pixels to remove from this row
      int removepixels = (int) Math.round(r - Math.sqrt(r * r - Math.pow(r - n + 1, 2)));
      
      // Top left
      for (int x = 0; x < removepixels && x < width; x++) {
        ored[n - 1][x] = 0;
        ogreen[n - 1][x] = 0;
        oblue[n - 1][x] = 0;
        oalpha[n - 1][x] = 0;
      }
      
      // Top right
      for (int x = width - removepixels; x < width && x >= 0; x++) {
        ored[n - 1][x] = 0;
        ogreen[n - 1][x] = 0;
        oblue[n - 1][x] = 0;
        oalpha[n - 1][x] = 0;
      }
      
      // Bottom left
      for (int x = 0; x < removepixels && x < width; x++) {
        ored[height - n][x] = 0;
        ogreen[height - n][x] = 0;
        oblue[height - n][x] = 0;
        oalpha[height - n][x] = 0;
      }
      
      // Bottom right
      for (int x = width - removepixels; x < width && x >= 0; x++) {
        ored[height - n][x] = 0;
        ogreen[height - n][x] = 0;
        oblue[height - n][x] = 0;
        oalpha[height - n][x] = 0;
      }
    }
    System.out.println("Corner rounded with radius: " + r);
  }

  // =================================================================================================================================== //

  public static void saveimage(String output, int[][] red, int[][] green, int[][] blue, int[][] alpha) throws IOException {
    // Creates empty image for everything to go into
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    // For every pixel in the image
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        // Put the red green and blue values into one pixel memory adress integer and set the corresponding pixel
        int argb = (alpha[y][x] << 24) | (red[y][x] << 16) | (green[y][x] << 8) | blue[y][x];
        img.setRGB(x, y, argb);
      }
    }
    // Saves the file to the same directory as originally inputted image
    ImageIO.write(img, "png", new File(output));
    System.out.println("Image exported to " + output);
  }
}