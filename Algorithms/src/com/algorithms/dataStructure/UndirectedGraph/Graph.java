package com.algorithms.dataStructure.UndirectedGraph;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Stack;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Graph {
	private static final String NEWLINE = System.getProperty("line.separator");
	private final int V;
	private int E;
	private List<Integer>[] adj;

	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V;
		E = 0;
		adj = (ArrayList<Integer>[]) new ArrayList[V];
		for (int i = 0; i < adj.length; i++) {
			adj[i] = new ArrayList<Integer>();
		}
	}

	@SuppressWarnings("unchecked")
	public Graph(In in) {
		try {
			this.V = in.readInt();
			if (V < 0)
				throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
			adj = (ArrayList<Integer>[]) new ArrayList[V];
			for (int v = 0; v < V; v++) {
				adj[v] = new ArrayList<Integer>();
			}
			int E = in.readInt();
			if (E < 0)
				throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
			for (int i = 0; i < E; i++) {
				int v = in.readInt();
				int w = in.readInt();
				validateVertex(v);
				validateVertex(w);
				addEdge(v, w);
			}
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException("invalid input format in Graph constructor", e);
		}
	}

	public Graph(Graph G) {
		this(G.V);
		this.E = G.E;
		for (int v = 0; v < G.V; v++) {
			Stack<Integer> reverse = new Stack<>();
			for (Integer w : G.adj(v)) {
				reverse.push(w);
			}
			for (Integer w : reverse) {
				adj[v].add(w);
			}
		}
	}

	public int V() {
		return V;
	}

	public int E() {
		return E;
	}

	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	public void addEdge(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	public Iterable<Integer> adj(int v) {
		validateVertex(v);
		return adj[v];
	}

	public int degree(int v) {
		validateVertex(v);
		return adj[v].size();
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

	// Test client
	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		StdOut.println(G);
		Graph G1 = new Graph(G);
		StdOut.println(G1);
		Graph G2 = new Graph(13);
		G2.addEdge(0, 5);
		G2.addEdge(4, 3);
		G2.addEdge(0, 1);
		G2.addEdge(9, 12);
		G2.addEdge(6, 4);
		G2.addEdge(5, 4);
		G2.addEdge(0, 2);
		G2.addEdge(11, 12);
		G2.addEdge(9, 10);
		G2.addEdge(0, 6);
		G2.addEdge(7, 8);
		G2.addEdge(9, 11);
		G2.addEdge(5, 3);
		System.out.println(G2);
	}

}
