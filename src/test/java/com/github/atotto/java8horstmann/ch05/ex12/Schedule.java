package com.github.atotto.java8horstmann.ch05.ex12;

import java.time.ZonedDateTime;

public class Schedule {
	public Schedule(ZonedDateTime zonedDateTime, String title) {
		this.zonedDateTime = zonedDateTime;
		this.title = title;
	}

	public ZonedDateTime zonedDateTime;
	public String title;
}
