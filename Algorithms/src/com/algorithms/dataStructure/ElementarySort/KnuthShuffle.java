package com.algorithms.dataStructure.ElementarySort;

import java.util.Arrays;
import java.util.Random;

import com.algorithms.dataStructure.util.Util;

public class KnuthShuffle {
	public static void main(String[] args) {
		Comparable[] a = { 9, 8, 4, 6, 2, 5, 1, 0, 3, 7, 99, 88, 66, 54, 23, 12, 75, 316, 242 };
		System.out.println(Arrays.toString(a));
		KnuthShuffle.shuffle(a);
		System.out.println(Arrays.toString(a));
	}

	private static void shuffle(Comparable[] a) {
		for (int i = 0; i < a.length; i++) {
			int rndm = new Random().nextInt(i + 1);
			Util.swap(a, i, rndm);
		}
	}
}
