package com.algorithms.dataStructure.unionFind;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class QuickUnionUF {
	private int[] id;
	private int count;

	public QuickUnionUF(int N) {
		count = N;
		id = new int[N];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
		}
	}

	private void validate(int p) {
		int n = id.length;
		if (p < 0 || p >= n) {
			throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (n - 1));
		}
	}

	@Override
	public String toString() {
		return "QuickUnionUF [id=" + Arrays.toString(id) + ", count=" + count + "]";
	}

	public int getRoot(int i) {
		while (id[i] != i)
			i = id[i];
		return i;
	}

	public void union(int p, int q) {
		if (connected(p, q))
			return;
		id[getRoot(p)] = getRoot(q);
		count--;
	}

	public boolean connected(int p, int q) {
		return getRoot(p) == getRoot(q);
	}

	public static void main(String[] args) {
		int n = StdIn.readInt();
		QuickUnionUF uf = new QuickUnionUF(n);
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

	private int count() {
		return count;
	}

}
