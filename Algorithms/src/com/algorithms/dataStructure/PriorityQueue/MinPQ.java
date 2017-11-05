package com.algorithms.dataStructure.PriorityQueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MinPQ<Key> implements Iterable<Key> {
	private Key[] pq;
	private int N = 0;
	private static int INIT_CAPACITY = 1;

	@SuppressWarnings("unchecked")
	public MinPQ(int initCapacity) {
		pq = (Key[]) new Object[initCapacity + 1];
	}

	@SuppressWarnings("unchecked")
	public MinPQ() {
		this(INIT_CAPACITY);
	}

	public MinPQ(int initCapacity, Comparator<Key> comparator) {
	}

	public MinPQ(Comparator<Key> comparator) {
	}

	// Important
	@SuppressWarnings("unchecked")
	public MinPQ(Key[] keys) {
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

	public Key min() {
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
	public Key delMin() {
		Key min = min();
		exch(1, N);
		N--;
		pq[N + 1] = null;
		sink(1);
		if (N > 0 && (pq.length - 1) / 4 == N)
			resize(pq.length / 2);
		return min;
	}

	private void swim(int k) {
		while (k > 1 && less(k, k / 2)) {
			exch(k / 2, k);
			k /= 2;
		}
	}

	// Important "if (!less(k, j))" break loop
	private void sink(int k) {
		while (k <= N / 2) {
			int j = k * 2;
			if (j < N && less(j + 1, j))
				j = j++;
			if (!less(j, k))
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
		return null;
	}

	@Override
	public String toString() {
		return "MinPQ [pq=" + Arrays.toString(pq) + ", N=" + N + "]";
	}

	private class HeapIterator implements Iterator<Key> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Key next() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	// a b c - - df - sdfsdf zczc xcv cvb qwe - - asd koko okok MyExit
//	public static void main(String[] args) {
//		MinPQ<String> pq = new MinPQ<String>();
//		while (!StdIn.isEmpty()) {
//			String item = StdIn.readString();
//			if (item.equalsIgnoreCase("MyEXIT"))
//				break;
//			else if (!item.equals("-"))
//				pq.insert(item);
//			else if (!pq.isEmpty())
//				StdOut.println(pq.delMin() + " ");
//			System.out.println(pq);
//		}
//		StdOut.println(pq);
//		for (String e : pq) {
//			System.out.print(e + " ");
//		}
//	}

}