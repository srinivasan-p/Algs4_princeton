package com.algorithms.dataStructure.SymbolTable;

import edu.princeton.cs.algs4.StdIn;

public class FrequencyCounter {

	public static void main(String[] args) {
		int distinct = 0, words = 0;
		int minlen = 10;
		BinarySearchST<String, Integer> st = new BinarySearchST<String, Integer>();

		while (!StdIn.isEmpty()) {
			String key = StdIn.readString();
			if (key.equalsIgnoreCase("My-EXIT"))
				break;
			if (key.length() < minlen)
				continue;
			words++;
			if (st.contains(key)) {
				st.put(key, st.get(key) + 1);
			} else {
				st.put(key, 1);
				distinct++;
			}
		}
		String max = "";
		st.put(max, 0);
		for (String word : st.keys()) {
			if (st.get(word) > st.get(max)) {
				max = word;
			}
		}
		System.out.println(max + " " + st.get(max));
	}
}
