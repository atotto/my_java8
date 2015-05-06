package com.github.atotto.java8horstmann.ch09.ex11;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class FindCardNumber {

	@Test
	public void example_findCardNumber() throws IOException, InterruptedException {
		findCardNumber();
		// Output:
		// ./src/test/java/com/github/atotto/java8horstmann/ch09/ex11/cardnumber.txt:number1: 1234-5678-1234-5678
	}

	public static void findCardNumber() throws InterruptedException, IOException {
		ProcessBuilder builder = new ProcessBuilder("grep",
				"\\d\\{4\\}-\\d\\{4\\}-\\d\\{4\\}-\\d\\{4\\}", "-r", ".");
		builder.inheritIO();
		builder.start().waitFor(1, TimeUnit.MINUTES);
	}
}
