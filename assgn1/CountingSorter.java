/**
 * Implements counting sort, used by RadixSorter.
 * @author Beryn Staub-Waldenberg (Dates Modified: 10/5/18, 10/6/18, 10/7/18, 10/8/18)
 * @author Drew Gawlinski. (Dates Modified: 10/8/18, 10/9/18)
 */
class CountingSorter {
    private int base;

    CountingSorter(int biggest) {
        this.base = biggest;
    }

    /**
     * Sort an array of integers with their digits.
     * @param value  The integer value array.
     * @param digits The integer digit array derived from the value array.
     */
    public void sort(int[] value, int[] digits) {
        int[] tally = new int[base + 1];

        // Counting Sort Step 1. (Tally up the digits.)
        for (int digit : digits)
            tally[digit]++;

        // Step 2. Prefix Sum.
        for (int i = 1; i < tally.length; i++)
            tally[i] += tally[i - 1];

        // Create Sorted Result.
        int[] sortedResult = new int[value.length];
        for (int j = value.length - 1; j >= 0; j--) {
            int sortIndex = --tally[digits[j]];
            sortedResult[sortIndex] = value[j]; // Use the real number when constructing the sorted array, instead of the digit we've used up until now.
        }

        System.arraycopy(sortedResult, 0, value, 0, value.length); // Updates input array.
    }
}
