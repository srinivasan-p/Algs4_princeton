package com.algorithms.dataStructure.QuickSort;

import java.util.Arrays;

import com.algorithms.dataStructure.util.Util;

public class QuickSort {
	public static void main(String[] args) {

		Comparable[] a = { 9, 7, 5, 3, 1, 0, 8, 6, 4, 2, -55, 99 };

		QuickSort.sort(a);

		// QuickSort.partition(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}

	private static void sort(Comparable[] a, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = partition(a, lo, hi);
		sort(a, lo, mid - 1);
		sort(a, mid + 1, hi);
	}

	private static int partition(Comparable[] a, int lo, int hi) {
		int i = lo + 1;
		int j = hi;
		int k = lo;

		while (true) {
			while (Util.less(a[i], a[k])) {
				i++;
				if (i == hi) { // To avoid index out of bound exception
					break;
				}
			}
			while (!Util.less(a[j], a[k])) {
				j--;
				if (j == lo) {
					break;
				}
			}

			if (i >= j)
				break;

			Util.swap(a, i, j);
		}
		Util.swap(a, k, j);
		System.out.println(Arrays.toString(a) + " " + j + " " + a[j]);
		return j;
	}
}
