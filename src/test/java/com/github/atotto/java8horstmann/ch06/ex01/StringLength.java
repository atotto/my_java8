package com.github.atotto.java8horstmann.ch06.ex01;

import java.util.concurrent.atomic.AtomicReference;

public class StringLength {
	AtomicReference<String> longestString = new AtomicReference<>("");

	public void updateString(String newstr) {
		longestString.updateAndGet((current) -> {
			if (newstr.length() > current.length()) {
				return newstr;
			}
			return current;
		});
	}

	public String getLongestString() {
		return longestString.get();
	}
}
