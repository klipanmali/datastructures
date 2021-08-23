package datastructures.graph.bidirectionalsearch;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {
	
	private int numOfVertices;
	private List<LinkedList<Integer>> adjacentVertices;
	
	public Graph(int numOfVertices) {
		this.numOfVertices = numOfVertices;
		adjacentVertices = new ArrayList<>();
		for (int i=0; i<this.numOfVertices; ++i)
			adjacentVertices.add(i,new LinkedList<Integer>());
	}
	
	public void addEdge(int u,int v)
	{
		this.adjacentVertices.get(u).add(v);
		this.adjacentVertices.get(v).add(u);
	}

	private void BFS(Queue<Integer> queue, List<Boolean> visited, List<Integer> parent) {
		
		Integer vertex = queue.poll();
		for (Integer adjacentVertex : adjacentVertices.get(vertex)) {
			if(!visited.get(adjacentVertex)) {
				parent.set(adjacentVertex,vertex);
				visited.set(adjacentVertex, true);
				queue.add(adjacentVertex);
			}
		}
	}
	
	private Integer isIntersected(List<Boolean> startVisited, List<Boolean> targetVisited) {
		for(int i = 0; i < numOfVertices; i++)
			if(startVisited.get(i) && targetVisited.get(i)) {
				return i;
			}
		
		return -1;
	}
	
	private void printPath(List<Integer> startParent, List<Integer> targetParent, Integer startVertex, Integer targetVertex, Integer intersectionVertex) {
		
		Deque<Integer> path = new LinkedList<>();
		path.add(intersectionVertex);
		Integer vertex = intersectionVertex;
		
		while(!vertex.equals(startVertex)) {
			path.addFirst(startParent.get(vertex));
			vertex = startParent.get(vertex);
		}
		
	
		vertex = intersectionVertex;
		while(!vertex.equals(targetVertex)) {
			path.addLast(targetParent.get(vertex));
			vertex = targetParent.get(vertex);
		}
		
		System.out.println("++++Path++++");
		System.out.println(path);
		
	}
	
	public int biDirectionSearch(int startVertex, int targetVertex) {
		List<Boolean> startVisited = new ArrayList<>(numOfVertices);
		List<Boolean> targetVisited = new ArrayList<>(numOfVertices);
		
		List<Integer> startParent = new ArrayList<>(numOfVertices);
		List<Integer> targetParent  = new ArrayList<>(numOfVertices);
		
		Queue<Integer> startQueue = new LinkedList<>();
		Queue<Integer> targetQueue = new LinkedList<>();
		
		int intersection = -1;
		
		for(int i = 0; i < this.numOfVertices; i++) {
			startVisited.add(i, false);
			startParent.add(i, -1);
			targetVisited.add(i, false);
			targetParent.add(i, -1);
			
		}
		
		startQueue.add(startVertex);
		startVisited.set(startVertex, true);
		startParent.set(startVertex, -1);
		
		targetQueue.add(targetVertex);
		targetVisited.set(targetVertex, true);
		targetParent.set(targetVertex, -1);
		
		while(!startQueue.isEmpty() && !targetQueue.isEmpty()) {
			
			BFS(startQueue, startVisited, startParent);
			BFS(targetQueue, targetVisited, targetParent);
			
			intersection = isIntersected(startVisited, targetVisited);
			
			if(intersection != -1) {
				System.out.println("Path exists between "+startVertex+" and "+targetVertex);
				System.out.println("Intersection at "+intersection);
				
				printPath(startParent, targetParent, startVertex, targetVertex, intersection);
				
				return 1;
			}
		}
		
		return -1;

	}
	

	public static void main(String[] args) {
		
		int numOfVertices = 15;
		int startVertex = 11;
		int targetVertex = 5;
		
//		# 0             11
//	    #  \           /
//	    #   4         9
//	    #  / \       / \
//	    # 1   \     /   12
//	    #      6-7-8
//	    # 2   /     \   13
//	    #  \ /       \ /
//	    #   5        10
//	    #  /           \
//	    # 3             14
//	    # Create a graph
		Graph graph = new Graph(numOfVertices);
		graph.addEdge(0, 4);
		graph.addEdge(1, 4);
		graph.addEdge(2, 5);
		graph.addEdge(3, 5);
		graph.addEdge(4, 6);
		graph.addEdge(5, 6);
		graph.addEdge(6, 7);
		graph.addEdge(7, 8);
		graph.addEdge(8, 9);
	    graph.addEdge(8, 10);
	    graph.addEdge(9, 11);
	    graph.addEdge(9, 12);
	    graph.addEdge(10, 13);
	    graph.addEdge(10, 14);
	    
	    if(graph.biDirectionSearch(startVertex,targetVertex) == -1) {
	    	System.out.println("Path dont exist between "+startVertex+" and "+targetVertex);
	    }
	}

}
