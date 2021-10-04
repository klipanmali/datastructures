//Detecting cycle in directed graph using topological sorting
//This doesn't considers self loops

package datastructures.graph.cycle.directedgraph.topologic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Graph {
	
	private Integer num_of_v = 0;
	private List<List<Integer>>adjacents = new ArrayList<>();
	private Stack<Integer> verticesStack = new Stack<>();
	private Stack<Integer> topologicalStack = new Stack<>();
	
	public Graph(Integer num_of_v) {
		this.num_of_v = num_of_v;
		for(int i=0; i<this.num_of_v; i++) {
			adjacents.add(new LinkedList<>());
		}
	}
	
	public void addEdge(Integer start_v, Integer end_v) {
		adjacents.get(start_v).add(end_v);
	}
	
	public void dfs(Integer start_v, Boolean[] visited_v) {

		visited_v[start_v] = true;
		
		for (Integer adjacent : adjacents.get(start_v)) {
			if (!visited_v[adjacent])
				dfs(adjacent,visited_v);
		}
		
		verticesStack.push(start_v);

	}
	
	public Boolean detectCycle() {
		
		Boolean result = false;

		Map<Integer,Integer> verticeWweigt = new HashMap<>();
		int index = 0;
		
		// assign weight to every vertex in stack
		while(!verticesStack.isEmpty()){
			Integer vertice = verticesStack.pop();
			verticeWweigt.put(vertice,index);
			topologicalStack.push(vertice);
			index++;
		}
				
		for(int i=0; i<adjacents.size(); i++)
			for (Integer adjacent: adjacents.get(i)) {
				Integer vWeight = verticeWweigt.containsKey(i)?verticeWweigt.get(i):0;
				Integer adjacentWeight = verticeWweigt.containsKey(adjacent)?verticeWweigt.get(adjacent):0;
				
				// If parent vertex
                // does not appear first
				if (vWeight > adjacentWeight){
					result = true;
					break;
				}
			}
		
		return result;
	}

	public static void main(String[] args) {
		
		int num_of_v = 4;
		Graph g = new Graph(num_of_v);
		
//	     +->0----->1
//	     |  |      |
//	     |  |      |
//	     |  |      |
//	     |  V<-----+
//	     +--2--------->3
	    
	    g.addEdge(0, 1);
	    g.addEdge(0, 2);
	    g.addEdge(1, 2);
	    g.addEdge(2, 0);
	    g.addEdge(2, 3);
	    
//	    another graph
//	    g.addEdge(3, 2);
//	    g.addEdge(2, 1);
//	    g.addEdge(1, 0);
//	    g.addEdge(0, 3);

	    
	    Boolean[] visited = new Boolean[num_of_v];
	    Arrays.fill(visited, false);
	    
	    for (int i=0; i< num_of_v; i++) {
	    	if (!visited[i])
	    		g.dfs(i,visited);
	    }
	    
	    if (g.detectCycle()) {
	    	System.out.println("Yes");
	    }else {
	    	System.out.println("No");
	    }

	}

}
