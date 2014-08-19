package com.github.atotto.java8horstmann.ch01;

import java.util.Comparator;

import org.junit.Test;

public class LambdaExample {

	@Test
	public void example_LambdaExpression() {
		@SuppressWarnings("unused")
		Comparator<String> comp;

		comp = (String first, String second) -> Integer.compare(first.length(),
				second.length());

		// Stringを省略できる
		comp = (first, second) -> Integer.compare(first.length(),
				second.length());

		// 複数行書くなら{}で括ってreturn文を書く
		comp = (first, second) -> {
			if (first.length() < second.length())
				return -1;
			else if (first.length() > second.length())
				return 1;
			else
				return 0;
		};
	}
}
