package com.algorithms.dataStructure.util;

import java.util.Scanner;

public class Util {
	public static Scanner getScanner() {
		return new Scanner(System.in);
	}

	public static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}

	public static void swap(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
