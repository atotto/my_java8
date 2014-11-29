package com.github.atotto.java8horstmann.ch05.ex04;

import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/*
 * http://opensource.apple.com/source/misc_cmds/misc_cmds-6/cal/cal.c
 */
public class Cal {

	private static final int MAXDAYS = 42; /* max slots in a month array */
	private static final int SPACE = 0;

	private static final int WEEK_LEN = 20; /* 7 * 3 - one space at the end */

	private static String day_headings = "Su Mo Tu We Th Fr Sa";
	private static String[] month_names = new String[] { "January", "February",
			"March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December", };

	/**
	 * 
	 * @param out
	 *            output print stream, not null
	 * @param date
	 *            calender date, not null
	 */
	public static void cal(PrintStream out, LocalDate date) {
		if (out == null) {
			throw new IllegalArgumentException("out is null");
		}
		if (date == null) {
			throw new IllegalArgumentException("date is null");
		}
		LocalDate startdate = date.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate enddate = date.with(TemporalAdjusters.lastDayOfMonth());
		int year = date.getYear();
		int month = date.getMonth().getValue();

		int dm = enddate.getDayOfMonth();
		int dw = startdate.getDayOfWeek().getValue() - 1; // Show the weekend at
															// the end of the
															// week.
		int day = 1;

		int[] days = new int[MAXDAYS];

		while (dm-- > 0) {
			days[dw++] = day++;
		}

		String head = String.format("%s %d", month_names[month - 1], year);
		String format = "%" + (WEEK_LEN - head.length()) / 2 + "s%s\n%s\n";
		out.printf(format, "", head, day_headings);
		for (int row = 0; row < 6; row++) {
			StringBuilder lineout = new StringBuilder(WEEK_LEN);
			for (int col = 0; col < 7; col++) {
				lineout.append(ascii_day(days[row * 7 + col]));
				if (col < 6) {
					lineout.append(" ");
				}
			}
			out.println(lineout.toString());
		}
	}

	private static String ascii_day(int d) {
		if (d == SPACE) {
			return "  ";
		}
		return String.format("%2d", d);
	}

	private static void usage() {
		System.err.println("usage: cal [month year]");
		System.exit(1);
	}

	// javac com/github/atotto/java8horstmann/ch05/ex04/Cal.java
	// java com.github.atotto.java8horstmann.ch05.ex04.Cal 3 2013
	public static void main(String[] args) {
		LocalDate date = null;
		switch (args.length) {
		case 2:
			try {
				int month = Integer.valueOf(args[0]);
				int year = Integer.valueOf(args[1]);
				date = LocalDate.of(year, month, 1);
			} catch (NumberFormatException | DateTimeException e) {
				usage();
			}
			break;
		case 0:
			date = LocalDate.now();
			break;
		default:
			usage();
		}

		Cal.cal(System.out, date);
		System.exit(0);
	}
}
