import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A graph with a fixed number of vertices implemented using adjacency maps.
 * Space complexity is &Theta;(n + m) where n is the number of vertices and m
 * the number of edges.
 *
 * @author [Name]
 * @version [Date]
 */
public class Graph {
    /**
     * The map edges[v] contains the key-value pair (w, c) if there is an edge from
     * v to w; c is the cost assigned to this edge.
     */
    private final Map<Integer, Integer>[] edges;

    /** Number of edges in the graph. */
    private int numEdges;

    /**
     * Constructs a HashGraph with n vertices and no edges. Time complexity: O(n)
     *
     * @throws IllegalArgumentException if n < 0
     */
    public Graph(int n) {
        if (n < 0)
            throw new IllegalArgumentException("n = " + n);

        // The array will contain only Map<Integer, Integer> instances created
        // in addEdge(). This is sufficient to ensure type safety.
        @SuppressWarnings("unchecked")
        Map<Integer, Integer>[] a = new HashMap[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = new HashMap<>();
        }
        edges = a;
        numEdges = 0;
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int numVertices() {
        return edges.length;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int numEdges() {
        return numEdges;
    }

    /**
    * Helper function that determines if v is a valid vertex in the graph.
    *
    * @param v vertex
    * @return boolean result for presence of vertex v
    */
    private boolean validEdge(int v) {
        return v >= 0 && v < edges.length;
    }

    /**
     * Returns the outdegree of vertex v.
     *
     * @param v vertex
     * @return the outdegree of vertex v
     * @throws IllegalArgumentException if v is out of range
     */
    public int degree(int v) throws IllegalArgumentException {
        if (!validEdge(v))
            throw new IllegalArgumentException();
        Map<Integer, Integer> current = edges[v];
        if (current.isEmpty()) {
            return 0; // has no edges.
        }
        return current.size();
    }

    /**
     * Returns an iterator of vertices adjacent to v.
     *
     * @param v vertex
     * @return an iterator of vertices adjacent to v
     * @throws IllegalArgumentException if v is out of range
     */
    public Iterator<Integer> neighbors(int v) {
        if (!validEdge(v)) {
            throw new IllegalArgumentException();
        }
        return edges[v].keySet().iterator(); // only interested in keys (the neighbors)
    }

    /**
     * Returns true if there is an edge (from, to).
     *
     * @param v vertex
     * @param w vertex
     * @return true if there is an edge (from, to).
     * @throws IllegalArgumentException if from or to are out of range
     */
    public boolean hasEdge(int v, int w) {
        if (!validEdge(v) || !validEdge(w)) {
            throw new IllegalArgumentException();
        }
        // self loop is also adjacent
        if (edges[v].containsKey(w)) {
            return true;
        }
        return false;
    }

    /**
     * Returns the edge cost if from and to are adjacent, otherwise -1.
     *
     * @param v vertex
     * @param w vertex
     * @return edge cost if available, -1 otherwise
     * @throws IllegalArgumentException if from or to are out of range
     */
    public int cost(int v, int w) throws IllegalArgumentException {
        if (!validEdge(v) || !validEdge(w)) {
            throw new IllegalArgumentException();
        }
        if (hasEdge(v, w)) {
            return edges[v].get(w);
        } else {
            return -1;
        }
    }

    /**
     * Inserts an edge with edge cost c.
     *
     * @param c edge cost, c >= 0
     * @param v vertex
     * @param w vertex
     * @throws IllegalArgumentException if from or to are out of range
     */
    public void add(int v, int w, int c) {
        if (!validEdge(v) || !validEdge(w)) {
            throw new IllegalArgumentException();
        }
        if (edges[v].get(w) != null) {
            edges[v].put(w, c);
            edges[w].put(v, c);
        } else {
            edges[v].put(w, c);
            edges[w].put(v, c);
            numEdges++;
        }

    }

    /**
     * Removes the edges between v and w.
     *
     * @param v vertex
     * @param w vertex
     * @throws IllegalArgumentException if v or w are out of range
     */
    public void remove(int v, int w) {
        if (!validEdge(v) || !validEdge(w)) {
            throw new IllegalArgumentException();
        }
        if (edges[v].get(w) != null) {
            edges[v].remove(w);
            edges[w].remove(v);
            numEdges--;
        }
    }

    /**
     * Returns a string representation of this graph.
     *
     * Should represent the graph in terms of edges (order does not matter). For
     * example, if a graph has edges (2,3,0) and (1,0,0), the
     * representaiton should be:
     *
     * "{(1,0,0), (2,3,0)}" or "{(2,3,0), (1,0,0)}"
     *
     * An empty graph is just braces:
     *
     * "{}"
     *
     * @return a String representation of this graph
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        boolean[][] added = new boolean[edges.length][edges.length];
        sb.append("{");
        for (int v = 0; v < edges.length; v++) {

            for (Map.Entry<Integer, Integer> entrySet : edges[v].entrySet()) {
                if (added[v][entrySet.getKey()] || added[entrySet.getKey()][v]) {
                    continue;
                }
                sb.append("(" + v + ",");
                sb.append(entrySet.getKey() + ",");
                sb.append(entrySet.getValue() + "), ");
                added[v][entrySet.getKey()] = true;
            }
        }
        if (sb.length() >= 2) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}");
        return sb.toString();

        }
}
