// A Java program to find if a given undirected graph is biconnected
package datastructures.graph.biconnected;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

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

	// A recursive function that returns true if there is an articulation
	// point in given graph, otherwise returns false.
	// This function is almost same as isAPUtil() @ http://goo.gl/Me9Fw
	// u --> The vertex to be visited next
	// visited[] --> keeps tract of visited vertices
	// disc[] --> Stores discovery times of visited vertices
	// parent[] --> Stores parent vertices in DFS tree
	boolean isBCUtil(int u, boolean visited[], int disc[], int low[], int parent[]) {

		// Count of children in DFS Tree
		int children = 0;

		// Mark the current node as visited
		visited[u] = true;

		// Initialize discovery time and low value
		disc[u] = low[u] = ++time;

		// Go through all vertices adjacent to this
		for (Integer v:adjacents.get(u)){

			// If v is not visited yet, then make it a child of u
			// in DFS tree and recur for it
			if (!visited[v]) {
				children++;
				parent[v] = u;

				// check if subgraph rooted with v has an articulation point
				if (isBCUtil(v, visited, disc, low, parent))
					return true;

				// Check if the subtree rooted with v has a connection to
				// one of the ancestors of u
				low[u] = Math.min(low[u], low[v]);

				// u is an articulation point in following cases

				// (1) u is root of DFS tree and has two or more children.
				if (parent[u] == -1 && children > 1)
					return true;

				// (2) If u is not root and low value of one of its
				// child is more than discovery value of u.
				if (parent[u] != -1 && low[v] >= disc[u])
					return true;
			}

			// Update low value of u for parent function calls.
			else if (v != parent[u])
				low[u] = Math.min(low[u], disc[v]);
		}
		return false;
	}

	// The main function that returns true if graph is Biconnected,
	// otherwise false. It uses recursive function isBCUtil()
	boolean isBC() {
		// Mark all the vertices as not visited
		boolean visited[] = new boolean[num_of_v];
		int disc[] = new int[num_of_v];
		int low[] = new int[num_of_v];
		int parent[] = new int[num_of_v];

		// Initialize parent and visited, and ap(articulation point)
		// arrays
		for (int i = 0; i < num_of_v; i++) {
			parent[i] = -1;
			visited[i] = false;
		}

		// Call the recursive helper function to find if there is an
		// articulation/ point in given graph. We do DFS traversal
		// starring from vertex 0
		if (isBCUtil(0, visited, disc, low, parent) == true)
			return false;

		// Now check whether the given graph is connected or not.
		// An undirected graph is connected if all vertices are
		// reachable from any starting point (we have taken 0 as
		// starting point)
		for (int i = 0; i < num_of_v; i++)
			if (visited[i] == false)
				return false;

		return true;
	}

	public static void main(String[] args) {
		Graph g1 = new Graph(2);
		// 0----1
		g1.addEdge(0, 1);
		if (g1.isBC())
			System.out.println("Yes");
		else
			System.out.println("No");

		Graph g2 = new Graph(5);

		// 1---0----3
		//  \  |    |
		//   \ |    |
		//     2----4
		g2.addEdge(1, 0);
		g2.addEdge(0, 2);
		g2.addEdge(2, 1);
		g2.addEdge(0, 3);
		g2.addEdge(3, 4);
		g2.addEdge(2, 4);
		if (g2.isBC())
			System.out.println("Yes");
		else
			System.out.println("No");

		Graph g3 = new Graph(3);

		// 0---1---2
		g3.addEdge(0, 1);
		g3.addEdge(1, 2);
		if (g3.isBC())
			System.out.println("Yes");
		else
			System.out.println("No");

		Graph g4 = new Graph(5);
		// 1---0----3
		//  \  |    |
		//   \ |    |
		//     2    4
		g4.addEdge(1, 0);
		g4.addEdge(0, 2);
		g4.addEdge(2, 1);
		g4.addEdge(0, 3);
		g4.addEdge(3, 4);
		if (g4.isBC())
			System.out.println("Yes");
		else
			System.out.println("No");

		Graph g5 = new Graph(3);
		// 0---1
		//  \  |
		//   \ |
		//     2
		g5.addEdge(0, 1);
		g5.addEdge(1, 2);
		g5.addEdge(2, 0);
		if (g5.isBC())
			System.out.println("Yes");
		else
			System.out.println("No");
	}

}
