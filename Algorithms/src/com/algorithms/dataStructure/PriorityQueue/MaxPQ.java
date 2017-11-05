package com.algorithms.dataStructure.PriorityQueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

class MaxPQ<Key> implements Iterable<Key> {
	private Key[] pq;
	private int N = 0;
	private static int INIT_CAPACITY = 1;

	@SuppressWarnings("unchecked")
	public MaxPQ(int initCapacity) {
		pq = (Key[]) new Object[initCapacity + 1];
	}

	@SuppressWarnings("unchecked")
	public MaxPQ() {
		this(INIT_CAPACITY);
	}

	public MaxPQ(int initCapacity, Comparator<Key> comparator) {
	}

	public MaxPQ(Comparator<Key> comparator) {
	}

	// Important
	@SuppressWarnings("unchecked")
	public MaxPQ(Key[] keys) {
		N = keys.length;
		pq = (Key[]) new Object[keys.length + 1];
		for (int i = 0; i < N; i++)
			pq[i + 1] = keys[i];
		for (int k = N / 2; k >= 1; k--)
			sink(k);
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return N;
	}

	public Key max() {
		if (isEmpty())
			throw new NoSuchElementException();
		return pq[1];
	}

	// important as Inx start @ 1
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		Key[] tempPQ = (Key[]) new Object[capacity];
		for (int i = 1; i <= N; i++) {
			tempPQ[i] = pq[i];
		}
		pq = tempPQ;
	}

	// Important as empty 0 position is considered to calculate "if (pq.length -
	// 1 == N)"
	public void insert(Key x) {
		if (pq.length - 1 == N)
			resize(2 * pq.length);
		pq[++N] = x;
		swim(N);

	}

	// Important as empty 0 position is considered to calculate "if (N > 0 &&
	// (pq.length - 1) / 4 == N)"
	public Key delMax() {
		Key max = max();
		exch(1, N);
		N--;
		pq[N + 1] = null;
		sink(1);
		if (N > 0 && (pq.length - 1) / 4 == N)
			resize(pq.length / 2);
		return max;
	}

	private void swim(int k) {
		while (k > 1 && less(k / 2, k)) {
			exch(k / 2, k);
			k /= 2;
		}
	}

	// Important "if (!less(k, j))" break loop
	private void sink(int k) {
		while (k <= N / 2) {
			int j = k * 2;
			if (j < N && less(j, j + 1))
				j++;
			if (!less(k, j))
				break;
			exch(k, j);
			k = j;
		}
	}

	@SuppressWarnings("unchecked")
	private boolean less(int i, int j) {
		return ((Comparable<Key>) pq[i]).compareTo(pq[j]) < 0;
	}

	private void exch(int i, int j) {
		Key swap = pq[i];
		pq[i] = pq[j];
		pq[j] = swap;
	}

	private boolean isMaxHeap() {
		return false;
	}

	private boolean isMaxHeap(int k) {
		return false;
	}

	public Iterator<Key> iterator() {
		return new HeapIterator();
	}

	@Override
	public String toString() {
		return "MaxPQ [pq=" + Arrays.toString(pq) + ", N=" + N + "]";
	}

	private class HeapIterator implements Iterator<Key> {

		MaxPQ<Key> copyPQ = new MaxPQ<>(size());

		public HeapIterator() {
			for (int i = 1; i <= size(); i++) {
				copyPQ.insert(pq[i]);
			}
		}

		@Override
		public boolean hasNext() {
			return !copyPQ.isEmpty();
		}

		@Override
		public Key next() {
			Key max = copyPQ.delMax();
			return max;
		}
	}

	// a b c - - df - sdfsdf zczc xcv cvb qwe - - asd koko okok MyExit
	public static void main(String[] args) {
		MaxPQ<String> pq = new MaxPQ<String>();
		while (!StdIn.isEmpty()) {
			String item = StdIn.readString();
			if (item.equalsIgnoreCase("MyEXIT"))
				break;
			else if (!item.equals("-"))
				pq.insert(item);
			else if (!pq.isEmpty())
				StdOut.println(pq.delMax() + " ");
			System.out.println(pq);
		}
		StdOut.println(pq);
		for (String e : pq) {
			System.out.print(e + " ");
		}
	}

}