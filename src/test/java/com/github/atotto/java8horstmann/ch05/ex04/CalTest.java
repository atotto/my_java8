package com.github.atotto.java8horstmann.ch05.ex04;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.Test;

public class CalTest {

	@Test
	public void test_2013_3() {
		String expect = 
				  "     March 2013\n"
				+ "Su Mo Tu We Th Fr Sa\n"
				+ "             1  2  3\n"
				+ " 4  5  6  7  8  9 10\n"
				+ "11 12 13 14 15 16 17\n"
				+ "18 19 20 21 22 23 24\n"
				+ "25 26 27 28 29 30 31\n"
				+ "                    \n";

		// prepare
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(os);

		// action
		Cal.cal(ps, LocalDate.of(2013, 3, 1));

		// verify
		String actual = os.toString();
		assertThat(actual, is(expect));
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_illegal_args_out() throws Exception {
		Cal.cal(null, LocalDate.now());
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_illegal_args_date() throws Exception {
		Cal.cal(System.out, null);
	}
}
