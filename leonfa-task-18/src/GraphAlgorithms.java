import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class for solving graph problems.
 *
 * @author [Name]
 * @version [Date]
 */

public class GraphAlgorithms {

    /**
    * Determines if a component has a cycle.
    *
    * @param g the graph to search
    * @param v the node to start at
    * @param visited boolean array keeping track of visited nodes
    * @param parent parent node of v
    * @return true if component has cycle, false otherwise
    */
    private static boolean DFS(Graph g, int v, boolean[] visited, int parent) {
        visited[v] = true;
        Iterator<Integer> it = g.neighbors(v);
        while (it.hasNext()) {
            int n = it.next();
            if (!visited[n]) {
                return DFS(g, n, visited, v);
            } else if (n != parent) {
                return true;
            }
        }
        return false;
    }

    /**
    * Determine if there is a cycle in the graph. Implement using DFS.
    *
    * @param g the graph to search
    * @return true if there exists at least one cycle in the graph, false otherwise
    */
    public static boolean hasCycle(Graph g) {
        boolean[] visited = new boolean[g.numVertices()];

        for (int v = 0; v < g.numVertices(); v++) {
            if (!visited[v]) {
                if (DFS(g, v, visited, -1) == true) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
    * Determine if there is a path between two vertices. Implement using
    * BFS.
    *
    * @param v vertex
    * @param w vertex
    * @param g the graph to search
    * @return true if there is a path between v and w, false otherwise
    */
    public static boolean hasPath(Graph g, int v, int w) {
        boolean[] visited = new boolean[g.numVertices()];
        LinkedList<Integer> queue = new LinkedList<>();
        Iterator<Integer> it;
        // visits the first element
        visited[v] = true;
        queue.addLast(v);
        int current;
        while (!queue.isEmpty()) {
            current = queue.removeFirst();
            it = g.neighbors(current);
            while (it.hasNext()) {
                int next = it.next();

                if (next == w) {
                    return true;
                }
                if (!visited[next]) {
                    visited[next] = true;
                    queue.addFirst(next);
                }
            }
        }
        return false;
    }
}
