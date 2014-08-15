package com.github.atotto.myutil;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ListsTest {

	@Test
	public void testPartition() {
		String[] s = { "a", "b", "c", "d", "e" };
		List<String> list = Arrays.asList(s);

		{
			List<List<String>> lists = Lists.partition(list, 2);
			String[][] expect = { { "a", "b" }, { "c", "d" }, { "e" } };

			for (int i = 0; i < expect.length; i++) {
				assertThat(lists.get(i), is(contains(expect[i])));
			}
		}
		{
			List<List<String>> lists = Lists.partition(list, 3);
			String[][] expect = { { "a", "b", "c" }, { "d", "e" } };

			for (int i = 0; i < expect.length; i++) {
				assertThat(lists.get(i), is(contains(expect[i])));
			}
		}
	}
}
