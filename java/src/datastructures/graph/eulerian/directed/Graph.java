package datastructures.graph.eulerian.directed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Graph {

	public Integer num_of_v;
	private List<List<Integer>> adjacents = new ArrayList<>();
	private int time = 0;
	private int in[];

	public Graph(Integer num_of_v) {
		this.num_of_v = num_of_v;
		in = new int[this.num_of_v];
		IntStream.range(0, this.num_of_v).forEach(i -> adjacents.add(new ArrayList<>()));
		Arrays.fill(in, 0);
	}

	public void addEdge(Integer source_v, Integer dest_v) {
		adjacents.get(source_v).add(dest_v);
        in[dest_v]++;
	}

    // A recursive function to print DFS starting from v
    void DFSUtil(int vertex,Boolean visited[])
    {
        // Mark the current node as visited
        visited[vertex] = true;
  
        // Recur for all the vertices adjacent to this vertex
        for(Integer u:adjacents.get(vertex)) {
            if (!visited[u])
                DFSUtil(u,visited);
        }
    }
    // Function that returns reverse (or transpose) of this graph
    Graph getTranspose()
    {
        Graph g = new Graph(num_of_v);
        for (int v = 0; v < num_of_v; v++)
        {
            // Recur for all the vertices adjacent to this vertex
        	for(Integer u:adjacents.get(v)) {
        		g.addEdge(u, v);
            }
        }
        return g;
    }
    
    // The main function that returns true if graph is strongly
    // connected
    Boolean isStronglyCon()
    {
        // Step 1: Mark all the vertices as not visited (For
        // first DFS)
        Boolean visited[] = new Boolean[num_of_v];
        for (int i = 0; i < num_of_v; i++)
            visited[i] = false;
 
        // Step 2: Do DFS traversal starting from the first vertex.
        DFSUtil(0, visited);
 
        // If DFS traversal doesn't visit all vertices, then return false.
        for (int i = 0; i < num_of_v; i++)
            if (visited[i] == false)
                return false;
 
        // Step 3: Create a reversed graph
        Graph gr = getTranspose();
 
        // Step 4: Mark all the vertices as not visited (For second DFS)
        for (int i = 0; i < num_of_v; i++)
            visited[i] = false;
 
        // Step 5: Do DFS for reversed graph starting from first vertex.
        // Starting Vertex must be same starting point of first DFS
        gr.DFSUtil(0, visited);
 
        // If all vertices are not visited in second DFS, then
        // return false
        for (int i = 0; i < num_of_v; i++)
            if (visited[i] == false)
                return false;
 
        return true;
    }
    Boolean isEulerianCycle()
    {
        // Check if all non-zero degree vertices are connected
        if (isStronglyCon() == false)
            return false;
 
        // Check if in degree and out degree of every vertex is same
        for (int i = 0; i < num_of_v; i++)
            if (adjacents.get(i).size() != in[i])
                return false;
 
        return true;
    }
	public static void main(String[] args) {
		
        Graph g = new Graph(5);
        // 1--->0--->3
        // A  /  <   |
        // | /     \ v
        // 2<        4
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);
        g.addEdge(4, 0); // comment out to change to NOT eulerian
 
        if (g.isEulerianCycle())
            System.out.println("Given directed graph is eulerian ");
        else
            System.out.println("Given directed graph is NOT eulerian ");
	}

}
