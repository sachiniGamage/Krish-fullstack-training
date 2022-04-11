package com.Sachini.sample;
import java.util.*;

public class BFS {
	static Queue<Node> queue = new LinkedList<Node>();
	
	public static void bfs(Node node) {
		queue.add(node);
		node.setVisited(false);
		
		while(!queue.isEmpty()) {
			Node remNode  = queue.remove();
			System.out.println(remNode);
			
			for(Node n: remNode.adjNeighbourList) {
				if(!n.isVisited()) {
					n.setVisited(true);
					queue.add(n);
				}
			}
		}
	}
}
