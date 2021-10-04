// A Java Program to detect cycle in a directed graph
// using DFS
// similar solution for number of paths in graph

package datastructures.graph.cycle.directedgraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Graph {
	private int numOfVertices;
	private List<List<Integer>> adjacent;
	
	public Graph(int numOfVeritces) {
		this.numOfVertices = numOfVeritces;
		
		adjacent = new ArrayList<>();
		for(int i = 0 ; i < this.numOfVertices; i++) {
			adjacent.add(new LinkedList<Integer>());
		}
	}
	
	public void addEdge(int startVertex, int endVertex) {
		adjacent.get(startVertex).add(endVertex);
	}
	
	protected boolean isCyclicUtil(int v, boolean[] visited, boolean[] reached) {
		
		if(reached[v]) {
			System.out.println(Arrays.toString(reached));
			return true;
		}
		
		// vertex can be visited but not in reached stack if traverse continues from disconnected vertex
		if(visited[v])
			return false;
		
		reached[v] = true;
		visited[v] = true;
		
		boolean cycleDetected = false;
		for(int adjacentv:adjacent.get(v)) {
			if(isCyclicUtil(adjacentv, visited, reached)) {
				cycleDetected = true;
				break;
			}
		}
		
		reached[v] = false;
		return cycleDetected;
	}
		 
		
		
	public boolean isCyclic() {
		boolean[] visited = new boolean[this.numOfVertices];
		boolean[] reached = new boolean[this.numOfVertices];
		
		for(int v = 0; v < this.numOfVertices; v++) {
			if(isCyclicUtil(v,visited, reached))
				return true;
			}
		return false;
	}

	public static void main(String[] args) {
		
//	    +->0--->1
//	    |  |   /
//	    |  |  /
//	    |  | /
//	    +--2-----3<-+
//	             |  |
//	             +--+
	    
		Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
         
        if(graph.isCyclic())
            System.out.println("Graph contains cycle");
        else
            System.out.println("Graph doesn't "
                                    + "contain cycle");

	}

}
