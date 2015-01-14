package com.github.atotto.java8horstmann.ch06.ex11;

import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PasswdAuth {
	private static <T> CompletableFuture<T> repeat(Supplier<T> action,
			Predicate<T> until) {
		return CompletableFuture.supplyAsync(action).thenComposeAsync(
				(T t) -> {
					return until.test(t) ? CompletableFuture.completedFuture(t)
							: repeat(action, until);
				});
	}

	private static Scanner in = new Scanner(System.in);

	private static String getInput(String prompt) {
		System.out.print(prompt + ": ");
		return in.nextLine();
	}

	public static void main(String[] args) {
		repeat(() -> {
			String user = getInput("User");
			String passwd = getInput("Password");
			return new PasswordAuthentication(user, passwd.toCharArray());
		}, pa -> {
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (Arrays.equals(pa.getPassword(), "secret".toCharArray())) {
				System.out.println("success!");
				return true;
			}
			System.out.println("incorrect");
			return false;
		});
		ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.SECONDS);
	}
}
