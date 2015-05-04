package com.github.atotto.java8horstmann.ch09.ex07;

import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.junit.Test;

public class Wget {
	public static void wget(String url, CopyOption... options)
			throws IOException {
		wget(new URL(url), options);
	}

	public static void wget(URL url, CopyOption... options) throws IOException {
		Files.copy(url.openStream(), Paths.get(toFileName(url)), options);
	}

	private static String toFileName(URL url) {
		return Paths.get(url.getFile()).getFileName().toString();
	}

	@Test
	public void testWget() throws Exception {
		wget("http://golang.org/doc/gopher/doc.png",
				StandardCopyOption.REPLACE_EXISTING);
	}
}
