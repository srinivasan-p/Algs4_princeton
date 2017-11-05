package com.algorithms.dataStructure.UndirectedGraph;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class BreadthFirstPaths {
	private final int INFINITY = Integer.MAX_VALUE;
	private boolean[] marked;
	private int[] edgeTo;
	private int[] distTo;

	public BreadthFirstPaths(Graph G, int s) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];
		validateVertex(s);
		bfs(G, s);
	}

	public BreadthFirstPaths(Graph G, Iterable<Integer> sources) {
		marked = new boolean[G.V()];
		edgeTo = new int[G.V()];
		distTo = new int[G.V()];
		validateVertices(sources);
		for (int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
		bfs(G, sources);
	}

	private void bfs(Graph G, int s) {
		Queue<Integer> queue = new Queue<>();
		queue.enqueue(s);
		for (int v = 0; v < G.V(); v++)
			distTo[v] = INFINITY;
		distTo[s] = 0;
		marked[s] = true;
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			for (Integer w : G.adj(v)) {
				if (!marked[w]) {
					queue.enqueue(w);
					marked[w] = true;
					distTo[w] = distTo[v] + 1;
					edgeTo[w] = v;
				}
			}
		}
	}

	private void bfs(Graph G, Iterable<Integer> sources) {
		Queue<Integer> queue = new Queue<>();
		for (Integer s : sources) {
			queue.enqueue(s);
			distTo[s] = 0;
			marked[s] = true;
		}
		while (!queue.isEmpty()) {
			int v = queue.dequeue();
			for (Integer w : G.adj(v)) {
				if (!marked[w]) {
					queue.enqueue(w);
					marked[w] = true;
					distTo[w] = distTo[v] + 1;
					edgeTo[w] = v;
				}
			}
		}
	}

	public boolean hasPathTo(int v) {
		return marked[v];
	}

	public int distTo(int v) {
		return distTo[v];
	}

	public Iterable<Integer> pathTo(int v) {
		validateVertex(v);
		if (!hasPathTo(v))
			return null;
		Stack<Integer> path = new Stack<>();
		int x;
		for (x = v; distTo[x] != 0; x = edgeTo[x]) {
			path.push(x);
		}
		path.push(x);
		return path;
	}

	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 0 || v > V)
			throw new IllegalArgumentException(v + "has to be between 0 to " + (V - 1));
	}

	private void validateVertices(Iterable<Integer> vertices) {
		for (Integer v : vertices) {
			validateVertex(v);
		}
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		Graph G = new Graph(in);
		// StdOut.println(G);

		int s = Integer.parseInt(args[1]);
		BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

		for (int v = 0; v < G.V(); v++) {
			if (bfs.hasPathTo(v)) {
				StdOut.printf("%d to %d (%d):  ", s, v, bfs.distTo(v));
				for (int x : bfs.pathTo(v)) {
					if (x == s)
						StdOut.print(x);
					else
						StdOut.print("-" + x);
				}
				StdOut.println();
			}

			else {
				StdOut.printf("%d to %d (-):  not connected\n", s, v);
			}

		}
	}

}
