package com.github.atotto.java8horstmann.ch03.ex20;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Test;

public class ListTest {

	static <T, U> List<U> map(List<T> list, Function<T, U> function) {
		return list.stream().map(function).collect(Collectors.toList());
	}

	@Test
	public void test() {
		class Person {
			String name;

			Person(String name) {
				this.name = name;
			}

			String getName() {
				return name;
			}
		}

		List<Person> persons = Arrays.asList(new Person[] {
				new Person("Shaun"), new Person("Bitzer"),
				new Person("Shirley"), new Person("Timmy") });
		List<String> expected = Arrays.asList(new String[] { "Shaun", "Bitzer",
				"Shirley", "Timmy" });

		List<String> actual = map(persons, Person::getName);

		assertThat(actual, is(expected));
	}
}
