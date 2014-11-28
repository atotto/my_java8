package com.github.atotto.java8horstmann.ch05.ex03;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

import org.junit.Test;

public class TemporalAdjusterTest {

	public static TemporalAdjuster next(Predicate<LocalDate> predicate) {
		return TemporalAdjusters.ofDateAdjuster(date -> {
			int dayDiff = 1;
			while (!predicate.test(date.plusDays(dayDiff))) {
				dayDiff++;
			}
			return date.plusDays(dayDiff);
		});
	}

	private LocalDate nextWorkday(LocalDate date) {
		return date.with(next(d -> d.getDayOfWeek().getValue() < 6));
	}

	@Test
	public void testNextWorkday() {
		assertThat(nextWorkday(LocalDate.of(2014, 11, 29)),
				is(LocalDate.of(2014, 12, 1)));

		assertThat(nextWorkday(LocalDate.of(2014, 11, 30)),
				is(LocalDate.of(2014, 12, 1)));

		assertThat(nextWorkday(LocalDate.of(2014, 12, 1)),
				is(LocalDate.of(2014, 12, 2)));
	}
}
