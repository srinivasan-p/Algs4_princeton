package com.algorithms.dataStructure.ElementarySort;

import java.util.Arrays;

import com.algorithms.dataStructure.util.Util;

public class SelectionSort {
	public static void main(String[] args) {
		Comparable[] a = { 9, 8, 4, 6, 2, 5, 1, 0, 3, 7 };
		System.out.println(Arrays.toString(a));
		SelectionSort.sort(a);
		System.out.println(Arrays.toString(a));
	}

	private static void sort(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			int minIdx = i;
			for (int j = i; j < a.length; j++) {
				if (Util.less(a[j], a[minIdx])) {
					minIdx = j;
				}
			}
			Util.swap(a, i, minIdx);
		}
	}

}
