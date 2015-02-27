package com.github.atotto.java8horstmann.ch05.ex01;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Test;

public class ProgrammersDayTest {

	/**
	 * Programmer’s Day is the 256th day of the year.
	 */
	public static LocalDate programmersDay(int year) {
		return LocalDate.ofYearDay(year, 256);
	}

	public static LocalDate programmersDay_old(int year) {
		return LocalDate.of(year, 1, 1).plusDays(255);
	}

	@Test
	public void test() {
		for (int year = 2000; year < 3000; year++) {
			LocalDate expect = programmersDay_old(year);
			LocalDate actual = programmersDay(year);

			assertThat(actual, is(expect));
		}
	}
}
