package com.github.atotto.java8horstmann.ch06;

import java.util.concurrent.CompletableFuture;

import org.junit.Test;

public class CompletableFutureExample {

	@Test
	public void testCompletableFuture() throws Exception {
		@SuppressWarnings("unused")
		CompletableFuture<String> link = CompletableFuture.supplyAsync(() -> {
			System.out.println("running");
			return "a,b,c";
		}).thenApply((u) -> u);
		// System.out.println(link.get());
	}
}
