package com.github.atotto.java8horstmann.ch01.ex03;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FilenameFilter;

import org.junit.Test;

public class ListFIlesTest {

	public static String[] listFilesWithFilter(File dir, String ext) {
		return dir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(ext);
			}
		});
	}

	public static String[] listFilesWithLambda(File dir, String ext) {
		return dir.list((d, name) -> name.endsWith(ext)); // capture `ext`
	}

	@Test
	public void testListFles() {
		String[] expect = { "a.txt", "b.txt" };

		File target = new File(
				"src/test/resources/fixture/dir/test1");

		{
			String[] files = listFilesWithFilter(target, "txt");
			assertThat(files, is(expect));
		}
		{
			String[] files = listFilesWithLambda(target, "txt");
			assertThat(files, is(expect));
		}
	}
}
