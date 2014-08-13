package com.github.atotto.java8horstmann.ch01.ex02;

import java.io.File;
import java.io.FileFilter;

import org.junit.Test;

public class FilterTest {

	public static File[] globSubdirWithFilter(File dir) {
		return dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File filename) {
				return filename.isDirectory();
			}
		});
	}

	public static File[] globSubdirWithLambda(File dir) {
		return dir.listFiles(f -> f.isDirectory());
	}

	public static File[] globSubdirWithMethodReference(File dir) {
		return dir.listFiles(File::isDirectory);
	}

	@Test
	public void testFileFilter() {
		// File[] dirs = globSubdirWithFilter(new File("."));
		File[] dirs = globSubdirWithLambda(new File("."));
		for (File file : dirs) {
			System.out.println(file);
		}
	}
}
