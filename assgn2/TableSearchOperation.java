/**
 * An abstract table operation which involves a search.
 * Created by gawlind on 10/23/18.
 */
public class TableSearchOperation {

    /**
     * Called when a match is found.
     * @param table       The table being searched.
     * @param primaryHash The PrimaryHash holding info about the double hash.
     * @param item        The match which was found.
     */
    public void onFindMatch(HashTable table, PrimaryHash primaryHash, DataItem item) {

    }

    /**
     * Called if there is no primary hash match.
     * @param table The table being searched.
     * @param word  The word being searched for.
     * @param index The index the PrimaryHash would be located at.
     */
    public void onMissingHash1(HashTable table, String word, int index) {

    }

    /**
     * Called if the primary hash is found, but there is no secondary match.
     * @param table       The table being searched.
     * @param primaryHash The relevant primary hash.
     * @param word        The word being searched for.
     * @param index       The index the DataItem should be located at in the PrimaryHash list.
     */
    public void onMissingHash2(HashTable table, PrimaryHash primaryHash, String word, int index) {

    }
}
