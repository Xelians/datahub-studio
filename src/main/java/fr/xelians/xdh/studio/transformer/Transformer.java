package fr.xelians.xdh.studio.transformer;

import fr.xelians.xdh.studio.ChannelMetricsRepository;
import fr.xelians.xdh.studio.ChannelStore;
import fr.xelians.xdh.studio.ResourceHandler;
import fr.xelians.xdh.studio.StepResult;
import fr.xelians.xdh.studio.WorkerContext;
import fr.xelians.xdh.studio.logging.XDHProcessLogger;
import fr.xelians.xdh.studio.storage.InputBin;
import fr.xelians.xdh.studio.storage.LegacyBridge;
import fr.xelians.xdh.studio.storage.OutputBin;
import fr.xelians.xdh.studio.storage.StoredFile;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Contract to be implemented by every channel transformer.
 *
 * <p>
 * A transformer takes the 1..n files of its input bin and publishes what it produces in its
 * output bin.
 * </p>
 *
 * <p>
 * Transformer parameters are defined through the transformer constructor.
 * </p>
 *
 * <p>New transformers implement {@link #transform(InputBin, OutputBin, WorkerContext)}. The
 * directory-based methods are deprecated and still work unchanged.</p>
 */
public interface Transformer extends ResourceHandler {

	/**
	 * Executes the transform operation.
	 *
	 * <p>Each file published in the output bin is sent separately. If nothing is published, the
	 * collect ends without any send. The default implementation delegates to the directory-based
	 * method.</p>
	 *
	 * @param collected   bin holding the collected files to transform
	 * @param transformed bin in which the produced files are published
	 * @param context     services and information of the execution
	 *
	 * @return the outcome of the transformation, with an optional message and warning flag
	 *
	 * @throws TransformException for functional errors
	 * @throws Exception          for unexpected technical errors
	 * @since 4.3
	 */
	default StepResult transform(InputBin collected, OutputBin transformed, WorkerContext context) throws Exception {
		LegacyBridge bridge = context.legacyBridge();
		List<String> fileNames = new ArrayList<>();
		for (StoredFile file : collected.files()) {
			fileNames.add(file.name());
		}
		Path sourceDirectory = bridge.expose(collected);
		Path targetDirectory = bridge.stage(transformed);
		TransformResult result = transform(fileNames, sourceDirectory, targetDirectory, context.logger(), context.store(), context.metrics());
		String produced = result == null ? null : result.getFileName();
		bridge.publish(transformed, targetDirectory, produced == null || produced.isBlank() ? List.of() : List.of(produced));
		return result;
	}

	/**
	 * Executes the transform operation.
	 *
	 * @param fileNames       list of input file names to transform (filenames only, no paths)
	 * @param sourceDirectory directory containing input files
	 * @param targetDirectory directory where the produced file must be created
	 * @param logger          process logger (log files are stored under the channel log directory)
	 *
	 * @return a {@link TransformResult} containing the produced file name and related information
	 *
	 * @throws TransformException for functional errors
	 * @throws Exception          for unexpected technical errors
	 * @deprecated implement {@link #transform(InputBin, OutputBin, WorkerContext)} instead
	 */
	@Deprecated(since = "4.3", forRemoval = true)
	TransformResult transform(List<String> fileNames,
							  Path sourceDirectory,
							  Path targetDirectory,
							  XDHProcessLogger logger) throws Exception;

	/**
	 * Executes the transform operation with access to the channel key-value store.
	 *
	 * @deprecated implement {@link #transform(InputBin, OutputBin, WorkerContext)} instead
	 */
	@Deprecated(since = "4.3", forRemoval = true)
	default TransformResult transform(List<String> fileNames,
									  Path sourceDirectory,
									  Path targetDirectory,
									  XDHProcessLogger logger,
									  ChannelStore channelStore) throws Exception {
		return transform(fileNames, sourceDirectory, targetDirectory, logger);
	}

	/**
	 * Executes the transform operation with access to the channel store and metrics repository.
	 *
	 * @deprecated implement {@link #transform(InputBin, OutputBin, WorkerContext)} instead
	 */
	@Deprecated(since = "4.3", forRemoval = true)
	default TransformResult transform(List<String> fileNames,
									  Path sourceDirectory,
									  Path targetDirectory,
									  XDHProcessLogger logger,
									  ChannelStore channelStore,
									  ChannelMetricsRepository metricsRepository) throws Exception {
		return transform(fileNames, sourceDirectory, targetDirectory, logger, channelStore);
	}
}
