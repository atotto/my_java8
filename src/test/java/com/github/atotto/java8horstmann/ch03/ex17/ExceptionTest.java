package com.github.atotto.java8horstmann.ch03.ex17;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import org.junit.Test;

public class ExceptionTest {

	public static void doInParallelAsync(Runnable first, Runnable second,
			Consumer<Throwable> handler) {
		worker(first, handler);
		worker(second, handler);
	}

	private static void worker(Runnable r, Consumer<Throwable> handler) {
		Thread t = new Thread() {
			public void run() {
				try {
					r.run();
				} catch (Throwable t) {
					handler.accept(t);
				}
			}
		};
		t.start();
	}

	@Test
	public void testDoInParallelAsync_no_exception()
			throws InterruptedException {
		AtomicReference<String> first_message = new AtomicReference<>();
		AtomicReference<String> second_message = new AtomicReference<>();
		AtomicReference<String> exception_message = new AtomicReference<>();
		CountDownLatch latch = new CountDownLatch(2);

		doInParallelAsync(() -> {
			first_message.set("first");
			latch.countDown();
		}, () -> {
			second_message.set("second");
			latch.countDown();
		}, (e) -> {
			exception_message.set(e.getMessage());
		});

		latch.await();
		assertThat(first_message.get(), is("first"));
		assertThat(second_message.get(), is("second"));
		assertThat(exception_message.get(), is(nullValue()));
	}

	@Test
	public void testDoInParallelAsync_get_exception_from_first()
			throws InterruptedException {
		AtomicReference<String> second_message = new AtomicReference<>();
		AtomicReference<String> exception_message = new AtomicReference<>();
		CountDownLatch latch = new CountDownLatch(2);

		doInParallelAsync(() -> {
			throw new IllegalStateException("first");
		}, () -> {
			second_message.set("second");
			latch.countDown();
		}, (e) -> {
			exception_message.set(e.getMessage());
			latch.countDown();
		});

		latch.await();
		assertThat(second_message.get(), is("second"));
		assertThat(exception_message.get(), is("first"));
	}

	@Test
	public void testDoInParallelAsync_get_exception_from_second()
			throws InterruptedException {
		AtomicReference<String> first_message = new AtomicReference<>();
		AtomicReference<String> exception_message = new AtomicReference<>();
		CountDownLatch latch = new CountDownLatch(2);

		doInParallelAsync(() -> {
			first_message.set("first");
			latch.countDown();
		}, () -> {
			throw new IllegalStateException("second");
		}, (e) -> {
			exception_message.set(e.getMessage());
			latch.countDown();
		});

		latch.await();
		assertThat(first_message.get(), is("first"));
		assertThat(exception_message.get(), is("second"));
	}

	@Test
	public void testDoInParallelAsync_get_exception_from_both()
			throws InterruptedException {
		AtomicReference<String> exception_message = new AtomicReference<>();
		CountDownLatch latch = new CountDownLatch(2);

		doInParallelAsync(() -> {
			throw new IllegalStateException("first");
		}, () -> {
			throw new IllegalStateException("second");
		}, (e) -> {
			latch.countDown();
			exception_message.set(e.getMessage());
		});

		latch.await();
		assertThat(exception_message.get(),
				anyOf(equalTo("first"), equalTo("second")));
	}
}
