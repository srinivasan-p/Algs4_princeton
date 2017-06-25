package com.algorithms.dataStructure.ElementarySort;

import java.util.Arrays;

import com.algorithms.dataStructure.util.Util;

public class InsertionSort {

	public static void main(String[] args) {
		Comparable[] a = {"z","d"};
		System.out.println(Arrays.toString(a));
		InsertionSort.sort(a);
		System.out.println(Arrays.toString(a));
	}

	private static void sort(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j > 0; j--) {
				if (Util.less(a[j], a[j - 1])) {
					Util.swap(a, j, j - 1);
				} else
					break;
			}
		}
	}
}
