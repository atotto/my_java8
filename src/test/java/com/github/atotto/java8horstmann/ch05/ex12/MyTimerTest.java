package com.github.atotto.java8horstmann.ch05.ex12;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.ExecutionException;

import org.junit.Test;

import com.github.atotto.myutil.Resource;

public class MyTimerTest {

	@Test
	public void testParse() throws IOException, InterruptedException,
			ExecutionException {
		InputStream is = Files.newInputStream(Resource
				.path("/fixture/schedule.txt"));
		MyTimer timer = new MyTimer.Builder().parse(is, "UTF-8").build();
		timer.waitFor();
		timer.shutdown();
	}

	@Test
	public void testScheduling() throws IOException, InterruptedException,
			ExecutionException {
		ZonedDateTime local = Instant.now().atZone(ZoneId.systemDefault())
				.plusMinutes(60);
		ZonedDateTime gmt = Instant.now().atZone(ZoneId.of("GMT"))
				.plusMinutes(60);

		MyTimer timer = new MyTimer.Builder().add(local.plusSeconds(1), "+1")
				.add(gmt.plusSeconds(2), "+2").add(local.plusSeconds(3), "+3")
				.add(gmt.plusSeconds(4), "+4").add(local.plusSeconds(5), "+5")
				.build();
		timer.waitFor();
		timer.shutdown();
	}
}
