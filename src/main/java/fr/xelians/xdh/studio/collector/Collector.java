package fr.xelians.xdh.studio.collector;

import fr.xelians.xdh.studio.ChannelMetricsRepository;
import fr.xelians.xdh.studio.ChannelStore;
import fr.xelians.xdh.studio.ResourceHandler;
import fr.xelians.xdh.studio.logging.XDHProcessLogger;

import java.nio.file.Path;
import java.util.List;

/**
 * Contract to be implemented by every channel collector.
 *
 * <p>
 * A collector is responsible for retrieving 1..n files from an external source
 * and placing them into the provided target directory.
 * </p>
 *
 * <p>
 * Collectors must:
 * <ul>
 *     <li>Process a small and bounded number of files per execution</li>
 *     <li>Remain non-blocking in terms of long-running operations</li>
 *     <li>Return only file names (not absolute paths)</li>
 * </ul>
 * </p>
 *
 * <p>
 * Collector parameters are defined through the collector constructor.
 * </p>
 */
public interface Collector extends ResourceHandler {

	/**
	 * Executes the collect operation.
	 *
	 * @param targetDirectory directory where collected files must be written
	 * @param webhook         optional webhook value configured for the channel (may be null)
	 * @param logger          process logger (log files are stored under the channel log directory)
	 *
	 * @return list of collected file names (filename only, no path)
	 *
	 * @throws CollectException for functional errors
	 * @throws Exception        for unexpected technical errors
	 */
	List<String> collect(Path targetDirectory,
						 String webhook,
						 XDHProcessLogger logger) throws Exception;

	/**
	 * Executes the collect operation with access to the channel key-value store.
	 */
	default List<String> collect(Path targetDirectory,
								 String webhook,
								 XDHProcessLogger logger,
								 ChannelStore channelStore) throws Exception {
		return collect(targetDirectory, webhook, logger);
	}

	/**
	 * Executes the collect operation with access to the channel store
	 * and metrics repository.
	 */
	default List<String> collect(Path targetDirectory,
								 String webhook,
								 XDHProcessLogger logger,
								 ChannelStore channelStore,
								 ChannelMetricsRepository metricsRepository) throws Exception {
		return collect(targetDirectory, webhook, logger, channelStore);
	}
}
