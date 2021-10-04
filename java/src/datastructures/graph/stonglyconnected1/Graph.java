// Java program to check if a given directed graph is strongly
// connected or not
// Following is Kosaraju’s DFS based simple algorithm that does two DFS traversals of graph:
// if every node can be reached from a vertex v, and every node can reach v, then the graph is strongly connected
// BFS can be used as well

package datastructures.graph.stonglyconnected1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	
	public void DFSUtil(Integer v, Boolean visited[]) {
		
		visited[v] = true;
		
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
	
	
	// is graph strongly connected
	public Boolean isSC() {
		Boolean visited[] = new Boolean[num_of_v];
		Arrays.fill(visited, false);
		
		DFSUtil(0,visited);
		
		for (int i = 0; i < num_of_v; i++)
            if (visited[i] == false)
                return false;
		
		Graph gTranspose = transpose();
		
		Arrays.fill(visited, false);
		gTranspose.DFSUtil(0, visited);
		
		for (int i = 0; i < num_of_v; i++)
            if (visited[i] == false)
                return false;
		
		return true;
		
	}
		
		
	public static void main(String[] args) {
		// Create graphs given in the above diagrams
        Graph g1 = new Graph(5);
        
//                    +-----+
//                    v     |       
//        0---->1---->2---->4
//        A           |
//        |           v
//        +-----------3
        
        g1.addEdge(0, 1);
        g1.addEdge(1, 2);
        g1.addEdge(2, 3);
        g1.addEdge(3, 0);
        g1.addEdge(2, 4);
        g1.addEdge(4, 2);
        if (g1.isSC())
            System.out.println("Yes");
        else
            System.out.println("No");
 
        Graph g2 = new Graph(4);
        
//        0---->1---->2---->3
        
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);
        if (g2.isSC())
            System.out.println("Yes");
        else
            System.out.println("No");
	}

}
