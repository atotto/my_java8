package com.github.atotto.java8horstmann.ch03.ex09;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.Test;

public class LexicographicComparatorTest {

	static Comparator<Object> lexicographicComparator(String... fieldNames) {
		return (obj1, obj2) -> {
			int res = 0;
			try {
				for (String fieldName : fieldNames) {
					Field field = obj1.getClass().getDeclaredField(fieldName);
					field.setAccessible(true);
					String field1 = field.get(obj1).toString();
					String field2 = field.get(obj2).toString();
					res = field1.compareTo(field2);
					if (res != 0) {
						break;
					}
				}
			} catch (NoSuchFieldException | IllegalAccessException e) {
				e.printStackTrace();
			}
			return res;
		};
	}

	static class Person {
		private String lastname;
		private String firstname;

		Person(String lastname, String firstname) {
			this.lastname = lastname;
			this.firstname = firstname;
		}

		@Override
		public String toString() {
			return lastname + " " + firstname;
		}

		static Person[] sort(Person... ps) {
			Arrays.sort(ps, lexicographicComparator("lastname", "firstname"));
			return ps;
		}
	}

	@Test
	public void test() {
		Person aa = new Person("a", "a");
		Person ab = new Person("a", "b");
		Person ba = new Person("b", "a");

		Person[] sorted = Person.sort(ab, ba, aa);

		assertThat(sorted, is(new Person[] { aa, ab, ba }));
	}
}