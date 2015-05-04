package com.github.atotto.java8horstmann.ch09;

import java.util.Optional;

import org.junit.Test;

public class ExampleSuppressedExceptions {

	@Test
	public void exampleSuppressedExceptions() throws Exception {
		try {
			mySuppressedExceptions();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			for (Throwable secondaryExceptions : ex.getSuppressed()) {
				System.out.println(secondaryExceptions.getMessage());
			}
		}
		// Output:
		// primary
		// secondary 1
		// secondary 2
	}

	private void mySuppressedExceptions() throws Exception {
		Optional<Exception> primaryException = Optional.empty();
		try {
			throw new Exception("primary");
		} catch (Exception ex) {
			primaryException = Optional.ofNullable(ex);
			throw ex;
		} finally {
			try {
				throw new Exception("secondary 1");
			} catch (Exception ex) {
				primaryException.ifPresent(e -> e.addSuppressed(ex));
			}
			try {
				throw new Exception("secondary 2");
			} catch (Exception ex) {
				primaryException.ifPresent(e -> e.addSuppressed(ex));
			}
		}
	}
}
