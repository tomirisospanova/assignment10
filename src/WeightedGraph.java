import java.util.*;

public class WeightedGraph<V> {
    private final boolean undirected;
    private Map<V, Vertex<V>> verticesMap = new HashMap<>();

    public WeightedGraph() {
        this.undirected = true;
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(V v) {
        verticesMap.put(v, new Vertex<>(v));
    }

    public void addEdge(V source, V dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest) || source.equals(dest))
            return;

        verticesMap.get(source).addAdjacentVertex(verticesMap.get(dest), weight);

        if (undirected)
            verticesMap.get(dest).addAdjacentVertex(verticesMap.get(source), weight);
    }

    public int getVerticesCount() {
        return verticesMap.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex<V> v : verticesMap.values()) {
            count += v.getAdjacentVertices().size();
        }

        if (undirected)
            count /= 2;

        return count;
    }

    public boolean hasVertex(V v) {
        return verticesMap.containsKey(v);
    }

    public boolean hasEdge(V source, V dest) {
        if (!hasVertex(source))
            return false;

        return verticesMap.get(source).getAdjacentVertices().containsKey(verticesMap.get(dest));
    }

    public Iterable<V> adjacencyList(V v) {
        if (!hasVertex(v))
            return null;

        List<V> adjacencyList = new LinkedList<>();
        for (Vertex<V> vertex : verticesMap.get(v).getAdjacentVertices().keySet()) {
            adjacencyList.add(vertex.getData());
        }

        return adjacencyList;
    }

    public Iterable<Vertex<V>> getEdges(V v) {
        if (!hasVertex(v))
            return null;

        return verticesMap.get(v).getAdjacentVertices().keySet();
    }

    public Iterable<V> getVertices() {
        return verticesMap.keySet();
    }
}