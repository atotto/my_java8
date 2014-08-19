package com.github.atotto.java8horstmann.ch01;

import org.junit.Test;

public class FunctionalInterfacesExample {

	@Test
	public void exampleFunctionalInterfaces() {
		My my = (x) -> System.out.println(x);
		my.print("Hello world!");
	}

	@FunctionalInterface
	interface My {
		void print(String message);
	}
}
