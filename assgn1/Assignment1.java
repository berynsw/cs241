//
//    Assignment1.java
//
//    The driver file for Assignment 1.
//    [Author] David Bover, Tanzima Islam
import java.util.*;
public class Assignment1 {
    // generate an array of size random integers in the range
// min to max
    private static int[] generateList(int size, int min, int max) {
        Random rand = new Random();
        int[] list = new int[size];
        for (int i = 0; i < size; i++)
            list[i] = min + rand.nextInt(max - min);
        return list;
    }
    // check the command line arguments
    private static boolean argErrors(String[] args, int[] val) {
// parse the command-line arguments
        try {
            val[0] = Integer.parseInt(args[0]);
            val[1] = Integer.parseInt(args[1]);
            val[2] = Integer.parseInt(args[2]);
        }
        catch (Exception e) {
            System.out.println("Usage: java Assignment1 size min max");
            return true;
        }
// validate the command-line arguments
        if (val[0] <= 0) {
            System.out.println("Usage: java Assignment1 size min max");
                    System.out.println("size must be greater than 0");
            return true;
        }
        if (val[1] > val[2]) {
            System.out.println("Usage: java Assignment1 size min max");
                    System.out.println("min must be less than max");
            return true;
        }
        return false; // no errors found
    }
    public static void main(String[] args) {
        int[] argvals = new int[3];
// argval[0] is the size of the array to be sorted
// argval[1] is the minimum value for array elements
// argval[2] is the maximum value for array elements
// check command-line arguments
        if (argErrors(args, argvals))
            return;
// generate the unsorted array
        int[] list = generateList(argvals[0], argvals[1],
                argvals[2]);
// keep a copy of the array for later reference
        int[] sorted = new int[list.length];
        for (int i = 0; i < list.length; i++)
            sorted[i] = list[i];
        Arrays.sort(sorted);
// use the radix sort to sort the array into ascending order
        RadixSorter sorter = new RadixSorter();
        sorter.sort(list);
// check that the array is sorted into ascending order
        int errorCount = 0;
        for (int i = 0; i < list.length; i++)
            if (list[i] != sorted[i]) {
                errorCount++;
                System.out.println("Error: " + list[i] + " should be " + sorted[i]);
            }
        if (errorCount == 0)
            System.out.println("Sorted correctly");
    }
}
