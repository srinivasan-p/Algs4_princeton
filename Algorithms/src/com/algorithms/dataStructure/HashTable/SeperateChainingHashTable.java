package com.algorithms.dataStructure.HashTable;

public class SeperateChainingHashTable<Key, Value> {
	private int M = 97;
	@SuppressWarnings("unchecked")
	private Node[] sc = (Node[]) new Object[M];

	public class Node {
		public Key key;
		public Value val;
		public Node next;

		Node() {

		}

		Node(Key key, Value value, Node next) {
			this.key = key;
			this.val = value;
			this.next = next;
		}
	}

	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % M;
	}

	public Value get(Key key) {
		int i = hash(key);
		for (Node x = sc[i]; x != null; x = x.next) {
			if (key.equals(x.key))
				return (Value) x.val;
		}
		return null;
	}

	public void put(Key key, Value value) {
		int i = hash(key);
		for (Node x = sc[i]; x != null; x = x.next) {
			if (key.equals(x.key)) {
				x.val = value;
				return;
			}
		}
		sc[i] = new Node(key, value, sc[i]);
	}
}
