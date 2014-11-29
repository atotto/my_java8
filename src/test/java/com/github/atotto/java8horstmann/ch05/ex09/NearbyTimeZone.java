package com.github.atotto.java8horstmann.ch05.ex09;

import java.time.Instant;
import java.time.ZoneId;

import org.junit.Test;

public class NearbyTimeZone {
	public static void nearby(int offset) {
		Instant now = Instant.now();
		ZoneId.getAvailableZoneIds()
				.stream()
				.filter(zone -> {
					int zoneoffset = ZoneId.of(zone).getRules().getOffset(now)
							.getTotalSeconds();
					return Math.abs(zoneoffset) < offset;
				}).sorted().forEach(System.out::println);
	}

	public static void main(String[] args) {
		nearby(3600);
	}

	@Test
	public void test() throws Exception {
		nearby(3600);
	}
}
