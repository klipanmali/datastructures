//DFS, stack solution
package datastructures.graph.DFS.stack;

import java.util.LinkedList;
import java.util.Stack;

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
	
	private void DFS(int startVertex) {
		boolean[] visited = new boolean[numOfVertices];

		 Stack<Integer> vertices = new Stack<>();
		 vertices.push(startVertex);
		 while(!vertices.isEmpty()) {
			 int vertexToCheck = vertices.pop();
			 if(!visited[vertexToCheck]) {
				 visited[vertexToCheck] = true;
				 System.out.print(vertexToCheck + " ");
				 for(int edge:edgesPerVertex[vertexToCheck]) {
					 if(!visited[edge]) {
						 vertices.push(edge);
					 }
				 }

			 }
			 
			 
		 }

	}
	

	public static void main(String[] args) {
		
//		stack solution
		
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
	        
	        int startVertex = 3;
	 
	        System.out.println(
	            "Following is Depth First Traversal "
	            + "(starting from vertex " + startVertex + ")");
	 
	        g.DFS(startVertex);

	}

}
