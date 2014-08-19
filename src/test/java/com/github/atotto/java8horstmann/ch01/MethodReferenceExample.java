package com.github.atotto.java8horstmann.ch01;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class MethodReferenceExample {

	@Test
	public void exampleMethodReference() {
		List<String> list = Arrays.asList("Hello", "world!");
		list.forEach(System.out::println);

		My my = new My("> ");
		list.forEach(my::print);
	}

	class My {
		String prefix = "";

		My(String prefix) {
			this.prefix = prefix;
		}

		void print(String message) {
			System.out.printf("%s%s\n", prefix, message);
		}
	}
}
