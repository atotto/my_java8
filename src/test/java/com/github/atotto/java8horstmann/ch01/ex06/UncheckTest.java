package com.github.atotto.java8horstmann.ch01.ex06;

import org.junit.Test;

public class UncheckTest {

	@Test
	public void testUncheck() throws InterruptedException {
		// before
		new Thread(() -> {
			try {
				Thread.sleep(10000);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}).start();

		// after
		new Thread(uncheck(() -> {
			Thread.sleep(10000);
		})).start();
	}

	interface RunnableEx {
		public void run() throws Exception;
	}

	public static Runnable uncheck(RunnableEx runner) {
		return () -> {
			try {
				runner.run();
			} catch (Exception ex) {
				// uncheck exception
				throw new IllegalStateException(ex);
			}
		};
	}
}
