package com.algorithms.dataStructure.util;

import java.util.Scanner;

public class Util {
	public static Scanner getScanner() {
		return new Scanner(System.in);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	@SuppressWarnings("rawtypes")
	public static void swap(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
