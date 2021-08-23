// path number between two vertices, directed graph
package datastructures.graph.pathnum;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph {

	private Integer numOfVertices;
	private List<List<Integer>> graph = new ArrayList<>();
	
	public Graph(int numOfVertices) {
		this.numOfVertices = numOfVertices;
		for(int index=0; index < this.numOfVertices; index++) {
			graph.add(index, new LinkedList<Integer>());
		}
	}
	
	public void addEdge(Integer vertex, Integer adjacentVertex) {
		graph.get(vertex).add(adjacentVertex);
	}
	
	private Integer pathUtil(Integer numOfPaths, Integer vertex, Integer endVertex, List<Integer>pathVertices) {
		if(!pathVertices.contains(vertex)) {
			pathVertices.add(vertex);
			if(!vertex.equals(endVertex)) {
				// Recur for all the vertices
		        // adjacent to this vertex
				for(Integer nextVertex: graph.get(vertex)) {
					if(!pathVertices.contains(nextVertex)) {
						List<Integer> pathVerticesClone = new ArrayList<>(pathVertices);
						numOfPaths = pathUtil(numOfPaths, nextVertex, endVertex, pathVerticesClone);
					}
				}
			}else {
				// If current vertex is same as
		        // destination, then increment count
				System.out.println("hit");
				System.out.println(pathVertices);
				numOfPaths++;
			}
		}
		return numOfPaths;

	}
	
	public Integer getPathNumber(Integer startVertex, Integer endVertex){
		Integer numOfPaths = 0;
		List<Integer> pathVertices = new ArrayList<Integer>();
		return pathUtil(numOfPaths, startVertex, endVertex, pathVertices);
//		return numOfPaths;
	}
	
	public static void main(String[] args) {
		
//	--->0------>1
//	|   |  \ /> |
//	|   |   X   |
//	|  \|  / \  |/
//	----2      >3<--
//		        |  |
//		        ----
		
		Graph g = new Graph(4);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(2, 0);
		g.addEdge(2, 1);
		g.addEdge(1, 3);
		g.addEdge(3, 3);
		
		Integer start = 0;
		Integer end = 3;
		Integer num  = g.getPathNumber(start, end);
		System.out.println(num);
		System.out.println(String.format("From %d to %d there are %d paths", start,end,num));

	}

}
