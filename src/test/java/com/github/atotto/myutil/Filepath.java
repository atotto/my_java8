package com.github.atotto.myutil;

import java.io.File;

public class Filepath {

	public static String toSlash(String path) {
		if (File.pathSeparator == "/") {
			return path;
		}
		return path.replaceAll(File.pathSeparator, "/");
	}
}
