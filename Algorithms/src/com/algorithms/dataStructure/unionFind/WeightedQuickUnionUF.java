package com.algorithms.dataStructure.unionFind;

import java.util.Arrays;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
	private int[] id;
	private int count;
	private int[] size;

	public WeightedQuickUnionUF(int N) {
		count = N;
		id = new int[N];
		size = new int[N];
		for (int i = 0; i < id.length; i++) {
			id[i] = i;
			size[i] = 1;
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
		return "WeightedQuickUnionUF [id=" + Arrays.toString(id) + ", count=" + count + ", size="
				+ Arrays.toString(size) + "]";
	}

	public int getRoot(int i) {
		while (id[i] != i)
			i = id[i];
		return i;
	}

	public void union(int p, int q) {
		if (connected(p, q))
			return;
		int pRoot = getRoot(p);
		int qRoot = getRoot(q);
		if (size[pRoot] < size[qRoot]) {
			id[pRoot] = qRoot;
			size[qRoot] += size[pRoot];
		} else {
			id[qRoot] = pRoot;
			size[pRoot] += size[qRoot];
		}
		count--;
	}

	public boolean connected(int p, int q) {
		return getRoot(p) == getRoot(q);
	}

	private int count() {
		return count;
	}

	public static void main(String[] args) {
		int n = StdIn.readInt();
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(n);
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

}
