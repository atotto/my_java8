package com.github.atotto.java8horstmann.ch08.ex09;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ScannerWrapper {

	public static Stream<String> scannerToLines(Scanner scanner) {
		Iterator<String> iter = new Iterator<String>() {
			String nextLine = null;

			@Override
			public boolean hasNext() {
				if (nextLine != null) {
					return true;
				} else {
					nextLine = scanner.nextLine();
					return (nextLine != null);
				}
			}

			@Override
			public String next() {
				if (nextLine != null || hasNext()) {
					String line = nextLine;
					nextLine = null;
					return line;
				} else {
					throw new NoSuchElementException();
				}
			}
		};
		return StreamSupport.stream(
				Spliterators.spliteratorUnknownSize(iter, Spliterator.ORDERED
						| Spliterator.NONNULL), false);
	}
}
