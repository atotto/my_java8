package com.github.atotto.java8horstmann.ch05.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

public class HowManyDaysTest {

	public static long howManyDays(LocalDate birthday) {
		return birthday.until(LocalDate.now(), ChronoUnit.DAYS);
	}

	@Test
	public void test_Gopher_Birthday() {
		LocalDate gopher_birthday = LocalDate.of(2009, 11, 10);
		long days = howManyDays(gopher_birthday);

		System.out.printf("%d days since gopher are born%n", days);
	}
}
