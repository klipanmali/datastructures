// A Java Program to detect cycle in an undirected graph
// because disconnected undirected graph is actually two undirected graph
// it is enough to monitor visited vertices, and parent of each vertex
// this is not valid for disconnected directed graph
// this solution is using DFS

package datastructures.graph.cycle.undirectedgraph;

import java.io.*;
import java.util.*;

public class Graph {
	
	private int numOfVertices;
	private List<List<Integer>> adjacent;

	Graph(int numOfV)
	{
		this.numOfVertices = numOfV;
		adjacent = new ArrayList<>();
		for(int i=0; i< this.numOfVertices; ++i)
			adjacent.add(i, new LinkedList<>());
	}
	
	void addEdge(int start_v,int end_v)
	{
		adjacent.get(start_v).add(end_v);
		adjacent.get(end_v).add(start_v);
	}

	// A recursive function that
	// uses visited[] and parent to detect
	// cycle in subgraph reachable
	// from vertex v.
	Boolean isCyclicUtil(int v,
				Boolean visited[], int parent)
	{
		// Mark the current node as visited
		visited[v] = true;

		// Recur for all the vertices
		// adjacent to this vertex
		for (Integer adjacent : adjacent.get(v)) {
			// If an adjacent is not
			// visited, then recur for that
			// adjacent
			if (!visited[adjacent])
			{
				if (isCyclicUtil(adjacent, visited, v))
					return true;
			}

			// If an adjacent is visited
			// and not parent of current
			// vertex, then there is a cycle.
			else if (!adjacent.equals(parent))
				return true;
		}

		return false;
		
	}

	Boolean isCyclic()
	{
		// Mark all the vertices as
		// not visited and not part of
		// recursion stack
		Boolean visited[] = new Boolean[this.numOfVertices];
		Arrays.fill(visited, false);


		// Call the recursive helper
		// function to detect cycle in
		// different DFS trees
		for (int u = 0; u < this.numOfVertices; u++)
		{
		
			// Don't recur for u if already visited
			if (!visited[u])
				if (isCyclicUtil(u, visited, -1))
					return true;
		}

		return false;
	}

	public static void main(String[] args) {
		// Create a graph given
		// in the above diagram
		
//		1---0---3---4
//		| /
//		2
		
		Graph g1 = new Graph(5);
		g1.addEdge(1, 0);
		g1.addEdge(0, 2);
		g1.addEdge(2, 1);
		g1.addEdge(0, 3);
		g1.addEdge(3, 4);
		if (g1.isCyclic())
			System.out.println("Graph contains cycle");
		else
			System.out.println("Graph doesn't contains cycle");

		//  0---1---2
						
		Graph g2 = new Graph(3);
		g2.addEdge(0, 1);
		g2.addEdge(1, 2);
		if (g2.isCyclic())
			System.out.println("Graph contains cycle");
		else
			System.out.println("Graph doesn't contains cycle");

	}

	
}
