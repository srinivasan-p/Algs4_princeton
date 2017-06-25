package com.algorithms.dataStructure.StacksAndQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.algorithms.dataStructure.util.Util;

public class LinkedQueue<T> implements Iterable<T> {
	private Node first = null;
	private Node last = null;

	private class Node {
		public T item;
		public Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void enqueue(T elem) {
		Node oldlast = last;
		last = new Node();
		last.item = elem;
		if (isEmpty())
			first = last;
		else
			oldlast.next = last;
	}

	public T dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("List is empty.");
		}
		T elem = first.item;
		first = first.next;
		if (isEmpty())
			last = null;
		return elem;
	}

	public static void main(String[] args) {
		LinkedQueue<String> stack = new LinkedQueue<>();
		Scanner sc = Util.getScanner();
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.equalsIgnoreCase("END"))
				break;
			if (s.equalsIgnoreCase("-"))
				System.out.println(stack.dequeue());
			else
				stack.enqueue(s);
		}

		System.out.println();
		for (String string : stack) {
			System.out.println(string + " ");
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node cNode = first;

			@Override
			public boolean hasNext() {
				return cNode.next != null;
			}

			@Override
			public T next() {
				T elem = cNode.item;
				cNode = cNode.next;
				return elem;
			}
		};
	}
}
