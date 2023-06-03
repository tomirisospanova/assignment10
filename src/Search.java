import java.util.*;

public class Search<V> {
    protected int count;
    protected Set<V> marked;
    protected Map<V, V> edgeTo;
    protected final V source;

    public Search(V source) {
        this.source = source;
        marked = new HashSet<>();
        edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(V v) {
        return marked.contains(v);
    }

    public Iterable<V> pathTo(V v) {
        if (!hasPathTo(v)) return null;
        LinkedList<V> path = new LinkedList<>();
        for (V i = v; i != source; i = edgeTo.get(i)) {
            path.push(i);
        }
        path.push(source);

        return path;
    }

    public int getCount() {
        return count;
    }
}