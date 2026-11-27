package fr.xelians.xdh.studio.collector;

import fr.xelians.xdh.studio.ChannelMetricsRepository;
import fr.xelians.xdh.studio.ChannelStore;
import fr.xelians.xdh.studio.ResourceHandler;
import fr.xelians.xdh.studio.StepResult;
import fr.xelians.xdh.studio.WorkerContext;
import fr.xelians.xdh.studio.logging.XDHProcessLogger;
import fr.xelians.xdh.studio.storage.LegacyBridge;
import fr.xelians.xdh.studio.storage.OutputBin;

import java.nio.file.Path;
import java.util.List;

/**
 * Contract to be implemented by every channel collector.
 *
 * <p>A collector retrieves 1..n files from an external source and publishes them in the
 * output bin it is given.</p>
 *
 * <p>Collectors must:</p>
 * <ul>
 *   <li>Process a small and bounded number of files per execution</li>
 *   <li>Avoid long-running blocking operations</li>
 * </ul>
 *
 * <p>Collector parameters are defined through the collector constructor.</p>
 *
 * <p>New collectors implement {@link #collect(OutputBin, WorkerContext)}. The directory-based
 * methods are deprecated and still work unchanged.</p>
 */
public interface Collector extends ResourceHandler {

	/**
	 * Executes the collect operation.
	 *
	 * <p>The platform starts a new collect when the bin holds at least one published file after
	 * the call. The default implementation delegates to the directory-based method.</p>
	 *
	 * @param collected bin in which the collected files are published
	 * @param context   services and information of the execution
	 *
	 * @return the outcome of the collect, with an optional message
	 *
	 * @throws CollectException for functional errors
	 * @throws Exception        for unexpected technical errors
	 * @since 4.3
	 */
	default StepResult collect(OutputBin collected, WorkerContext context) throws Exception {
		LegacyBridge bridge = context.legacyBridge();
		Path targetDirectory = bridge.stage(collected);
		List<String> fileNames = collect(targetDirectory, context.webhook(), context.logger(), context.store(), context.metrics());
		bridge.publish(collected, targetDirectory, fileNames == null ? List.of() : fileNames);
		return new StepResult(null);
	}

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
	 * @deprecated implement {@link #collect(OutputBin, WorkerContext)} instead
	 */
	@Deprecated(since = "4.3", forRemoval = true)
	List<String> collect(Path targetDirectory,
						 String webhook,
						 XDHProcessLogger logger) throws Exception;

	/**
	 * Executes the collect operation with access to the channel key-value store.
	 *
	 * @deprecated implement {@link #collect(OutputBin, WorkerContext)} instead
	 */
	@Deprecated(since = "4.3", forRemoval = true)
	default List<String> collect(Path targetDirectory,
								 String webhook,
								 XDHProcessLogger logger,
								 ChannelStore channelStore) throws Exception {
		return collect(targetDirectory, webhook, logger);
	}

	/**
	 * Executes the collect operation with access to the channel store
	 * and metrics repository.
	 *
	 * @deprecated implement {@link #collect(OutputBin, WorkerContext)} instead
	 */
	@Deprecated(since = "4.3", forRemoval = true)
	default List<String> collect(Path targetDirectory,
								 String webhook,
								 XDHProcessLogger logger,
								 ChannelStore channelStore,
								 ChannelMetricsRepository metricsRepository) throws Exception {
		return collect(targetDirectory, webhook, logger, channelStore);
	}
}
