package com.github.atotto.java8horstmann.ch05.ex07;

import java.time.LocalTime;

public class TimeInterval {
	public LocalTime start;
	public LocalTime end;

	public static boolean isOverlap(TimeInterval first, TimeInterval second) {
		if (first.start.isBefore(second.start)) {
			return second.start.isBefore(first.end);
		} else {
			return first.start.isBefore(second.end);
		}
	}

	public TimeInterval(LocalTime start, LocalTime end) {
		if (start == null || end == null) {
			throw new IllegalArgumentException("start or end is null");
		}
		this.start = start;
		this.end = end;
	}

	public TimeInterval(CharSequence start, CharSequence end) {
		this(LocalTime.parse(start), LocalTime.parse(end));
	}

	public static TimeInterval of(LocalTime start, LocalTime end) {
		return new TimeInterval(start, end);
	}

	public static TimeInterval of(CharSequence start, CharSequence end) {
		return new TimeInterval(start, end);
	}

	@Override
	public String toString() {
		return String.format("%s - %s", start, end);
	}
}
