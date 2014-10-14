package com.github.atotto.java8horstmann.ch03;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;

public class CompletableFutureExample {

	CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
		// ...long running...
			return "42";
		});

	@Test
	public void testCompletableFuture() throws Exception {
		System.out.println(future.get());
	}
}
