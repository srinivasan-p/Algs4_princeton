package com.algorithms.dataStructure.StacksAndQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.algorithms.dataStructure.util.Util;

public class LinkedStack<T> implements Iterable<T> {
	private Node first = null;

	private class Node {
		public T item;
		public Node next;
	}

	public boolean isEmpty() {
		return first == null;
	}

	public void push(T elem) {
		Node newFirst = new Node();
		newFirst.item = elem;
		newFirst.next = first;
		first = newFirst;
	}

	public T pop() {
		if (first == null)
			throw new NoSuchElementException("Stack is empty");
		T elem = first.item;
		first = first.next;
		return elem;
	}

	public static void main(String[] args) {
		LinkedStack<String> stack = new LinkedStack<>();
		Scanner sc = Util.getScanner();
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.equalsIgnoreCase("END"))
				break;
			if (s.equalsIgnoreCase("-"))
				System.out.println(stack.pop());
			else
				stack.push(s);
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
