package com.github.atotto.java8horstmann.ch03.ex07;

import java.util.Comparator;

public class StringComparatorBuilder {

	private boolean reversed = false;
	private boolean caseSensitive = false;
	private boolean spaceSensitive = false;

	public Comparator<String> comparator() {
		return (first, second) -> {
			if (!spaceSensitive) {
				first = first.replaceAll("\\s+", "");
				second = second.replaceAll("\\s+", "");
			}
			if (!caseSensitive) {
				first = first.toLowerCase();
				second = second.toLowerCase();
			}
			int res = first.compareTo(second);
			if (reversed) {
				res = -res;
			}
			return res;
		};
	}

	public StringComparatorBuilder reversed() {
		reversed = true;
		return this;
	}

	public StringComparatorBuilder caseSensitive() {
		caseSensitive = true;
		return this;
	}

	public StringComparatorBuilder spaceSensitive() {
		spaceSensitive = true;
		return this;
	}
}
