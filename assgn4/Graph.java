import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * Represents a graph.
 */
public class Graph {
    public List<String> townNames = new ArrayList<>();
    private LinkedList<Vertex> vertexes = new LinkedList<>();

    /**
     * Insert an edge connecting two towns.
     * @param town1    The first town.
     * @param town2    The second town.
     * @param distance The distance between them.
     */
    public void insert(String town1, String town2, int distance) {
        Vertex v1 = getVertex(town1);
        Vertex v2 = getVertex(town2);

        v1.adjacencyList.addFirst(new Edge(v2, distance));
        v2.adjacencyList.addFirst(new Edge(v1, distance));
    }

    /**
     * Builds the heap.
     * @param start The string paths will start at.
     * @param heap  The heap to do calculations in.
     */
    private void buildHeap(String start, MinHeap heap) {

        for (int i = 0; i < size(); i++) {
            String townName = townNames.get(i);
            Vertex vertex = getVertex(townName);
            vertex.distance = Integer.MAX_VALUE;
            heap.array[i] = new Edge(vertex, vertex.distance);
            heap.posMap.put(townName, i);
        }

        Vertex startVertex = getVertex(start);
        startVertex.distance = 0; // The distance of the starting vertex is 0 distance from the start.
        heap.decreaseKey(startVertex, startVertex.distance);

        heap.heapSize = size();

        while (heap.size() > 0) {
            Edge newNode = heap.extractMin();
            Vertex visitTown = newNode.town;
            int townDistance = visitTown.distance;

            for (Edge crawl : newNode.town.adjacencyList) {
                Vertex crawlTown = crawl.town;

                if (heap.isInHeap(crawlTown.name) && townDistance != Integer.MAX_VALUE && crawl.distance + townDistance < crawlTown.distance) {
                    crawlTown.distance = crawl.distance + townDistance;
                    crawlTown.previousVertex = visitTown;
                    heap.decreaseKey(crawlTown, crawlTown.distance);
                }
            }
        }
    }

    /**
     * Show the towns and distances for the shortest route from one point to another.
     * @param start  The starting town.
     * @param finish The destination town.
     */
    private void showPath(String start, String finish) {
        Vertex finishVertex = getVertex(finish);
        boolean hasNext = !start.equals(finish);

        System.out.println(finishVertex.name + (hasNext ? " (" + finishVertex.distance + ")" : ""));
        if (hasNext)
            showPath(start, finishVertex.previousVertex.name);
    }

    /**
     * Calculate and display the shortest path from a start-point to an end-point.
     * @param start  The starting point.
     * @param finish The destination.
     */
    public void shortestPath(String start, String finish) {
        buildHeap(start, new MinHeap(size()));
        showPath(start, finish);
    }

    /**
     * The number of unique towns tracked.
     */
    private int size() {
        return this.townNames.size();
    }

    /**
     * Gets a Vertex based on a town's name.
     * @param townName The name of the town to get a vertex for.
     * @return vertex
     */
    public Vertex getVertex(String townName) {
        for (Vertex vertex : vertexes)
            if (vertex.name.equals(townName))
                return vertex;

        Vertex vertex = new Vertex(townName);
        vertexes.addLast(vertex);
        return vertex;
    }
}
