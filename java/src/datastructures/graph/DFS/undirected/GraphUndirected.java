//DFS of undirected graph
package datastructures.graph.DFS.undirected;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class GraphUndirected {
	
	private List<List<Integer>> graph = new ArrayList<>();
	private int numOfVerticies;
	

	public GraphUndirected(int numOfVerticies) {
		this.numOfVerticies = numOfVerticies;
		for(int i=0; i<this.numOfVerticies; i++ ) {
			graph.add(i, new LinkedList<Integer>());
		}
	}
	
	public void addEdge(int vertex,int adjacenVertex){
		graph.get(vertex).add(adjacenVertex);
		graph.get(adjacenVertex).add(vertex);
	}
	
	private void dfsUtil(Integer rootVertex, Set<Integer> visited) {
		if(!visited.contains(rootVertex)) {
			visited.add(rootVertex);
			System.out.print(rootVertex+" ");
			for(Integer vertex: graph.get(rootVertex)) {
				if(!visited.contains(vertex)) {
					dfsUtil(vertex,visited);
				}
			}
		}
	}

	public void dfs(int rootVertex) {
		Set<Integer> visited = new HashSet<>();
		dfsUtil(rootVertex,visited);
		
	}
	
	

	public static void main(String[] args) {
		
//	    Vertex numbers should be from 0 to 4.
//
//	    0-------1-------2
//	    |     / |     /
//	    |   /   |   /
//	    | /     | /
//	    4-------3
		
		int numOfVertices = 5;
		GraphUndirected uGraph = new GraphUndirected(numOfVertices);
		
		uGraph.addEdge(0, 1);
		uGraph.addEdge(0, 4);
		uGraph.addEdge(1, 2);
		uGraph.addEdge(1, 3);
		uGraph.addEdge(1, 4);
		uGraph.addEdge(2, 3);
		uGraph.addEdge(3, 4);

		uGraph.dfs(4);

	}

}
