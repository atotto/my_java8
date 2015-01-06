package com.github.atotto.java8horstmann.ch06.ex06;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WordMap {

	public static ConcurrentHashMap<String, Set<File>> read(Path... paths) {
		Objects.requireNonNull(paths);
		ConcurrentHashMap<String, Set<File>> map = new ConcurrentHashMap<>();

		ExecutorService pool = Executors.newCachedThreadPool();
		for (Path path : paths) {
			pool.submit(() -> {
				Function<? super String, ? extends Set<File>> mapping = k -> {
					Set<File> value = new HashSet<File>();
					value.add(path.toFile());
					return value;
				};
				try {
					String contents = new String(Files.readAllBytes(path),
							StandardCharsets.UTF_8);
					List<String> words = Arrays.asList(contents
							.split("[\\P{L}]+"));

					for (String word : words) {
						// 利点：Setのオブジェクトを毎回生成する必要がない
						map.computeIfAbsent(word, mapping).add(path.toFile());
					}
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			});
		}
		pool.shutdown();
		try {
			pool.awaitTermination(1, TimeUnit.HOURS);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return map;
	}
}
