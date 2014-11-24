package com.github.atotto.java8horstmann.ch01;

import org.junit.Test;

public class ObjectInstanceMethod {

	@Test
	public void test() {
		Person me = new Person("me");

		who(me::getName);
	}

	public void who(you t) {
		System.out.println(t.get());
	}
}

@FunctionalInterface
interface you {
	public String get();
}

class Person {
	String name;

	public Person(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
