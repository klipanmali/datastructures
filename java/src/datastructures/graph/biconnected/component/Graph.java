// Java program to find biconnected components in a given undirected graph

package datastructures.graph.biconnected.component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Graph {

	public Integer num_of_v;
	private Integer edges = 0;
	private List<List<Integer>> adjacents = new ArrayList<>();
	private int time = 0, count = 0;

	class Edge {
		int u;
		int v;

		Edge(int u, int v) {
			this.u = u;
			this.v = v;
		}
	};

	public Graph(Integer num_of_v) {
		this.num_of_v = num_of_v;
		IntStream.range(0, this.num_of_v).forEach(i -> adjacents.add(new ArrayList<>()));
	}

	public void addEdge(Integer source_v, Integer dest_v) {
		adjacents.get(source_v).add(dest_v);
		edges++;
		adjacents.get(dest_v).add(source_v);
		edges++;
	}

    // A recursive function that finds and prints strongly connected
    // components using DFS traversal
    // u --> The vertex to be visited next
    // disc[] --> Stores discovery times of visited vertices
    // low[] -- >> earliest visited vertex (the vertex with minimum
    // discovery time) that can be reached from subtree
    // rooted with current vertex
    // *st -- >> To store visited edges
    void BCCUtil(int u, int disc[], int low[], LinkedList<Edge> st,
                 int parent[])
    {
 
        // Initialize discovery time and low value
        disc[u] = low[u] = ++time;
        int children = 0;
 
        // Go through all vertices adjacent to this
        for (Integer v : adjacents.get(u)) {
 
            // If v is not visited yet, then recur for it
            if (disc[v] == -1) {
                children++;
                parent[v] = u;
 
                // store the edge in stack
                st.add(new Edge(u, v));
                BCCUtil(v, disc, low, st, parent);
 
                // Check if the subtree rooted with 'v' has a
                // connection to one of the ancestors of 'u'
                // Case 1 -- per Strongly Connected Components Article
                if (low[u] > low[v])
                    low[u] = low[v];
 
                // If u is an articulation point,
                // pop all edges from stack till u -- v
                if ((disc[u] == 1 && children > 1) || (disc[u] > 1 && low[v] >= disc[u])) {
                    while (st.getLast().u != u || st.getLast().v != v) {
                        System.out.print(st.getLast().u + "--" + st.getLast().v + " ");
                        st.removeLast();
                    }
                    System.out.println(st.getLast().u + "--" + st.getLast().v + " ");
                    st.removeLast();
 
                    count++;
                }
            }
 
            // Update low value of 'u' only if 'v' is still in stack
            // (i.e. it's a back edge, not cross edge).
            // Case 2 -- per Strongly Connected Components Article
            else if (v != parent[u] && disc[v] < disc[u] ) {
                if (low[u] > disc[v])
                    low[u] = disc[v];
 
                st.add(new Edge(u, v));
            }
        }
    }
	void BCC() {
		int disc[] = new int[num_of_v];
		int low[] = new int[num_of_v];
		int parent[] = new int[num_of_v];
        LinkedList<Edge> st = new LinkedList<Edge>();

		// Initialize disc and low, and parent arrays
		for (int i = 0; i < num_of_v; i++) {
			disc[i] = -1;
			low[i] = -1;
			parent[i] = -1;
		}

		for (int i = 0; i < num_of_v; i++) {
			if (disc[i] == -1)
				BCCUtil(i, disc, low, st, parent);

			int j = 0;

			// If stack is not empty, pop all edges from stack
			while (st.size() > 0) {
				j = 1;
				System.out.print(st.getLast().u + "--" + st.getLast().v + " ");
				st.removeLast();
			}
			if (j == 1) {
				System.out.println();
				count++;
			}
		}
	}

	public static void main(String[] args) {
		Graph g = new Graph(12);
		
		//  4----3       7
		//  |   /|      / \
		//  | /  |     /   \
		//  2----1----5-----8----9
		//       |    |
		//       0----6    10----11
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 4);
        g.addEdge(1, 5);
        g.addEdge(0, 6);
        g.addEdge(5, 6);
        g.addEdge(5, 7);
        g.addEdge(5, 8);
        g.addEdge(7, 8);
        g.addEdge(8, 9);
        g.addEdge(10, 11);
 
        g.BCC();
 
        System.out.println("Above are " + g.count + " biconnected components in graph");
	}

}
