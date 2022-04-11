package com.Sachini.sample;
import java.util.*;

public class DFS {
	static Stack<Node> stack = new Stack<Node>();
	
	public static void dfs(Node node) { // specify the starting node
		stack.push(node);
		node.setVisited(true);
		
		while(!stack.isEmpty()) {
			Node popNode = stack.pop();
			System.out.println(popNode);
			
			for(Node n: popNode.adjList) { 
				if(!n.isVisited()) { //if there is a circle, it will loop if this not checked whether it is visited or not
					n.setVisited(true);
					stack.push(n);
				}
			}
		}
	}
}
