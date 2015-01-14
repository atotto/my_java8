package com.github.atotto.java8horstmann.ch06.ex10;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class WebScraping {
	private static final String hrefPattern = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";

	private static Scanner in = new Scanner(System.in);

	private static String getInput(String prompt) {
		System.out.print(prompt + ": ");
		return in.nextLine();
	}

	public static void main(String[] args) throws ExecutionException,
			InterruptedException {
		CompletableFuture<String> f = Util.repeat(() -> getInput("URL"),
				s -> s.startsWith("http://")).thenApplyAsync(
				(url) -> Util.crawl(url));
		CompletableFuture<List<String>> links = f.thenApply(c -> Util
				.matches(c, hrefPattern));
		links.thenAccept(System.out::println);
		ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
	}
}
