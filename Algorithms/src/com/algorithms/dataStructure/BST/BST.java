package com.algorithms.dataStructure.BST;

import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;

	private class Node {
		private Key key;
		private Value val;
		private Node left, right;
		private int size;

		public Node(Key key, Value val, int size) {
			this.key = key;
			this.val = val;
			this.size = size;
		}
	}

	public BST() {
	}

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return size(root);
	}

	private int size(Node x) {
		if (x == null)
			return 0;
		return x.size;
	}

	public boolean contains(Key key) {
		if (key == null)
			throw new IllegalArgumentException("called get() with a null key");
		return get(key) != null;

	}

	public Value get(Key key) {
		return get(root, key);
	}

	// This can also be written with while loop in iterative form....
	// But this one looks cool and matches the other recursive methods
	private Value get(Node x, Key key) {
		if (key == null)
			throw new IllegalArgumentException("called get() with a null key");
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			return get(x.left, key);
		} else if (cmp > 0) {
			return get(x.right, key);
		}
		return x.val;
	}

	public void put(Key key, Value val) {
		if (key == null)
			throw new IllegalArgumentException("calledput() with a null key");
		if (val == null) {
			delete(key);
			return;
		}
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null)
			return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if (cmp < 0) {
			x.left = put(x.left, key, val);
		} else if (cmp > 0) {
			x.right = put(x.right, key, val);
		} else {
			x.val = val;
		}
		// Use size() instead of x.left.size to avoid null exception
		x.size = size(x.left) + size(x.right) + 1;
		return x;
	}

	public void deleteMin() {
		if (isEmpty())
			throw new NoSuchElementException("Symbol table underflow");
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null) {
			return x.right;
		}
		x.left = deleteMin(x.left);
		x.size = size(x.left) + 1 + size(x.right);
		return x;
	}

	public void deleteMax() {
		if (isEmpty())
			throw new NoSuchElementException("Symbol table underflow");
		root = deleteMax(root);
	}

	private Node deleteMax(Node x) {
		if (x.right == null)
			return x.left;
		x.right = deleteMax(x.right);
		x.size = size(x.left) + 1 + size(x.right);
		return x;
	}

	public void delete(Key key) {
		if (key == null)
			throw new NoSuchElementException("Argument is null");
		root = delete(root, key);
	}

	private Node delete(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0)
			x.left = delete(x.left, key);
		else if (cmp > 0)
			x.right = delete(x.right, key);
		else {
			if (x.right == null)
				return x.left;
			if (x.left == null)
				return x.right;

			Node t = x;
			x = min(x.right);
			x.right = deleteMin(x.right);
			x.left = t.left;
		}
		x.size = size(x.left) + 1 + size(x.right);
		return x;
	}

	public Key min() {
		if (isEmpty())
			throw new NoSuchElementException("Tree Empty!");
		return min(root).key;
	}

	private Node min(Node x) {
		if (x.left == null)
			return x;
		else
			return min(x.left);
	}

	public Key max() {
		if (isEmpty())
			throw new NoSuchElementException("Tree Empty!");
		return max(root).key;
	}

	private Node max(Node x) {
		if (x.right == null)
			return x;
		else
			return max(x.right);
	}

	public Key floor(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to floor() is null");
		if (isEmpty())
			throw new NoSuchElementException("called floor() with empty symbol table");
		Node x = floor(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	private Node floor(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) {
			return x;
		} else if (cmp < 0) {
			return floor(x.left, key);
		}
		Node t = floor(x.right, key);
		if (t != null)
			return t;
		else
			return x;
	}

	public Key ceiling(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to ceiling() is null");
		if (isEmpty())
			throw new NoSuchElementException("called ceiling() with empty symbol table");
		Node x = ceiling(root, key);
		if (x == null)
			return null;
		return x.key;
	}

	// Check if it is working as the solution is different !!Important
	private Node ceiling(Node x, Key key) {
		if (x == null)
			return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0)
			return x;
		else if (cmp > 0)
			return ceiling(x.right, key);
		Node t = ceiling(x.left, key);
		if (t != null)
			return t;
		else
			return x;
	}

	public Key select(int k) {
		return null;
	}

	private Node select(Node x, int k) {
		return x;
	}

	public int rank(Key key) {
		if (key == null)
			throw new IllegalArgumentException("argument to rank() is null");
		return rank(key, root);
	}

	private int rank(Key key, Node x) {
		if (x == null)
			return 0;
		int cmp = key.compareTo(x.key);
		if (cmp < 1)
			return rank(key, x.left);
		else if (cmp > 1)
			return size(x.left) + 1 + rank(key, x.right);
		return size(x.left);
	}

	public Iterable<Key> keys() {
		return keys(min(), max());
	}

	public Iterable<Key> keys(Key lo, Key hi) {
		Queue<Key> q = new Queue<>();
		keys(root, q, lo, hi);
		return q;
	}

	private void keys(Node x, Queue<Key> q, Key lo, Key hi) {
		if (x == null)
			return;
		int cmplo = lo.compareTo(x.key);
		int cmphi = hi.compareTo(x.key);
		if (cmplo < 0)
			keys(x.left, q, lo, hi);
		if (cmplo <= 0 && cmphi >= 0)
			q.enqueue(x.key);
		if (cmphi > 0)
			keys(x.right, q, lo, hi);
	}

	public int size(Key lo, Key hi) {
		if (lo.compareTo(hi) > 0)
			return 0;
		if (contains(hi))
			return rank(hi) - rank(lo) + 1;
		else
			return rank(hi) - rank(lo);
	}

	public int height() {
		return height(root);
	}

	private int height(Node x) {
		if (x == null)
			return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}

	public Iterable<Key> levelOrder() {
		Queue<Key> keys = new Queue<Key>();
		Queue<Node> queue = new Queue<Node>();
		queue.enqueue(root);
		while (!queue.isEmpty()) {
			Node x = queue.dequeue();
			if (x == null)
				continue;
			keys.enqueue(x.key);
			queue.enqueue(x.left);
			queue.enqueue(x.right);
		}
		return keys;
	}

	private boolean check() {
		return false;
	}

	private boolean isBST() {
		return false;
	}

	private boolean isBST(Node x, Key min, Key max) {
		return false;
	}

	private boolean isSizeConsistent() {
		return false;
	}

	private boolean isSizeConsistent(Node x) {
		return false;
	}

	private boolean isRankConsistent() {
		return false;
	}

	public static void main(String[] args) {
		BST<String, Integer> st = new BST<String, Integer>();
		for (int i = 0; !StdIn.isEmpty(); i++) {
			String key = StdIn.readString();
			if (key.equalsIgnoreCase("MyExit"))
				break;
			st.put(key, i);
		}

		for (String s : st.levelOrder())
			StdOut.println(s + " " + st.get(s));

		StdOut.println();

		for (String s : st.keys())
			StdOut.println(s + " " + st.get(s));
	}
}
