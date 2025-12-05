// A Java program that demonstrates the Bubble Sort algorithm
public class BubbleSortExample {

    public static void main(String[] args) {
        // Initialize an array of integers to be sorted
        int[] numbers = {64, 34, 25, 12, 22, 11, 90};

        // Print the original unsorted array
        System.out.println("Original Array:");
        printArray(numbers);

        // Call the bubbleSort method to sort the array
        bubbleSort(numbers);

        // Print the sorted array
        System.out.println("\nSorted Array:");
        printArray(numbers);
    }

    // Method to perform bubble sort on an integer array
    public static void bubbleSort(int[] arr) {
        int n = arr.length;  // Get the length of the array
        boolean swapped;     // Flag to detect if a swap occurred in the inner loop

        // Outer loop: runs through all elements
        for (int i = 0; i < n - 1; i++) {
            swapped = false; // Reset swap flag before each inner loop

            // Inner loop: compares adjacent elements
            for (int j = 0; j < n - i - 1; j++) {
                // If the current element is greater than the next one, swap them
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];        // Store current element in temp
                    arr[j] = arr[j + 1];      // Move smaller element forward
                    arr[j + 1] = temp;        // Move larger element backward
                    swapped = true;           // Mark that a swap has occurred
                }
            }

            // If no swaps occurred in this pass, the array is already sorted
            if (!swapped)
                break;
        }
    }

    // Method to print all elements of an array
    public static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " "); // Print each element separated by a space
        }
        System.out.println(); // Move to a new line after printing the array
    }
}


