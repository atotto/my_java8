package com.github.atotto.java8horstmann.ch09.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.github.atotto.myutil.Resource;

public class TryWithResources {

	public static void tryWithResources() {
		try (Scanner in = new Scanner(Resource.path("/alice.txt"));
				PrintWriter out = new PrintWriter("out.txt")) {
			while (in.hasNext()) {
				out.println(in.next().toLowerCase());
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void withoutTryWithResources() {
		Scanner in = null;
		PrintWriter out = null;
		try {
			in = new Scanner(Resource.path("/alice.txt"));
			out = new PrintWriter("out.txt");
			while (in.hasNext()) {
				out.println(in.next().toLowerCase());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
