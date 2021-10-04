package datastructures.graph.cycle.directedgraph.bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.IntStream;

public class Graph {
	
	private int num_of_v;
	private List<List<Integer>> adjacents;
	
	
	public Graph(int num_of_v) {
		this.num_of_v = num_of_v;
		adjacents = new ArrayList<>();
		IntStream.range(0, this.num_of_v).forEach(v -> adjacents.add(new ArrayList<>()));
	}
	
	public void addEdge(Integer start_v, Integer end_v) {
		adjacents.get(start_v).add(end_v);
	}
	
	public Boolean isCycleDetected() {
		Integer vertices_hit = 0;
		
		// compute in degree = num of incoming edges
		Integer[] in_degree_a = new Integer[num_of_v];
		Arrays.fill(in_degree_a, 0);
		List<Integer> in_degree = new ArrayList<>(Arrays.asList(in_degree_a));
		adjacents.forEach(adjs -> adjs.forEach(adjacent -> in_degree.set(adjacent, in_degree.get(adjacent) + 1)));
		
		Queue<Integer> q = new LinkedList<>();
		
		Integer index = 0;
		// fill queue with only "outgoing" vertices, degree = 0
		for(Integer degree:in_degree) {
			if (degree == 0) q.add(index);
			index++;
		}
		
		// BFS traverse trough graph
		while(!q.isEmpty()) {
			
			Integer vertex = q.poll();
			for(Integer adjacent: adjacents.get(vertex)) {
				in_degree.set(adjacent, in_degree.get(adjacent) -1);
				if(in_degree.get(adjacent) == 0) {
					q.add(adjacent);
				}
			}
			
			vertices_hit++;
		}
		
		return (vertices_hit == num_of_v)? false: true;
			
	}

	public static void main(String[] args) {
		
		int num_of_v = 6;
		Graph g = new Graph(num_of_v);
		
//	    3--->4--->5--->0--->1
//	                   A    |
//	                   |    v
//	                   +----2
		
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(2, 0); //cycle
		
		g.addEdge(3, 4);
		g.addEdge(4, 5);
		g.addEdge(5, 0);

		System.out.println((g.isCycleDetected()?"Graphh has cycle":"No cycle in graph"));

	}

}
