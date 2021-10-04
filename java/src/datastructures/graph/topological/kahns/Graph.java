// Kahn’s algorithm for Topological Sorting
package datastructures.graph.topological.kahns;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class Graph {

	private Integer num_of_v;
	private List<List<Integer>> adjacents;

	public Graph(Integer num_of_v) {
		this.num_of_v = num_of_v;
		adjacents = new ArrayList<>();
		IntStream.range(0, this.num_of_v).forEach(a -> adjacents.add(new ArrayList<>()));
	}

	public void addEdge(Integer source, Integer destination) {
		adjacents.get(source).add(destination);
	}

	public void topologicalSort() {

		Integer verticesHit = 0;
		ArrayList<Integer> inDegree = new ArrayList<>();
		IntStream.range(0, this.num_of_v).forEach(i -> inDegree.add(0));
		adjacents.forEach(adjs -> adjs.forEach(a -> inDegree.set(a, inDegree.get(a) + 1)));

		Queue<Integer> q = new LinkedList<>();
		Integer index = 0;
		for (Integer deg : inDegree) {
			if (deg.equals(0))
				q.add(index);
			index++;
		}

		Queue<Integer> topology = new LinkedList<>();
		// BFS traverse trough graph
		while (!q.isEmpty()) {

			Integer vertex = q.poll();
			topology.add(vertex);
			for (Integer adjacent : adjacents.get(vertex)) {
				inDegree.set(adjacent, inDegree.get(adjacent) - 1);
				if (inDegree.get(adjacent) == 0) {
					q.add(adjacent);
				}
			}

			verticesHit++;
		}

		if (verticesHit != num_of_v){
			System.out.println("Graph ccontains cycle");
		}else {
			while(!topology.isEmpty()) {
				System.out.print(topology.poll()+ ",");
			}
		}

	}

	public static void main(String[] args) {

		Graph g = new Graph(6);

//		5             4
//		|\           /|
//		|  \       /  |
//		|    > 0 <    |
//		v             v
//		2--->3------->1

		g.addEdge(5, 2);
		g.addEdge(5, 0);
		g.addEdge(4, 0);
		g.addEdge(4, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 1);
//		g.addEdge(1, 4); // cycle

		System.out.println("Following is a Topological Sort");
		g.topologicalSort();
	}

}
