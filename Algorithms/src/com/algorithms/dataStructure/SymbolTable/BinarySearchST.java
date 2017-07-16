package com.algorithms.dataStructure.SymbolTable;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BinarySearchST<Key extends Comparable<Key>, Value> {
	private static int INT_CAPACITY = 4;
	private Key[] keys;
	private Value[] vals;
	private int N;

	public BinarySearchST() {
		this(INT_CAPACITY);
	}

	@SuppressWarnings("unchecked")
	public BinarySearchST(int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Object[capacity];
		N = 0;
	}

	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		assert capacity >= N;
		Key[] tempKeys = (Key[]) new Comparable[capacity];
		Value[] tempVals = (Value[]) new Object[capacity];
		for (int i = 0; i < N; i++) {
			tempKeys[i] = keys[i];
			tempVals[i] = vals[i];
		}
		keys = tempKeys;
		vals = tempVals;
	}

	public int size() {
		return N;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public Value get(Key key) {
		int i = rank(key);
		if (i < N && key.compareTo(keys[i]) == 0)
			return vals[i];
		return null;
	}

	public int rank(Key key) {
		int lo = 0;
		int hi = N - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp > 0)
				lo = mid + 1;
			else if (cmp < 0)
				hi = mid - 1;
			else
				return mid;
		}
		return lo;
	}

	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("Key is null");

		if (val == null) {
			delete(key);
			return;
		}

		int i = rank(key);

		// Key Already present ==> So replace the value
		if (i < N && key.compareTo(keys[i]) == 0) {
			vals[i] = val;
			return;
		}

		// Not present ==> Move elements to create space
		if (N == keys.length)
			resize(keys.length * 2);

		for (int j = N; j > i; j--) {
			keys[j] = keys[j - 1];
			vals[j] = vals[j - 1];
		}

		keys[i] = key;
		vals[i] = val;

		N++;
	}

	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("Key is null");

		int i = rank(key);

		if (i >= N || i < 0)
			return;

		// Key is present ==> Delete by moving array left
		for (int j = i; j < N - 1; j++) {
			keys[j] = keys[j + 1];
			vals[j] = vals[j + 1];
		}

		keys[N - 1] = null;
		vals[N - 1] = null;

		N--;

		if (N > 0 && N == keys.length / 4)
			resize(keys.length / 2);
	}

	public void deleteMin() {
		delete(min());
	}

	public void deleteMax() {
		delete(max());
	}

	public Key min() {
		return keys[0];
	}

	public Key max() {
		return keys[N - 1];
	}

	public Key select(int k) {
		return keys[k];
	}

	public Key floor(Key key) {
		int i = rank(key);
		// found key itself (=)
		if (i < N && key.compareTo(keys[i]) == 0)
			return keys[i];
		// All elements are > Key (>)
		if (i == 0)
			return null;
		// find element greatest element < key (<)
		else
			return keys[i - 1];
	}

	public Key ceiling(Key key) {
		int i = rank(key);
		if (i == N)
			return null;
		else
			return keys[i];
	}

	public int size(Key lo, Key hi) {
		if (lo.compareTo(hi) > 0)
			return 0;
		if (contains(hi))
			return rank(hi) - rank(lo) + 1;
		else
			return rank(hi) - rank(lo);
	}

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		if (lo.compareTo(hi) > 0)
			return null;
		Queue<Key> queue = new Queue<>();
		for (int i = rank(lo); i < rank(hi); i++)
			queue.enqueue(keys[i]);
		if (contains(hi))
			queue.enqueue(keys[rank(hi)]);
		return queue;
	}

	private boolean check() {
		return false;
	}

	public static void main(String[] args) {
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			if (key.equalsIgnoreCase("EXIT"))
				break;
			st.put(key, i);
		}
		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}
}
