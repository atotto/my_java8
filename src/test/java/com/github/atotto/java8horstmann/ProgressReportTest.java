package com.github.atotto.java8horstmann;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.IntStream;

import org.junit.Test;

public class ProgressReportTest {
	static Map<String, C> chapter = new TreeMap<>();
	static {
		chapter.put("ch01", new C(1, 12));
		chapter.put("ch02", new C(1, 13));
		chapter.put("ch03", new C(1, 24));
		chapter.put("ch04", new C(1, 10));
		chapter.put("ch05", new C(1, 12));
		// chapter.put("ch06", new C(1, 11));
		// chapter.put("ch07", new C(1, 10));
		// chapter.put("ch08", new C(1, 16));
		// chapter.put("ch09", new C(1, 12));
		// TODO: add next chapter exercises.
	}

	Path root = Paths.get(getClass().getResource(".").getPath());

	public List<Path> exercises() {
		ArrayList<Path> exercises = new ArrayList<Path>();

		chapter.forEach((ch, c) -> {
			IntStream.rangeClosed(c.start, c.end)
					.mapToObj(n -> String.format("%s/ex%02d", ch, n))
					.forEach(p -> exercises.add(root.resolve(p)));
		});

		return exercises;
	}

	@Test
	public void progressReport() throws IOException {
		List<Result> report = new ArrayList<>();

		for (Path exercise : exercises()) {
			boolean passed;
			try {
				passed = Files.walk(exercise, 2).filter(file -> {
					return file.getFileName().toString().endsWith(".class");
				}).limit(1).count() == 1;
			} catch (NoSuchFileException e) {
				passed = false;
			}

			report.add(new Result(root.relativize(exercise), passed));
		}

		System.out.println("| exercise  | ok? |");
		System.out.println("|-----------|-----|");
		report.forEach((result) -> {
			System.out.printf("| %s | %s |\n", result.path.toString(),
					result.passed ? "yes" : "no ");
		});
		long sum = report.stream().count();
		long passed = report.stream().filter(result -> {
			return result.passed;
		}).count();
		System.out.println();
		System.out.printf("passed = %3.2f%%\n", (double) passed / sum * 100);
	}

	static class C {
		C(int start, int end) {
			this.start = start;
			this.end = end;
		}

		int start;
		int end;
	}

	class Result {
		Result(Path path, boolean passed) {
			this.path = path;
			this.passed = passed;
		}

		Path path;
		boolean passed;
	}
}
