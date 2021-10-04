// Fleury’s Algorithm for printing Eulerian Path or Circuit in undirected graph
// Not very efficient, ime complexity is O ((V+E)2)
// There are better algorithms to print Euler tour, Hierholzer’s Algorithm finds in O(V+E) time.
// A Java program print Eulerian Trail in a given Eulerian or Semi-Eulerian Graph
package datastructures.graph.eulerian.undirected.fleury;

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
	
	public void removeEdge(Integer source_v, Integer dest_v) {
		adjacents.get(source_v).remove(dest_v);
		adjacents.get(dest_v).remove(source_v);
	}
	
	/*
	 * The main function that print Eulerian Trail. It first finds an odd degree
	 * vertex (if there is any) and then calls printEulerUtil() to print the path
	 */
	private void printEulerTour() {
		// Find a vertex with odd degree
		Integer u = 0;
		for (int i = 0; i < num_of_v; i++) {
			if (adjacents.get(i).size() % 2 == 1) {
				u = i;
				break;
			}
		}

		// Print tour starting from oddv
		printEulerUtil(u);
		System.out.println();
	}
	
    // Print Euler tour starting from vertex u
    private void printEulerUtil(Integer u)
    {
        // Recur for all the vertices adjacent to this
        // vertex
        for (int i = 0; i < adjacents.get(u).size(); i++) {
            Integer v = adjacents.get(u).get(i);
            // If edge u-v is a valid next edge
            if (isValidNextEdge(u, v)) {
                System.out.print(u + "-" + v + " ");
 
                // This edge is used so remove it now
                removeEdge(u, v);
                printEulerUtil(v);
            }
        }
    }
    
    // The function to check if edge u-v can be
    // considered as next edge in Euler Tout
    private boolean isValidNextEdge(Integer u, Integer v)
    {
        // The edge u-v is valid in one of the
        // following two cases:
 
        // 1) If v is the only adjacent vertex of u
        // ie size of adjacent vertex list is 1
        if (adjacents.get(u).size() == 1) {
            return true;
        }
 
        // 2) If there are multiple adjacents, then
        // u-v is not a bridge Do following steps
        // to check if u-v is a bridge
        // 2.a) count of vertices reachable from u
        boolean[] isVisited = new boolean[num_of_v];
        int count1 = dfsCount(u, isVisited);
 
        // 2.b) Remove edge (u, v) and after removing
        //  the edge, count vertices reachable from u
        removeEdge(u, v);
        isVisited = new boolean[num_of_v];
        int count2 = dfsCount(u, isVisited);
 
        // 2.c) Add the edge back to the graph
        addEdge(u, v);
        return (count1 > count2) ? false : true;
    }
    
    // A DFS based function to count reachable
    // vertices from v
    private int dfsCount(Integer v, boolean[] isVisited)
    {
        // Mark the current node as visited
        isVisited[v] = true;
        int count = 1; // (u,v), u is also reachable , is this correct ???
        // Recur for all vertices adjacent to this vertex
        for (int adj : adjacents.get(v)) {
            if (!isVisited[adj]) {
                count = count + dfsCount(adj, isVisited);
            }
        }
        return count;
    }
    
	public static void main(String[] args) {
        // Let us first create and test
        // graphs shown in above figure
        Graph g1 = new Graph(4);
        // 0----2---3
        //  \   |
        //   \  |
        //     1
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.printEulerTour();
 
        Graph g2 = new Graph(3);
        // 0----1----2
        // |         |
        // +---------+
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 0);
        g2.printEulerTour();
 
        Graph g3 = new Graph(5);
        //   +--------+
        //   |        |
        //   1---0----3----4
        //   |  /     |    |
        //   | /      |    |
        //   2--------+    |
        //   |             |
        //   +-------------+
        g3.addEdge(1, 0);
        g3.addEdge(0, 2);
        g3.addEdge(2, 1);
        g3.addEdge(0, 3);
        g3.addEdge(3, 4);
        g3.addEdge(3, 2);
        g3.addEdge(3, 1);
        g3.addEdge(2, 4);
        g3.printEulerTour();
        
        // with this graph there are problems
        // if here are more than 2 vertices with odd degree there is no Eulerian path
        Graph g4 = new Graph(6);
        //  0---1---3---4
        //   \  |   |
        //    \ |   |
        //      2   5
        g4.addEdge(0, 1);
        g4.addEdge(1, 2);
        g4.addEdge(2, 0);
        g4.addEdge(1, 3);
        g4.addEdge(3, 4);
        g4.addEdge(3, 5);
        g4.printEulerTour();
	}

}
