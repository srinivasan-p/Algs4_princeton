package com.algorithms.dataStructure.StacksAndQueues;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.algorithms.dataStructure.util.Util;

public class ResizingArrayQueue<T> implements Iterable<T> {

	private T[] q;
	private int first;
	private int last;
	private int n;

	@SuppressWarnings("unchecked")
	public ResizingArrayQueue() {
		q = (T[]) new Object[1];
		first = 0;
		last = 0;
		n = 0;
	}

	public boolean isEmpty() {
		return n == 0;
	}

	public int size() {
		return n;
	}

	private void resize(int newSize) {
		@SuppressWarnings("unchecked")
		T[] copyQ = (T[]) new Object[newSize];
		for (int i = 0; i < n; i++) {
			copyQ[i] = q[(first + i) % q.length];
		}
		q = copyQ;
		first = 0;
		last = n;
	}

	public void enqueue(T elem) {
		if (q.length == n)
			resize(n * 2);
		q[last++] = elem;
		n++;
		if (last == q.length)
			last = 0;
	}

	public T dequeue() {
		T elem = q[first];
		q[first] = null;
		n--;
		first++;
		if (first == q.length)
			first = 0;

		if (n > 0 && n == q.length / 4)
			resize(n * 2);
		return elem;

	}

	public T peek() {
		if (isEmpty())
			throw new NoSuchElementException();
		return q[first];

	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<T>() {
			private int cIdx = first;

			@Override
			public boolean hasNext() {
				return cIdx < n;
			}

			@Override
			public T next() {
				return q[(cIdx++ + first) % q.length];
			}
		};
	}

	public static void main(String[] args) {
		ResizingArrayQueue<String> resizingArrayQueue = new ResizingArrayQueue<>();
		Scanner sc = Util.getScanner();
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.equalsIgnoreCase("END"))
				break;
			if (s.equalsIgnoreCase("-"))
				System.out.println(resizingArrayQueue.dequeue());
			else
				resizingArrayQueue.enqueue(s);
		}
		for (String string : resizingArrayQueue) {
			System.out.print(string + " ");
		}
		System.out.println(resizingArrayQueue);
	}

}
