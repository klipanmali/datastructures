package datastructures.graph.eulerian.undirected;

import java.util.ArrayList;
import java.util.List;
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
		adjacents.get(dest_v).add(source_v);
	}
	
    // A function used by DFS
    void DFSUtil(int v,boolean visited[])
    {
        // Mark the current node as visited
        visited[v] = true;
 
        // Recur for all the vertices adjacent to this vertex
        for (Integer n : adjacents.get(v)) {
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }
    
    // Method to check if all non-zero degree vertices are
    // connected. It mainly does DFS traversal starting from
    boolean isConnected()
    {
        // Mark all the vertices as not visited
        boolean visited[] = new boolean[num_of_v];
        int i;
        for (i = 0; i < num_of_v; i++)
            visited[i] = false;
 
        // Find a vertex with non-zero degree
        for (i = 0; i < num_of_v; i++)
            if (adjacents.get(i).size() != 0)
                break;
 
        // If there are no edges in the graph, return true
        if (i == num_of_v)
            return true;
 
        // Start DFS traversal from a vertex with non-zero degree
        DFSUtil(i, visited);
 
        // Check if all non-zero degree vertices are visited
        for (i = 0; i < num_of_v; i++)
           if (visited[i] == false && adjacents.get(i).size() > 0)
                return false;
 
        return true;
    }

    /* The function returns one of the following values
    0 --> If graph is not Eulerian
    1 --> If graph has an Euler path (Semi-Eulerian)
    2 --> If graph has an Euler Circuit (Eulerian)  */
 int isEulerian()
 {
     // Check if all non-zero degree vertices are connected
     if (isConnected() == false)
         return 0;

     // Count vertices with odd degree
     int odd = 0;
     for (int i = 0; i < num_of_v; i++)
         if (adjacents.get(i).size()%2 != 0)
             odd++;

     // If count is more than 2, then graph is not Eulerian
     if (odd > 2)
         return 0;

     // If odd count is 2, then semi-eulerian.
     // If odd count is 0, then eulerian
     // Note that odd count can never be 1 for undirected graph
     return (odd==2)? 1 : 2;
 }
    void test()
    {
        int res = isEulerian();
        if (res == 0)
            System.out.println("graph is not Eulerian");
        else if (res == 1)
            System.out.println("graph has a Euler path");
        else
           System.out.println("graph has a Euler cycle");
    }
	public static void main(String[] args) {
        // Let us create and test graphs shown in above figures
        Graph g1 = new Graph(5);
        
        // 1---0---3
        // |  /    |
        // | /     |
        // 2       4
        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);
        g1.test();
 
        Graph g2 = new Graph(5);
        
        // 1---0---3
        // |  / \  |
        // | /   \ |
        // 2       4
        g2.addEdge(1, 0);
        g2.addEdge(0, 2);
        g2.addEdge(2, 1);
        g2.addEdge(0, 3);
        g2.addEdge(3, 4);
        g2.addEdge(4, 0);
        g2.test();
 
        Graph g3 = new Graph(5);
        
        // +-------+
        // |       |
        // 1---0---3
        // |  /    |
        // | /     |
        // 2       4
        g3.addEdge(1, 0);
        g3.addEdge(0, 2);
        g3.addEdge(2, 1);
        g3.addEdge(0, 3);
        g3.addEdge(3, 4);
        g3.addEdge(1, 3);
        g3.test();
 
        // Let us create a graph with 3 vertices
        // connected in the form of cycle
        Graph g4 = new Graph(3);
        
        // 0----1----2
        // |         |
        // +---------+
        g4.addEdge(0, 1);
        g4.addEdge(1, 2);
        g4.addEdge(2, 0);
        g4.test();
 
        // Let us create a graph with all vertices
        // with zero degree
        Graph g5 = new Graph(3);
        g5.test();
	}

}
