package com.programs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnagramInString {
	public static void main(String[] args) {
		System.out.println(findAnagrams("cbaebabacd", "abc"));
	}

	public static List<Integer> findAnagrams(String source, String target) {
		List<Integer> result = new ArrayList<>();
		Map<Character, Integer> charMapTarget = new HashMap<>();

		for (char c : target.toCharArray()) {
			charMapTarget.put(c, charMapTarget.getOrDefault(c, 0) + 1);
		}
		int start = 0, end = 0, MaxLen = 0, count = charMapTarget.size();
		while (end < source.length()) {
			char tempC = source.charAt(end);
			if (charMapTarget.containsKey(tempC)) {
				charMapTarget.put(tempC, charMapTarget.get(tempC) - 1);
				if (charMapTarget.get(tempC) == 0) {
					count--;
				}
			}
			end++;
			while (count == 0) {
				char c = source.charAt(start);

				if (charMapTarget.containsKey(c)) {
					charMapTarget.put(c, charMapTarget.get(c) + 1);
					if (charMapTarget.get(c) == 1) {
						count++;
					}
				}
				if (end - start == target.length()) {
					result.add(start);
				}
				start++;
			}

		}
		return result;

	}
}
