package com.github.atotto.java8horstmann.ch09.ex05;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

public class TacTest {

	public static void tac(String path, String storepath) throws IOException {
		byte[] data = Files.readAllBytes(Paths.get(path));
		byte[] ndata = reverse(data);
		writeFile(storepath, ndata);
	}

	private static byte[] reverse(byte[] data) {
		int size = data.length;
		byte[] ndata = new byte[size];
		for (int i = 0; i < size; i++) {
			ndata[i] = data[size - i - 1];
		}
		return ndata;
	}

	private static void writeFile(String filename, byte[] data)
			throws IOException {
		Files.write(Paths.get(filename), data, StandardOpenOption.CREATE);
	}

	@Test
	public void testReverse() throws Exception {
		byte[] data = reverse("hello\nbye!".getBytes("UTF-8"));

		assertThat(data, is("!eyb\nolleh".getBytes("UTF-8")));
	}
}
