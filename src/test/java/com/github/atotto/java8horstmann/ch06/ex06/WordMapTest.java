package com.github.atotto.java8horstmann.ch06.ex06;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.nio.file.Path;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

import com.github.atotto.myutil.Resource;

public class WordMapTest {

	@Test
	public void testWordMap() {
		Path[] paths = Resource.paths("/war-and-peace.txt", "/alice.txt");
		ConcurrentHashMap<String, Set<File>> map = WordMap.read(paths);

		Set<File> english = map.get("English");
		assertThat(english.contains(paths[0].toFile()), is(true));
		assertThat(english.contains(paths[1].toFile()), is(true));
		assertThat(english.size(), is(2));

		assertThat(map.get("Alice").size(), is(1));
	}
}
