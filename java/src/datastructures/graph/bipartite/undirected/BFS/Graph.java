// JAVA Code to check whether a given undirected graph is Bipartite or not
// BS version
package datastructures.graph.bipartite.undirected.BFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
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
	
	// This function returns true if graph is Bipartite, else false
	public boolean isBipartiteUtil(int src, int colorArr[]) {
		colorArr[src] = 1;

		// Create a queue (FIFO) of vertex numbers and
		// enqueue source vertex for BFS traversal
		LinkedList<Integer> q = new LinkedList<Integer>();
		q.add(src);

		// Run while there are vertices in queue
		// (Similar to BFS)
		while (!q.isEmpty()) {
			// Dequeue a vertex from queue
			// ( Refer http://goo.gl/35oz8 )
			int u = q.pop();

			// Return false if there is a self-loop
			if (adjacents.get(u).contains(u)) {
				return false;				
			}

			// Find all non-colored adjacent vertices
			for (Integer v: adjacents.get(u)) {
				// An edge from u to v exists and
				// destination v is not colored
				if (colorArr[v] == -1) {
					// Assign alternate color to this
					// adjacent v of u
					colorArr[v] = 1 - colorArr[u];
					q.push(v);
				}

				// An edge from u to v exists and
				// destination v is colored with same
				// color as u
				else if (colorArr[v] == colorArr[u]) {
					return false;
				}
			}
		}

		// If we reach here, then all adjacent vertices
		// can be colored with alternate color
		return true;
	}

	public boolean isBipartite() {
		// Create a color array to store colors assigned
		// to all vertices. Vertex/ number is used as
		// index in this array. The value '-1' of
		// colorArr[i] is used to indicate that no color
		// is assigned to vertex 'i'. The value 1 is used
		// to indicate first color is assigned and value
		// 0 indicates second color is assigned.
		int colorArr[] = new int[num_of_v];
		Arrays.fill(colorArr, -1);

		// This code is to handle disconnected graoh
		for (int i = 0; i < num_of_v; i++)
			if (colorArr[i] == -1)
				if (isBipartiteUtil(i, colorArr) == false)
					return false;

		return true;
	}
	
	public static void main(String[] args) {
        // Let us first create and test
        // graphs shown in above figure
        Graph g1 = new Graph(4);
        //  0---1---2
        //   \     /
        //    \   /
        //      3
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 0);
        
        if (g1.isBipartite())
            System.out.println("Yes");
        else
            System.out.println("No");
        
        Graph g2 = new Graph(6);
        // 1---0---4---2
        //     |   |
        //     |   |
        //     5---3
        g2.addEdge(0, 1);
        g2.addEdge(0, 4);
        g2.addEdge(2, 4);
        g2.addEdge(3, 4);
        g2.addEdge(3, 5);
        g2.addEdge(5, 0);
        
        if (g2.isBipartite())
            System.out.println("Yes");
        else
            System.out.println("No");
        
        Graph g3 = new Graph(6);
        // 1---0---4---2
        // |       |
        // |       |
        // 5-------3
        g3.addEdge(0, 1);
        g3.addEdge(0, 4);
        g3.addEdge(2, 4);
        g3.addEdge(3, 4);
        g3.addEdge(3, 5);
        g3.addEdge(5, 1);
        
        if (g3.isBipartite())
            System.out.println("Yes");
        else
            System.out.println("No");
        
        Graph g4 = new Graph(3);
        // 1---0---2
        // |       |
        // +-------+
        g4.addEdge(1, 0);
        g4.addEdge(0, 2);
        g4.addEdge(2, 1);
        
        if (g4.isBipartite())
            System.out.println("Yes");
        else
            System.out.println("No");
        
        Graph g5 = new Graph(3);
        // 1---0---2
        g5.addEdge(1, 0);
        g5.addEdge(0, 2);
        
        System.out.println(g5.isBipartite()? "Yes": "No");

	}

}
