import java.util.HashMap;
import java.util.Map;

/**
 * Represents a heap.
 */
public class MinHeap {
    public Edge[] array;
    public int heapSize;
    public Map<String, Integer> posMap = new HashMap<>();
    
    public MinHeap(int size) {
        this.array = new Edge[size];
    }

    public void buildHeap() {
        // Here because the Assignment pdf says it should be.
    }

    /**
     * Swap two nodes.
     * @param a One of the nodes.
     * @param b Another of the nodes.
     */
    public void swapHeapNode(int a, int b) {
        Edge temp = this.array[a];
        this.array[a] = this.array[b];
        this.array[b] = temp;
    }

    /**
     * Balance the heap.
     */
    public void minHeapify() {
        for (int i = size() / 2 - 1; i >= 0; i--)
            minHeapify(i);
    }

    /**
     * Balance the heap
     * @param i The index to attempt to balance.
     */
    private void minHeapify(int i) {
        int smallest = i;
        int left = 2 * i + 1;
        int right = left + 1;

        if (left < size() && array[left].distance < array[smallest].distance)
            smallest = left;
        if (right < size() && array[right].distance < array[smallest].distance)
            smallest = right;

        if (smallest != i) {
            this.posMap.put(this.array[smallest].town.name, i);
            this.posMap.put(this.array[i].town.name, smallest);
            this.swapHeapNode(smallest, i);
        }
    }

    /**
     * Extract the next value to visit.
     * @return toVisit
     */
    public Edge extractMin() {
        if (size() == 0)
            return null; // We're empty!

        Edge root = this.array[0];
        Edge lastNode = this.array[size() - 1];
        this.array[0] = lastNode;

        posMap.put(lastNode.town.name, 0);
        posMap.put(root.town.name, size() - 1);

        this.heapSize--;
        this.minHeapify();
        return root;
    }

    /**
     * Move a key, part of balancing, but also updates distances.
     * @param crawledTown The town to move.
     * @param distance    The new shortest distance
     */
    public void decreaseKey(Vertex crawledTown, int distance) {
        int i = posMap.get(crawledTown.name);
        this.array[i].distance = distance;

        int nextIndex = (i - 1) / 2;
        while (i > 0 && this.array[i].distance < this.array[nextIndex].distance) {
            posMap.put(this.array[i].town.name, nextIndex);
            posMap.put(this.array[nextIndex].town.name, i);
            this.swapHeapNode(i, nextIndex);

            i = nextIndex;
            nextIndex = (i - 1) / 2;
        }
    }

    /**
     * Gets the number of active elements in the heap.
     */
    public int size() {
        return this.heapSize;
    }

    /**
     * Test if a town is inside of the active heap data.
     * @param townName The name of the town to test.
     * @return isInHeap
     */
    public boolean isInHeap(String townName) {
        return posMap.get(townName) < size();
    }

}
