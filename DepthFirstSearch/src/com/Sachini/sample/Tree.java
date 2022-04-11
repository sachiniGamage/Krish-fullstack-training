package com.Sachini.sample;

public class Tree {
	public static void main(String[] args) {
		Node node0 = new Node("0");
		Node node1 = new Node("1");
		Node node2 = new Node("2");
		Node node3 = new Node("3");
		Node node4 = new Node("4");
		Node node5 = new Node("5");
		Node node6 = new Node("6");
		Node node7 = new Node("7");
		Node node8 = new Node("8");
		
		node0.addNeighbour(node3);
		
		node3.addNeighbour(node5);
		node3.addNeighbour(node7);
		
		node5.addNeighbour(node6);
		
		node6.addNeighbour(node2);
		
		node2.addNeighbour(node1);
		
		node7.addNeighbour(node8);
		
		DFS.dfs(node0);
	}
}
