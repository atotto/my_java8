package com.github.atotto.java8horstmann.ch03.ex16;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.junit.Test;

public class ExceptionTest {

	public static <T> void doInOrderAsync(Supplier<T> first,
			Consumer<T> second, Consumer<Throwable> handler) {
		Thread t = new Thread() {
			public void run() {
				try {
					T result = first.get();
					second.accept(result);
				} catch (Throwable t) {
					handler.accept(t);
				}
			}
		};
		t.start();
	}

	@Test
	public void testDoInOrderAsync_with_Consumer_no_exception()
			throws InterruptedException {
		AtomicReference<String> message = new AtomicReference<>();
		CountDownLatch latch = new CountDownLatch(1);

		doInOrderAsync(() -> {
			return "Hello";
		}, (r) -> {
			message.set(r + " world!");
			latch.countDown();
		}, (e) -> {
			message.set(e.getMessage());
		});

		latch.await();
		assertThat(message.get(), is("Hello world!"));
	}

	@Test
	public void testDoInOrderAsync_with_Consumer_get_exception_from_first()
			throws InterruptedException {
		AtomicReference<String> message = new AtomicReference<>();
		CountDownLatch latch = new CountDownLatch(1);

		doInOrderAsync(() -> {
			throw new IllegalStateException("Goodby");
		}, (r) -> {
			message.set(r + " world!");
		}, (e) -> {
			message.set(e.getMessage());
			latch.countDown();
		});

		latch.await();
		assertThat(message.get(), is("Goodby"));
	}

	@Test
	public void testDoInOrderAsync_with_Consumer_get_exception_from_second()
			throws InterruptedException {
		AtomicReference<String> message = new AtomicReference<>();
		CountDownLatch latch = new CountDownLatch(1);

		doInOrderAsync(() -> {
			return "Hello";
		}, (r) -> {
			throw new IllegalStateException("Goodby");
		}, (e) -> {
			message.set(e.getMessage());
			latch.countDown();
		});

		latch.await();
		assertThat(message.get(), is("Goodby"));
	}

	public static <T> void doInOrderAsync(Supplier<T> first,
			BiConsumer<T, Throwable> second) {
		Thread t = new Thread() {
			public void run() {
				try {
					T result = first.get();
					second.accept(result, null);
				} catch (Throwable t) {
					second.accept(null, t);
				}
			}
		};
		t.start();
	}

	@Test
	public void testDoInOrderAsync_with_BiConsumer_no_exception()
			throws InterruptedException {
		AtomicReference<String> message = new AtomicReference<>();
		CountDownLatch latch = new CountDownLatch(1);

		doInOrderAsync(() -> {
			return "Hello";
		}, (r, e) -> {
			if (r != null) {
				message.set(r + " world!");
			} else {
				message.set(e.getMessage());
			}
			latch.countDown();
		});

		latch.await();
		assertThat(message.get(), is("Hello world!"));
	}

	@Test
	public void testDoInOrderAsync_with_BiConsumer_get_exception_from_first()
			throws InterruptedException {
		AtomicReference<String> message = new AtomicReference<>();
		CountDownLatch latch = new CountDownLatch(1);

		doInOrderAsync(() -> {
			throw new IllegalStateException("Goodby");
		}, (r, e) -> {
			if (r != null) {
				message.set(r + " world!");
			} else {
				message.set(e.getMessage());
			}
			latch.countDown();
		});

		latch.await();
		assertThat(message.get(), is("Goodby"));
	}
}
