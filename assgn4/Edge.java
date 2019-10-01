/**
 * Connects two vertexes. Has distance.
 */
public class Edge {
    public int distance;
    public Vertex town;

    public Edge(Vertex town, int distance) {
        this.distance = distance;
        this.town = town;
    }
}
