package com.github.atotto.java8horstmann.ch06.ex03;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

import org.junit.Test;

import com.github.atotto.myutil.Benchmark;

public class CounterTest {

	final int N = 1;
	final int threads = 1000;
	final int iterations = 100_000;

	@Test
	public void benchAtomicLong() {
		Benchmark b = () -> {
			AtomicLong atomic = new AtomicLong();
			ExecutorService pool = Executors.newCachedThreadPool();
			for (int th = 0; th < threads; th++) {
				pool.submit(() -> {
					for (int i = 0; i < iterations; i++) {
						atomic.incrementAndGet();
					}
				});
			}
			pool.shutdown();
			try {
				pool.awaitTermination(1, TimeUnit.HOURS);
			} catch (Exception e) {
				fail();
			}
			assertThat(atomic.get(), is((long) threads * iterations));
		};
		b.wakeUp(3);

		long time = b.run(N);
		System.out.printf("BenchAtomicLong  \t%10d ns/op \n", time / N);
	}

	@Test
	public void benchLongAdder() {
		Benchmark b = () -> {
			LongAdder adder = new LongAdder();
			ExecutorService pool = Executors.newCachedThreadPool();
			for (int th = 0; th < threads; th++) {
				pool.submit(() -> {
					for (int i = 0; i < iterations; i++) {
						adder.increment();
					}
				});
			}
			pool.shutdown();
			try {
				pool.awaitTermination(1, TimeUnit.HOURS);
			} catch (Exception e) {
				fail();
			}
			assertThat(adder.longValue(), is((long) threads * iterations));
		};
		b.wakeUp(3);

		long time = b.run(N);
		System.out.printf("BenchLongAdder   \t%10d ns/op \n", time / N);
	}

	@Test
	public void benchSynchronized() {
		class Counter {
			private long count;

			synchronized void increment() {
				count++;
			}

			synchronized long get() {
				return count;
			}
		}

		Benchmark b = () -> {
			Counter counter = new Counter();
			ExecutorService pool = Executors.newCachedThreadPool();
			for (int th = 0; th < threads; th++) {
				pool.submit(() -> {
					for (int i = 0; i < iterations; i++) {
						counter.increment();
					}
				});
			}
			pool.shutdown();
			try {
				pool.awaitTermination(1, TimeUnit.HOURS);
			} catch (Exception e) {
				fail();
			}
			assertThat(counter.get(), is((long) threads * iterations));
		};
		b.wakeUp(3);

		long time = b.run(N);
		System.out.printf("BenchSynchronized\t%10d ns/op \n", time / N);
	}
}
