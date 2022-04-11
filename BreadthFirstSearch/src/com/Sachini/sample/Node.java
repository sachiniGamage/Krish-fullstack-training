package com.Sachini.sample;
import java.util.*;

public class Node {
	String vertex;
	boolean visited;
	List<Node> adjNeighbourList;
	ArrayList<Node> arr = new ArrayList<Node>();
	
	public Node(String vertex) {
		this.vertex = vertex;
		this.adjNeighbourList  = arr;
	}
	
	public void addNeighbour(Node neighbour) {
		this.adjNeighbourList.add(neighbour);
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
