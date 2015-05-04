package com.github.atotto.java8horstmann.ch09.ex02;

import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;

import com.github.atotto.myutil.Resource;

public class AddSuppressedExceptions {

	// see com.github.atotto.java8horstmann.ch09.ExampleSuppressedExceptions
	public static void addSuppressedExceptions() {
		Scanner in = null;
		PrintWriter out = null;
		Optional<Exception> primaryException = Optional.empty();
		try {
			in = new Scanner(Resource.path("/alice.txt"));
			out = new PrintWriter("out.txt");
			while (in.hasNext()) {
				out.println(in.next().toLowerCase());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			primaryException = Optional.ofNullable(ex);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				primaryException.ifPresent(e -> e.addSuppressed(ex));
			}
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
				primaryException.ifPresent(e -> e.addSuppressed(ex));
			}
		}
	}
}