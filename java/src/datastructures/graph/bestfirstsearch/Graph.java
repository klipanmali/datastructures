package datastructures.graph.bestfirstsearch;

import java.nio.channels.Pipe.SourceChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import javafx.util.Pair;

public class Graph {

	private Integer numOfVertices;
	private List<List<Pair<Integer, Integer>>> adjacent;

	public Graph(int numOfVertices) {
		this.numOfVertices = numOfVertices;
		adjacent = new ArrayList<>();
		for (int i = 0; i < this.numOfVertices; i++) {
			adjacent.add(i, new LinkedList<>());
		}
	}

	public void addEdge(Integer startVertex, Integer targetVertex, Integer cost) {
		adjacent.get(startVertex).add(new Pair(targetVertex, cost));
	}

	public void bestFirstSearch(Integer sourceVertex, Integer targetVertex) {

		PriorityQueue<Pair<Integer, Integer>> pQueue = new PriorityQueue<>(14, new PairComparator());
		Boolean[] helparray = new Boolean[this.numOfVertices];
		Arrays.fill(helparray, false);
		List<Boolean> visited = new ArrayList<>(Arrays.asList(helparray));

		pQueue.add(new Pair<Integer, Integer>(sourceVertex, 0));
		visited.set(sourceVertex, true);

		while (!pQueue.isEmpty()) {
			Pair<Integer, Integer> pair = pQueue.poll();
			Integer vertex = pair.getKey();
			System.out.print(vertex + ", ");
			if (vertex.equals(targetVertex)) {
				System.out.println("hit");
				break;
			} else {
				for (Pair<Integer, Integer> adjacentVertex : adjacent.get(vertex)) {
					if (!visited.get(adjacentVertex.getKey())) { // vertex not visited
						visited.set(adjacentVertex.getKey(), true);
						pQueue.add(adjacentVertex);
//						System.out.println("add "+adjacentVertex);
					}					
				}
			}

		}
		System.out.println();

	}

	public static void main(String[] args) {

		Graph graph = new Graph(14);
		graph.addEdge(0, 1, 3);
		graph.addEdge(0, 2, 6);
		graph.addEdge(0, 3, 5);
		graph.addEdge(1, 4, 9);
		graph.addEdge(1, 5, 8);
		graph.addEdge(2, 6, 12);
		graph.addEdge(2, 7, 14);
		graph.addEdge(3, 8, 7);
		graph.addEdge(8, 9, 5);
		graph.addEdge(8, 10, 6);
		graph.addEdge(9, 11, 1);
		graph.addEdge(9, 12, 10);
		graph.addEdge(9, 13, 2);

		graph.bestFirstSearch(0, 10);

	}

	private class PairComparator implements Comparator<Pair<Integer, Integer>> {

		@Override
		public int compare(Pair<Integer, Integer> o1, Pair<Integer, Integer> o2) {
			Integer c1 = o1.getValue();
			Integer c2 = o2.getValue();

			if (c1 < c2) {
				return -1;
			} else if (c1 > c2) {
				return 1;
			} else {
				return 0;
			}
		}

	}

}
