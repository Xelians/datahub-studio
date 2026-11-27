package test.bad;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

/** Every rule of the signatures file broken once; Thread.sleep stays allowed. */
public class Bad {
	void all() throws Exception {
		System.exit(1);
		new Thread(() -> { }).start();
		Executors.newFixedThreadPool(2);
		new ForkJoinPool();
		new Timer();
		CompletableFuture.supplyAsync(() -> 1);
		List.of(1).parallelStream();
		List.of(1).stream().parallel();
		System.out.println("x");
		java.io.File.createTempFile("a", "b");
		Paths.get("x");
		Path.of("y");
		new java.io.File("/etc");
		System.getenv("HOME");
		System.getProperty("user.home");
		org.slf4j.LoggerFactory.getLogger(Bad.class).info("x");
		Thread.sleep(1);
	}
}
