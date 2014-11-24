package com.github.atotto.java8horstmann.ch05.ex02;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;

import org.junit.Test;

public class LeapDayTest {

	@Test
	public void testLeapDay() {
		LocalDate base = LocalDate.of(2000, 2, 29);

		assertThat(base.plusYears(1), is(LocalDate.of(2001, 2, 28)));
		assertThat(base.plusYears(4), is(LocalDate.of(2004, 2, 29)));
		assertThat(base.plusYears(1).plusYears(1).plusYears(1).plusYears(1),
				is(LocalDate.of(2004, 2, 28)));
	}
}
