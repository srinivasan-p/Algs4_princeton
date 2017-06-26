package com.algorithms.dataStructure.MergeSort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		int[] a = new int[] { 2, 4, 6, 8, 10, 1, 3, 5, 6, 7 };
		System.out.println(Arrays.toString(a));
		MergeSort.sort(a);
		System.out.println(Arrays.toString(a));
	}

	private static void sort(int[] a) {
		int[] aux = new int[a.length];
		sort(a, aux, 0, a.length - 1);
	}

	private static void sort(int[] a, int[] aux, int lo, int hi) {
		if (hi <= lo)
			return;
		int mid = lo + ((hi - lo) / 2);
		sort(a, aux, 0, mid);
		sort(a, aux, mid + 1, hi);
		mergeSort(a, aux, lo, mid, hi);

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
			else if (aux[i] <= aux[j]) // If i idx is less than j idx
				a[k++] = aux[i++];
			else if (aux[j] < aux[i]) // If j idx is less than i idx
				a[k++] = aux[j++];
		}

	}
}
