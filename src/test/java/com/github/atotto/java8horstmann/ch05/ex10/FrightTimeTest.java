package com.github.atotto.java8horstmann.ch05.ex10;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class FrightTimeTest {

	@Test
	public void testFrightTime() {
		// prepare
		ZonedDateTime departure = ZonedDateTime.of(2014, 12, 1, 15, 5, 0, 0,
				ZoneId.of("America/Los_Angeles"));
		Duration take = Duration.ZERO.plusHours(10).plusMinutes(30);
		ZoneId zone = ZoneId.of("Europe/Berlin");

		// test
		ZonedDateTime arrival = departure.plus(take).toInstant().atZone(zone);

		// verify
		ZonedDateTime expect = ZonedDateTime
				.of(2014, 12, 2, 10, 35, 0, 0, zone);

		assertThat(arrival, is(expect));
	}
}
