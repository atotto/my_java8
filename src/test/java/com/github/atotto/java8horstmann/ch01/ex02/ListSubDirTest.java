package com.github.atotto.java8horstmann.ch01.ex02;

import static com.github.atotto.myutil.Filepath.toSlash;
import static org.hamcrest.Matchers.arrayContainingInAnyOrder;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import org.junit.Test;

public class ListSubDirTest {

	public static File[] listSubDirsWithFilter(File dir) {
		return dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File filename) {
				return filename.isDirectory();
			}
		});
	}

	public static File[] listSubDirsWithLambda(File dir) {
		return dir.listFiles(f -> f.isDirectory());
	}

	public static File[] listSubDirsWithMethodReference(File dir) {
		return dir.listFiles(File::isDirectory);
	}

	@Test
	public void testListSubDirs() {
		String[] expect = new String[] {
				"src/test/resources/fixture/dir/test1",
				"src/test/resources/fixture/dir/test2" };

		File target = new File("src/test/resources/fixture/dir");

		{
			File[] dirs = listSubDirsWithFilter(target);
			String[] actual = Arrays.stream(dirs)
					.map(f -> toSlash(f.toString())).toArray(String[]::new);
			assertThat(actual, is(arrayContainingInAnyOrder(expect)));
		}
		{
			File[] dirs = listSubDirsWithLambda(target);
			String[] actual = Arrays.stream(dirs)
					.map(f -> toSlash(f.toString())).toArray(String[]::new);
			assertThat(actual, is(arrayContainingInAnyOrder(expect)));
		}
		{
			File[] dirs = listSubDirsWithMethodReference(target);
			String[] actual = Arrays.stream(dirs)
					.map(f -> toSlash(f.toString())).toArray(String[]::new);
			assertThat(actual, is(arrayContainingInAnyOrder(expect)));
		}
	}
}
