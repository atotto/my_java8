package com.github.atotto.java8horstmann.ch06.ex02;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class IdGeneratorTest {

	@Test
	public void testIdGenerator() throws InterruptedException {
		final int threads = 1000;
		final int iterations = 100_000;

		boolean[] checklist = new boolean[threads * iterations];

		IdGenerator idGen = new IdGenerator();

		ExecutorService pool = Executors.newCachedThreadPool();
		for (int th = 0; th < threads; th++) {
			pool.submit(() -> {
				for (int i = 0; i < iterations; i++) {
					int id = (int) idGen.get();
					assertThat(checklist[id], is(false));
					checklist[id] = true;
				}
			});
		}
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.HOURS);

		assertThat(idGen.current(), is((long) threads * iterations));

		idGen.reset();
		assertThat(idGen.current(), is(0L));
	}
}
