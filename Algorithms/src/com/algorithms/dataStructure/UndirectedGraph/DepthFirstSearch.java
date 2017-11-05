package com.algorithms.dataStructure.UndirectedGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstSearch {
	private boolean[] marked;
	private int count = 0;

	public DepthFirstSearch(Graph G, int s) {
		marked = new boolean[G.V()];
		validateVertex(s);
		dfs(G, s);
	}

	private void dfs(Graph G, int v) {
		validateVertex(v);
		marked[v] = true;
		count++;
		for (Integer w : G.adj(v)) {
			if (!marked(w)) {
				dfs(G, w);
			}
		}
	}

	public boolean marked(int v) {
		validateVertex(v);
		return marked[v];
	}

	public int count() {
		return count;
	}

	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 0 || v > V)
			throw new IllegalArgumentException(v + "has to be between 0 to " + (V - 1));
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		int s = Integer.parseInt(args[1]);
		DepthFirstSearch search = new DepthFirstSearch(G, s);
		for (int v = 0; v < G.V(); v++) {
			if (search.marked(v))
				StdOut.print(v + " ");
		}

		StdOut.println();
		if (search.count() != G.V())
			StdOut.println("NOT connected");
		else
			StdOut.println("connected");
	}

}
