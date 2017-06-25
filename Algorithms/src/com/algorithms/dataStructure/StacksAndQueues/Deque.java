package com.algorithms.dataStructure.StacksAndQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.algorithms.dataStructure.util.Util;

public class Deque<T> implements Iterable<T> {

	private Node first;
	private Node last;
	private int N;

	private class Node {
		public T item;
		public Node prev;
		public Node next;
	}

	public Deque() {
		first = null;
		last = null;
		N = 0;
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public int size() {
		return N;
	}

	public void addFirst(T elem) {
		Node oldFirst = first;
		first = new Node();
		first.item = elem;
		first.next = oldFirst;
		if (isEmpty())
			last = first;
		else
			oldFirst.prev = first;
		N++;
	}

	public void addLast(T elem) {
		Node oldLast = last;
		last = new Node();
		last.item = elem;
		last.prev = oldLast;
		if (isEmpty())
			first = last;
		else
			oldLast.next = last;
		N++;
	}

	public T removeFirst() {
		if (isEmpty())
			throw new NoSuchElementException();
		T elem = first.item;
		first = first.next;
		N--;
		if (isEmpty())
			last = null;
		else
			first.prev = null;

		return elem;
	}

	public T removeLast() {
		if (isEmpty())
			throw new NoSuchElementException();
		T elem = last.item;
		last = last.prev;
		N--;
		if (isEmpty())
			first = null;
		else
			last.next = null;

		return elem;
	}

	public Iterator<T> iterator() {
		return new Iterator<T>() {

			private Node cNode = first;

			@Override
			public boolean hasNext() {
				return cNode != null;
			}

			@Override
			public T next() {
				T elem = cNode.item;
				cNode = cNode.next;
				return elem;
			}
		};
	}

	public static void main(String[] args) {
		Deque<String> stack = new Deque<>();
		Scanner sc = Util.getScanner();
		while (sc.hasNext()) {
			System.out.println();
			String s = sc.next();
			if (s.equalsIgnoreCase("END"))
				break;
			if (s.equalsIgnoreCase("-F"))
				System.out.println(stack.removeFirst());
			else if (s.equalsIgnoreCase("-L"))
				System.out.println(stack.removeLast());
			else if (s.equalsIgnoreCase("+F"))
				stack.addFirst(sc.next());
			else if (s.equalsIgnoreCase("+L"))
				stack.addLast(sc.next());

			System.out.println();
			for (String string : stack) {
				System.out.print(string + " ");
			}
		}

		System.out.println();
		for (String string : stack) {
			System.out.print(string + " ");
		}
	}
}
