// Transitive closure using DFS
// Complexity : O(V^2)
package datastructure.graph.trasitiveclosure;

import java.util.ArrayList;
import java.util.Arrays;

// A directed graph using
// adjacency list representation
public class GraphClosureV2 {

	// No. of vertices in graph
	private int vertices;

	// adjacency list
	private ArrayList<Integer>[] adjList;

	// To store transitive closure
	private int[][] tc;

	// Constructor
	public GraphClosureV2(int vertices) {

		// initialise vertex count
		this.vertices = vertices;
		// Transitive closure matrices, it is actually matrices of visited vertices
		this.tc = new int[this.vertices][this.vertices];

		// initialise adjacency list
		initAdjList();
	}

	// utility method to initialise adjacency list
	@SuppressWarnings("unchecked")
	private void initAdjList() {

		adjList = new ArrayList[vertices];
		for (int i = 0; i < vertices; i++) {
			adjList[i] = new ArrayList<>();
		}
	}

	// add edge from u to v
	public void addEdge(int u, int v) {
					
	// Add v to u's list.
		adjList[u].add(v);
	}

	// The function to find transitive
	// closure. It uses
	// recursive DFSUtil()
	public void transitiveClosure() {

		// Call the recursive helper
		// function to print DFS
		// traversal starting from all
		// vertices one by one
		for (int i = 0; i < vertices; i++) {
			dfsUtil(i, i);
		}

		for (int i = 0; i < vertices; i++) {
		System.out.println(Arrays.toString(tc[i]));
		}
	}

	// A recursive DFS traversal
	// function that finds
	// all reachable vertices for s
	private void dfsUtil(int s, int v) {

//		System.out.println(String.format("s=%d v=%d", s,v));
		// Mark reachability from
		// s to v as true.
		if(s==v){
//			if(adjList[v].contains(v)) // now the result is the same as for GraphClosure
			tc[s][v] = 1;
			}
		else
			tc[s][v] = 1;
			
		// Find all the vertices reachable
		// through v
		for (int adj : adjList[v]) {		
			if (tc[s][adj]==0) { // if not visited
				dfsUtil(s, adj);
			}
		}
	}
		
	// Driver Code
	public static void main(String[] args) {

		/* Let us create the following weighted graph
		10
	(0)------->(3)
	|		  |\
  5 |         |
	|         | 1
   \|         |
   (1)------->(2)
		3		 */
		
		GraphClosureV2 g = new GraphClosureV2(4);
		g.addEdge(0, 0);
		g.addEdge(0, 1);
		g.addEdge(0, 3);
		g.addEdge(1, 2);
		g.addEdge(2, 3);
		System.out.println("Transitive closure " +
				"matrix is");

		g.transitiveClosure();

	}
}


