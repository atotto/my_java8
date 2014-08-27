package com.github.atotto.java8horstmann.ch03.ex01;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.LogRecord;

import org.junit.Test;

public class LazyLoggingTest {

	@Test
	public void testExerciseLog() throws Exception {
		Logger logger = Logger.getLogger(LazyLoggingTest.class.getName());

		/*
		 * Error: Local variable i defined in an enclosing scope must be final
		 * or effectively final
		 */
		// int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		// for (int i = 0; i < a.length; i++) {
		// logger.logIf(Level.FINEST, () -> i == 10, () -> "a[10] = " + a[10]);
		// }

		int[] a = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (int i = 0; i < a.length; i++) {
			int j = i;
			logger.logIf(Level.FINEST, () -> j == 10, () -> "a[10] = " + a[10]);
		}

		assertThat(logger.loglist.size(), is(1));
		assertThat(logger.loglist.get(0).getMessage(), is("a[10] = 10"));
	}

	@Test
	public void testMyLog() {
		Logger logger = Logger.getLogger(LazyLoggingTest.class.getName());

		logger.logIf(Level.FINEST, () -> true, () -> "record");
		logger.logIf(Level.FINEST, () -> false, () -> "does not record");

		assertThat(logger.loglist.size(), is(1));
		assertThat(logger.loglist.get(0).getMessage(), is("record"));
	}

	static class Logger {
		ArrayList<LogRecord> loglist = new ArrayList<>();
		Level level = Level.ALL;
		String name = "";

		static Logger getLogger(String name) {
			return new Logger(name);
		}

		void logIf(Level level, Supplier<Boolean> condition,
				Supplier<String> message) {
			if (this.isLoggable(level) && condition.get()) {
				this.log(level, message);
			}
		}

		void log(Level level, Supplier<String> message) {
			LogRecord lr = new LogRecord(level, message.get());
			lr.setLoggerName(name);
			loglist.add(lr);
		}

		Logger(String name) {
			this.name = name;
		}

		boolean isLoggable(Level level) {
			return level.intValue() > this.level.intValue();
		}

		void setLevel(Level level) {
			this.level = level;
		}
	}
}
