package com.github.atotto.java8horstmann.ch05.ex12;

import java.time.ZonedDateTime;

class Schedule {
	protected ZonedDateTime zonedDateTime;
	protected ZonedDateTime notifyTime;
	protected String title;

	public Schedule(ZonedDateTime zonedDateTime, String title) {
		this.zonedDateTime = zonedDateTime;
		this.title = title;
		this.setNotifyMinutes(60);
	}

	@Override
	public String toString() {
		return String.format("%s %s", zonedDateTime, title);
	}

	public void setNotifyMinutes(long minutes) {
		if (minutes < 0) {
			throw new IllegalArgumentException("minutes must set positive.");
		}
		this.notifyTime = zonedDateTime.minusMinutes(minutes);
	}
}
