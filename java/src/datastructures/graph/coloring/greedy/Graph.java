// A Java program to implement greedy algorithm for graph coloring
// in undirected graph
package datastructures.graph.coloring.greedy;

import java.util.ArrayList;
import java.util.Arrays;
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
	
    // Assigns colors (starting from 0) to all vertices and
    // prints the assignment of colors
    void greedyColoring()
    {
        int result[] = new int[num_of_v];
 
        // Initialize all vertices as unassigned
        Arrays.fill(result, -1);
 
        // Assign the first color to first vertex
        result[0]  = 0;
 
        // A temporary array to store the available colors. False
        // value of available[cr] would mean that the color cr is
        // assigned to one of its adjacent vertices
        boolean available[] = new boolean[num_of_v];
         
        // Initially, all colors are available
        Arrays.fill(available, true);
 
        // Assign colors to remaining num_of_v-1 vertices
        for (int u = 1; u < num_of_v; u++)
        {
            // Process all adjacent vertices and flag their colors
            // as unavailable
        	for (Integer adj: adjacents.get(u)) {
                if (result[adj] != -1)
                    available[result[adj]] = false;
            }
 
            // Find the first available color
            int cr;
            for (cr = 0; cr < num_of_v; cr++){
                if (available[cr])
                    break;
            }
 
            result[u] = cr; // Assign the found color
 
            // Reset the values back to true for the next iteration
            Arrays.fill(available, true);
        }
 
        // print the result
        for (int u = 0; u < num_of_v; u++)
            System.out.println("Vertex " + u + " --->  Color "
                                + result[u]);
    }
    
	public static void main(String[] args) {
		
		Graph g1 = new Graph(5);
		//(assigned color)
		//        1(1)
		//      / | \
		// (0)0   |(0)3---4(1)
		//      \ | /
		//        2(2)
        g1.addEdge(0, 1);
        g1.addEdge(0, 2);
        g1.addEdge(1, 2);
        g1.addEdge(1, 3);
        g1.addEdge(2, 3);
        g1.addEdge(3, 4);
        System.out.println("Coloring of graph 1");
        g1.greedyColoring();
 
        System.out.println();
        //(assigned color)
		//     (1)1
		//      / | \
		// (0)0   |(3)4---3(0)
		//      \ | /
		//     (2)2
        Graph g2 = new Graph(5);
        g2.addEdge(0, 1);
        g2.addEdge(0, 2);
        g2.addEdge(1, 2);
        g2.addEdge(1, 4);
        g2.addEdge(2, 4);
        g2.addEdge(4, 3);
        System.out.println("Coloring of graph 2 ");
        g2.greedyColoring();
	}

}
