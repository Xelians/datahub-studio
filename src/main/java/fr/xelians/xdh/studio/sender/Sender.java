package fr.xelians.xdh.studio.sender;

import fr.xelians.xdh.studio.ChannelMetricsRepository;
import fr.xelians.xdh.studio.ChannelStore;
import fr.xelians.xdh.studio.ResourceHandler;
import fr.xelians.xdh.studio.logging.XDHProcessLogger;

import java.nio.file.Path;

/**
 * Contract to be implemented by every channel sender.
 *
 * <p>
 * A sender is responsible for transmitting a produced file to an external
 * destination (API, FTP, webhook, email, etc.).
 * </p>
 *
 * <p>
 * Senders must:
 * <ul>
 *     <li>Process one file per execution</li>
 *     <li>Avoid long-running blocking operations</li>
 *     <li>Return a {@link SenderResult} describing the outcome</li>
 * </ul>
 * </p>
 *
 * <p>
 * Sender parameters are defined through the sender constructor.
 * </p>
 */
public interface Sender extends ResourceHandler {

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
	 */
	SenderResult send(String fileName,
					  Path sourceDirectory,
					  Path resultDirectory,
					  XDHProcessLogger logger) throws Exception;

	/**
	 * Executes the send operation with access to the channel key-value store.
	 */
	default SenderResult send(String fileName,
							  Path sourceDirectory,
							  Path resultDirectory,
							  XDHProcessLogger logger,
							  ChannelStore channelStore) throws Exception {
		return send(fileName, sourceDirectory, resultDirectory, logger);
	}

	/**
	 * Executes the send operation with access to the channel store and metrics repository.
	 */
	default SenderResult send(String fileName,
							  Path sourceDirectory,
							  Path resultDirectory,
							  XDHProcessLogger logger,
							  ChannelStore channelStore,
							  ChannelMetricsRepository metricsRepository) throws Exception {
		return send(fileName, sourceDirectory, resultDirectory, logger, channelStore);
	}
}