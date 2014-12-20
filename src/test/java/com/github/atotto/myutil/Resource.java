package com.github.atotto.myutil;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.management.RuntimeErrorException;

public class Resource {

	public static byte[] readFile(String filename) {
		Path file = path(filename);
		try {
			return Files.readAllBytes(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Path path(String filename) {
		URL url = Resource.class.getResource(filename);
		if (url == null) {
			throw new RuntimeErrorException(null, "no such file:" + filename);
		}
		return Paths.get(url.getFile());
	}
}
