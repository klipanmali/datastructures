// Java program to find the transpose of a graph
package datastructures.graph.transpose;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {
	
	private Map<Integer,List<Integer>> graph= new HashMap<>();
	public Graph() {
		
	}
	
	private void addEdge(int vertex, int otherVertex) {
		if(!graph.containsKey(vertex)) {
			graph.put(vertex,new ArrayList<Integer>());
		}
		graph.get(vertex).add(otherVertex);
	}
	
	public Graph transpose() {
		Graph transposeGraph = new Graph();
		
		for (int vertex:graph.keySet())
			for(int otherVertex:graph.get(vertex))
				transposeGraph.addEdge(otherVertex, vertex);
		return transposeGraph;
	}

	@Override
	public String toString() {
		return graph.toString();
	}

	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.addEdge(0, 1);
		graph.addEdge(0, 4);
		graph.addEdge(0, 3);
		graph.addEdge(2, 0);
		graph.addEdge(3, 2);
		graph.addEdge(4, 1);
		graph.addEdge(4, 3);
		
		System.out.println("Original Graph:");
		System.out.println(graph.toString());
		System.out.println("Transpose Graph:");
		System.out.println(graph.transpose().toString());

	}

}
