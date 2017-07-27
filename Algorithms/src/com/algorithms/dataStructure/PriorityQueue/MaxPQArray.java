package com.algorithms.dataStructure.PriorityQueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MaxPQArray<Key> implements Iterable<Key> {
	private Key[] pq;
	private int N = 0;
	private int INIT_CAPACITY = 2;

	@SuppressWarnings("unchecked")
	public MaxPQArray(int initCapacity) {
		pq = (Key[]) new Object[initCapacity];
	}

	@SuppressWarnings("unchecked")
	public MaxPQArray() {
		pq = (Key[]) new Object[INIT_CAPACITY];
	}

	public MaxPQArray(int initCapacity, Comparator<Key> comparator) {
	}

	public MaxPQArray(Comparator<Key> comparator) {
	}

	public MaxPQArray(Key[] keys) {
		pq = keys;
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
		int maxIdx = 0;
		for (int i = 0; i < N; i++) {
			if (less(maxIdx, i)) {
				maxIdx = i;
			}
		}
		return pq[maxIdx];
	}

	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		Key[] tempPQ = (Key[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			tempPQ[i] = pq[i];
		}
		pq = tempPQ;
	}

	public void insert(Key x) {
		if (pq.length == N)
			resize(N * 2);
		pq[N++] = x;
	}

	public Key delMax() {
		if (isEmpty())
			throw new NoSuchElementException();
		int maxIdx = 0;
		for (int i = 0; i < N; i++) {
			if (less(maxIdx, i)) {
				maxIdx = i;
			}
		}
		Key max = pq[maxIdx];
		for (int i = maxIdx; i < N - 1; i++) {
			pq[i] = pq[i + 1];
		}
		N--;
		pq[N] = null;
		if (pq.length / 4 == N)
			resize(pq.length / 2);
		return max;
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

	public Iterator<Key> iterator() {
		return new HeapIterator();
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

	@Override
	public String toString() {
		return "MaxPQArray [pq=" + Arrays.toString(pq) + ", N=" + N + ", INIT_CAPACITY=" + INIT_CAPACITY + "]";
	}

	// a b c - - df - sdfsdf zczc xcv cvb qwe - - asd koko okok MyExit
	public static void main(String[] args) {
		MaxPQArray<String> pq = new MaxPQArray<String>();
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
	}

}