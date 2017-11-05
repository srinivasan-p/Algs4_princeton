package com.algorithms.dataStructure.DirectedGraph;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class DirectedDFS {
	private boolean[] marked;
	private int count = 0;

	public DirectedDFS(Digraph G, int s) {
		marked = new boolean[G.V()];
		validateVertex(s);
		dfs(G, s);
	}

	public DirectedDFS(Digraph G, Iterable<Integer> sources) {
		marked = new boolean[G.V()];
		validateVertices(sources);
		for (int v : sources) {
			if (!marked[v])
				dfs(G, v);
		}
	}

	private void validateVertices(Iterable<Integer> sources) {
		for (Integer v : sources) {
			validateVertex(v);
		}
	}

	private void dfs(Digraph G, int v) {
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

	public static void main(String[] args) {// read in digraph from command-line
											// argument
		In in = new In(args[0]);
		Digraph G = new Digraph(in);

		// read in sources from command-line arguments
		Bag<Integer> sources = new Bag<Integer>();
		for (int i = 1; i < args.length; i++) {
			int s = Integer.parseInt(args[i]);
			sources.add(s);
		}

		// multiple-source reachability
		DirectedDFS dfs = new DirectedDFS(G, sources);

		// print out vertices reachable from sources
		for (int v = 0; v < G.V(); v++) {
			if (dfs.marked(v))
				StdOut.print(v + " ");
		}
		StdOut.println();
	}

}
