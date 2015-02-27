package com.github.atotto.java8horstmann.ch05.ex11;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.Test;

public class FlightTimeTest {

	@Test
	public void testFlightTime() {
		// prepare
		ZonedDateTime departure = ZonedDateTime.of(2014, 12, 1, 14, 5, 0, 0,
				ZoneId.of("Europe/Berlin"));
		ZonedDateTime arrival = ZonedDateTime.of(2014, 12, 1, 16, 40, 0, 0,
				ZoneId.of("America/Los_Angeles"));

		// test
		Duration take = Duration.between(departure, arrival);

		// verify
		Duration expect = Duration.ZERO.plusHours(11).plusMinutes(35);

		assertThat(take, is(expect));
	}
}
