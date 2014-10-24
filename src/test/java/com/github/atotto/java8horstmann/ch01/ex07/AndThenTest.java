package com.github.atotto.java8horstmann.ch01.ex07;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AndThenTest {

	public static Runnable andThen(Runnable r1, Runnable r2) {
		return () -> {
			r1.run();
			r2.run();
		};
	}

	private static int count = 0;

	private synchronized static void doWork() {
		count++;
	}

	@Test
	public void test() throws InterruptedException {
		Thread th = new Thread(andThen(() -> doWork(), () -> doWork()));
		th.start();
		th.join();

		// TODO: run order testing
		assertThat(count, is(2));
	}
}
