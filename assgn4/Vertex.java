import java.util.LinkedList;

/**
 * Represents a Vertex / Town. We'd call it a town, but it was given to us as a Vertex.
 */
public class Vertex {
    public String name;
    public LinkedList<Edge> adjacencyList;

    public int distance;
    public Vertex previousVertex;

    public Vertex(String town) {
        this.name = town;
        this.adjacencyList = new LinkedList<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Town[name=");
        sb.append(name).append(",adjacent=").append(adjacencyList.size());
        for (Edge edge : adjacencyList)
            sb.append(", ").append(edge.town.name);
        return sb.append("]").toString();
    }
}
