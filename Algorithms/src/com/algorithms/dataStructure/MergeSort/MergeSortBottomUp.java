package com.algorithms.dataStructure.MergeSort;

import java.util.Arrays;

public class MergeSortBottomUp {
	public static void main(String[] args) {
		int[] a = new int[] { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
		System.out.println(Arrays.toString(a));
		MergeSortBottomUp.sort(a);
		System.out.println(Arrays.toString(a));
	}

	private static void sort(int[] a) {
		int[] aux = new int[a.length];
		int N = a.length;
		for (int sz = 1; sz < N; sz = sz + sz) {
			System.out.println(sz);
			for (int lo = 0; lo < N - sz; lo = lo + sz + sz) {
				mergeSort(a, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
			}
		}
	}

	private static void mergeSort(int[] a, int[] aux, int lo, int mid, int hi) {

		for (int i = lo; i <= hi; i++) {
			aux[i] = a[i];
		}

		int k = lo;
		int i = lo;
		int j = mid + 1;
		while (k <= hi) {
			if (j > hi) // If j exceeded
				a[k++] = aux[i++];
			else if (i > mid) // If i exceeded
				a[k++] = aux[j++];
			// For stability, If to values are equal take from left side to
			// avoid long distance exchange which may cause instability.
			else if (aux[i] <= aux[j]) // If i idx is less than j idx
				a[k++] = aux[i++];
			else if (aux[j] < aux[i]) // If j idx is less than i idx
				a[k++] = aux[j++];
		}

	}
}
