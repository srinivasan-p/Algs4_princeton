package com.algorithms.dataStructure.ElementarySort;

import java.util.Arrays;

import com.algorithms.dataStructure.util.Util;

public class ShellSort {
	public static void sort(Comparable[] a) {
		int N = a.length;
		int h = 1;
		while (h < N / 3) {
			h = h * 3 + 1;
		}

		while (h >= 1) {

			for (int i = h; i < a.length; i++) {
				for (int j = i; j >= h; j -= h) {
					if (Util.less(a[j], a[j - h])) {
						Util.swap(a, j, j - h);
					}
				}
			}

			h = h / 3;
		}
	}

	public static void main(String[] args) {
		Comparable[] a = { 9, 8, 4, 6, 2, 5, 1, 0, 3, 7, 99, 88, 66, 54, 23, 12, 75, 316, 242 };
		System.out.println(Arrays.toString(a));
		ShellSort.sort(a);
		System.out.println(Arrays.toString(a));

	}
}
