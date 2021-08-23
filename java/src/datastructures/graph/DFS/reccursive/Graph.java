//DFS, recursive solution
package datastructures.graph.DFS.reccursive;

import java.util.LinkedList;

public class Graph {

	private int numOfVertices;
	private LinkedList<Integer> edgesPerVertex[];
	
	public Graph(int numOfVeritces) {
		this.numOfVertices = numOfVeritces;
		edgesPerVertex = new LinkedList[this.numOfVertices];
		for(int i=0 ; i < this.numOfVertices; i++) {
			edgesPerVertex[i] = new LinkedList<>();
		}
	
	}
	
	public void addEdge(int v, int w) {
		edgesPerVertex[v].add(w);
	}
	
	private void DFS(int startVertex, boolean visitedVertex[]) {
		 LinkedList<Integer> edgesToCheck = edgesPerVertex[startVertex];

		 visitedVertex[startVertex] = true;
		 System.out.print(startVertex + " ");
		 for(int edge: edgesToCheck) {
			 if(!visitedVertex[edge]) {
				 DFS(edge,visitedVertex);
			 }
		 }

	}
	
	public void DFS(int startVertex) {
		 boolean visitedVertex[] = new boolean[numOfVertices];
		 DFS(startVertex,visitedVertex);
		
	}
	
	public static void main(String[] args) {
		
		//recursive solution
		
//		---0------>1
//		|  |\     /
//		|  |    /
//		|  |  /
//		-->2<-       3<---
//		    --------> |  |
//		              ----
		 Graph g = new Graph(4);
		 
	        g.addEdge(0, 1);
	        g.addEdge(0, 2);
	        g.addEdge(1, 2);
	        g.addEdge(2, 0);
	        g.addEdge(2, 3);
	        g.addEdge(3, 3);
	        
	        int startVertex = 1;
	 
	        System.out.println(
	            "Following is Depth First Traversal "
	            + "(starting from vertex " + startVertex + ")");
	 
	        g.DFS(startVertex);

	}

}
