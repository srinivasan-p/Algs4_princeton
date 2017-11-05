package com.algorithms.dataStructure.DirectedGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstDirectedPaths {
	private final int S;
	private boolean[] marked;
	private int[] edgeTo;

	public DepthFirstDirectedPaths(Digraph G, int s) {
		S = s;
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		validateVertex(s);
		dfs(G, s);
	}

	private void dfs(Digraph G, int v) {
		validateVertex(v);
		marked[v] = true;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);

			}
		}
	}

	public boolean hasPathTo(int v) {
		validateVertex(v);
		return marked[v];
	}

	public Iterable<Integer> pathTo(int v) {
		validateVertex(v);
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<>();
		for (int x = v; x != S; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(S);
		return path;
	}

	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 0 || v > V)
			throw new IllegalArgumentException(v + "has to be between 0 to " + (V - 1));
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		// StdOut.println(G);

		int s = Integer.parseInt(args[1]);
		DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, s);

		for (int v = 0; v < G.V(); v++) {
			if (dfs.hasPathTo(v)) {
				StdOut.printf("%d to %d:  ", s, v);
				for (int x : dfs.pathTo(v)) {
					if (x == s)
						StdOut.print(x);
					else
						StdOut.print("-" + x);
				}
				StdOut.println();
			}

			else {
				StdOut.printf("%d to %d:  not connected\n", s, v);
			}

		}
	}

}
