// Java implementation of Kosaraju's algorithm to print all Strongly connected components
package datastructures.graph.stronglyconnectedcomponents1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class Graph {

	public Integer num_of_v;
	private List<List<Integer>> adjacents = new ArrayList<>();

	public Graph(Integer num_of_v) {
		this.num_of_v = num_of_v;
		IntStream.range(0, this.num_of_v).forEach(i -> adjacents.add(new ArrayList<>()));
	}

	public void addEdge(Integer source_v, Integer dest_v) {
		adjacents.get(source_v).add(dest_v);
	}
	
	private void DFSUtil(Integer v, boolean visited[]) {
		
		visited[v] = true;
        System.out.print(v + " ");
		
		for (Integer adj: adjacents.get(v)) {
			if (!visited[adj]) {
				DFSUtil(adj,visited);
			}
		}
	}

	private Graph transpose() {
		Graph tg  = new Graph(this.num_of_v);
		
		IntStream.range(0, num_of_v).forEach(v -> adjacents.get(v).forEach(adj -> tg.addEdge(adj, v)));
		
		return tg;
	}

    private void fillOrder(Integer vertex, boolean visited[], Stack<Integer> stack)
    {
        // Mark the current node as visited and print it
        visited[vertex] = true;
  
        // Recur for all the vertices adjacent to this vertex
        for(Integer adj: adjacents.get(vertex))
        {
            if(!visited[adj])
                fillOrder(adj, visited, stack);
        }
  
        // All vertices reachable from v are processed by now,
        // push v to Stack
        stack.push(vertex);
    }
    
    public void printSCCs()
    {
        Stack<Integer> stack = new Stack<>();
  
        // Mark all the vertices as not visited (For first DFS)
        boolean visited[] = new boolean[num_of_v];
        Arrays.fill(visited, false);
  
        // Fill vertices in stack according to their finishing
        // times
        for (int i = 0; i < num_of_v; i++)
            if (visited[i] == false)
                fillOrder(i, visited, stack);
  
        // Create a reversed graph
        Graph gr = transpose();
  
        // Mark all the vertices as not visited (For second DFS)
        Arrays.fill(visited, false);
  
        // Now process all vertices in order defined by Stack
        while (stack.empty() == false)
        {
            // Pop a vertex from stack
            Integer vertex = stack.pop();
  
            // Print Strongly connected component of the popped vertex
            if (visited[vertex] == false)
            {
                gr.DFSUtil(vertex, visited);
                System.out.println();
            }
        }
    }

	public static void main(String[] args) {
	    // Create a graph given in the above diagram
        Graph g = new Graph(6);
        
//        1---->0--->3--->4--->5
//        A    /          A    |
//        |  /            +----+
//        2<-
        
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 5);
        g.addEdge(5, 4);

  
        System.out.println("Following are strongly connected components "+
                           "in given graph ");
        g.printSCCs();
	}

}
