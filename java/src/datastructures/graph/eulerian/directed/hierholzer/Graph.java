// Java program to print Eulerian circuit in given
// directed graph using Hierholzer algorithm
// The algorithm assumes that the given graph has a Eulerian cycle.
package datastructures.graph.eulerian.directed.hierholzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
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
	}
	
	public void printCircuit() {
		
		Stack<Integer> currentPath = new Stack<>();
		Stack<Integer> finalPath = new Stack<>();
		
		currentPath.add(0);
		
		while(!currentPath.isEmpty()) {
			
			Integer currentVertex = currentPath.peek();
			
			// if there's remaining edge in adjacency list of the current vertex  
			if(!adjacents.get(currentVertex).isEmpty()) {
				
				// find and remove the next vertex that is adjacent to the current vertex
				List<Integer> currentAdjacents = adjacents.get(currentVertex);
				Integer nextV = currentAdjacents.remove(currentAdjacents.size()-1);
				currentPath.add(nextV);
				
			// back-track to find remaining circuit
			} else {
				// Remove the current vertex from the current oath and put it in the finalPath
				finalPath.add(currentPath.pop());
			}
		}
		
		// we have the finalpath, print it in reverse
		while(!finalPath.isEmpty()) {
			System.out.print(finalPath.pop() + " -> ");
		}
		System.out.println();
		
	}
	
	public static void main(String[] args) {
	    Graph g1 = new Graph(3);
	    // 0-->1
	    // A   |
	    // |   |
	    // 2<--+
		g1.addEdge(0, 1);
		g1.addEdge(1, 2);
		g1.addEdge(2, 0);
		System.out.println("graph1");
		g1.printCircuit();

		Graph g2 = new Graph(7);
		// +-------------------+
		// | +-->6--------+    |
		// v/             v    |
		// 0<---2--->3--->4--->5
		// |   > <        |
		// |  /   \       |
		// v /     +------+
		// 1
		g2.addEdge(0, 1);
		g2.addEdge(0, 6);
		g2.addEdge(1, 2);
		g2.addEdge(2, 0);
		g2.addEdge(2, 3);
		g2.addEdge(3, 4);
		g2.addEdge(4, 2);
		g2.addEdge(4, 5);
		g2.addEdge(5, 0);
		g2.addEdge(6, 4);
		System.out.println("graph2");
		g2.printCircuit();

		// uuupppssss,
		// first you need to check if there is Eulerian path off eulerian cycle
		Graph g3 = new Graph(4);
		// 0--->1--->3
		// A   /
		// |  /
		// 2<+
		g3.addEdge(0, 1);
		g3.addEdge(1, 2);
		g3.addEdge(2, 0);
		g3.addEdge(1, 3);
		System.out.println("graph3");
		g3.printCircuit();
	}

}
