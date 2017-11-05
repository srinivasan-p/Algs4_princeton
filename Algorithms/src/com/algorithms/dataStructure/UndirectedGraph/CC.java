package com.algorithms.dataStructure.UndirectedGraph;

import edu.princeton.cs.algs4.EdgeWeightedGraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class CC {
	private boolean[] marked;
	private int[] id;
	private int[] size;
	private int count;

	public CC(Graph G) {
		marked = new boolean[G.V()];
		id = new int[G.V()];
		count = 0;
		size = new int[G.V()];
		for (int v = 0; v < G.V(); v++) {
			if (!marked[v]) {
				dfs(G, v);
				count++;
			}
		}
	}

	public CC(EdgeWeightedGraph G) {
	}

	private void dfs(Graph G, int v) {
		validateVertex(v);
		marked[v] = true;
		size[count]++;
		for (int w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
			id[w] = count;
		}
	}

	private void dfs(EdgeWeightedGraph G, int v) {
	}

	public int id(int v) {
		return id[v];
	}

	public int size(int v) {
		return size[id[v]];
	}

	public int count() {
		return count;
	}

	public boolean connected(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		return id[v] == id[w];
	}

	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 0 || v > V)
			throw new IllegalArgumentException(v + "has to be between 0 to " + (V - 1));
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		CC cc = new CC(G);

		// number of connected components
		int m = cc.count();
		StdOut.println(m + " components");

		// compute list of vertices in each connected component
		Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
		for (int i = 0; i < m; i++) {
			components[i] = new Queue<Integer>();
		}
		for (int v = 0; v < G.V(); v++) {
			components[cc.id(v)].enqueue(v);
		}

		// print results
		for (int i = 0; i < m; i++) {
			for (int v : components[i]) {
				StdOut.print(v + " ");
			}
			StdOut.println();
		}
	}
}
