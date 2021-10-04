// A Java program to find articulation points in undirected graph (Cut vertices)
// in an undirected graph,  Tarjan’s algorithm
package datastructures.graph.articulationpoints;

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

	private void aPUtil(int u, boolean visited[], int disc[], int low[], int parent, boolean isAP[]) {

		System.out.println("IN u" + u);
		// Count of children in DFS Tree
		int children = 0;
		// Mark the current node as visited
		visited[u] = true;

		// Initialize discovery time and low value
		disc[u] = low[u] = ++time;
		System.out.println("in N" + u + " d" + disc[u] + " l" + low[u]);

		// Go through all vertices adjacent to this
		for (Integer v : adjacents.get(u)) {
			// If v is not visited yet, then make it a child of u
			// in DFS tree and recur for it
			if (!visited[v]) {
				children++;
				aPUtil(v, visited, disc, low, u, isAP);

				// Check if the subtree rooted with v has
				// a connection to one of the ancestors of u
				low[u] = Math.min(low[u], low[v]);
				System.out.println("vis N" + u + " d" + disc[u] + " l" + low[u]);

				// If u is not root and low value of one of
				// its child is more than discovery value of u.
				if (parent != -1 && low[v] >= disc[u])
					isAP[u] = true;
			}

			// Update low value of u for parent function calls.
			else if (v != parent) {
				low[u] = Math.min(low[u], disc[v]);
				System.out.println("noP N" + u + " d" + disc[u] + " l" + low[u]);
			}
		}

		// If u is root of DFS tree and has two or more children.
		if (parent == -1 && children > 1)
			isAP[u] = true;
	}

	public void articulationPoints() {

		boolean[] visited = new boolean[num_of_v];
		int[] disc = new int[num_of_v];
		int[] low = new int[num_of_v];
		boolean[] isAP = new boolean[num_of_v];
		int par = -1;

		// Adding this loop so that the
		// code works even if we are given
		// disconnected graph
		for (int u = 0; u < num_of_v; u++)
			if (visited[u] == false)
				aPUtil(u, visited, disc, low, par, isAP);

		for (int u = 0; u < num_of_v; u++)
			if (isAP[u] == true)
				System.out.print(u + " ");
		System.out.println();
	}

	public static void main(String[] args) {

		// Creating first example graph
		Graph g1 = new Graph(5);

//        1-----0-----3-----4
//        |    /
//        |   /
//        2---
		g1.addEdge(1, 0);
		g1.addEdge(0, 2);
		g1.addEdge(2, 1);
		g1.addEdge(0, 3);
		g1.addEdge(3, 4);
		System.out.println("Articulation points in first graph");
		g1.articulationPoints();

		// Creating second example graph
		Graph g2 = new Graph(4);

		// 0----1----2----3
		g2.addEdge(0, 1);
		g2.addEdge(1, 2);
		g2.addEdge(2, 3);

		System.out.println("Articulation points in second graph");
		g2.articulationPoints();

		// Creating third example graph
		Graph g3 = new Graph(7);

		// 6
		// |
		// |
		// 0-----1----3-----5
		// |    / \         |
		// |   /   \        |
		// 2---     --4-----+
		g3.addEdge(0, 1);
		g3.addEdge(1, 2);
		g3.addEdge(2, 0);
		g3.addEdge(1, 3);
		g3.addEdge(1, 4);
		g3.addEdge(1, 6);
		g3.addEdge(3, 5);
		g3.addEdge(4, 5);

		System.out.println("Articulation points in third graph");
		g3.articulationPoints();
	}

}
