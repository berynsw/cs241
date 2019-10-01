/**
 * This class is used to perform Radix Sort (Using Counting Sort) on an array of integers.
 * @author Beryn Staub-Waldenberg (Modification Dates: 10/5/18, 10/6/18, 10/7/18, 10/8/18)
 * @author Drew Gawlinski (Modification Dates: 10/8/18, 10/9/18)
 */
class RadixSorter {
    private CountingSorter sorter;

    public RadixSorter() {
        this.sorter = new CountingSorter(9);
    }

    /**
     * Sort an array of integers using radix sort.
     * @param list The array of integers to sort.
     */
    public void sort(int[] list) {

        // Finds the biggest value in the list.
        int biggestNum = Integer.MIN_VALUE;
        for (int testValue : list)
            if (testValue > biggestNum)
                biggestNum = testValue;

        int mostDigits = (int) Math.log10(biggestNum) + 1; // Gets the amount of digits the maximum number has.

        // Goes through each digit column, and sorts it.
        for (int digit = 1; digit <= mostDigits; digit++) {
            int[] digitsToSort = new int[list.length];
            for (int index = 0; index < list.length; index++)
                digitsToSort[index] = getDigit(list[index], digit);

            sorter.sort(list, digitsToSort); // Sort.
        }
    }

    /**
     * Get the nth digit from a number.
     * @param num    The source number to get the digit from.
     * @param curDig The digit index to get.
     * @return digit
     */
    private static int getDigit(int num, int curDig) {
        if ((1 + (int) Math.log10(num)) < curDig)
            return 0; // Bounds check. If the digit is past the max digit of the number, the digit is zero.

        int resultDigit = 0;
        for (int i = 0; i < curDig; i++) {
            resultDigit = num % 10; //saves 1's place
            num /= 10; //deletes 1's place
        }

        return resultDigit;
    }
}
