// All possible Topological Sorts, using in_degree
package datastructures.graph.topological.allsort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.IntStream;

public class Graph {

	private Integer num_of_v;
	private List<List<Integer>> adjacents = new ArrayList<>();

	public Graph(Integer num_of_v) {
		this.num_of_v = num_of_v;
		IntStream.range(0, this.num_of_v).forEach(i -> adjacents.add(new ArrayList<>()));
	}

	public void addEdge(Integer source_v, Integer dest_v) {
		adjacents.get(source_v).add(dest_v);
	}

	private void allTopoUtil(Boolean[] visited, List<Integer> in_degree, Stack<Integer> stack) {

		boolean hasmoretoprocess = false;
		for (Integer i = 0; i < this.num_of_v; i++) {

			if (!visited[i] && in_degree.get(i) == 0) {
				visited[i] = true;
				stack.add(i);
				adjacents.get(i).forEach(adj -> in_degree.set(adj, in_degree.get(adj) - 1));
				allTopoUtil(visited, in_degree, stack);

				// resetting visited, res and indegree for
				// backtracking
				visited[i] = false;
				stack.remove(stack.size() - 1);
				adjacents.get(i).forEach(adj -> in_degree.set(adj, in_degree.get(adj) + 1));

				hasmoretoprocess = true;
			}
		}

		if (!hasmoretoprocess) {
			stack.forEach(i -> System.out.print(i + " "));
			System.out.println();
		}

	}

	public void allTopologicalSorts() {

		List<Integer> in_degree = new ArrayList<>();
		IntStream.range(0, this.num_of_v).forEach(i -> in_degree.add(0));
		adjacents.forEach(adjs -> adjs.forEach(a -> in_degree.set(a, in_degree.get(a) + 1)));
		Boolean[] visited = new Boolean[num_of_v];
		Arrays.fill(visited, false);
		Stack<Integer> stack = new Stack<>();
		allTopoUtil(visited, in_degree, stack);
	}

	public static void main(String[] args) {
		// Create a graph given in the above diagram
		Graph graph = new Graph(6);

//		5             4
//		|\           /|
//		|  \       /  |
//		|    > 0 <    |
//		v             v
//		2--->3------->1

		graph.addEdge(5, 2);
		graph.addEdge(5, 0);
		graph.addEdge(4, 0);
		graph.addEdge(4, 1);
		graph.addEdge(2, 3);
		graph.addEdge(3, 1);

		System.out.println("All Topological sorts");
		graph.allTopologicalSorts();
	}

}
