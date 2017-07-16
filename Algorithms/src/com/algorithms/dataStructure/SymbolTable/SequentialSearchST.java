package com.algorithms.dataStructure.SymbolTable;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SequentialSearchST<Key, Value> {
	private int N;
	private Node first;

	public SequentialSearchST() {
		first = null;
		N = 0;
	}

	public class Node {
		public Key key;
		public Value val;
		public Node next;

		Node(Key key, Value val, Node next) {
			this.key = key;
			this.val = val;
			this.next = next;
		}
	}

	public Value get(Key key) {
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key))
				return x.val;
		}
		return null;
	}

	public void put(Key key, Value val) {
		for (Node x = first; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = val;
				return;
			}
		}
		first = new Node(key, val, first);
		N++;
	}

	/*
	 * Important - Understand clearly - recursive
	 */
	public void delete(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to delete() is null");
		first = delete(first, key);
	}

	/*
	 * Important - Understand clearly - recursive
	 */
	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		if (key.equals(x.key)) {
			N--;
			return x.next;
		}
		x.next = delete(x.next, key);
		return x;
	}

	public boolean contains(Key key) {
		return get(key) != null;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public Iterable<Key> keys() {
		Queue<Key> queue = new Queue<Key>();
		for (Node x = first; x != null; x = x.next)
			queue.enqueue(x.key);
		return queue;
	}

	/*
	 * @Override public Iterator<Key> iterator() { return new Iterator<Key>() {
	 * private Node x = first;
	 * 
	 * @Override public boolean hasNext() { return x.next != null; }
	 * 
	 * @Override public Key next() { Key key = x.key; x = x.next; return key; }
	 * 
	 * }; }
	 */

	public static void main(String[] args) {
		SequentialSearchST<String, Integer> st = new SequentialSearchST<String, Integer>();
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
