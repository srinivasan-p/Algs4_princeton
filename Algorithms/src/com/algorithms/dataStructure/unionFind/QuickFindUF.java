package com.algorithms.dataStructure.unionFind;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickFindUF {
	private int[] id;
	private int count;

	public QuickFindUF(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
	}

	public void union(int p, int q) {
		validate(p);
		validate(q);
		if (connected(p, q))
			return;
		int temp = id[p];
		for (int i = 0; i < id.length; i++) {
			if (id[i] == temp) {
				id[i] = id[q];
			}
		}
		count--;

	}

	public int count() {
		return count;
	}

	public boolean connected(int p, int q) {
		validate(p);
		validate(q);
		return id[p] == id[q];
	}

	private void validate(int p) {
		int n = id.length;
		if (p < 0 || p >= n) {
			throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n - 1));
		}
	}

	public static void main(String[] args) {
		int n = StdIn.readInt();
		QuickFindUF uf = new QuickFindUF(n);
		while (!StdIn.isEmpty()) {
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p, q))
				continue;
			uf.union(p, q);
			StdOut.println(uf.toString());
		}
		StdOut.println(uf.count() + " components");
	}

	@Override
	public String toString() {
		return "QuickFindUF [id=" + Arrays.toString(id) + ", count=" + count + "]";
	}

}
