package fr.xelians.xdh.studio.sender;

import fr.xelians.xdh.studio.ChannelMetricsRepository;
import fr.xelians.xdh.studio.ChannelStore;
import fr.xelians.xdh.studio.ResourceHandler;
import fr.xelians.xdh.studio.WorkerContext;
import fr.xelians.xdh.studio.logging.XDHProcessLogger;
import fr.xelians.xdh.studio.storage.InputBin;
import fr.xelians.xdh.studio.storage.LegacyBridge;
import fr.xelians.xdh.studio.storage.OutputBin;

import java.nio.file.Path;

/**
 * Contract to be implemented by every channel sender.
 *
 * <p>
 * A sender is responsible for transmitting a produced file to an external
 * destination (API, FTP, webhook, email, etc.).
 * </p>
 *
 * <p>Senders must:</p>
 * <ul>
 *     <li>Process one file per execution</li>
 *     <li>Avoid long-running blocking operations</li>
 *     <li>Return a {@link SenderResult} describing the outcome</li>
 * </ul>
 *
 * <p>
 * Sender parameters are defined through the sender constructor.
 * </p>
 *
 * <p>New senders implement {@link #send(InputBin, OutputBin, WorkerContext)}. The
 * directory-based methods are deprecated and still work unchanged.</p>
 */
public interface Sender extends ResourceHandler {

	/**
	 * Executes the send operation.
	 *
	 * <p>The files published in the result bin (acknowledgments, response files) are kept with
	 * the collect and can be collected by a chained channel. The default implementation
	 * delegates to the directory-based method.</p>
	 *
	 * @param toSend  bin holding the single file to send
	 * @param results bin in which result files may be published
	 * @param context services and information of the execution
	 *
	 * @return a {@link SenderResult} describing the successful send operation
	 *
	 * @throws SendException           for functional send errors
	 * @throws DelayExecutionException if the execution must be postponed
	 * @throws Exception               for unexpected technical errors
	 * @since 4.3
	 */
	default SenderResult send(InputBin toSend, OutputBin results, WorkerContext context) throws Exception {
		LegacyBridge bridge = context.legacyBridge();
		String fileName = toSend.single().name();
		Path sourceDirectory = bridge.expose(toSend);
		Path resultDirectory = bridge.stage(results);
		SenderResult result = send(fileName, sourceDirectory, resultDirectory, context.logger(), context.store(), context.metrics());
		bridge.publishAll(results, resultDirectory);
		return result;
	}

	/**
	 * Executes the send operation.
	 *
	 * @param fileName        name of the file to send (filename only, no path)
	 * @param sourceDirectory directory where the file is located
	 * @param resultDirectory directory where result artifacts (acknowledgment, response files, etc.) may be written
	 * @param logger          process logger (log files are stored under the channel log directory)
	 *
	 * @return a {@link SenderResult} describing the successful send operation
	 *
	 * @throws SendException           for functional send errors
	 * @throws DelayExecutionException if the execution must be postponed
	 * @throws Exception               for unexpected technical errors
	 * @deprecated implement {@link #send(InputBin, OutputBin, WorkerContext)} instead
	 */
	@Deprecated(since = "4.3", forRemoval = true)
	SenderResult send(String fileName,
					  Path sourceDirectory,
					  Path resultDirectory,
					  XDHProcessLogger logger) throws Exception;

	/**
	 * Executes the send operation with access to the channel key-value store.
	 *
	 * @deprecated implement {@link #send(InputBin, OutputBin, WorkerContext)} instead
	 */
	@Deprecated(since = "4.3", forRemoval = true)
	default SenderResult send(String fileName,
							  Path sourceDirectory,
							  Path resultDirectory,
							  XDHProcessLogger logger,
							  ChannelStore channelStore) throws Exception {
		return send(fileName, sourceDirectory, resultDirectory, logger);
	}

	/**
	 * Executes the send operation with access to the channel store and metrics repository.
	 *
	 * @deprecated implement {@link #send(InputBin, OutputBin, WorkerContext)} instead
	 */
	@Deprecated(since = "4.3", forRemoval = true)
	default SenderResult send(String fileName,
							  Path sourceDirectory,
							  Path resultDirectory,
							  XDHProcessLogger logger,
							  ChannelStore channelStore,
							  ChannelMetricsRepository metricsRepository) throws Exception {
		return send(fileName, sourceDirectory, resultDirectory, logger, channelStore);
	}
}
