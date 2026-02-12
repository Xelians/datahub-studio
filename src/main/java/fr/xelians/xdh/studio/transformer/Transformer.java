package fr.xelians.xdh.studio.transformer;

import fr.xelians.xdh.studio.ChannelMetricsRepository;
import fr.xelians.xdh.studio.ChannelStore;
import fr.xelians.xdh.studio.ResourceHandler;
import fr.xelians.xdh.studio.logging.XDHProcessLogger;

import java.nio.file.Path;
import java.util.List;

/**
 * Contract to be implemented by every channel transformer.
 *
 * <p>
 * A transformer takes 1..n input files (provided by name and located in the given source directory)
 * and produces a new file in the target directory.
 * </p>
 *
 * <p>
 * Transformer parameters are defined through the transformer constructor.
 * </p>
 */
public interface Transformer extends ResourceHandler {

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
	 */
	TransformResult transform(List<String> fileNames,
							  Path sourceDirectory,
							  Path targetDirectory,
							  XDHProcessLogger logger) throws Exception;

	/**
	 * Executes the transform operation with access to the channel key-value store.
	 */
	default TransformResult transform(List<String> fileNames,
									  Path sourceDirectory,
									  Path targetDirectory,
									  XDHProcessLogger logger,
									  ChannelStore channelStore) throws Exception {
		return transform(fileNames, sourceDirectory, targetDirectory, logger);
	}

	/**
	 * Executes the transform operation with access to the channel store and metrics repository.
	 */
	default TransformResult transform(List<String> fileNames,
									  Path sourceDirectory,
									  Path targetDirectory,
									  XDHProcessLogger logger,
									  ChannelStore channelStore,
									  ChannelMetricsRepository metricsRepository) throws Exception {
		return transform(fileNames, sourceDirectory, targetDirectory, logger, channelStore);
	}
}
