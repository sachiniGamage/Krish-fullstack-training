package com.Sachini.sample;
import java.util.*;

public class Node {
	String vertex;
	boolean visited;
	List<Node> adjList; 
	ArrayList<Node> arr = new ArrayList<Node>();
	
	//constructor
	public Node(String vertex) {
		this.vertex = vertex;
		this.adjList = arr; 
	}
	
	public void addNeighbour(Node neighbour) {
		this.adjList.add(neighbour);
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	@Override
	public String toString() {
		return this.vertex;
	}
}
