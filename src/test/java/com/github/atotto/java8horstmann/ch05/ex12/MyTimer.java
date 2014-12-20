package com.github.atotto.java8horstmann.ch05.ex12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class MyTimer {
	List<ScheduledFuture<?>> futures = new ArrayList<>();
	private final ScheduledExecutorService executor;

	public MyTimer() {
		executor = Executors.newScheduledThreadPool(2);
	}

	public void waitFor() throws InterruptedException, ExecutionException {
		for (ScheduledFuture<?> future : futures) {
			future.get();
		}
	}

	public void shutdown() {
		executor.shutdown();
	}

	protected void addSchedule(ZonedDateTime tm, String title) {
		Duration duration = Duration.between(
				Instant.now().atZone(ZoneId.systemDefault()), tm);
		if (duration.isNegative()) {
			System.err.printf("schedule %s `%s` is expired. Let's skip.%n", tm,
					title);
			return;
		}

		ScheduledFuture<?> future = executor.schedule(() -> {
			System.out.printf("%s : %s%n", tm, title);
		}, duration.toMillis(), TimeUnit.MILLISECONDS);
		futures.add(future);
	}

	public static class Builder {
		List<Schedule> list;

		public Builder() {
			list = new ArrayList<>();
		}

		public Builder add(ZonedDateTime zonedDateTime, String title) {
			list.add(new Schedule(zonedDateTime, title));
			return this;
		}

		public Builder parse(InputStream is, String charsetName)
				throws IOException {
			InputStreamReader isr = new InputStreamReader(is, charsetName);
			BufferedReader br = new BufferedReader(isr);
			String line;
			int n = 0;
			while ((line = br.readLine()) != null) {
				n++;
				String[] res = line.split(" ", 2);
				if (res.length != 2) {
					System.err.printf("parse error line %d: %s%n", n, line);
					continue;
				}
				try {
					ZonedDateTime tm = ZonedDateTime.parse(res[0]);
					list.add(new Schedule(tm, res[1]));
				} catch (DateTimeParseException e) {
					System.err.printf("parse error line %d: %s%n", n, line);
					continue;
				}
			}
			return this;
		}

		public MyTimer build() {
			MyTimer timer = new MyTimer();
			for (Schedule sc : list) {
				timer.addSchedule(sc.zonedDateTime, sc.title);
			}
			return timer;
		}
	}

	public static void main(String[] args) {
		try {
			if (System.in.available() != 0) {
				MyTimer timer = new MyTimer.Builder().parse(System.in, "UTF-8")
						.build();
				timer.waitFor();
				timer.shutdown();
			} else {
				System.out.println("input not available");
				System.out
						.println("ex: echo 2014-12-20T17:38:45+09:00 next meeting | java com.github.atotto.java8horstmann.ch05.ex12.MyTimer");
				System.exit(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
