package test.clean;

import fr.xelians.xdh.studio.StepResult;
import fr.xelians.xdh.studio.WorkerContext;
import fr.xelians.xdh.studio.logging.XDHProcessLogger;
import fr.xelians.xdh.studio.storage.InputBin;
import fr.xelians.xdh.studio.storage.OutputBin;
import fr.xelians.xdh.studio.storage.StoredFile;
import fr.xelians.xdh.studio.transformer.TransformResult;
import fr.xelians.xdh.studio.transformer.Transformer;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/** A worker that follows the rules: bins, tempDir(), XDHProcessLogger, the caller's thread. */
public class UpperCaseTransformer implements Transformer {

	@Override
	public StepResult transform(InputBin collected, OutputBin transformed, WorkerContext context) throws Exception {
		Path work = Files.createTempFile(context.tempDir(), "work", ".txt");
		for (StoredFile file : collected.files()) {
			try (InputStream in = file.open()) {
				Files.writeString(work, new String(in.readAllBytes(), StandardCharsets.UTF_8).toUpperCase());
			}
			try (OutputStream out = transformed.create(transformed.uniqueName(file.name()))) {
				out.write(Files.readAllBytes(work));
			}
			context.logger().info("transformed " + file.name());
		}
		Thread.sleep(1);
		return new StepResult("done");
	}

	@Override
	@SuppressWarnings("removal")
	public TransformResult transform(List<String> fileNames, Path sourceDirectory, Path targetDirectory, XDHProcessLogger logger) {
		throw new UnsupportedOperationException("bin-based worker");
	}
}
