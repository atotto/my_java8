package com.github.atotto.java8horstmann.ch03.ex21;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import org.junit.Test;

import com.github.atotto.myutil.Resource;

public class FutureTest {

	static <T, U> Future<U> map(Future<T> future, Function<T, U> function) {
		return new Future<U>() {
			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				return future.cancel(mayInterruptIfRunning);
			}

			@Override
			public boolean isCancelled() {
				return future.isCancelled();
			}

			@Override
			public boolean isDone() {
				return future.isDone();
			}

			@Override
			public U get() throws InterruptedException, ExecutionException {
				return function.apply(future.get());
			}

			@Override
			public U get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException,
					TimeoutException {
				return function.apply(future.get(timeout, unit));
			}
		};
	}

	@Test
	public void exapmle_map() throws IOException, InterruptedException,
			ExecutionException {

		CompletableFuture<byte[]> future = CompletableFuture
				.supplyAsync(() -> {
					return Resource.readFile("/alice.txt");
				});

		Future<String> mapped_future = map(future, s -> {
			return s.length + "bytes";
		});

		String actual = mapped_future.get();

		assertThat(actual, is("167517bytes"));
	}
}
