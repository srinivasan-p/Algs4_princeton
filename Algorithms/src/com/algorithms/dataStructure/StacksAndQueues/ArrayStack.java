package com.algorithms.dataStructure.StacksAndQueues;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

import com.algorithms.dataStructure.util.Util;

public class ArrayStack<T> implements Iterable<T> {
	public T[] item;
	int N = 0;

	@SuppressWarnings("unchecked")
	public ArrayStack() {
		item = (T[]) new Object[1];
	}

	public boolean isEmpty() {
		return N == 0;
	}

	public void push(T elem) {
		if (N == item.length)
			resizeArray(N * 2);
		item[N++] = elem;
	}

	public T pop() {
		if (N == item.length / 4)
			resizeArray(N / 2);
		T elem = item[--N];
		item[N] = null;
		return elem;
	}

	private void resizeArray(int newSize) {
		@SuppressWarnings("unchecked")
		T[] copyArray = (T[]) new Object[newSize];
		for (int i = 0; i < N; i++) {
			copyArray[i] = item[i];
		}
		item = copyArray;
	}

	@Override
	public String toString() {
		return "ArrayStack [item=" + Arrays.toString(item) + ", N=" + N + "]";
	}

	public static void main(String[] args) {
		ArrayStack<String> stack = new ArrayStack<>();
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
		for (String string : stack) {
			System.out.print(string + " ");
		}
		System.out.println(stack);
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int cIdx = 0;

			@Override
			public boolean hasNext() {
				return cIdx < N;
			}

			@Override
			public T next() {
				return item[cIdx++];
			}
		};
	}
}
