package com.github.atotto.java8horstmann.ch06.ex10;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	public static String crawl(String urlString) {
		try {
			Scanner in = new Scanner(new URL(urlString).openStream());
			StringBuilder builder = new StringBuilder();
			while (in.hasNextLine()) {
				builder.append(in.nextLine());
				builder.append("%n");
			}
			in.close();
			return builder.toString();
		} catch (IOException ex) {
			RuntimeException rex = new RuntimeException();
			rex.initCause(ex);
			throw rex;
		}
	}

	public static List<String> matches(String input, String patternString) {
		Pattern pattern = Pattern.compile(patternString,
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(input);
		List<String> result = new ArrayList<>();

		while (matcher.find())
			result.add(matcher.group(1));
		return result;
	}

	public static <T> CompletableFuture<T> repeat(Supplier<T> action,
			Predicate<T> until) {
		return CompletableFuture.supplyAsync(action).thenComposeAsync(
				(T t) -> {
					return until.test(t) ? CompletableFuture.completedFuture(t)
							: repeat(action, until);
				});
	}
}
