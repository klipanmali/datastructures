// A Java program to print topological
// sorting of a DAG
package datastructures.graph.topological;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class Graph {
	
	private Integer num_of_v;
	private List<List<Integer>> adjacents
;	
	public Graph(Integer num_of_v) {
		this.num_of_v = num_of_v;
		adjacents = new ArrayList<>();
		IntStream.range(0, this.num_of_v).forEach(a -> adjacents.add(new ArrayList<Integer>()));
	}
	
	public void addEdge(Integer start_v, Integer end_v) {
		adjacents.get(start_v).add(end_v);
	}
	
	private void topoSortUtil(Integer vertex, Boolean[] visited, Stack<Integer> stack) {
		visited[vertex] = true;
		
		for(Integer adjacent: adjacents.get(vertex)) {
			if(!visited[adjacent]) topoSortUtil(adjacent,visited,stack);
		}
		
		stack.add(vertex);
	}
	
	public void topologicalSort() {
		 
		Stack<Integer> stack = new Stack<>();
		Boolean[] visited = new Boolean[this.num_of_v];
		Arrays.fill(visited, false);
		
		IntStream.range(0, this.num_of_v).forEach(v -> {if (visited[v] == false) this.topoSortUtil(v,visited,stack);});
		while(!stack.isEmpty()) {
			System.out.print(stack.pop() + ",");
		}
		System.out.println();
	}

	public static void main(String[] args) {

        Graph g = new Graph(6);
        
//           5             4
//           | \         / |
//           |   \     /   |
//           v    > 0 <    |
//           2             |
//           |             v
//           +--->3------->1
           
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        
        System.out.println("Topological sorting of the graph");
        g.topologicalSort();
	}

}
