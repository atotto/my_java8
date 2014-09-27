package com.github.atotto.java8horstmann.ch01.ex04;

import static com.github.atotto.myutil.Filepath.toSlash;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.util.Arrays;

import org.junit.Test;

public class SortDirContentsTest {

	public static File[] sortDirContents(File[] files) {
		Arrays.sort(files,
				(f1, f2) -> {
					if ((f1.isDirectory() && f2.isDirectory())
							|| (f1.isFile() && f2.isFile())) {
						return f1.getPath().compareTo(f2.getPath());
					} else if (f1.isDirectory() && f2.isFile()) {
						return -1;
					} else {
						return 1;
					}
				});
		return files;
	}

	@Test
	public void testListDirContents() {
		String[] expect = { "src/test/resources/fixture/dir/test1/a",
				"src/test/resources/fixture/dir/test1/b",
				"src/test/resources/fixture/dir/test1/a.txt",
				"src/test/resources/fixture/dir/test1/b.txt",
				"src/test/resources/fixture/dir/test1/c.md", };

		File target = new File("src/test/resources/fixture/dir/test1");

		File[] files = sortDirContents(target.listFiles());

		String[] actual = Arrays.stream(files).map(f -> toSlash(f.toString()))
				.toArray(String[]::new);
		assertThat(actual, is(expect));
	}
}
