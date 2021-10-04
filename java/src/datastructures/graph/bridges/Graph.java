// A Java program to find bridges in a given undirected graph
package datastructures.graph.bridges;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.sun.org.apache.xerces.internal.impl.dv.dtd.NMTOKENDatatypeValidator;

public class Graph {

	public Integer num_of_v;
	private List<List<Integer>> adjacents = new ArrayList<>();
	private int time = 0;

	public Graph(Integer num_of_v) {
		this.num_of_v = num_of_v;
		IntStream.range(0, this.num_of_v).forEach(i -> adjacents.add(new ArrayList<>()));
	}

	public void addEdge(Integer source_v, Integer dest_v) {
		adjacents.get(source_v).add(dest_v);
		adjacents.get(dest_v).add(source_v);
	}
	
	   // A recursive function that finds and prints bridges
    // using DFS traversal
    // u --> The vertex to be visited next
    // visited[] --> keeps tract of visited vertices
    // disc[] --> Stores discovery times of visited vertices
    // parent[] --> Stores parent vertices in DFS tree
    void bridgeUtil(int u, boolean visited[], int disc[],
                    int low[], int parent[])
    {
 
        // Mark the current node as visited
        visited[u] = true;
 
        // Initialize discovery time and low value
        disc[u] = low[u] = ++time;
 
        // Go through all vertices adjacent to this
        for (Integer v: adjacents.get(u)) {
 
            // If v is not visited yet, then make it a child
            // of u in DFS tree and recur for it.
            // If v is not visited yet, then recur for it
            if (!visited[v])
            {
                parent[v] = u;
                bridgeUtil(v, visited, disc, low, parent);
 
                // Check if the subtree rooted with v has a
                // connection to one of the ancestors of u
                low[u]  = Math.min(low[u], low[v]);
 
                // If the lowest vertex reachable from subtree
                // under v is below u in DFS tree, then u-v is
                // a bridge
                if (low[v] > disc[u])
                    System.out.println(u+" "+v);
            }
 
            // Update low value of u for parent function calls.
            else if (v != parent[u])
                low[u]  = Math.min(low[u], disc[v]);
        }
    }

	void bridge()
    {
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[num_of_v];
        int disc[] = new int[num_of_v];
        int low[] = new int[num_of_v];
        int parent[] = new int[num_of_v];
 
 
        // Initialize parent and visited, and ap(articulation point)
        // arrays
        for (int i = 0; i < num_of_v; i++)
        {
            parent[i] = -1;
            visited[i] = false;
        }
 
        // Call the recursive helper function to find Bridges
        // in DFS tree rooted with vertex 'i'
        for (int i = 0; i < num_of_v; i++)
            if (visited[i] == false)
                bridgeUtil(i, visited, disc, low, parent);
    }
	public static void main(String[] args) {
		 // Create graphs given in above diagrams
        System.out.println("Bridges in first graph ");
        Graph g1 = new Graph(5);
        
        // 1----0----3
        // |  /      |
        // | /       |
        // 2         4
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        g1.bridge();
        System.out.println();
 
        System.out.println("Bridges in Second graph");
        Graph g2 = new Graph(4);
        
        // 0---1---2---3
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        g2.bridge();
        System.out.println();
 
        System.out.println("Bridges in Third graph ");
        Graph g3 = new Graph(7);
        
        //  0----1----3----5
        //  |  / | \       |
        //  | /  |  \      |
        //  2    6   4-----+
        g3.addEdge(0, 1);
        g3.addEdge(1, 2);
        g3.addEdge(2, 0);
        g3.addEdge(1, 3);
        g3.addEdge(1, 4);
        g3.addEdge(1, 6);
        g3.addEdge(3, 5);
        g3.addEdge(4, 5);
        g3.bridge();
	}

}
