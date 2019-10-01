import java.util.ArrayList;

/**
 * Implements a HashTable as defined in Assignment2.pdf
 * Uses Binary Search due to efficiency.
 *
 * @author Drew Gawlinski
 * @author Beryn Staub-Waldenberg
 * Created on 10/23/2018.
 */
class HashTable {
    private final ArrayList<PrimaryHash> entryList = new ArrayList<>(); // Sorted, highest to lowest hashCode value.
    private int successfulSearchComparisons;
    private int successfulSearchCount;
    private int unsuccessfulSearchComparisons;
    private int unsuccessfulSearchCount;

    private static final TableSearchOperation ADD_OPERATION = new TableSearchOperation() {
        @Override
        public void onFindMatch(HashTable table, PrimaryHash primaryHash, DataItem item) {
            item.count++; // Increase the frequency of this word.
        }

        @Override
        public void onMissingHash2(HashTable table, PrimaryHash primaryHash, String word, int index) {
            primaryHash.hashMatches.add(new DataItem(word));
        }

        @Override
        public void onMissingHash1(HashTable table, String word, int index) {
            table.entryList.add(index, new PrimaryHash(hashString1(word), new DataItem(word)));
        }
    };

    private static final TableSearchOperation DELETE_OPERATION = new TableSearchOperation() {
        @Override
        public void onFindMatch(HashTable table, PrimaryHash primaryHash, DataItem item) {
            primaryHash.hashMatches.remove(item); // Remove this match.
        }
    };

    /**
     * Add a word occurrence to this hash table.
     * @param word The word to add an occurrence of.
     */
    public void add(String word) {
        search(word, ADD_OPERATION);
    }

    /**
     * Deletes the given word from the hash table.
     * If the word is not present, this message will silently fail.
     * @param word The word to delete.
     */
    public void delete(String word) {
        search(word, DELETE_OPERATION);
    }

    /**
     * Perform a table operation which involves a search.
     * @param word  The word to search for.
     * @param operation The operation to apply.
     */
    private void search(String word, TableSearchOperation operation) {
        word = word.toLowerCase(); // The example uses this.
        int comparisons = 0;
        int wordHashCode = hashString1(word);

        int left = 0;
        int right = entryList.size() - 1;

        // Perform a binary search based on hash code.
        while (left <= right) {
            int tempIndex = (left + right) / 2;
            PrimaryHash tempHash = entryList.get(tempIndex);

            comparisons++;
            if (tempHash.primaryHashCode < wordHashCode) { // Hash code does not match.
                left = tempIndex + 1;
            } else {
                comparisons++;

                if (tempHash.primaryHashCode > wordHashCode) { // Hash code does not match.
                    right = tempIndex - 1;
                } else { // Hash code matches. We've found a match.
                    int doubleWordHashCode = hashString2(word);

                    for (DataItem testItem : tempHash.hashMatches) {
                        comparisons++;
                        if (doubleWordHashCode != hashString2(testItem.word))
                            continue; // Does not match.

                        // We've found a matching double hash.
                        successfulSearchComparisons += comparisons;
                        successfulSearchCount++;
                        operation.onFindMatch(this, tempHash, testItem);
                        return;
                    }

                    // If the word was not found, there is nothing to delete. Record it as an unsuccessful search.
                    unsuccessfulSearchComparisons += comparisons;
                    unsuccessfulSearchCount++;
                    operation.onMissingHash2(this, tempHash, word, tempIndex);
                    return;
                }
            }
        }

        // If the word was not found, there is nothing to delete. Record it as an unsuccessful search.
        unsuccessfulSearchComparisons += comparisons;
        unsuccessfulSearchCount++;
        operation.onMissingHash1(this, word, left);
    }

    /**
     * Gets the DataItem with the highest occurance count.
     * @return highestCount
     */
    public DataItem highcount() {
        DataItem maxItem = null;

        for (PrimaryHash tempHash : entryList)
            for (DataItem testItem : tempHash.hashMatches)
                if (maxItem == null || testItem.count >= maxItem.count)
                    maxItem = testItem;

        return maxItem;
    }

    /**
     * Computes the average number of comparisons done for successful searches, and returns that value.
     * Includes comparisons from insertions and deletions.
     * @return successfulSearchComparisons
     */
    public double get_avg_successful_search() {
        return ((double) successfulSearchComparisons / successfulSearchCount);
    }

    /**
     * Computes the average number of comparisons done for unsuccessful searches, and returns the sum.
     * Includes comparisons from insertions and deletions.
     * @return unsuccessfulSearchComparisons
     */
    public double get_avg_unsuccessful_search() {
        return ((double) unsuccessfulSearchComparisons / unsuccessfulSearchCount);
    }

    /**
     * Primary string hash method.
     * @param str The string to hash.
     * @return strHashCode
     */
    public static int hashString1(String str) {
        return hashString(str, 31);
    }

    /**
     * Secondary string hash method, which can completely avoid collisions when combined with the first hash method.
     * @param str The string to hash.
     * @return strHashCode
     */
    public static int hashString2(String str) {
        return hashString(str, 29);
    }

    /**
     * Generate a hashCode for a given string.
     * Uses all characters in the word being added, in a way which should minimize collisions.
     * @param str The string to get a hashcode for.
     * @param factor The factor to use when hashing the string. I read somewhere that it's good to use prime numbers for this.
     * @return hashCode
     */
    private static int hashString(String str, int factor) {
        char[] strCharacters = str.toCharArray();

        int hash = 0;
        for (char aChar : strCharacters)
            hash = (factor * hash) + aChar;

        return hash;
    }
}