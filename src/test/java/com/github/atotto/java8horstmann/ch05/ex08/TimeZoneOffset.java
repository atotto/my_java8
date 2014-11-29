package com.github.atotto.java8horstmann.ch05.ex08;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;

import org.junit.Test;

public class TimeZoneOffset {
	public static void all() {
		Instant now = Instant.now();
		ZoneId.getAvailableZoneIds().stream().sorted().map(zone -> {
			ZoneOffset offset = ZoneId.of(zone).getRules().getOffset(now);
			return String.format("%6s : %s", offset, zone);
		}).forEach(System.out::println);
	}

	public static void main(String[] args) {
		all();
	}

	@Test
	public void test() {
		all();
	}
}
