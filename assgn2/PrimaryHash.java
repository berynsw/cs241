import java.util.LinkedList;

/**
 * Holds the primary hash and all DataItems which have a matching primary hash.
 * Created by gawlind on 10/23/2018.
 */
public class PrimaryHash {
    int primaryHashCode;
    LinkedList<DataItem> hashMatches = new LinkedList<>();

    public PrimaryHash(int primaryHashCode, DataItem startItem) {
        this.primaryHashCode = primaryHashCode;
        this.hashMatches.add(startItem);
    }
}
