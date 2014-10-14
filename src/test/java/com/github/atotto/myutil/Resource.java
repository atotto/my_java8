package com.github.atotto.myutil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Resource {

	public static byte[] readFile(String filename) {
		Path file = Paths.get(Resource.class.getResource(filename).getFile());
		try {
			return Files.readAllBytes(file);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
