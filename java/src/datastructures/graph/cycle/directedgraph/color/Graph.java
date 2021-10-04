package datastructures.graph.cycle.directedgraph.color;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Graph {

	private Integer num_of_v = 0;
	private List<Node> nodes;
	
	public Graph(Integer num_of_v) {
		this.num_of_v = num_of_v;
		nodes = new ArrayList<>();
		IntStream.range(0, this.num_of_v).forEach(a -> nodes.add(a, new Node(Color.WHITE, new ArrayList<>())));
		
	}
	
	public void addEdge(Integer start_v, Integer end_v) {
		nodes.get(start_v).getAdjacents().add(end_v);
	}
	
	private Boolean cycleDetectedOn(Node node) {
		
		node.setColor(Color.GRAY);
		for (Integer adjacent: node.getAdjacents()) {
			Node adjacentNode = nodes.get(adjacent);
			if(adjacentNode.getColor().equals(Color.GRAY)) {
				return true;
			}else if(adjacentNode.getColor().equals(Color.WHITE)) {
				if(cycleDetectedOn(adjacentNode)) {
					return true;
				}
			}
			
		}
		
		node.setColor(Color.BLACK);
		return false;
		
	}
	public Boolean is_cycle_detected() {
		
		Boolean result = false;
		
		for (Node node:nodes) {
			if(node.getColor().equals(Color.WHITE)) {
				if(cycleDetectedOn(node)) {
					result = true;
					break;
				}
			}
		}
		return result;
				
	}
	
	class Node{
		
		private Color color;
		private List<Integer> adjacents;
		
		
		public Node(Color color, List<Integer> adjacents) {
			super();
			this.color = color;
			this.adjacents = adjacents;
		}
		public Color getColor() {
			return color;
		}
		public void setColor(Color color) {
			this.color = color;
		}
		public List<Integer> getAdjacents() {
			return adjacents;
		}
		public void setAdjacents(List<Integer> adjacents) {
			this.adjacents = adjacents;
		}
		
		
		
	}
	
	enum Color{
		WHITE,GRAY,BLACK
	}
	
	
	public static void main(String[] args) {
		
		Integer num_of_v = 4;
		
		Graph g = new Graph(num_of_v);
		
//		0---->1
//		A \   |
//		|  \  |     +-+
//		|   v v     v |
//		+---- 2---->3-+
		
		g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
//        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);
        
        
        System.out.println(g.is_cycle_detected()? "Cycle detected" : "No cycle");
	}
	
}
