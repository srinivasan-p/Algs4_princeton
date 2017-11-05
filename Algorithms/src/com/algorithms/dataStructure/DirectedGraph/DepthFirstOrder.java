package com.algorithms.dataStructure.DirectedGraph;

import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class DepthFirstOrder {
	private boolean[] marked;
	private int[] pre;
	private int[] post;
	private Queue<Integer> preorder;
	private Queue<Integer> postorder;
	private int preCounter;
	private int postCounter;

	public DepthFirstOrder(Digraph G) {
		marked = new boolean[G.V()];
		pre = new int[G.V()];
		post = new int[G.V()];
		preorder = new Queue<>();
		postorder = new Queue<>();
		preCounter = 0;
		postCounter = 0;
		for (int i = 0; i < G.V(); i++) {
			if (!marked[i]) {
				dfs(G, i);
			}
		}
	}

	public DepthFirstOrder(EdgeWeightedDigraph G) {
	}

	private void dfs(Digraph G, int v) {
		validateVertex(v);
		marked[v] = true;
		preorder.enqueue(v);
		pre[v] = preCounter++;
		for (Integer w : G.adj(v)) {
			if (!marked[w]) {
				dfs(G, w);
			}
		}
		postorder.enqueue(v);
		post[v] = postCounter++;
	}

	private void dfs(EdgeWeightedDigraph G, int v) {
	}

	public int pre(int v) {
		validateVertex(v);
		return pre[v];
	}

	public int post(int v) {
		validateVertex(v);
		return post[v];
	}

	public Iterable<Integer> post() {
		return postorder;
	}

	public Iterable<Integer> pre() {
		return preorder;
	}

	public Iterable<Integer> reversePost() {
		Stack<Integer> reversePostorder = new Stack<>();
		for (int v : postorder) {
			reversePostorder.push(v);
		}
		return reversePostorder;
	}

	private void validateVertex(int v) {
		int V = marked.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);

		DepthFirstOrder dfs = new DepthFirstOrder(G);
		StdOut.println("   v  pre post");
		StdOut.println("--------------");
		for (int v = 0; v < G.V(); v++) {
			StdOut.printf("%4d %4d %4d\n", v, dfs.pre(v), dfs.post(v));
		}

		StdOut.print("Preorder:  ");
		for (int v : dfs.pre()) {
			StdOut.print(v + " ");
		}
		StdOut.println();

		StdOut.print("Postorder: ");
		for (int v : dfs.post()) {
			StdOut.print(v + " ");
		}
		StdOut.println();

		StdOut.print("Reverse postorder: ");
		for (int v : dfs.reversePost()) {
			StdOut.print(v + " ");
		}
		StdOut.println();

	}

}
