package datastructures.graph.twnondesonsamepath;

import java.util.LinkedList;

public class Graph {

	private Integer numOfVertices;
	private LinkedList<Integer> edgesPerVertex[];
	private Integer inTime[];
	private Integer outTime[];
	private Integer timer;

	public Graph(int numOfVeritces) {
		this.numOfVertices = numOfVeritces;
		edgesPerVertex = new LinkedList[this.numOfVertices];
		for (int i = 0; i < this.numOfVertices; i++) {
			edgesPerVertex[i] = new LinkedList<>();
		}

		inTime = new Integer[this.numOfVertices];
		outTime = new Integer[this.numOfVertices];
		timer = 0;
	}

	protected void DFS_util(int startVertex, boolean visitedVertex[]) {
		LinkedList<Integer> edgesToCheck = edgesPerVertex[startVertex];

		timer++;
		visitedVertex[startVertex] = true;
		inTime[startVertex] = timer;
//		System.out.print(startVertex + " ");
		for (int edge : edgesToCheck) {
			if (!visitedVertex[edge]) {
				DFS_util(edge, visitedVertex);
			}
		}

		timer++;
		outTime[startVertex] = timer;

	}

	public void DFS(int startVertex) {
		boolean visitedVertex[] = new boolean[numOfVertices];
		inTime = new Integer[this.numOfVertices];
		outTime = new Integer[this.numOfVertices];
		timer = 0;

		DFS_util(startVertex, visitedVertex);

	}

	protected boolean isParent(Integer parenVertex, Integer otherVertex) {
		return inTime[parenVertex] < inTime[otherVertex] && outTime[parenVertex] > outTime[otherVertex];
	}

	public boolean onTheSamePath(Integer vertex, Integer otherVertex) {
		return isParent(vertex, otherVertex) || isParent(otherVertex, vertex);
	}

	public void addEdge(int v, int w) {
		edgesPerVertex[v].add(w);
	}

	public static void main(String[] args) {
		int numOfVertices = 10;
		Graph graph = new Graph(numOfVertices);
		graph.addEdge(1, 2);
		graph.addEdge(1, 3);
		graph.addEdge(3, 6);
		graph.addEdge(2, 4);
		graph.addEdge(2, 5);
		graph.addEdge(5, 7);
		graph.addEdge(5, 8);
		graph.addEdge(5, 9);
		
		graph.DFS(1);
		
		System.out.println(graph.onTheSamePath(1, 5)? "Yes":"No");
		System.out.println(graph.onTheSamePath(2, 9)? "Yes":"No");
		System.out.println(graph.onTheSamePath(2, 6)? "Yes":"No");


	}

}
