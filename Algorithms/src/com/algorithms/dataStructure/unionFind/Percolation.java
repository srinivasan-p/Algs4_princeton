package com.algorithms.dataStructure.unionFind;

public class Percolation {
	private int N;
	private int openCount = 0;
	boolean[][] grid;
	WeightedQuickUnionUF uf;

	public Percolation(int n) {
		if (n <= 0)
			throw new IllegalArgumentException();
		N = n;
		uf = new WeightedQuickUnionUF(N * N + 2);
		grid = new boolean[N][N];
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = false;
			}
		}

	}

	public void open(int row, int col) {
		row = row - 1;
		col = col - 1;
		validate(row, col);
		if (row == 0) {

		}
		if (row == N - 1 && isOpen(row, col)) {

		}
		if (row - 1 >= 0) {

		}
		if (col + 1 >= 0) {

		}
		if (row + 1 >= 0) {

		}
		if (col - 1 >= 0) {

		}

	}

	public boolean isOpen(int row, int col) {
		return false;
	}

	public boolean isFull(int row, int col) {
		return false;
	} // is site (row, col) full?

	public int numberOfOpenSites() {

		return 0;
	}// number of open sites

	public boolean percolates() {
		return false;

	}

	private boolean validate(int row, int col) {
		if (row < 0 || col < 0 || row >= N || col >= N) {
			throw new IndexOutOfBoundsException();
		}
		return true;

	}

	public static void main(String[] args) {
		int size = 10;
		Percolation pc = new Percolation(size);
		while (!pc.percolates()) {
			int row = (int) (edu.princeton.cs.algs4.StdRandom.uniform() * size) + 1;
			int col = (int) (edu.princeton.cs.algs4.StdRandom.uniform() * size) + 1;
			System.out.println(row + " " + col);
			pc.open(row, col);
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (pc.grid[i][j]) {
					System.out.print("# ");
				} else {
					System.out.print(". ");
				}
			}
			System.out.println();
		}
	}
}
